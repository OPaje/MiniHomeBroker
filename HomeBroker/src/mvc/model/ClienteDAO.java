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
    private Cliente[] clientes = new Cliente[5];

    public Cliente[] getClientes() {
        return clientes;
    }
    
//    public ClienteDAO(){
//        // criando os usuários
//        Cliente c1 = new Cliente();
//        c1.setNome("ADM");
//        c1.setCpf("000000001");
//        c1.setTipoUsuario(0); // administrador 
//        c1.setLogin("ADM");
//        c1.setSenha("Systemadm");
//        c1.setDataCriacao(LocalDate.now());
//        c1.setDataModificacao(LocalDate.now());
//        this.adiciona(c1);
//        
//        
//        Cliente c2 = new Cliente();
//        c2.setNome("João");
//        c2.setCpf("000000002");
//        c2.setTipoUsuario(1); // normal
//        c2.setLogin("Joao");
//        c2.setSenha("asdf");
//        c2.setDataCriacao(LocalDate.now());
//        c2.setDataModificacao(LocalDate.now());
//        this.adiciona(c2);
//        
//        Cliente c3 = new Cliente();
//        c3.setNome("Stheffany");
//        c3.setCpf("000000003");
//        c3.setTipoUsuario(1); // normal
//        c3.setLogin("Sthe");
//        c3.setSenha("qwer");
//        c3.setDataCriacao(LocalDate.now());
//        c3.setDataModificacao(LocalDate.now());
//        this.adiciona(c3);
//        
//        Cliente c4 = new Cliente();
//        c4.setNome("Pajé");
//        c4.setCpf("000000004");
//        c4.setTipoUsuario(1); // normal
//        c4.setLogin("Paje");
//        c4.setSenha("senha");
//        c4.setDataCriacao(LocalDate.now());
//        c4.setDataModificacao(LocalDate.now());
//        this.adiciona(c4);
//    }
    
    public boolean adiciona(Cliente c) {
        int proximaPosicaoLivre = this.proximaPosicaoLivre();
        if (proximaPosicaoLivre != -1) {
            clientes[proximaPosicaoLivre] = c;
            return true;
        } else {
            return false;
        }
    }

    public boolean ehVazio() {
        for (Cliente cliente : clientes) {
            if (cliente != null) {
                return false;
            }
        }
        return true;

    }

   public void mostrarTodos() {
        boolean temCliente = false;
        StringBuilder builder = new StringBuilder("");
        
        for (Cliente c : clientes) {
            if (c != null) {
                builder.append(c.toString());
                temCliente = true;
            }
        }
        
        JOptionPane.showMessageDialog(null,builder.toString(),"Clientes",JOptionPane.INFORMATION_MESSAGE);
        
        if (!temCliente) {
            System.out.println("Não existe cliente cadastrado");
        }
    }

     public boolean alterarNome(String nome, String novoNome){
        Cliente c = this.buscaPorNome(nome);
        if(c != null){
            c.setNome(novoNome);
            c.setDataModificacao(LocalDate.now());
            return true;
        }else{
            return false;
        }
    }

    public Cliente buscaPorNome(String nome) {
        for (Cliente c : clientes) {
            if (c != null && c.getNome().equals(nome)) {
                return c;
            }
        }
        return null;

    }
    
    public Cliente buscaPorId(long id) {
        for (Cliente c : clientes) {
            if (c != null && c.getId() == id) {
                return c;
            }
        }
        return null;

    }
    
    public Cliente buscarPorLoginESenha(String login, String senha) {
        for (Cliente c : clientes) {
            if (c != null && (c.getLogin().equals(login) && c.getSenha().equals(senha))) {
                return c;
            }
        }
        return null;
    }

    public boolean remover(String cpf) {
        for (int i = 0; i < clientes.length; i++) {
            if (clientes[i] != null && clientes[i].getCpf().equals(cpf)) {
                clientes[i] = null;
                return true;
            }
        }
        return false;

    }
    
    public boolean removerPorId(long id) {
        for (int i = 0; i < clientes.length; i++) {
            if (clientes[i] != null && clientes[i].getId() == id) {
                clientes[i] = null;
                return true;
            }
        }
        return false;

    }

    private int proximaPosicaoLivre() {
        for (int i = 0; i < clientes.length; i++) {
            if (clientes[i] == null) {
                return i;
            }

        }
        return -1;
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
        
        public void alterar(Cliente elemento, int id) {
            String sql = "update cliente set senha = ? where idcliente = ?";

            try (Connection connection = new ConnectionFactory().getConnection();
                    PreparedStatement stmt = connection.prepareStatement(sql)) {

                stmt.setString(1, elemento.getSenha());
                stmt.setLong(2, id);
                stmt.execute();
                stmt.close();
                System.out.println("Senha alterado com sucesso.");
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
