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
        Cliente[] c = new Cliente[5];
        ContaCorrente[] conta = new ContaCorrente[5];
        int opcao = 0;
        String menuOpcao = "1) Criar clientes\n"
                + "2) Mostrar clientes\n"
                + "3) Depósito\n"
                + "4) Saque\n"
                + "5) Saldo\n"
                + "6) Transferência\n"
                + "7) Pagamento\n"
                + "8) Extrato\n";
        do {
            switch (opcao) {

                case 1: {
                    c[0] = new Cliente();
                    c[1] = new Cliente();
                    c[2] = new Cliente();
                    c[3] = new Cliente();
                    c[4] = new Cliente();
                    c[0].nome = "Administrador";
                    c[1].nome = "Joao";
                    c[2].nome = "Pedro";
                    c[3].nome = "Maria";
                    c[4].nome = "Jean";
                    c[0].senha = "adm";
                    c[1].senha = "abcd";
                    c[2].senha = "qwer";
                    c[3].senha = "dcba";
                    c[4].senha = "asdf";
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
                    conta[4] = new ContaCorrente();
                    conta[4].c = new Cliente();
                    conta[4].c = c[4];
                    conta[0].saldo = 0;
                    conta[1].saldo = 1000;
                    conta[2].saldo = 1000;
                    conta[3].saldo = 1000;
                    conta[4].saldo = 0;
                    break;
                }

                case 2: {
                    JOptionPane.showMessageDialog(null, "Nome: " + c[0].nome + "\nSaldo: $" + conta[0].saldo
                            + "\n\nNome: " + c[1].nome + "\nSaldo: $" + conta[1].saldo
                            + "\n\nNome: " + c[2].nome + "\nSaldo: $" + conta[2].saldo
                            + "\n\nNome: " + c[3].nome + "\nSaldo: $" + conta[3].saldo
                            + "\n\nNome: " + c[4].nome + "\nSaldo: $" + conta[4].saldo);
                    break;
                }

                case 3: {
                    String senha = JOptionPane.showInputDialog(null, "Insira sua senha para confirmar a operação: ");
                    int index = 0;
                    int flag = 0;
                    for (int i = 0; i < c.length; i++) {
                        if (senha.equals(c[i].senha)) {
                            index = i;
                            flag = 1;
                        }
                    }

                    if (flag == 0) {
                        JOptionPane.showMessageDialog(null, "Senha inválida!");
                    } else {
                        String Deposito = JOptionPane.showInputDialog(null, "Insira o valor que deseja depositar: ");
                        int vDeposito = Integer.parseInt(Deposito);
                        conta[index].depositar(vDeposito);
                    }
                    break;
                }

                case 4: {
                    String senha = JOptionPane.showInputDialog(null, "Insira sua senha para confirmar a operação: ");
                    int index = 0;
                    int flag = 0;
                    for (int i = 0; i < c.length; i++) {
                        if (senha.equals(c[i].senha)) {
                            index = i;
                            flag = 1;
                        }
                    }

                    if (flag == 0) {
                        JOptionPane.showMessageDialog(null, "Senha inválida!");
                    } else {
                        String Deposito = JOptionPane.showInputDialog(null, "Insira o valor que deseja sacar: ");
                        int vDeposito = Integer.parseInt(Deposito);
                        conta[index].sacar(vDeposito);
                    }
                    break;
                }

                case 5: {
                    String senha = JOptionPane.showInputDialog(null, "Insira sua senha para confirmar a operação: ");
                    int index = 0;
                    int flag = 0;
                    for (int i = 0; i < c.length; i++) {
                        if (senha.equals(c[i].senha)) {
                            index = i;
                            flag = 1;
                        }
                    }

                    if (flag == 0) {
                        JOptionPane.showMessageDialog(null, "Senha inválida!");
                    } else {
                        JOptionPane.showMessageDialog(null, conta[index].mostraSaldo());
                    }
                    break;
                }

                case 6: {
                    String senha = JOptionPane.showInputDialog(null, "Insira sua senha para confirmar a operação: ");
                    int index = 0;
                    int flag = 0;
                    for (int i = 0; i < c.length; i++) {
                        if (senha.equals(c[i].senha)) {
                            index = i;
                            flag = 1;
                        }
                    }
                    int index2=0;
                    if (flag == 0) {
                        JOptionPane.showMessageDialog(null, "Senha inválida!");
                    } else {
                        String cDestino = JOptionPane.showInputDialog(null, "Informe a conta para qual deseja transferir: ");//nome
                        String sTransferencia = JOptionPane.showInputDialog(null, "Informe o valor que deseja transferir: ");
                        int vTransferencia = Integer.parseInt(sTransferencia);
                        for (int i = 0; i < c.length; i++) {
                        if (cDestino.equals(c[i].nome)) {
                            index2 = i;
                        }
                    }
                        conta[index].transfere(conta[index2], vTransferencia);
                    }                    
                    break;
                }
                case 7: {
                    String senha = JOptionPane.showInputDialog(null, "Insira sua senha para confirmar a operação: ");
                    int index = 0;
                    int flag = 0;
                    for (int i = 0; i < c.length; i++) {
                        if (senha.equals(c[i].senha)) {
                            index = i;
                            flag = 1;
                        }
                    }

                    if (flag == 0) {
                        JOptionPane.showMessageDialog(null, "Senha inválida!");
                    } else {
                        conta[index].transfere(conta[0], 20);
                    }
                    break;
                }
                case 8: {
                    break;
                }
            }
            opcao = Integer.parseInt(JOptionPane.showInputDialog(menuOpcao));
        } while (opcao != 9);

    }

}
