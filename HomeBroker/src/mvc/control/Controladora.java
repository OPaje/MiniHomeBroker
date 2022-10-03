/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.control;

import javax.swing.JOptionPane;
import mvc.model.Ativo;
import mvc.model.AtivoDAO;
import mvc.model.Cliente;
import mvc.model.ClienteDAO;
import mvc.model.ContaCorrente;
import mvc.model.ContaCorrenteDAO;
import mvc.view.GUI;

/**
 *
 * @author jeanc
 */
public class Controladora {
    ClienteDAO clienteDAO = new ClienteDAO();
    AtivoDAO ativoDAO = new AtivoDAO();
    ContaCorrenteDAO contaCorrenteDAO = new ContaCorrenteDAO(clienteDAO.getClientes()); // passando como parãmetro o vetor com os clientes
    GUI gui = new GUI();
    
    public static void main(String[] args) {
        new Controladora();
    }
    
    public Controladora() {
        
        int opcao = 0;
        do{
            opcao = gui.Menu();
            switch(opcao){
                case 1:
                    clienteDAO.mostrarTodos();
                break;
                
                case 8:
                    ativoDAO.mostrarTodos();
                    break;
                
                case 11: 
                    contaCorrenteDAO.mostrarTodos();
                break;
                
                case 12:
                    ativoDAO.mostrarTodos();
                    String pegaId = JOptionPane.showInputDialog(null, "Informe o ID do ativo: ");
                    long id = Long.parseLong(pegaId);
                   
                    String novoTicker = JOptionPane.showInputDialog(null, "Informe o novo ticker do ativo: ");
                    
                    String novoNome = JOptionPane.showInputDialog(null, "Informe o novo nome da empresa: ");
                   
                    if(ativoDAO.alterarTicker(id, novoTicker) && ativoDAO.alterarNomeEmpresa(novoNome, id)){
                        JOptionPane.showMessageDialog(null,"Ticker alterado","Alterar Ticker",JOptionPane.INFORMATION_MESSAGE);
                        
                    }else{
                        JOptionPane.showMessageDialog(null,"Ticker não alterado","Alterar Ticker",JOptionPane.INFORMATION_MESSAGE);
                    }
                    break;
                    
                case 13:
                    ContaCorrente c = gui.criarCliente();
                    clienteDAO.adiciona(c.getC()); // adcionando também ao vetor de clientes
                    if(contaCorrenteDAO.adicionaConta(c)){
                        JOptionPane.showMessageDialog(null,"Cliente cadastrado","Cadastro de Cliente",JOptionPane.INFORMATION_MESSAGE);
                        
                    }else{
                        JOptionPane.showMessageDialog(null,"Cliente não cadastrado","Cadastro de Cliente",JOptionPane.INFORMATION_MESSAGE);
                    }
                    
                    break;
                    
                case 14:
                    Ativo a = gui.criarAtivo();
                    
                    if(ativoDAO.adicionaAtivo(a)){
                        JOptionPane.showMessageDialog(null,"Ativo cadastrado","Cadastro de Ativo",JOptionPane.INFORMATION_MESSAGE);
                        
                    }else{
                        JOptionPane.showMessageDialog(null,"Ativo não cadastrado","Cadastro de Ativo",JOptionPane.INFORMATION_MESSAGE);
                    }
                    break;
            }
        }while (opcao != 15);
    }
    
      
}




