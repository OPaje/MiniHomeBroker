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
    private Ordem[] ordens = new Ordem[10];

    public Ordem[] getOrdens() {
        return ordens;
    }   
    
    public OrdemDAO(AtivoDAO ativos, ContaCorrenteDAO contas){
        
        Ordem o1 = new Ordem();
        o1.setConta(contas.buscaPorID(2));
        o1.setTipoOrdem("Venda");
        o1.setTicker(ativos.buscaPorTicker("NTCO3"));
        o1.setQuantidade(40);
        o1.setValor(10);
        o1.setValorTotal(o1.getValor() * o1.getQuantidade());
        o1.setDataCriacao(LocalDate.now());
        o1.setDataModificacao(LocalDate.now());
        this.adiciona(o1);
        
        Ordem o2 = new Ordem();
        o2.setConta(contas.buscaPorID(3));
        o2.setTipoOrdem("Compra");
        o2.setTicker(ativos.buscaPorTicker("NTCO3"));
        o2.setQuantidade(20);
        o2.setValor(10);
        o2.setValorTotal(o2.getValor() * o2.getQuantidade());
        o2.setDataCriacao(LocalDate.now());
        o2.setDataModificacao(LocalDate.now());
        this.adiciona(o2);
        
        Ordem o3 = new Ordem();
        o3.setConta(contas.buscaPorID(3));
        o3.setTipoOrdem("Compra");
        o3.setTicker(ativos.buscaPorTicker("NTCO3"));
        o3.setQuantidade(30);
        o3.setValor(10);
        o3.setValorTotal(o3.getValor() * o3.getQuantidade());
        o3.setDataCriacao(LocalDate.now());
        o3.setDataModificacao(LocalDate.now());
        this.adiciona(o3);
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
        
        if (!temOrdem) {
            JOptionPane.showMessageDialog(null,"Não existe ordem feita","Ordens",JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null,builder.toString(),"Ordens",JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public String bookOfertas(Ativo ativo){
        boolean temOrdem = false;
        StringBuilder builder = new StringBuilder("");
        
        for (Ordem o : ordens) {
            if (o != null && o.getTicker().equals(ativo) && !"Ordem 0".equals(o.getTipoOrdem())) {
                builder.append("Ativo: ").append(o.getTicker().getTicker()).append(" Valor: ").append(o.getValor()).append(" Tipo: ").append(o.getTipoOrdem()).append("\n\n");
                temOrdem = true;
            }
        }
                
        if (!temOrdem) {
            return "Não existe ordem feita";
        }else{
            return builder.toString();
        }
    }
    
    public boolean removerPorId(long id) {
        for (int i = 0; i < ordens.length; i++) {
            if (ordens[i] != null && ordens[i].getId() == id) {
                ordens[i] = null;
                return true;
            }
        }
        return false;

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
