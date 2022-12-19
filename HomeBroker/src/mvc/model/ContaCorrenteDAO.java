/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Stheffany
 */
public class ContaCorrenteDAO {
    
    
    private PreparedStatement criaDeclaracao(Connection con, long id) throws SQLException {
        String sql = "select * from ccorrente where idccorrente = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setLong(1, id);
        return ps;
    }
        
    public ContaCorrente buscaPorId(long code) {
    try (Connection connection = new ConnectionFactory().getConnection();
        PreparedStatement ps = criaDeclaracao(connection, code);
        ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {                   
            ContaCorrente elemento = new ContaCorrente();
            elemento.setId(rs.getLong("idccorrente"));
            elemento.setSaldo(rs.getDouble("saldo_ccorrente"));
            Date currentDate = rs.getDate("data_criacao_ccorrente");
            LocalDate dataCriacao = currentDate.toLocalDate();
            elemento.setDataCriacao(dataCriacao);
            Date currentDateMod = rs.getDate("data_modificacao_ccorrente");
            LocalDate dataMod = currentDateMod.toLocalDate();
            elemento.setDataModificacao(dataMod);
            return elemento;
        }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    
    public boolean depositar(long id, double valor, ContaCorrente elemento) {
        String sql = "update ccorrente set saldo_ccorrente = ? where idccorrente = ?";
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, elemento.getSaldo() + valor);
            stmt.setLong(2, id);
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;//rever aqui as condições do retorno
    }
    
    public boolean sacar(long id, double valor, ContaCorrente elemento) {
        String sql = "update ccorrente set saldo_ccorrente = ? where idccorrente = ?";
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            if(elemento.getSaldo() < valor){
                return false;
            }else{
                stmt.setDouble(1, elemento.getSaldo() - valor);
                stmt.setLong(2, id);
                stmt.execute();
                stmt.close();
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String mostraSaldo(long id, ContaCorrenteDAO conta){
        ContaCorrente c = conta.buscaPorId(id);
        
        return "Id da conta: " + c.getId() + 
                "\nSaldo da conta: " + c.getSaldo();
    }   
    
    public boolean transfere(ContaCorrenteDAO elemento, double valor, long idOrigem, long idDestino) {
        ContaCorrente origem = buscaPorId(idOrigem);
        ContaCorrente destino = buscaPorId(idDestino);       
        if(elemento.sacar(origem.getId(), valor, origem)){
            if(elemento.depositar(idDestino, valor, destino)){
                return true;
            }else{
                origem.setSaldo(origem.getSaldo() + valor);
                return false;
            }            
        }else{
            return false;
        }
    }
    
//    public boolean pagarDividendos(ContaCorrenteDAO contas, double valor, int quantidade, long id){
//        double dividendo = valor * quantidade;
//        
//        return this.transfere(contas, dividendo, 1, id);
//    }  
    
    public void pagarDividendos(ContaCorrenteDAO cdao, String ticker, double valor, long id) throws SQLException{
        //importante saber a quantidade de ativos
        MeusAtivosDAO meusAtivosDAO = new MeusAtivosDAO();
        List<MeusAtivos> meus = meusAtivosDAO.buscaPorID(id);
        int qtdAtivos = 0;  
        double dividendos = 0;
        for(MeusAtivos a : meus){
            if(a.getAtivo().getTicker().equals(ticker)){
                qtdAtivos += a.getQtdAtivos();
                dividendos = qtdAtivos * valor;
            }
        }        
        System.out.println(qtdAtivos);
        cdao.transfere(cdao, dividendos, 36, id); 
    }
    
    public void pagarMensalidade(LocalDate data, MovimentaContaDAO movimenta) throws SQLException{
        if(data.getDayOfMonth() > 14){
            ContaCorrenteDAO cdao = new ContaCorrenteDAO();
            List<ContaCorrente> lista = cdao.getLista();
            for (ContaCorrente ccorrente : lista){
                if(ccorrente.getId() != 36){ //verifica se a conta que vai pagar a mensalidade é a do administrador
                    cdao.transfere(cdao, 20, ccorrente.getId(), 36);
                    movimenta.novaMovimentacao("Débito", "Pagamento Mensalidade", 20, LocalDate.now(), LocalDate.now(), ccorrente.getId());
                }
            } 
        }
    }
    
    public String gerarExtrato(long id, MovimentaContaDAO m) throws SQLException{
         MeusAtivosDAO meusAtivosDAO = new MeusAtivosDAO();
        List<MeusAtivos> meus = meusAtivosDAO.buscaPorID(id); 
        double valorAtivos = 0;
        for(MeusAtivos a : meus){
            valorAtivos = a.getValorPago();
        }
        ContaCorrente c = this.buscaPorId(id);
        StringBuilder builder = new StringBuilder("");      
        builder.append("\n");
        builder.append("Saldo Disponível: ").append(c.getSaldo() - valorAtivos); // menos o valor alocados em ativos
        return builder.toString();
    }
    
//    public String gerarExtrato(long id, MovimentaContaDAO m, double valor){
//        MovimentaConta[] movi = m.getMovimentos();
//        ContaCorrente c = this.buscaPorID(id);
//        StringBuilder builder = new StringBuilder("");
//        
//        for (int i = 0; i < movi.length; i++) {
//            if(movi[i] != null){
//                if(movi[i].getConta().getId() == id){                  
//                    builder.append(movi[i].toString());
//                }
//            }            
//        }
//        builder.append("\n");
//        builder.append("Saldo Disponível: ").append(c.getSaldo() - valor); // menos o valor alocados em ativos
//        return builder.toString();
//    }
    
//      public void mostrarTodos() {
//        boolean temConta = false;
//        StringBuilder builder = new StringBuilder("");
//        
//        for (int i=1; i<contas.length; i++) {
//            if (contas[i] != null) {
//                builder.append(contas[i].toString());
//                temConta = true;
//            }
//        }
//        
//        JOptionPane.showMessageDialog(null,builder.toString(),"Contas",JOptionPane.INFORMATION_MESSAGE);
//        
//        if (!temConta) {
//            System.out.println("Não existe conta cadastrada");
//        }
//    }
     
    
    private PreparedStatement createPreparedStatement(Connection con, long id) throws SQLException {
        String sql = "select * from ccorrente where ccorrente_cliente = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setLong(1, id);
        return ps;
    }
        
    public ContaCorrente buscaPorIdCliente(long code) {
    try (Connection connection = new ConnectionFactory().getConnection();
        PreparedStatement ps = createPreparedStatement(connection, code);
        ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {                   
            ContaCorrente elemento = new ContaCorrente();
            elemento.setId(rs.getLong("idccorrente"));
            elemento.setSaldo(rs.getDouble("saldo_ccorrente"));

            Date currentDate = rs.getDate("data_criacao_ccorrente");
            LocalDate dataCriacao = currentDate.toLocalDate();
            elemento.setDataCriacao(dataCriacao);

            Date currentDateMod = rs.getDate("data_modificacao_ccorrente");
            LocalDate dataMod = currentDateMod.toLocalDate();
            elemento.setDataModificacao(dataMod);

            return elemento;
        }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
        
    public long inserir(ContaCorrente cc, long id) throws SQLException{
        String sql = "insert into ccorrente(saldo_ccorrente, data_criacao_ccorrente, data_modificacao_ccorrente, "
                + "ccorrente_cliente) values (?,?,?,?)";
        try (Connection connection = new ConnectionFactory().getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        stmt.setDouble(1, cc.getSaldo());
        stmt.setDate(2, java.sql.Date.valueOf(cc.getDataCriacao()));
        stmt.setDate(3, java.sql.Date.valueOf(cc.getDataModificacao()));
        stmt.setLong(4, id); //id do cliente 
        stmt.execute();
        //retorna o id do objeto inserido
        ResultSet rs=stmt.getGeneratedKeys();
        int retorno=0;
        if(rs.next()){
            retorno = rs.getInt(1);
        }            
        System.out.println("O id inserido foi: " + retorno); 
        System.out.println("Gravado!");
        return retorno;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
        
    public void remover(int id) {
        String sql = "delete from ccorrente where idccorrente = ?"; 
        try (Connection connection = new ConnectionFactory().getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ContaCorrente> getLista() throws SQLException {
        String sql = "select * from ccorrente";
        try (Connection connection = new ConnectionFactory().getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)) {
            List<ContaCorrente> contas = new ArrayList<ContaCorrente>();
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // criando o objeto
                ContaCorrente elemento = new ContaCorrente();
                elemento.setId(rs.getLong("idccorrente"));
                elemento.setSaldo(rs.getDouble("saldo_ccorrente"));

                Date currentDate = rs.getDate("data_criacao_ccorrente");
                LocalDate dataCriacao = currentDate.toLocalDate();
                elemento.setDataCriacao(dataCriacao);

                Date currentDateMod = rs.getDate("data_modificacao_ccorrente");
                LocalDate dataMod = currentDateMod.toLocalDate();
                elemento.setDataModificacao(dataMod);                  

                contas.add(elemento);
            }
            rs.close();
            stmt.close();
            return contas;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }     
}
