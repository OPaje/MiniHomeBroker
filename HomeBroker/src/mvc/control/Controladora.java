/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.control;

import javax.swing.JOptionPane;
import mvc.model.Ativo;
import mvc.model.AtivoDAO;
import mvc.model.ClienteDAO;
import mvc.model.ContaCorrente;
import mvc.model.ContaCorrenteDAO;
import mvc.model.Ordem;
import mvc.model.OrdemDAO;
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
    GUI gui = new GUI();
    
    public static void main(String[] args) {
        new Controladora();
    }
    
    public Controladora() {
        
        int opcao = 0;
        do {
            opcao = gui.Menu();
            switch (opcao) {
                case 1:
                    clienteDAO.mostrarTodos();
                    break;

                case 2:
                    String id = JOptionPane.showInputDialog(null, "Informe o ID da sua conta: ");
                    long idConta = Long.parseLong(id);

                    String valor = JOptionPane.showInputDialog(null, "Informe o valor que deseja depositar: ");
                    double valorDeposito = Double.parseDouble(valor);

                    if (contaCorrenteDAO.depositar(idConta, valorDeposito, contaCorrenteDAO)) {
                        JOptionPane.showMessageDialog(null, "Depósito evetuado com sucesso", "Depósito", 0);

                    } else {
                        JOptionPane.showMessageDialog(null, "Não foi possível fazer o depósito", "Depósito", 0);
                    }
                    break;
                    
                case 3: 
                    String idSaque = JOptionPane.showInputDialog(null, "Informe o ID da sua conta: ");
                    long idSacar = Long.parseLong(idSaque);
                    
                    String valorSaque = JOptionPane.showInputDialog(null, "Informe o valor do saque: ");
                    double valorSacado = Double.parseDouble(valorSaque);
                    
                    ContaCorrente contaSaque = contaCorrenteDAO.buscaPorId(idSacar);
                    
                    if (contaCorrenteDAO.sacar(contaSaque, valorSacado)) {
                        JOptionPane.showMessageDialog(null, "Saque evetuado com sucesso", "Saque", 0);

                    } else {
                        JOptionPane.showMessageDialog(null, "Não foi possível fazer o saque", "Saque", 0);
                    }
                    
                  
                    break;

                case 4:
                    ContaCorrente[] teste;

                    teste = contaCorrenteDAO.getContaCorrente();
                    for (ContaCorrente c1 : teste) {
                        if (c1 != null) {
                            JOptionPane.showMessageDialog(null, c1.getSaldo(),
                                     "Saldo", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }

                    break;
                
                case 5: 
                    
                    String idTransfere = JOptionPane.showInputDialog(null, "Informe o ID da sua conta: ");
                    long idOrigem = Long.parseLong(idTransfere);
                    
                    String valorTransfere = JOptionPane.showInputDialog(null, "Informe o valor da transferência: ");
                    double valorTransferido = Double.parseDouble(valorTransfere);
                    
                    String idTransfere2 = JOptionPane.showInputDialog(null, "Informe o ID da conta para a qual você quer transferir esse valor: ");
                    long idDestino = Long.parseLong(idTransfere2);
                    
                    if (contaCorrenteDAO.transfere(contaCorrenteDAO, valorTransferido, idOrigem, idDestino)) {
                        JOptionPane.showMessageDialog(null, "Transferência evetuada com sucesso", "Transferência", 0);

                    } else {
                        JOptionPane.showMessageDialog(null, "Não foi possível fazer a transferência", "Transferência", 0);
                    }
                   
                    break;
                
                case 8:
                    ativoDAO.mostrarTodos();
                    break;

                case 9:
                    Ordem o = gui.criarOrdem(ativoDAO, contaCorrenteDAO);
                    if (o != null) {
                        ordemDAO.adiciona(o);

                    } else {
                        JOptionPane.showMessageDialog(null, "Compra não concluida", "Erro", 0);
                    }
                    ordemDAO.mostrarTodos();
                    break;

                case 11:
                    contaCorrenteDAO.mostrarTodos();
                    break;

                case 12:
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

                case 13:
                    ContaCorrente c = gui.criarCliente();
                    clienteDAO.adiciona(c.getC()); // adcionando também ao vetor de clientes
                    if (contaCorrenteDAO.adicionaConta(c)) {
                        JOptionPane.showMessageDialog(null, "Cliente cadastrado", "Cadastro de Cliente", JOptionPane.INFORMATION_MESSAGE);

                    } else {
                        JOptionPane.showMessageDialog(null, "Cliente não cadastrado", "Cadastro de Cliente", JOptionPane.INFORMATION_MESSAGE);
                    }

                    break;

                case 14:
                    Ativo a = gui.criarAtivo();

                    if (ativoDAO.adicionaAtivo(a)) {
                        JOptionPane.showMessageDialog(null, "Ativo cadastrado", "Cadastro de Ativo", JOptionPane.INFORMATION_MESSAGE);

                    } else {
                        JOptionPane.showMessageDialog(null, "Ativo não cadastrado", "Cadastro de Ativo", JOptionPane.INFORMATION_MESSAGE);
                    }
                    break;
            }
        } while (opcao != 15);
    }
    
      
}




