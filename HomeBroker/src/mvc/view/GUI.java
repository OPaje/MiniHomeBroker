/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.view;

import java.sql.SQLException;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import mvc.model.Ativo;
import mvc.model.AtivoDAO;
import mvc.model.Cliente;
import mvc.model.ClienteDAO;
import mvc.model.ContaCorrente;
import mvc.model.ContaCorrenteDAO;
import mvc.model.MeusAtivos;
import mvc.model.MeusAtivosDAO;
import mvc.model.MovimentaContaDAO;
import mvc.model.Ordem;

/**
 *
 * @author Stheffany
 */
public class GUI {
    
    public ContaCorrente criarCliente() throws SQLException{
        Cliente c = new Cliente();
        ClienteDAO cdao = new ClienteDAO();
        ContaCorrente conta = new ContaCorrente();
        ContaCorrenteDAO ccdao = new ContaCorrenteDAO();
        
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
        
        long i = cdao.inserir(c);
        conta.setSaldo(500000); // ao criar uma nova conta a bolsa ganha 500 mil
        conta.setDataCriacao(LocalDate.now());
        conta.setDataModificacao(LocalDate.now());
        conta.setC(c);
        ccdao.inserir(conta, i);
        
        // vinculando o cliente com a conta
        conta.setC(c);
        conta.setSaldo(20000);
        conta.setDataCriacao(LocalDate.now());
        conta.setDataModificacao(LocalDate.now());

        return conta;
    }
    
    //tela só aparece p administrador
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
    
    public Ordem criarOrdemVenda(AtivoDAO ativos, ContaCorrenteDAO contas){
        Ordem o = new Ordem();
       
        do{
            String id = JOptionPane.showInputDialog(null, "Informe o id da sua conta: ");
            long idConta = Long.parseLong(id);
            o.setConta(contas.buscaPorIdCliente(idConta));
            
            if(o.getConta() == null){
                JOptionPane.showMessageDialog(null, "ID Inválido", "Erro", 0);
            }
            
        }while(o.getConta() == null);
        
        o.setTipoOrdem("Venda");
        o.setEstadoOrdem(" ");
        
        do{
            String ticker = JOptionPane.showInputDialog(null, "Informe o ticker do ativo: ");
            o.setTicker(ativos.buscaPorTicker(ticker));
            
            if(o.getTicker() == null){
                JOptionPane.showMessageDialog(null, "Ticker Inválido", "Erro", 0);
            }
            
        }while(o.getTicker() == null);
        
        String qtd = JOptionPane.showInputDialog(null, "Informe a quantidade de ativos: ");
        int qtd1 = Integer.parseInt(qtd);
        o.setQuantidade(qtd1);

        double valor = 10; // valor padrão dos ativos
        o.setValor(valor);
        o.setValorTotal(qtd1 * valor);

        o.setDataCriacao(LocalDate.now());
        o.setDataModificacao(LocalDate.now());
        
        return o;
    }
    
    public Ordem criarOrdemCompra(AtivoDAO ativos, ContaCorrenteDAO contas){
        Ordem o = new Ordem();
       
        do{
            String id = JOptionPane.showInputDialog(null, "Informe o id da sua conta: ");
            long idConta = Long.parseLong(id);
            o.setConta(contas.buscaPorId(idConta));
            
            if(o.getConta() == null){
                JOptionPane.showMessageDialog(null, "ID Inválido", "Erro", 0);
            }
            
        }while(o.getConta() == null);

        o.setTipoOrdem("Compra");
        o.setEstadoOrdem(" ");
        
        do{
            String ticker = JOptionPane.showInputDialog(null, "Informe o ticker do ativo: ");
            o.setTicker(ativos.buscaPorTicker(ticker));
            
            if(o.getTicker() == null){
                JOptionPane.showMessageDialog(null, "Ticker Inválido", "Erro", 0);
            }
            
        }while(o.getTicker() == null);

        String qtd = JOptionPane.showInputDialog(null, "Informe a quantidade de ativos: ");
        int qtd1 = Integer.parseInt(qtd);
        o.setQuantidade(qtd1);
        
        
        double valor = 10; // valor padrão
        o.setValor(valor);
        o.setValorTotal(qtd1 * valor);

        o.setDataCriacao(LocalDate.now());
        o.setDataModificacao(LocalDate.now());
        
        return o;
    }
    
