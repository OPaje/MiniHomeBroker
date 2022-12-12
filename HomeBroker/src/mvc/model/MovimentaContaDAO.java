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

/**
 *
 * @author Stheffany
 */
public class MovimentaContaDAO {
        
    private PreparedStatement createPreparedStatement(Connection con, long id) throws SQLException {
        String sql = "select * from mov_conta where idmovimento = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setLong(1, id);
        return ps;
    }
        
        public MovimentaConta buscaPorID(long code) {
        try (Connection connection = new ConnectionFactory().getConnection();
            PreparedStatement ps = createPreparedStatement(connection, code);
            ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {                   
                MovimentaConta elemento = new MovimentaConta();
                elemento.setId(rs.getLong("idmovimento"));
                elemento.setValor(rs.getDouble("valor_mov"));
                elemento.setTipoMovimento(rs.getString("tipo_mov"));
                elemento.setDescricao(rs.getString("desc_mov"));
                Date currentDate = rs.getDate("data_criacao_mov");
                LocalDate dataCriacao = currentDate.toLocalDate();
                elemento.setDataCriacao(dataCriacao);    
                Date currentDateMod = rs.getDate("data_modificacao_mov");
                LocalDate dataMod = currentDateMod.toLocalDate();
                elemento.setDataModificacao(dataMod);
                
                return elemento;
            }
            } catch (SQLException e) {
              throw new RuntimeException(e);
            }
            return null;
        }
        
        public long novaMovimentacao(String tipoMovimento, String descricao, double valor, LocalDate c, LocalDate m, long id) throws SQLException{
            String sql = "insert into mov_conta(tipo_mov, desc_mov, valor_mov, "
                       + "data_criacao_mov, data_modificacao_mov, mov_conta_ccorrente) values (?,?,?,?,?,?)";
            try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, tipoMovimento);
                stmt.setString(2, descricao);
                stmt.setDouble(3, valor);
                stmt.setDate(4, java.sql.Date.valueOf(c));
                stmt.setDate(5, java.sql.Date.valueOf(m));
                stmt.setLong(6, id); //id da conta 
                stmt.execute();
                //retorna o id do objeto inserido
                ResultSet rs=stmt.getGeneratedKeys();
                int retorno=0;
                if(rs.next()){
                    retorno = rs.getInt(1);
                }
                System.out.println("Gravado!");
                return retorno;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        
        public void remover(int id) {
            String sql = "delete from mov_conta where idmovimento = ?"; 
            try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.execute();
                stmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally{
                System.out.println("Removido com sucesso"); 
            }
        }
        
        public List<MovimentaConta> getLista(long id) throws SQLException {
            String sql = "select * from mov_conta where mov_conta_ccorrente = ?";
            try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setLong(1, id);
                List<MovimentaConta> movimentos = new ArrayList<MovimentaConta>();
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    // criando o objeto
                    MovimentaConta elemento = new MovimentaConta();
                    elemento.setId(rs.getLong("idmovimento"));
                    elemento.setTipoMovimento(rs.getString("tipo_mov"));
                    elemento.setDescricao(rs.getString("desc_mov"));
                    elemento.setValor(rs.getDouble("valor_mov"));
                    
                    Date currentDate = rs.getDate("data_criacao_mov");
                    LocalDate dataCriacao = currentDate.toLocalDate();
                    elemento.setDataCriacao(dataCriacao);
                    
                    Date currentDateMod = rs.getDate("data_modificacao_mov");
                    LocalDate dataMod = currentDateMod.toLocalDate();
                    elemento.setDataModificacao(dataMod);                  
                    
                    movimentos.add(elemento);
                }
                rs.close();
                stmt.close();
                return movimentos;
            }catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        
//        public void alterar(MovimentaConta elemento, String tipoMovimento, String descricao, double valor, LocalDate dataCriacao, LocalDate dataMod, int id) {
//            String sql = "update mov_conta set da = ? where idcliente = ?";
//
//            try (Connection connection = new ConnectionFactory().getConnection();
//                    PreparedStatement stmt = connection.prepareStatement(sql)) {
//
//                stmt.setString(1, elemento.getSenha());
//                stmt.setLong(2, id);
//                stmt.execute();
//                stmt.close();
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }finally{
//            System.out.println("Senha alterado com sucesso.");
//            }
//        }
}
