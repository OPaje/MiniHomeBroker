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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author jeanc
 */
public class OrdemDAO {
    
    
//    public OrdemDAO(AtivoDAO ativos, ContaCorrenteDAO contas){
//        
//        Ordem o1 = new Ordem();
//        o1.setConta(contas.buscaPorIdCliente(2));
//        o1.setTipoOrdem("Venda");
//        o1.setTicker(ativos.buscaPorTicker("NTCO3"));
//        o1.setQuantidade(40);
//        o1.setValor(10);
//        o1.setValorTotal(o1.getValor() * o1.getQuantidade());
//        o1.setDataCriacao(LocalDate.now());
//        o1.setDataModificacao(LocalDate.now());
//        this.adicionaOrdem(o1);
//        
//        Ordem o2 = new Ordem();
//        o2.setConta(contas.buscaPorIdCliente(3));
//        o2.setTipoOrdem("Compra");
//        o2.setTicker(ativos.buscaPorTicker("NTCO3"));
//        o2.setQuantidade(20);
//        o2.setValor(10);
//        o2.setValorTotal(o2.getValor() * o2.getQuantidade());
//        o2.setDataCriacao(LocalDate.now());
//        o2.setDataModificacao(LocalDate.now());
//        this.adicionaOrdem(o2);
//        
//        Ordem o3 = new Ordem();
//        o3.setConta(contas.buscaPorIdCliente(3));
//        o3.setTipoOrdem("Compra");
//        o3.setTicker(ativos.buscaPorTicker("NTCO3"));
//        o3.setQuantidade(30);
//        o3.setValor(10);
//        o3.setValorTotal(o3.getValor() * o3.getQuantidade());
//        o3.setDataCriacao(LocalDate.now());
//        o3.setDataModificacao(LocalDate.now());
//        this.adicionaOrdem(o3);
//    }
    
        public Ordem adicionaOrdem(Ordem elemento) throws SQLException{
        String sql = "insert into ordem "
                + "(qtde_ordem,valor_ordem,valor_total_ordem,tipo_ordem,estado_ordem,data_criacao_ordem,data_modificacao_ordem, "
                + "ordem_ccorrente, ordem_ativo)" + " values (?,?,?,?,?,?,?,?,?)";

        try ( Connection connection = new ConnectionFactory().getConnection();  PreparedStatement stmt = connection.prepareStatement(sql)) {
            // seta os valores
            stmt.setInt(1, elemento.getQuantidade());
            stmt.setDouble(2, elemento.getValor());
            stmt.setDouble(3, elemento.getValorTotal());
            stmt.setString(4, elemento.getTipoOrdem());
            stmt.setString(5, elemento.getEstadoOrdem());
            stmt.setDate(6, java.sql.Date.valueOf(LocalDate.now()));
            stmt.setDate(7, java.sql.Date.valueOf(LocalDate.now()));
            stmt.setLong(8, elemento.getConta().getId());
            stmt.setLong(9, elemento.getTicker().getId());
            stmt.execute();

            System.out.println("Elemento inserido com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        return elemento;
    }
        
    public List<Ordem> lista(Ordem elemento) {
        String sql = "select * from ordem";
        ContaCorrenteDAO contaCorrenteDAO = new ContaCorrenteDAO();
        AtivoDAO ativoDAO = new AtivoDAO();
        List<Ordem> ordens = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Long id = rs.getLong("idordem");
                int quantidade = rs.getInt("qtde_ordem");
                double valor = rs.getDouble("valor_ordem");
                double valorTotal = rs.getDouble("valor_total_ordem");
                String tipo = rs.getString("tipo_ordem");
                String estadoOrdem = rs.getString("estado_ordem");
                Date currenteDate = rs.getDate("data_criacao_ordem");
                LocalDate dataCriacao = currenteDate.toLocalDate();
                Date currenteMod = rs.getDate("data_modificacao_ordem");
                LocalDate dataModificacao = currenteMod.toLocalDate();
                long idAtivo = rs.getLong("ordem_ativo");
                long idConta = rs.getLong("ordem_ccorrente");
                
                ContaCorrente conta = contaCorrenteDAO.buscaPorId(idConta);
                Ativo ativo = ativoDAO.buscaPorID(idAtivo);

                Ordem ordem = new Ordem();
                ordem.setId(id); 
                ordem.setQuantidade(quantidade);
                ordem.setValor(valor);
                ordem.setValorTotal(valorTotal);
                ordem.setTipoOrdem(tipo);
                ordem.setEstadoOrdem(estadoOrdem);
                ordem.setDataCriacao(dataCriacao);
                ordem.setDataModificacao(dataModificacao);
                ordem.setTicker(ativo);
                ordem.setConta(conta);
                ordens.add(ordem);
            }
        } catch (SQLException e) {
             throw new RuntimeException(e);
        }

        // itera no ResultSet
        return ordens;
    }
        
