/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.control;

import java.time.LocalDate;
import javax.swing.JOptionPane;
import mvc.model.Ativo;
import mvc.model.AtivoDAO;
import mvc.model.Cliente;
import mvc.model.ClienteDAO;
import mvc.model.ContaCorrente;
import mvc.model.ContaCorrenteDAO;
import mvc.model.Ordem;
import mvc.model.OrdemDAO;
import mvc.model.OrdemExecucao;
import mvc.model.OrdemExecucaoDAO;
import mvc.view.GUI;

/**
 *
 * @author jeanc
 */
public class Controladora {
    ClienteDAO clienteDAO = new ClienteDAO();
    AtivoDAO ativoDAO = new AtivoDAO();
    ContaCorrenteDAO contaCorrenteDAO = new ContaCorrenteDAO(clienteDAO.getClientes()); // passando como parãmetro o vetor com os clientes
    OrdemDAO ordemDAO = new OrdemDAO(ativoDAO, contaCorrenteDAO);
    OrdemExecucaoDAO ordemExecucaoDAO = new OrdemExecucaoDAO();
    GUI gui = new GUI();
    
    public static void main(String[] args) {
        new Controladora();
    }
    
    public Controladora() {
        
        int opcao = 0;
        do {
            opcao = gui.menuInicial();
            switch (opcao) {
                case 1:
                    ContaCorrente c = gui.criarCliente();
                    clienteDAO.adiciona(c.getC()); // adcionando também ao vetor de clientes
                    if (contaCorrenteDAO.adicionaConta(c)) {
                        JOptionPane.showMessageDialog(null, "Cliente cadastrado", "Cadastro de Cliente", JOptionPane.INFORMATION_MESSAGE);

                    } else {
                        JOptionPane.showMessageDialog(null, "Cliente não cadastrado", "Cadastro de Cliente", JOptionPane.INFORMATION_MESSAGE);
                    }
                    break;
                    
                case 2:
                    
                    String login = JOptionPane.showInputDialog(null, "Informe o login: ");
                    
                    String senha = JOptionPane.showInputDialog(null, "Informe sua senha: ");
                    
                    Cliente cliente = clienteDAO.buscarPorLoginESenha(login, senha); // cliente do login
                    ContaCorrente conta = contaCorrenteDAO.buscaPorId(cliente.getId()); // conta do login
                    
                    if(cliente != null){
                        if(cliente.getTipoUsuario() == 0){
                            int escolha = 0;
                            do{
                                escolha = gui.menuADM();
                                
                                switch (escolha) {
                                    case 1:
                                        System.out.println("Pagar dividendos");
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
                                        ordemExecucaoDAO.executarOrdem(ordemDAO, contaCorrenteDAO);
                                        OrdemExecucao[] ordensExecutadas = ordemExecucaoDAO.getOrdensExecucao();
                                        
                                        JOptionPane.showMessageDialog(null, ordensExecutadas[0].toString(), "Ordens Executadas", JOptionPane.INFORMATION_MESSAGE);
                                        JOptionPane.showMessageDialog(null, ordensExecutadas[1].toString(), "Ordens Executadas", JOptionPane.INFORMATION_MESSAGE);
                                  
                                        break;


                                        
                                    case 5:
                                        System.out.println("Passar o tempo");
                                        break;

                                }
                                
                            }while(escolha > 0 && escolha < 6);
                        }else{
                            int decisao = 0;
                            do{
                                decisao = gui.menuCliente();
                                
                                switch (decisao) {
                                    case 1:
                                        contaCorrenteDAO.depositar(conta.getId(), gui.perguntarValor(), contaCorrenteDAO);
                                        
                                        break;
                                        
                                    case 2:
                                        JOptionPane.showMessageDialog(null, contaCorrenteDAO.mostraSaldo(conta.getId(), contaCorrenteDAO), "Saldo", JOptionPane.INFORMATION_MESSAGE);
  
                                        break;
                                        
                                    case 3:
                                        //ContaCorrente contaSaque = contaCorrenteDAO.buscaPorId(cliente.getId());
                    
                                        if (contaCorrenteDAO.sacar(conta, gui.perguntarValor())) {
                                            JOptionPane.showMessageDialog(null,"Saque evetuado com sucesso" , "Saque", JOptionPane.INFORMATION_MESSAGE);

                                        } else {
                                            JOptionPane.showMessageDialog(null, "Não foi possível fazer o saque", "Saque", 0);
                                        }

                                        break;
                                        
                                    case 4: 
                                        if(contaCorrenteDAO.transfere(contaCorrenteDAO, gui.perguntarValor(), conta.getId(), gui.perguntarId())){
                                            JOptionPane.showMessageDialog(null,"Transferência evetuada com sucesso" , "Transferência", JOptionPane.INFORMATION_MESSAGE);

                                        }else{
                                            JOptionPane.showMessageDialog(null, "Não foi possível fazer a transferência", "Transferência", 0);
                                        }

                                        break;
                                        
                                    case 5:
                                        // extrato
                                        break;
                                    
                                    case 6:
                                        ativoDAO.mostrarTodos();
                                        break;
                                    
                                    case 7:
                                        // comprar ativos
                                        break;
                                        
                                    case 8:
                                        // vender ativos
                                        break;
                                        
                                    case 9:
                                        // meus ativos
                                        break;
                                    
                                    case 10:
                                        clienteDAO.alterarNome(cliente.getNome(), gui.perguntarNome());
                                        conta.setDataModificacao(LocalDate.now());
                                        
                                        break;
                                }
                                
                            }while(decisao > 0 && decisao < 11);
                            
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Login e/ou senha inválidos", "Login", JOptionPane.INFORMATION_MESSAGE);
                    }
                    
                    break;
            }
        } while (opcao > 0 && opcao < 3);
    }
    
      
}




