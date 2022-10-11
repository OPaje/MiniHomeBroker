/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.model;

import javax.swing.JOptionPane;

/**
 *
 * @author jeanc
 */
public class OrdemExecucaoDAO {
    OrdemExecucao[] ordensExecucao = new OrdemExecucao[10];
    
    
    
    public boolean adiciona(OrdemExecucao o) {
        int proximaPosicaoLivre = this.proximaPosicaoLivre();
        if (proximaPosicaoLivre != -1) {
            ordensExecucao[proximaPosicaoLivre] = o;
            return true;
        } else {
            return false;
        }
    }
    
    public void mostrarTodos() {
        boolean temOrdem = false;
        StringBuilder builder = new StringBuilder("");
        
        for (OrdemExecucao o : ordensExecucao) {
            if (o != null) {
                builder.append(o.toString());
                temOrdem = true;
            }
        }
        
        JOptionPane.showMessageDialog(null,builder.toString(),"Ordens",JOptionPane.INFORMATION_MESSAGE);
        
        if (!temOrdem) {
            System.out.println("Não existe ordem feita");
        }
    }
    
    private int proximaPosicaoLivre() {
        for (int i = 0; i < ordensExecucao.length; i++) {
            if (ordensExecucao[i] == null) {
                return i;
            }

        }
        return -1;

    }
}
