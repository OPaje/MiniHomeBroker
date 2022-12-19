/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.control;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
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
    ContaCorrenteDAO contaCorrenteDAO = new ContaCorrenteDAO(); 
    OrdemDAO ordemDAO = new OrdemDAO();
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
                                        ativoDAO.adicionaAtivo(a);
                                      break;
                                    case 3:
                                        List<Ativo> ativos = null;
                                        try {
                                            ativos = ativoDAO.lista(null);
                                            JOptionPane.showMessageDialog(null, ativos.toString(), "Ativos", 0);

                                            String pegaId = JOptionPane.showInputDialog(null, "Informe o ID do ativo: ");
                                            long idAtivo = Long.parseLong(pegaId);

                                            String novoNome = JOptionPane.showInputDialog(null, "Informe o novo nome da empresa: ");

                                            ativoDAO.altera(idAtivo, novoNome);
                                            
                                        } catch (SQLException e) {
                                            JOptionPane.showMessageDialog(null, "Não foi possível fazer alterações no ativo", "Erro", JOptionPane.INFORMATION_MESSAGE);
                                        }
                                        
                                        
                                        break;
                                    
                                    case 4:    
                                        try {
                                            ordemExecucaoDAO.executarOrdem(ordemDAO, contaCorrenteDAO, movimentaContaDAO);
                                            meusAtivosDAO.organizaMeusAtivos(ordemExecucaoDAO);
                                        
                                        } catch (SQLException e) {
                                            JOptionPane.showMessageDialog(null, "Não foi possível executar as ordens", "Erro", JOptionPane.INFORMATION_MESSAGE);
                                        }
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
                                        try {
                                            ordemDAO.mostrarTodos();
                                        
                                        } catch (SQLException e) {
                                            JOptionPane.showMessageDialog(null, "Não foi possível mostrar as ordens", "Erro", JOptionPane.INFORMATION_MESSAGE);
                                        }
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
                                        JOptionPane.showMessageDialog(null,contaCorrenteDAO.gerarExtrato(conta.getId(), movimentaContaDAO, valorTotalAtivos) , "Extrato", JOptionPane.INFORMATION_MESSAGE);      
                                        break;
                                    
                                    case 6:
                                        List<Ativo> ativos = null;
                                        try {
                                            ativos = ativoDAO.lista(null);
                                            JOptionPane.showMessageDialog(null, ativos.toString(), "Ativos", 0);
                                         
                                        } catch (SQLException e) {
                                            JOptionPane.showMessageDialog(null, "Não foi possível carregar a lista de ativos", "Erro", JOptionPane.INFORMATION_MESSAGE);
                                        }
                                        break;
                                    
                                    case 7:
                                        try {
                                        ordemDAO.adicionaOrdem(gui.criarOrdemCompra(ativoDAO, contaCorrenteDAO));

                                    } catch (SQLException e) {
                                        JOptionPane.showMessageDialog(null, "Ocorreu um erro ao adcionar sua ordem", "Erro", JOptionPane.INFORMATION_MESSAGE);
                                    }
                                    break;
                                        
                                    case 8:
                                        try {
                                            ordemDAO.adicionaOrdem(gui.criarOrdemVenda(ativoDAO, contaCorrenteDAO));                                      
                                        } catch (SQLException e) {
                                            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao adcionar sua ordem", "Erro", JOptionPane.INFORMATION_MESSAGE);
                                        }
                                        break;
                                        
                                    case 9:
                                        int qtdTotalAtivos = 0;
                                        StringBuilder builder = new StringBuilder("");
                                        List<MeusAtivos> meus = meusAtivosDAO.buscaPorID(conta.getId());
                                        
                                        for (MeusAtivos a : meus) {
                                            qtdTotalAtivos += a.getQtdAtivos() ;
                                            valorTotalAtivos += a.getTotalDinheiroAtivos();
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
                                        try {
                                            ordemExecucaoDAO.mostraUltimaNegociacao();  
                                        } catch (ArrayIndexOutOfBoundsException e) {
                                            JOptionPane.showMessageDialog(null, "O ativo ainda não possui negociações ", "Erro", JOptionPane.INFORMATION_MESSAGE);
                                        }
                                        break;
                                        
                                    case 13:
                                        try {
                                            JOptionPane.showMessageDialog(null, ordemDAO.bookOfertas(gui.perguntarId()), "Book de Ofertas", JOptionPane.INFORMATION_MESSAGE);

                                        } catch (SQLException e) {
                                            JOptionPane.showMessageDialog(null, "Não foi possível trazer o book de ofertas", "Erro", JOptionPane.INFORMATION_MESSAGE);
                                        }
                                        
                                        break;
                                        
                                    case 14:
                                        try {
                                            gui.criarOrdem0(ativoDAO, contaCorrenteDAO, meusAtivosDAO, movimentaContaDAO);  
                                        } catch (SQLException e) {
                                            JOptionPane.showMessageDialog(null, "Não possível registrar sua ordem", "Erro", JOptionPane.INFORMATION_MESSAGE);
                                        }
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