    public void criarOrdem0(AtivoDAO ativos, ContaCorrenteDAO contas, MeusAtivosDAO meusAtivos, MovimentaContaDAO movimenta) throws SQLException{
        Ativo a;
        ContaCorrente c;
        MeusAtivos meu = new MeusAtivos();
        long idConta;
        
        do{
            String id = JOptionPane.showInputDialog(null, "Informe o id da sua conta: ");
            idConta = Long.parseLong(id);
            c = (contas.buscaPorId(idConta));
            
            if(c == null){
                JOptionPane.showMessageDialog(null, "ID Inválido", "Erro", 0);
            }
            
        }while(c == null);

        
        do{
            String ticker = JOptionPane.showInputDialog(null, "Informe o ticker do ativo: ");
            a = (ativos.buscaPorTicker(ticker));
            
            if(a == null){
                JOptionPane.showMessageDialog(null, "Ticker Inválido", "Erro", 0);
            }
            
        }while(a == null);
        
        if(a.getTotalAtivos() == 0){
            JOptionPane.showMessageDialog(null, "O ticker não possui ativos a venda", "Erro", 0);
        }else{
            String qtd = JOptionPane.showInputDialog(null, "Informe a quantidade de ativos: ");
            int qtd1 = Integer.parseInt(qtd);
            if(a.getTotalAtivos() >= qtd1){
                meu.setAtivo(a);
                meu.setConta(c);
                meu.setCotacao(10);
                meu.setQtdAtivos(qtd1);
                double valor = 10; // valor padrão
                meu.setValorPago(valor);
                meu.setTotalDinheiroAtivos(qtd1 * valor);
                meusAtivos.adicionaMeusAtivos(meu);
                contas.transfere(contas, valor * qtd1, c.getId(), 1);
                movimenta.novaMovimentacao("Débito", "Negociação de Ativos", meu.getTotalDinheiroAtivos(), LocalDate.now(), LocalDate.now(), idConta);
                
            }else{
                meu.setAtivo(a);
                meu.setConta(c);
                meu.setCotacao(10);
                meu.setQtdAtivos(a.getTotalAtivos());
                double valor = 10; // valor padrão
                meu.setValorPago(valor);
                meu.setTotalDinheiroAtivos(a.getTotalAtivos() * valor);
                meusAtivos.adicionaMeusAtivos(meu);
                
                contas.transfere(contas, a.getTotalAtivos()* valor, c.getId(), 1);
                movimenta.novaMovimentacao("Débito", "Negociação de Ativos", meu.getTotalDinheiroAtivos(), LocalDate.now(), LocalDate.now(), idConta);
            }
            
        }

    }
    
    public int menuInicial(){
        
       StringBuilder builder = new StringBuilder("");
       
       builder.append("SEJA BEM VINDO AO HOME BROKER\n\n");
       builder.append("\n1- Fazer cadastro na plataforma");
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
        builder.append("\n7- Comprar Ativos");
        builder.append("\n8- Vender Ativos");
        builder.append("\n9- Meus Ativos");
        builder.append("\n10- Editar conta");
        builder.append("\n11 - Mostrar data");
        builder.append("\n12 - Mostrar última negociação");
        builder.append("\n13 - Mostrar book de ofertas");
        builder.append("\n14 - Ordem 0");
        builder.append("\n0- Sair");
        
        String op = JOptionPane.showInputDialog(null, builder.toString());
        
        return Integer.parseInt(op);
    }
    
    public int menuADM(){
        
        StringBuilder builder = new StringBuilder("");
        builder.append("\n1- Pagar dividendos");
        builder.append("\n2- Cadastrar ativo");
        builder.append("\n3- Editar ativo");
        builder.append("\n4- Executar ordens");
        builder.append("\n5- Mostrar data");
        builder.append("\n6- Passar o tempo");
        builder.append("\n7- Mostrar Ordens");
        builder.append("\n0- Sair");
        
        String op = JOptionPane.showInputDialog(null, builder.toString());
        
        return Integer.parseInt(op);
    }
    
    public double perguntarValor(){
        String valor = JOptionPane.showInputDialog(null, "Informe o valor: ");
        
        return Double.parseDouble(valor);
    }
    
    public long perguntarId(){
        String idTransfere = JOptionPane.showInputDialog(null, "Informe o ID: ");
        
        return Long.parseLong(idTransfere);
    }
    
    public String perguntarNome(){
        return JOptionPane.showInputDialog(null, "Informe o nome: ");
    }
}
