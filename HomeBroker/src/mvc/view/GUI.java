/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.view;

import java.time.LocalDate;
import javax.swing.JOptionPane;
import mvc.model.Ativo;
import mvc.model.Cliente;

/**
 *
 * @author jeanc
 */
public class GUI {
    public Cliente criarCliente(){
        Cliente c = new Cliente();
        String nome = JOptionPane.showInputDialog(null, "Informe o seu nome: ");
        c.setNome(nome);
        
        String cpf = JOptionPane.showInputDialog(null, "Informe o seu CPF: ");
        c.setCpf(cpf);
        
        String tipoUsuario = JOptionPane.showInputDialog(null, "Informe o tipo: ");
        int tipo = Integer.parseInt(tipoUsuario);
        c.setTipoUsuario(tipo);
        
        String login = JOptionPane.showInputDialog(null, "Informe o seu login: ");
        c.setLogin(login);
        
        String senha = JOptionPane.showInputDialog(null, "Informe a sua sennha: ");
        c.setSenha(senha);
        
        String endereco = JOptionPane.showInputDialog(null, "Informe o seu endereco: ");
        c.setEndereco(endereco);
        
        String telefone = JOptionPane.showInputDialog(null, "Informe o seu telefone: ");
        c.setTelefone(telefone);
        
        c.setDataCriacao(LocalDate.now());
        c.setDataModificacao(LocalDate.now());
        
        return c;
    }
    
    public Ativo criarAtivo(){
        Ativo a = new Ativo();
        
        String nomeEmpresa = JOptionPane.showInputDialog(null, "Informe o nome da empresa: ");
        a.setNomeEmpresa(nomeEmpresa);
        
        String ticker = JOptionPane.showInputDialog(null, "Informe o ticker do ativo: ");
        a.setTicker(ticker);
        
        String precoInicial = JOptionPane.showInputDialog(null, "Informe o preco inicial do ativo: ");
        double preco = Double.parseDouble(precoInicial);
        a.setPrecoInicial(preco);
       
        
        a.setDataCriacao(LocalDate.now());
        a.setDataModficacao(LocalDate.now());
        
        return a;
    }
   
    
    public int Menu(){
        
        StringBuilder builder = new StringBuilder("");
        
        builder.append("SEJA BEM VINDO AO MEU PROGRAMA\n\n");
        builder.append("\n1- Listar usuários");
        builder.append("\n2 - Deposita");
        builder.append("\n3 - Saque");
        builder.append("\n4 - Mostra saldo");
        builder.append("\n5 - Transfere");
        builder.append("\n6 - Extrato");
        builder.append("\n7 - Mostrar meus ativos");
        builder.append("\n8 - Mostrar ativos");
        builder.append("\n9 - Comprar ativos");
        builder.append("\n10 - Vender ativos");
        builder.append("\n Qual sua opção? \n\n");
        System.out.print(builder.toString());
        
        String op = JOptionPane.showInputDialog(null, builder.toString());
        int x = 0;
        x = Integer.parseInt(op);
        return x;
    }
}