    public Ordem buscaPorID(long code) {
            ContaCorrenteDAO contaCorrenteDAO = new ContaCorrenteDAO();
            AtivoDAO ativoDAO = new AtivoDAO();
        try (Connection connection = new ConnectionFactory().getConnection();
            PreparedStatement ps = createPreparedStatement(connection, code);
            ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Long id = rs.getLong("idordem");
                int quantidade = rs.getInt("qtde_ordem");
                double valor = rs.getDouble("valor_ordem");
                double valorTotal = rs.getDouble("valor_total_ordem");
                String tipo = rs.getString("tipo_ordem");
                String estadoOrdem = rs.getString("estado_ordem");
                Date currenteDate = rs.getDate("data_criacao_ordem");
                LocalDate dataCriacao = currenteDate.toLocalDate();
                Date currenteMod = rs.getDate("data_modificacao_ordem");
                LocalDate dataModificacao = currenteMod.toLocalDate();
                long idAtivo = rs.getLong("ordem_ativo");
                long idConta = rs.getLong("ordem_ccorrente");
                
                ContaCorrente conta = contaCorrenteDAO.buscaPorId(idConta);
                Ativo ativo = ativoDAO.buscaPorID(idAtivo);

                Ordem ordem = new Ordem();
                ordem.setId(id); 
                ordem.setQuantidade(quantidade);
                ordem.setValor(valor);
                ordem.setValorTotal(valorTotal);
                ordem.setTipoOrdem(tipo);
                ordem.setEstadoOrdem(estadoOrdem);
                ordem.setDataCriacao(dataCriacao);
                ordem.setDataModificacao(dataModificacao);
                ordem.setTicker(ativo);
                ordem.setConta(conta);
  
                return ordem;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
     public void exclui(long id) {
        String sql = "delete from ordem where idordem = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);
            
            stmt.execute();
            
            System.out.println("Elemento excluído com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    
    public void mostrarTodos() throws SQLException{
        List<Ordem> ordens = null;
        ordens = this.lista(null);
        StringBuilder builder = new StringBuilder("");
        
        for (Ordem o : ordens) {
             builder.append(o.toString());            
        }      
       
        JOptionPane.showMessageDialog(null,builder.toString(),"Ordens",JOptionPane.INFORMATION_MESSAGE);
        
    }
//    
//    public String bookOfertas(Ativo ativo){  // fazer um join para trazer todas as ordens de um ativo
//        boolean temOrdem = false;
//        StringBuilder builder = new StringBuilder("");
//        
//        for (Ordem o : ordens) {
//            if (o != null && o.getTicker().equals(ativo) && !"Ordem 0".equals(o.getTipoOrdem())) {
//                builder.append("Ativo: ").append(o.getTicker().getTicker()).append(" Valor: ").append(o.getValor()).append(" Tipo: ").append(o.getTipoOrdem()).append("\n\n");
//                temOrdem = true;
//            }
//        }
//                
//        if (!temOrdem) {
//            return "Não existe ordem feita";
//        }else{
//            return builder.toString();
//        }
//    }
    
    
    public String bookOfertas(long id) throws SQLException{  
        AtivoDAO ativoDAO = new AtivoDAO();
        List<Ordem> ordens = new ArrayList<>();

        StringBuilder builder = new StringBuilder("");
        
        try (Connection connection = new ConnectionFactory().getConnection();
            PreparedStatement ps = createPreparedStatementBookOfertas(connection, id);
            ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                
                int quantidade = rs.getInt("qtde_ordem");
                double valor = rs.getDouble("valor_ordem");
                String tipo = rs.getString("tipo_ordem");
                long idAtivo = rs.getLong("ordem_ativo");
                
                Ativo ativo = ativoDAO.buscaPorID(idAtivo);

                Ordem ordem = new Ordem();
                ordem.setQuantidade(quantidade);
                ordem.setValor(valor);
                ordem.setTipoOrdem(tipo);
                ordem.setTicker(ativo);
                ordens.add(ordem);
            }
        }
        for (Ordem o : ordens) {
             builder.append("Ativo: ").append(o.getTicker().getTicker()).append(" Valor: ").append(o.getValor()).append(" Tipo: ").append(o.getTipoOrdem()).append("\n\n"); 
        }
 
        return builder.toString();
 
    }
    
    
    private PreparedStatement createPreparedStatement(Connection con, long id) throws SQLException {
        String sql = "select * from ordem where idordem = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setLong(1, id);
        return ps;
    }
    
       private PreparedStatement createPreparedStatementBookOfertas(Connection con, long id) throws SQLException {
        String sql = "SELECT ordem_ativo, qtde_ordem, valor_ordem, tipo_ordem FROM ordem WHERE ordem_ativo = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setLong(1, id);
        return ps;
    }

}
