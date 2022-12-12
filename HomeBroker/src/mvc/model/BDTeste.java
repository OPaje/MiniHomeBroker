/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Stheffany
 */
public class BDTeste {
       
    public static void main(String[] args) throws SQLException {
        //adiciona cliente           
//        ClienteDAO cdao = new ClienteDAO();
//        Cliente c = new Cliente();
//        c.setNome("Pajé");
//        c.setCpf("000000004");
//        c.setTipoUsuario(1); // normal
//        c.setLogin("Paje");
//        c.setSenha("senha");
//        c.setEndereco("Rua Leopoldino de Oliveira");
//        c.setTelefone("3315-5802");
//        c.setDataCriacao(LocalDate.now());
//        c.setDataModificacao(LocalDate.now());
//        long i = cdao.inserir(c);        
//        //sempre que um cliente for adicionado uma conta será associada a ele
//        ContaCorrente cc = new ContaCorrente();
//        ContaCorrenteDAO ccdao = new ContaCorrenteDAO();
//        cc.setSaldo(500000); // ao criar uma nova conta a bolsa ganha 500 mil
//        cc.setDataCriacao(LocalDate.now());
//        cc.setDataModificacao(LocalDate.now());
//        cc.setC(c);
//        long ix = ccdao.inserir(cc, i); // adicionando a conta a tabela de movimentacao;
//        MovimentaConta m =  new MovimentaConta();
//        MovimentaContaDAO mdao = new MovimentaContaDAO();
//        m.setConta(cc);
//        m.setDescricao("Débito");
//        m.setTipoMovimento("Movimentação de ativos");
//        m.setValor(2000);
//        m.setDataCriacao(LocalDate.now());
//        m.setDataModificacao(LocalDate.now());
//        mdao.novaMovimentacao("Débito", "Movimentação de ativos", 2000, LocalDate.now(), LocalDate.now(), ix);
        //altera cliente         
//        ClienteDAO cdao = new ClienteDAO();
//        Cliente elemento = new Cliente();
//        elemento.setSenha("senha");
//        cdao.alterar(elemento, 2);
//        List<Cliente> lista = cdao.getLista();
//        for (Cliente cli : lista){
//            System.out.println(cli);
//        }
        //busca ccorrente
//        ContaCorrenteDAO cdao = new ContaCorrenteDAO();
//        List<ContaCorrente> lista = cdao.getLista();
//        for (ContaCorrente c : lista){
//            System.out.println(c);
//        }
        //delete ccorrente
//        ContaCorrenteDAO cdao = new ContaCorrenteDAO();
//        cdao.remover(9);
//        List<ContaCorrente> lista = cdao.getLista();
//        for (ContaCorrente cli : lista){
//            System.out.println(cli);
//        }
        ContaCorrente c = BDTeste.criarCliente();
    }
    
    public static ContaCorrente criarCliente() throws SQLException{
        Cliente c = new Cliente();
        ClienteDAO cdao = new ClienteDAO();
        ContaCorrenteDAO ccdao = new ContaCorrenteDAO();
        ContaCorrente conta = new ContaCorrente(); 
        MovimentaConta m =  new MovimentaConta();
        MovimentaContaDAO mdao = new MovimentaContaDAO();
//        String nome = JOptionPane.showInputDialog(null, "Informe o seu nome: ");
//        c.setNome(nome);
//        
//        String cpf = JOptionPane.showInputDialog(null, "Informe o seu CPF: ");
//        c.setCpf(cpf);
//        c.setTipoUsuario(1);
//        
//        String login = JOptionPane.showInputDialog(null, "Informe o seu login: ");
//        c.setLogin(login);
//        
//        String senha = JOptionPane.showInputDialog(null, "Informe a sua senha: ");
//        c.setSenha(senha);
//        
//        String endereco = JOptionPane.showInputDialog(null, "Informe o seu endereco: ");
//        c.setEndereco(endereco);
//        
//        String telefone = JOptionPane.showInputDialog(null, "Informe o seu telefone: ");
//        c.setTelefone(telefone);
//        
//        c.setDataCriacao(LocalDate.now());
//        c.setDataModificacao(LocalDate.now());
//        
//        long i = cdao.inserir(c);
//        conta.setSaldo(500000); // ao criar uma nova conta a bolsa ganha 500 mil
//        conta.setDataCriacao(LocalDate.now());
//        conta.setDataModificacao(LocalDate.now());
//        conta.setC(c);
//        ccdao.inserir(conta, i);
//        
//        // vinculando o cliente com a conta
//        conta.setC(c);
//        conta.setSaldo(20000);
//        conta.setDataCriacao(LocalDate.now());
//        conta.setDataModificacao(LocalDate.now());
        
//        String login = JOptionPane.showInputDialog(null, "Informe o login: ");                   
//        String senha = JOptionPane.showInputDialog(null, "Informe sua senha: ");
//        String x = JOptionPane.showInputDialog(null, "Informe o valor que deseja transferir: ");
//        Double valor = Double.parseDouble(x);
//        String xx = JOptionPane.showInputDialog(null, "Informe o id da conta que deseja transferir: ");
//        long id = Long.parseLong(xx);
//        
//        Cliente testeCli = cdao.buscarPorLoginESenha(login, senha);
//        
//        if(testeCli != null){
//            System.out.println("\nLogin bem sucedido");
//            ContaCorrente testeConta = ccdao.buscaPorIdCliente(testeCli.getId());
//            ccdao.transfere(ccdao, valor, testeConta.getId(), id);
//            JOptionPane.showMessageDialog(null, ccdao.mostraSaldo(testeConta.getId(), ccdao), "Saldo", JOptionPane.INFORMATION_MESSAGE); 
//        }else{
//            System.out.println("Erro");
//        }

            
        
        return conta;
    }
         
}
