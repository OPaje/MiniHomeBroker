/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.model;

import java.time.LocalDate;
import javax.swing.JOptionPane;

/**
 *
 * @author jeanc
 */
public class OrdemDAO {
    Ordem[] ordens = new Ordem[10];
    
    public OrdemDAO(AtivoDAO ativos, ContaCorrenteDAO contas){
        
        Ordem o1 = new Ordem();
        o1.setConta(contas.buscaPorId(1));
        o1.setEstadoOrdem("Venda");
        o1.setTicker(ativos.buscaPorTicker("NTCO3"));
        o1.setQuantidade(20);
        o1.setValor(10);
        o1.setValorTotal(o1.getValor() * o1.getQuantidade());
        o1.setDataCriacao(LocalDate.now());
        o1.setDataModificacao(LocalDate.now());
        this.adiciona(o1);
        
        Ordem o2 = new Ordem();
        o2.setConta(contas.buscaPorId(1));
        o2.setEstadoOrdem("Compra");
        o2.setTicker(ativos.buscaPorTicker("NTCO3"));
        o2.setQuantidade(20);
        o2.setValor(10);
        o2.setValorTotal(o1.getValor() * o1.getQuantidade());
        o2.setDataCriacao(LocalDate.now());
        o2.setDataModificacao(LocalDate.now());
        this.adiciona(o2);
    }
    
    public boolean adiciona(Ordem o) {
        int proximaPosicaoLivre = this.proximaPosicaoLivre();
        if (proximaPosicaoLivre != -1) {
            ordens[proximaPosicaoLivre] = o;
            return true;
        } else {
            return false;
        }
    }
    
    public void mostrarTodos() {
        boolean temOrdem = false;
        StringBuilder builder = new StringBuilder("");
        
        for (Ordem o : ordens) {
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
        for (int i = 0; i < ordens.length; i++) {
            if (ordens[i] == null) {
                return i;
            }

        }
        return -1;

    }
}
