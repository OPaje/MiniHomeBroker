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
public class AtivoDAO {
   
    public AtivoDAO(){
//        Ativo a1 = new Ativo();
//        a1.setTicker("NTCO3");
//        a1.setNomeEmpresa("Grupo Natura");
//        a1.setPrecoInicial(10);
//        a1.setTotalAtivos(1000);
//        a1.setDataCriacao(LocalDate.now());
//        a1.setDataModficacao(LocalDate.now());
//        this.adicionaAtivo(a1);
//        
//        Ativo a2 = new Ativo();
//        a2.setTicker("PETR4");
//        a2.setNomeEmpresa("Petrobras");
//        a2.setPrecoInicial(10);
//        a2.setTotalAtivos(1000);
//        a2.setDataCriacao(LocalDate.now());
//        a2.setDataModficacao(LocalDate.now());
//        this.adicionaAtivo(a2);
//        
//        Ativo a3 = new Ativo();
//        a3.setTicker("ABEV3");
//        a3.setNomeEmpresa("Ambev");
//        a3.setPrecoInicial(10);
//        a3.setTotalAtivos(1000);
//        a3.setDataCriacao(LocalDate.now());
//        a3.setDataModficacao(LocalDate.now());
//        this.adicionaAtivo(a3);
        
    }
    
    public Ativo adicionaAtivo(Ativo elemento) {
        String sql = "insert into ativo "
                + "(total_ativos,preco_inicial,nome_empresa,ticker,data_criacao_ativo,data_modificacao_ativo)" + " values (?,?,?,?,?,?)";

        try ( Connection connection = new ConnectionFactory().getConnection();  PreparedStatement stmt = connection.prepareStatement(sql)) {
            // seta os valores
            stmt.setInt(1, elemento.getTotalAtivos());
            stmt.setDouble(2, elemento.getPrecoInicial());
            stmt.setString(3, elemento.getNomeEmpresa());
            stmt.setString(4, elemento.getTicker());
            stmt.setDate(5, java.sql.Date.valueOf(LocalDate.now()));
            stmt.setDate(6, java.sql.Date.valueOf(LocalDate.now()));
            stmt.execute();

            System.out.println("Elemento inserido com sucesso.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Não foi possível cadastrar o ativo", "Erro", JOptionPane.INFORMATION_MESSAGE);
        }
        //na verdade deveria retornar o elemento que foi inserido agora
        return elemento;
    }
       
     public List<Ativo> lista(Ativo elemento){
        String sql = "select * from ativo";

        List<Ativo> ativos = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Long id = rs.getLong("idativo");
                int totalAtivos = rs.getInt("total_ativos");
                double precoInicial = rs.getDouble("preco_inicial");
                String nomeEmpresa = rs.getString("nome_empresa");
                String ticker = rs.getString("ticker");
                Date currenteDate = rs.getDate("data_criacao_ativo");
                LocalDate dataCriacao = currenteDate.toLocalDate();
                Date currenteMod = rs.getDate("data_modificacao_ativo");
                LocalDate dataModificacao = currenteMod.toLocalDate();
                

                Ativo ativo = new Ativo();
                ativo.setId(id); 
                ativo.setTotalAtivos(totalAtivos);
                ativo.setPrecoInicial(precoInicial);
                ativo.setNomeEmpresa(nomeEmpresa);
                ativo.setTicker(ticker);
                ativo.setDataCriacao(dataCriacao);
                ativo.setDataModficacao(dataModificacao);
                ativos.add(ativo);
            }
        } catch (SQLException e) {
             //throw new RuntimeException(e);
             JOptionPane.showMessageDialog(null, "Não foi possível trazer a lista", "Erro", 0);
        }

        // itera no ResultSet
        return ativos;
    }
    
     public Ativo buscaPorID(long code) {
        try (Connection connection = new ConnectionFactory().getConnection();
            PreparedStatement ps = createPreparedStatement(connection, code);
            ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Long id = rs.getLong("idativo");
                int totalAtivos = rs.getInt("total_ativos");
                double precoInicial = rs.getDouble("preco_inicial");
                String nomeEmpresa = rs.getString("nome_empresa");
                String ticker = rs.getString("ticker");
                Date currenteDate = rs.getDate("data_criacao_ativo");
                LocalDate dataCriacao = currenteDate.toLocalDate();
                Date currenteMod = rs.getDate("data_modificacao_ativo");
                LocalDate dataModificacao = currenteMod.toLocalDate();
                

                Ativo ativo = new Ativo();
                ativo.setId(id); 
                ativo.setTotalAtivos(totalAtivos);
                ativo.setPrecoInicial(precoInicial);
                ativo.setNomeEmpresa(nomeEmpresa);
                ativo.setTicker(ticker);
                ativo.setDataCriacao(dataCriacao);
                ativo.setDataModficacao(dataModificacao);
  
                return ativo;
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            JOptionPane.showMessageDialog(null, "Ativo não encontrado", "Erro", 0);
        }
        return null;
    }
     
    public Ativo buscaPorTicker(String ticker) {
        try (Connection connection = new ConnectionFactory().getConnection();
            PreparedStatement ps = createPreparedStatement2(connection, ticker);
            ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Long id = rs.getLong("idativo");
                int totalAtivos = rs.getInt("total_ativos");
                double precoInicial = rs.getDouble("preco_inicial");
                String nomeEmpresa = rs.getString("nome_empresa");
                String tickerAtivo = rs.getString("ticker");
                Date currenteDate = rs.getDate("data_criacao_ativo");
                LocalDate dataCriacao = currenteDate.toLocalDate();
                Date currenteMod = rs.getDate("data_modificacao_ativo");
                LocalDate dataModificacao = currenteMod.toLocalDate();
                

                Ativo ativo = new Ativo();
                ativo.setId(id); 
                ativo.setTotalAtivos(totalAtivos);
                ativo.setPrecoInicial(precoInicial);
                ativo.setNomeEmpresa(nomeEmpresa);
                ativo.setTicker(tickerAtivo);
                ativo.setDataCriacao(dataCriacao);
                ativo.setDataModficacao(dataModificacao);
  
                return ativo;
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            JOptionPane.showMessageDialog(null, "Ativo não encontrado", "Erro", 0);
        }
        return null;
    }
      public void altera(long id, String nomeEmpresa) {
        String sql = "update ativo set nome_empresa = ? where idativo = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setString(1, nomeEmpresa);
            stmt.setLong(2, id);
            
            stmt.execute();
            
            System.out.println("Elemento alterado com sucesso.");
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            JOptionPane.showMessageDialog(null, "Não foi possível alterar o ativo", "Erro", 0);
        }
    }
    
      public void exclui(long id) {
        String sql = "delete from ativo where idativo = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);
            
            stmt.execute();
            
            System.out.println("Elemento excluído com sucesso.");
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            JOptionPane.showMessageDialog(null, "Não foi possível excluir o ativo", "Erro", 0);
        }

    }
      
    private PreparedStatement createPreparedStatement(Connection con, long id) throws SQLException {
        String sql = "select * from ativo where idativo = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setLong(1, id);
        return ps;
    }
    
    private PreparedStatement createPreparedStatement2(Connection con, String ticker) throws SQLException {
        String sql = "select * from ativo where ticker = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, ticker);
        return ps;
    }
}
