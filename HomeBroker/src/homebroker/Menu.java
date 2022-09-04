/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package homebroker;
import javax.swing.JOptionPane;

/**
 *
 * @author jeanc
 */
public class Menu {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {      
        Cliente [] c = new Cliente[4];
        ContaCorrente [] conta = new ContaCorrente[4];
        int opcao = 0;
        String menuOpcao = "1) Criar clientes\n"
                         + "2) Mostrar clientes\n"
                         + "3) Depósito\n"
                         + "4) Saque\n"
                         + "5) Saldo\n"
                         + "6) Pagamento\n"
                         + "7) Transferência\n"
                         + "8) Extrato\n";
    do{  
        switch(opcao){
            
            case 1:
            {
                c[0] = new Cliente();
                c[1] = new Cliente();
                c[2] = new Cliente();
                c[3] = new Cliente();
                c[0].nome = "Administrador";                
                c[1].nome = "Joao";
                c[2].nome = "Pedro";
                c[3].nome = "Maria";
                conta[0] = new ContaCorrente();
                conta[0].c = new Cliente();
                conta[0].c = c[0];
                conta[1] = new ContaCorrente();
                conta[1].c = new Cliente();
                conta[1].c = c[1];
                conta[2] = new ContaCorrente();
                conta[2].c = new Cliente();
                conta[2].c = c[2];
                conta[3] = new ContaCorrente();
                conta[3].c = new Cliente();
                conta[3].c = c[3];
                conta[0].saldo = 0;                
                conta[1].saldo = 1000;
                conta[2].saldo = 1000;
                conta[3].saldo = 1000;
                break;
            }

            case 2:
            {                    
                JOptionPane.showMessageDialog(null, "Nome: " + c[0].nome + "\nSaldo: " + conta[0].saldo
                        + "\n\nNome: " + c[1].nome + "\nSaldo: " + conta[1].saldo
                        + "\n\nNome: " + c[2].nome + "\nSaldo: " + conta[2].saldo
                        + "\n\nNome: " + c[3].nome + "\nSaldo: " + conta[3].saldo);                                    
                break;
            }
            
            case 3:
            {
                break; 
            }
            
            case 4:
            {
                break;
            }
            
            case 5:
            {
                break;
            }
              
            case 6:
            {
                break;
            }
            case 7:
            {
                break;
            }
            case 8:
            {
                break;
            }
        }
        opcao = Integer.parseInt(JOptionPane.showInputDialog(menuOpcao));
    }while(opcao != 9);
    }
    
}
