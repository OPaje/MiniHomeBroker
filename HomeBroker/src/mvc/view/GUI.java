/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.view;

import java.time.LocalDate;
import javax.swing.JOptionPane;
import mvc.model.Ativo;
import mvc.model.AtivoDAO;
import mvc.model.Cliente;
import mvc.model.ContaCorrente;
import mvc.model.ContaCorrenteDAO;
import mvc.model.Ordem;

/**
 *
 * @author jeanc
 */
public class GUI {
    
    public ContaCorrente criarCliente(){
        Cliente c = new Cliente();
        ContaCorrente conta = new ContaCorrente();
        
        String nome = JOptionPane.showInputDialog(null, "Informe o seu nome: ");
        c.setNome(nome);
        
        String cpf = JOptionPane.showInputDialog(null, "Informe o seu CPF: ");
        c.setCpf(cpf);
        c.setTipoUsuario(1);
        
        String login = JOptionPane.showInputDialog(null, "Informe o seu login: ");
        c.setLogin(login);
        
        String senha = JOptionPane.showInputDialog(null, "Informe a sua senha: ");
        c.setSenha(senha);
        
        String endereco = JOptionPane.showInputDialog(null, "Informe o seu endereco: ");
        c.setEndereco(endereco);
        
        String telefone = JOptionPane.showInputDialog(null, "Informe o seu telefone: ");
        c.setTelefone(telefone);
        
        c.setDataCriacao(LocalDate.now());
        c.setDataModificacao(LocalDate.now());
        
        // vinculando o cliente com a conta
        conta.setC(c);
        conta.setSaldo(20000);
        conta.setDataCriacao(LocalDate.now());
        conta.setDataModificacao(LocalDate.now());
        
        return conta;
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
        
        String totalDeAtivos = JOptionPane.showInputDialog(null, "Informe a quantidade total de ativos: ");
        int totalAtivos = Integer.parseInt(totalDeAtivos);
        a.setTotalAtivos(totalAtivos);
        
        a.setDataCriacao(LocalDate.now());
        a.setDataModficacao(LocalDate.now());
        
        return a;
    }
    
    public Ordem criarOrdem(AtivoDAO ativos, ContaCorrenteDAO contas){
        Ordem o = new Ordem();
       
        do{
            String id = JOptionPane.showInputDialog(null, "Informe o id da sua conta: ");
            long idConta = Long.parseLong(id);
            o.setConta(contas.buscaPorId(idConta));
            
            if(o.getConta() == null){
                JOptionPane.showMessageDialog(null, "ID Inválido", "Erro", 0);
            }
            
        }while(o.getConta() == null);
        
        
        String tipo = JOptionPane.showInputDialog(null, "Informe o tipo da ordem: ");
        o.setTipoOrdem(tipo);
        
        do{
            String ticker = JOptionPane.showInputDialog(null, "Informe o ticker do ativo: ");
            o.setTicker(ativos.buscaPorTicker(ticker));
            
            if(o.getTicker() == null){
                JOptionPane.showMessageDialog(null, "Ticker Inválido", "Erro", 0);
            }
            
        }while(o.getTicker() == null);
        
        if(o.getTicker().getTotalAtivos() == 0){
            o.setEstadoOrdem("Não");
            JOptionPane.showMessageDialog(null, "O ticker não possui ativos a venda", "Erro", 0);
            return null;
        }

        String qtd = JOptionPane.showInputDialog(null, "Informe a quantidade de ativos: ");
        int qtd1 = Integer.parseInt(qtd);
        o.setQuantidade(qtd1);
        
        
        double valor = 10;
        o.setValor(valor);
        o.setValorTotal(qtd1 * valor);
        
        // chamar método transfere
        // mandando para a conta do adm
        //contas.transfere(contas.buscaPorId(1), o.getValorTotal()); // adcionar como parâmetro a conta de origem
        
        if(o.getTicker().getTotalAtivos() < qtd1){
            o.setEstadoOrdem("Parcial");
            o.setQuantidade(qtd1 - o.getTicker().getTotalAtivos());
            o.getTicker().setTotalAtivos(0);
            
        }else if(o.getTicker().getTotalAtivos() > qtd1){
            o.setEstadoOrdem("Total");
            o.setQuantidade(qtd1);
            o.getTicker().setTotalAtivos(o.getTicker().getTotalAtivos() - qtd1);
            
        }
        
        o.setDataCriacao(LocalDate.now());
        o.setDataModificacao(LocalDate.now());
        
        return o;
    }
    
    
    public int menu(){
        
        StringBuilder builder = new StringBuilder("");
        
        builder.append("SEJA BEM VINDO AO MEU PROGRAMA\n\n");
        builder.append("\n1- Listar usuários");
        builder.append("\n2 - Depositar");
        builder.append("\n3 - Saque");
        builder.append("\n4 - Mostra saldo");
        builder.append("\n5 - Transfere");
        builder.append("\n6 - Extrato");
        builder.append("\n7 - Mostrar meus ativos");
        builder.append("\n8 - Mostrar ativos");
        builder.append("\n9 - Comprar ativos");
        builder.append("\n10 - Vender ativos");
        builder.append("\n11 - Mostrar contas");
        builder.append("\n12 - Editar ativo");
        builder.append("\n13 - Cadastrar cliente");
        builder.append("\n14 - Cadastrar ativo");
        builder.append("\n Qual sua opção? \n\n");
        
        String op = JOptionPane.showInputDialog(null, builder.toString());
        int x = 0;
        x = Integer.parseInt(op);
        return x;
    }
    
    public int menuInicial(){
        
       StringBuilder builder = new StringBuilder("");
       
       builder.append("SEJA BEM VINDO AO HOME BROKER\n\n");
       builder.append("\n1- Cadastrar cliente");
       builder.append("\n2- Fazer login");
       builder.append("\n0- Sair");
       
       String op = JOptionPane.showInputDialog(null, builder.toString());
       
       return Integer.parseInt(op);
    }
    
    public int menuCliente(){
        
        StringBuilder builder = new StringBuilder("");
        builder.append("\n1- Depositar");
        builder.append("\n2- Mostrar saldo");
        builder.append("\n3- Saque");
        builder.append("\n4- Transferência");
        builder.append("\n5- Extrato");
        builder.append("\n6- Mostrar Ativos");
        builder.append("\n7- Comprar Ativo");
        builder.append("\n8- Vender Ativo");
        builder.append("\n9- Meus Ativo");
        builder.append("\n10- Editar conta");
        builder.append("\n0- Sair");
        
        String op = JOptionPane.showInputDialog(null, builder.toString());
        
        return Integer.parseInt(op);
    }
    
    public int menuADM(){
        
        StringBuilder builder = new StringBuilder("");
        builder.append("\n1- Pagar dividendos");
        builder.append("\n2- Cadastrar ativo");
        builder.append("\n3- Editar ativo");
        builder.append("\n4- Passar o tempo");
        builder.append("\n0- Sair");
        
        String op = JOptionPane.showInputDialog(null, builder.toString());
        
        return Integer.parseInt(op);
    }
    
    public double retornarValor(){
        String valor = JOptionPane.showInputDialog(null, "Informe o valor: ");
        
        return Double.parseDouble(valor);
    }
    
    public long perguntarId(){
        String idTransfere = JOptionPane.showInputDialog(null, "Informe o ID da conta: ");
        
        return Long.parseLong(idTransfere);
    }
    
    public String perguntarNome(){
        return JOptionPane.showInputDialog(null, "Informe o nome: ");
    }
}
