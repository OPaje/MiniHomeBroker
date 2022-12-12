/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.control;

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
import mvc.model.OrdemDAO;
import mvc.model.OrdemExecucaoDAO;
import mvc.view.GUI;

/**
 *
 * @author jeanc
 */
public class Controladora {
    ClienteDAO clienteDAO = new ClienteDAO();
    AtivoDAO ativoDAO = new AtivoDAO();
    ContaCorrenteDAO contaCorrenteDAO = new ContaCorrenteDAO(); // passando como parâmetro o vetor com os clientes
    OrdemDAO ordemDAO = new OrdemDAO(ativoDAO, contaCorrenteDAO);
    OrdemExecucaoDAO ordemExecucaoDAO = new OrdemExecucaoDAO();
    MovimentaContaDAO movimentaContaDAO = new MovimentaContaDAO();
    MeusAtivosDAO meusAtivosDAO = new MeusAtivosDAO();
    GUI gui = new GUI();
    public LocalDate data = LocalDate.of(2022, 10, 15);
    public LocalDate dataAtualizavel = LocalDate.of(2022, 10, 15);
    
   
    public static void main(String[] args) throws SQLException {
        new Controladora();
    }
    
    public Controladora() throws SQLException {
        
        int opcao = 0;
        do {
            opcao = gui.menuInicial();
            switch (opcao) {
                case 1:
                    ContaCorrente c = gui.criarCliente();
                    if (c.getC() != null) {
                        JOptionPane.showMessageDialog(null, "Cliente cadastrado", "Cadastro de Cliente", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Cliente não cadastrado", "Cadastro de Cliente", JOptionPane.INFORMATION_MESSAGE);
                    }
                    break;
                    
                case 2:                    
                    String login = JOptionPane.showInputDialog(null, "Informe o login: ");                   
                    String senha = JOptionPane.showInputDialog(null, "Informe sua senha: ");                   
                    Cliente cliente = clienteDAO.buscarPorLoginESenha(login, senha); // cliente do login
                    
                    if(cliente != null){
                        ContaCorrente conta = contaCorrenteDAO.buscaPorIdCliente(cliente.getId()); // conta do login
                        if(cliente.getTipoUsuario() == 0){
                            int escolha = 0;
                            do{
                                escolha = gui.menuADM();                                
                                switch (escolha) {
                                    case 1:
                                        //contaCorrenteDAO.pagarDividendos(contaCorrenteDAO, gui.perguntarValor(), quantidade, gui.perguntarId()); // pegar a quantidade nos meus ativos
                                        break;
                                        
                                    case 2:
                                        Ativo a = gui.criarAtivo();

                                        if (ativoDAO.adicionaAtivo(a)) {
                                            JOptionPane.showMessageDialog(null, "Ativo cadastrado", "Cadastro de Ativo", JOptionPane.INFORMATION_MESSAGE);

                                        } else {
                                            JOptionPane.showMessageDialog(null, "Ativo não cadastrado", "Cadastro de Ativo", JOptionPane.INFORMATION_MESSAGE);
                                        }
                                        break;
                                        
                                    case 3:
                                        ativoDAO.mostrarTodos();
                                        
                                        String pegaId = JOptionPane.showInputDialog(null, "Informe o ID do ativo: ");
                                        long idAtivo = Long.parseLong(pegaId);

                                        String novoTicker = JOptionPane.showInputDialog(null, "Informe o novo ticker do ativo: ");

                                        String novoNome = JOptionPane.showInputDialog(null, "Informe o novo nome da empresa: ");

                                        if (ativoDAO.alterarTicker(idAtivo, novoTicker) && ativoDAO.alterarNomeEmpresa(novoNome, idAtivo)) {
                                            JOptionPane.showMessageDialog(null, "Ticker alterado", "Alterar Ticker", JOptionPane.INFORMATION_MESSAGE);

                                        } else {
                                            JOptionPane.showMessageDialog(null, "Ticker não alterado", "Alterar Ticker", JOptionPane.INFORMATION_MESSAGE);
                                        }
                                        break;
                                    
                                    case 4:                                     
                                        ordemExecucaoDAO.executarOrdem(ordemDAO, contaCorrenteDAO, movimentaContaDAO, meusAtivosDAO, movimentaContaDAO);
                                        meusAtivosDAO.organizaMeusAtivos(ordemExecucaoDAO);
                                        break;
                                        
                                    case 5:
                                        JOptionPane.showMessageDialog(null, data, "Data", JOptionPane.INFORMATION_MESSAGE);
                                        break;
                                        
                                    case 6: 
                                        data = dataAtualizavel.plusDays(31);
                                        JOptionPane.showMessageDialog(null, data, "Data", JOptionPane.INFORMATION_MESSAGE);
                                        contaCorrenteDAO.pagarMensalidade(data, movimentaContaDAO);
                                        break;
                                        
                                    case 7: 
                                        ordemDAO.mostrarTodos();
                                        break;

                                }
                                
                            }while(escolha > 0 && escolha < 8);
                        }else{
                            int decisao = 0;
                            double valorTotalAtivos = 0;
                            do{
                                decisao = gui.menuCliente();
                                
                                switch (decisao) {
                                    case 1:
                                        contaCorrenteDAO.depositar(conta.getId(), gui.perguntarValor(), conta);
                                        break;
                                        
                                    case 2:
                                        JOptionPane.showMessageDialog(null, contaCorrenteDAO.mostraSaldo(conta.getId(), contaCorrenteDAO), "Saldo", JOptionPane.INFORMATION_MESSAGE); 
                                        break;
                                        
                                    case 3:  
                                        double valor = gui.perguntarValor();
                                        if (contaCorrenteDAO.sacar(conta.getId(), valor, conta)) {
                                            JOptionPane.showMessageDialog(null,"Saque evetuado com sucesso" , "Saque", JOptionPane.INFORMATION_MESSAGE);
                                            movimentaContaDAO.novaMovimentacao("Débito", "Saque", valor, LocalDate.now(), LocalDate.now(), conta.getId());

                                        } else {
                                            JOptionPane.showMessageDialog(null, "Não foi possível fazer o saque", "Saque", 0);
                                        }
                                        break;
                                        
                                    case 4: 
                                        double valorTransferencia = gui.perguntarValor();
                                        if(contaCorrenteDAO.transfere(contaCorrenteDAO, valorTransferencia, conta.getId(), gui.perguntarId())){
                                            JOptionPane.showMessageDialog(null,"Transferência evetuada com sucesso" , "Transferência", JOptionPane.INFORMATION_MESSAGE);
                                            movimentaContaDAO.novaMovimentacao("Débito", "Tranferência", valorTransferencia, LocalDate.now(), LocalDate.now(), conta.getId());

                                        }else{
                                            JOptionPane.showMessageDialog(null, "Não foi possível fazer a transferência", "Transferência", 0);
                                        }
                                        break;
                                        
                                    case 5:
                                        //JOptionPane.showMessageDialog(null,contaCorrenteDAO.gerarExtrato(conta.getId(), movimentaContaDAO, valorTotalAtivos) , "Extrato", JOptionPane.INFORMATION_MESSAGE);      
                                        break;
                                    
                                    case 6:
                                        ativoDAO.mostrarTodos();
                                        break;
                                    
                                    case 7:
                                        ordemDAO.adiciona(gui.criarOrdemCompra(ativoDAO, contaCorrenteDAO));
                                        break;
                                        
                                    case 8:
                                        ordemDAO.adiciona(gui.criarOrdemVenda(ativoDAO, contaCorrenteDAO));                                      
                                        break;
                                        
                                    case 9:
                                        MeusAtivos[] meus = meusAtivosDAO.getMeusAtivos();
                                        int qtdTotalAtivos = 0;
                                        StringBuilder builder = new StringBuilder("");
                                        
                                        for (int i = 0; i < meus.length; i++) {
                                            if(meus[i] != null){
                                                if(meus[i].getConta().getId() == conta.getId()){
                                                    valorTotalAtivos += meus[i].getTotalDinheiroAtivos();
                                                    qtdTotalAtivos += meus[i].getQtdAtivos();
                                                }
                                            }                                            
                                        }
                                        
                                        builder.append("\nTotal de Ativos: ").append(qtdTotalAtivos).append("\n");
                                        builder.append("Dinheiro Total em Ativos: ").append(valorTotalAtivos);
                                        
                                        JOptionPane.showMessageDialog(null, builder.toString(), "Meus Ativos", JOptionPane.INFORMATION_MESSAGE);
                                        
                                        valorTotalAtivos = 0.0;
                                        
                                        break;

                                    
                                    case 10:
                                        clienteDAO.alterarNome(cliente.getNome(), gui.perguntarNome());
                                        conta.setDataModificacao(LocalDate.now());
                                        
                                        break;
                                        
                                    case 11: 
                                        // mostra data
                                        JOptionPane.showMessageDialog(null, data, "Data", JOptionPane.INFORMATION_MESSAGE);
                                        break;
                                        
                                    case 12:
                                        ordemExecucaoDAO.mostraUltimaNegociacao();
                                        break;
                                        
                                    case 13:
                                        Ativo ativo;
                                        do{
                                            String ticker = JOptionPane.showInputDialog(null, "Informe o ticker do ativo: ");
                                            ativo = ativoDAO.buscaPorTicker(ticker);

                                            if(ativo == null){
                                                JOptionPane.showMessageDialog(null, "Ticker Inválido", "Erro", 0);
                                            }

                                        }while(ativo == null);
                                        JOptionPane.showMessageDialog(null, ordemDAO.bookOfertas(ativo), "Book de Ofertas", JOptionPane.INFORMATION_MESSAGE);
                                        
                                        break;
                                        
                                    case 14:
                                        gui.criarOrdem0(ativoDAO, contaCorrenteDAO, meusAtivosDAO, movimentaContaDAO);
                                        break;
                                }                                
                            }while(decisao > 0 && decisao < 15);                            
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Login e/ou senha inválidos", "Login", JOptionPane.INFORMATION_MESSAGE);
                    }
                    
                    break;
            }
        } while (opcao > 0 && opcao < 3);
    }
          
}




