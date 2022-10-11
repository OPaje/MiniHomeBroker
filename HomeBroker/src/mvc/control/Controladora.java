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
                    
                    Cliente cliente = clienteDAO.buscarPorLoginESenha(login, senha);
                    
                    if(cliente != null){
                        if(cliente.getTipoUsuario() == 0){
                            gui.menuADM();
                            // fazer o switch case
                        }else{
                            gui.menuCliente();
                            // fazer o switch case
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Login e/ou senha inválidos", "Login", JOptionPane.INFORMATION_MESSAGE);
                    }
                    
                    break;
            }
        } while (opcao > 0 && opcao < 3);
    }
    
      
}




