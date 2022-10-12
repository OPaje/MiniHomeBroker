/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.model;

import java.time.LocalDate;
import javax.swing.JOptionPane;

/**
 *
 * @author jeanc
 */
public class ClienteDAO {
    private Cliente[] clientes = new Cliente[5];

    public Cliente[] getClientes() {
        return clientes;
    }
    
    public ClienteDAO(){
        // criando os usuários
        Cliente c1 = new Cliente();
        c1.setNome("ADM");
        c1.setCpf("000000001");
        c1.setTipoUsuario(0); // administrador 
        c1.setLogin("ADM");
        c1.setSenha("Systemadm");
        c1.setDataCriacao(LocalDate.now());
        c1.setDataModificacao(LocalDate.now());
        this.adiciona(c1);
        
        
        Cliente c2 = new Cliente();
        c2.setNome("João");
        c2.setCpf("000000002");
        c2.setTipoUsuario(1); // normal
        c2.setLogin("Joao");
        c2.setSenha("asdf");
        c2.setDataCriacao(LocalDate.now());
        c2.setDataModificacao(LocalDate.now());
        this.adiciona(c2);
        
        Cliente c3 = new Cliente();
        c3.setNome("Stheffany");
        c3.setCpf("000000003");
        c3.setTipoUsuario(1); // normal
        c3.setLogin("Sthe");
        c3.setSenha("qwer");
        c3.setDataCriacao(LocalDate.now());
        c3.setDataModificacao(LocalDate.now());
        this.adiciona(c3);
        
        Cliente c4 = new Cliente();
        c4.setNome("Pajé");
        c4.setCpf("000000004");
        c4.setTipoUsuario(1); // normal
        c4.setLogin("Paje");
        c4.setSenha("senha");
        c4.setDataCriacao(LocalDate.now());
        c4.setDataModificacao(LocalDate.now());
        this.adiciona(c4);
    }
    
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
}
