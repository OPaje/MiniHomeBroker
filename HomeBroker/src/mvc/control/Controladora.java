/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.control;

import mvc.model.AtivoDAO;
import mvc.model.ClienteDAO;
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
                case 11: 
                    contaCorrenteDAO.mostrarTodos();
                break;
            }
        }while (opcao != 12);
    }
    
      
}




