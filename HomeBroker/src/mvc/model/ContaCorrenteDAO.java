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
    
    private ContaCorrente[] contas = new ContaCorrente[5];

    public ContaCorrente[] getContaCorrente() {
        return contas;
    }

//    public ContaCorrenteDAO(Cliente[] cliente) {
//        
//        ContaCorrente c1 = new ContaCorrente();
//        c1.setC(cliente[0]); // esse é o ADM
//        c1.setSaldo(500000); // ao criar uma nova conta a bolsa ganha 500 mil
//        c1.setDataCriacao(LocalDate.now());
//        c1.setDataModificacao(LocalDate.now());
//        this.adicionaConta(c1); // adicionando a conta ao vetor contas;
//        
//        ContaCorrente c2 = new ContaCorrente();
//        c2.setC(cliente[1]);
//        c2.setSaldo(20000); // ao criar uma conta nova o cliente recebe 20 mil;
//        c2.setDataCriacao(LocalDate.now());
//        c2.setDataModificacao(LocalDate.now());
//        this.adicionaConta(c2);
//        
//        ContaCorrente c3 = new ContaCorrente();
//        c3.setC(cliente[2]);
//        c3.setSaldo(20000); // ao criar uma conta nova o cliente recebe 20 mil;
//        c3.setDataCriacao(LocalDate.now());
//        c3.setDataModificacao(LocalDate.now());
//        this.adicionaConta(c3);
//        
//        ContaCorrente c4 = new ContaCorrente();
//        c4.setC(cliente[3]);
//        c4.setSaldo(20000); // ao criar uma conta nova o cliente recebe 20 mil;
//        c4.setDataCriacao(LocalDate.now());
//        c4.setDataModificacao(LocalDate.now());
//        this.adicionaConta(c4);     
//    }
    
    
//    //verifica uma posição livre para adicionar uma nova conta ao vetor de contas
//    public boolean adicionaConta(ContaCorrente c) {
//        int proximaPosicaoLivre = this.proximaPosicaoLivre();
//        if (proximaPosicaoLivre != -1) {
//            contas[proximaPosicaoLivre] = c;
//            return true;
//        } else {
//            return false;
//        }
//    }
    
    public boolean depositar(long id, double valor, ContaCorrenteDAO contas){
        ContaCorrente c; 
        c = contas.buscaPorID(id);
        if(c != null){
            c.setSaldo(c.getSaldo() + valor);
            return true;
        }else{
            return false;
        }
    }

    public boolean sacar(ContaCorrente conta, double valor){
        if(conta.getSaldo() < valor){
            return false;
        }else{
            conta.setSaldo(conta.getSaldo() - valor);
            return true;
        }
    }

    public String mostraSaldo(long id, ContaCorrenteDAO contas){
        ContaCorrente c = contas.buscaPorID(id);
        
        return "Id da conta: " + c.getId() + 
                "Saldo conta do(a) " + c.getC().getNome() + ": " + c.getSaldo();
    }
    
    public boolean transfere(ContaCorrenteDAO contas, double valor, long idOrigem, long idDestino){ 
        ContaCorrente origem = contas.buscaPorID(idOrigem);
        
        if(contas.sacar(origem, valor)){
            if(contas.depositar(idDestino, valor, contas)){
                return true;
            }else{
                origem.setSaldo(origem.getSaldo() + valor);
                return false;
            }
            
        }else{
            return false;
        }
    }
    
    public boolean pagarDividendos(ContaCorrenteDAO contas, double valor, int quantidade, long id){
        double dividendo = valor * quantidade;
        
        return this.transfere(contas, dividendo, 1, id);
    }    
    
//    public void pagarMensalidade(LocalDate data, MovimentaContaDAO movimenta){
//        if(data.getDayOfMonth() > 14){
//            for(int i=1; i<contas.length; i++){
//                if(contas[i] != null){
//                    this.transfere(this, 20,contas[i].getId(), 1);  
//                    movimenta.criarMovimento("Débito", "Pagamento Mensalidade", 20, contas[i]);                    
//                }                
//            }
//        }
//    }
    
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
    
      public void mostrarTodos() {
        boolean temConta = false;
        StringBuilder builder = new StringBuilder("");
        
        for (int i=1; i<contas.length; i++) {
            if (contas[i] != null) {
                builder.append(contas[i].toString());
                temConta = true;
            }
        }
        
        JOptionPane.showMessageDialog(null,builder.toString(),"Contas",JOptionPane.INFORMATION_MESSAGE);
        
        if (!temConta) {
            System.out.println("Não existe conta cadastrada");
        }
    }
     
//    private int proximaPosicaoLivre() {
//        for (int i = 0; i < contas.length; i++) {
//            if (contas[i] == null) {
//                return i;
//            }
//        }
//        return -1;
//    }
    
    private PreparedStatement createPreparedStatement(Connection con, long id) throws SQLException {
        String sql = "select * from ccorrente where idccorrente = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setLong(1, id);
        return ps;
    }
        
        public ContaCorrente buscaPorID(long code) {
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
