/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import java.util.List;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.time.LocalDate;

/**
 *
 * @author jeanc
 */
public class ClienteDAO {
    
    private PreparedStatement criaConsulta(Connection con, String login, String senha) throws SQLException {
        String sql = "select * from cliente where login = ? and senha = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, login);
        ps.setString(2, senha);
        return ps;
    }
    
    public Cliente buscarPorLoginESenha(String login, String senha) {
        try (Connection connection = new ConnectionFactory().getConnection();
        PreparedStatement ps = criaConsulta(connection, login, senha);
        ResultSet rs = ps.executeQuery()) {            
        while (rs.next()) {                   
                Cliente elemento = new Cliente();
                elemento.setId(rs.getLong("idcliente"));
                elemento.setTipoUsuario(rs.getInt("tipo_usuario"));
                elemento.setNome(rs.getString("nome_cliente"));
                elemento.setCpf(rs.getString("cpf"));
                elemento.setLogin(rs.getString("login"));
                elemento.setSenha(rs.getString("senha"));
                elemento.setEndereco(rs.getString("endereco"));
                elemento.setTelefone(rs.getString("telefone"));

                Date currentDate = rs.getDate("data_criacao_cliente");
                LocalDate dataCriacao = currentDate.toLocalDate();
                elemento.setDataCriacao(dataCriacao);

                Date currentDateMod = rs.getDate("data_modificacao_cliente");
                LocalDate dataMod = currentDateMod.toLocalDate();
                elemento.setDataModificacao(dataMod);                  
            return elemento;
        }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    
        
    public long inserir(Cliente c) throws SQLException{
        String sql = "insert into cliente(tipo_usuario, nome_cliente, cpf, login, senha, endereco, "
                + "telefone, data_criacao_cliente, data_modificacao_cliente) values (?,?,?,?,?,?,?,?,?)";
        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        stmt.setInt(1, c.getTipoUsuario());
        stmt.setString(2, c.getNome());
        stmt.setString(3, c.getCpf());
        stmt.setString(4, c.getLogin());
        stmt.setString(5, c.getSenha());
        stmt.setString(6, c.getEndereco());
        stmt.setString(7, c.getTelefone());
        stmt.setDate(8, java.sql.Date.valueOf(c.getDataCriacao()));
        stmt.setDate(9, java.sql.Date.valueOf(c.getDataModificacao()));       
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
        
    public void alterar(String login, String senha, String senhaNova) {
        String sql = "update cliente set senha = ? where idcliente = ?";
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            Cliente cli = this.buscarPorLoginESenha(login, senha);
            stmt.setString(1, senhaNova);
            stmt.setLong(2, cli.getId());
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Senha alterada!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
        
    public void remover(int id) {
        String sql = "delete from cliente where idcliente = ?"; 
        try (Connection connection = new ConnectionFactory().getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
        
    public List<Cliente> getLista() throws SQLException {
        String sql = "select * from cliente";
        try (Connection connection = new ConnectionFactory().getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)) {
            List<Cliente> clientes = new ArrayList<Cliente>();
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // criando o objeto
                Cliente elemento = new Cliente();
                elemento.setId(rs.getLong("idcliente"));
                elemento.setTipoUsuario(rs.getInt("tipo_usuario"));
                elemento.setNome(rs.getString("nome_cliente"));
                elemento.setCpf(rs.getString("cpf"));
                elemento.setLogin(rs.getString("login"));
                elemento.setSenha(rs.getString("senha"));
                elemento.setEndereco(rs.getString("endereco"));
                elemento.setTelefone(rs.getString("telefone"));

                Date currentDate = rs.getDate("data_criacao_cliente");
                LocalDate dataCriacao = currentDate.toLocalDate();
                elemento.setDataCriacao(dataCriacao);

                Date currentDateMod = rs.getDate("data_modificacao_cliente");
                LocalDate dataMod = currentDateMod.toLocalDate();
                elemento.setDataModificacao(dataMod);

                clientes.add(elemento);
            }
            rs.close();
            stmt.close();
            return clientes;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
        
    private PreparedStatement createPreparedStatement(Connection con, long id) throws SQLException {
        String sql = "select * from cliente where idcliente = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setLong(1, id);
        return ps;
    }
        
    public Cliente buscaPorID(long code) {
    try (Connection connection = new ConnectionFactory().getConnection();
        PreparedStatement ps = createPreparedStatement(connection, code);
        ResultSet rs = ps.executeQuery()) {            
        while (rs.next()) {                   
                Cliente elemento = new Cliente();
                elemento.setId(rs.getLong("idcliente"));
                elemento.setTipoUsuario(rs.getInt("tipo_usuario"));
                elemento.setNome(rs.getString("nome_cliente"));
                elemento.setCpf(rs.getString("cpf"));
                elemento.setLogin(rs.getString("login"));
                elemento.setSenha(rs.getString("senha"));
                elemento.setEndereco(rs.getString("endereco"));
                elemento.setTelefone(rs.getString("telefone"));

                Date currentDate = rs.getDate("data_criacao_cliente");
                LocalDate dataCriacao = currentDate.toLocalDate();
                elemento.setDataCriacao(dataCriacao);

                Date currentDateMod = rs.getDate("data_modificacao_cliente");
                LocalDate dataMod = currentDateMod.toLocalDate();
                elemento.setDataModificacao(dataMod);                  
            return elemento;
        }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    
    
}
