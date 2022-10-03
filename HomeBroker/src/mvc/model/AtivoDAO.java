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
public class AtivoDAO {
    private Ativo[] ativos = new Ativo[5];

    public Ativo[] getAtivos() {
        return ativos;
    }
    
    public AtivoDAO(){
        Ativo a1 = new Ativo();
        a1.setTicker("NTCO3");
        a1.setNomeEmpresa("Grupo Natura");
        a1.setPrecoInicial(10);
        a1.setTotalAtivos(1000);
        a1.setDataCriacao(LocalDate.now());
        a1.setDataModficacao(LocalDate.now());
        this.adicionaAtivo(a1);
        
        Ativo a2 = new Ativo();
        a2.setTicker("PETR4");
        a2.setNomeEmpresa("Petrobras");
        a2.setPrecoInicial(10);
        a2.setTotalAtivos(1000);
        a2.setDataCriacao(LocalDate.now());
        a2.setDataModficacao(LocalDate.now());
        this.adicionaAtivo(a2);
        
        Ativo a3 = new Ativo();
        a3.setTicker("ABEV3");
        a3.setNomeEmpresa("Ambev");
        a3.setPrecoInicial(10);
        a3.setTotalAtivos(1000);
        a3.setDataCriacao(LocalDate.now());
        a3.setDataModficacao(LocalDate.now());
        this.adicionaAtivo(a3);
        
    }
    
        public boolean adicionaAtivo(Ativo a) {
        int proximaPosicaoLivre = this.proximaPosicaoLivre();
        if (proximaPosicaoLivre != -1) {
            ativos[proximaPosicaoLivre] = a;
            return true;
        } else {
            return false;
        }
    }
        
        public Ativo buscaPorId(long id) {
        for (Ativo a : ativos) {
            if (a != null && a.getId() == id) {
                return a;
            }
        }
        return null;

    }
        
        public Ativo buscaPorTicker(String ticker) {
        for (Ativo a : ativos) {
            if (a != null && a.getTicker().equals(ticker)) {
                return a;
            }
        }
        return null;

    }
        
        public void mostrarTodos() {
        boolean temAtivo = false;
        StringBuilder builder = new StringBuilder("");
        
        for (Ativo a : ativos) {
            if (a != null) {
                builder.append(a.toString());
                temAtivo = true;
            }
        }
        
        JOptionPane.showMessageDialog(null,builder.toString(),"Ativos",JOptionPane.INFORMATION_MESSAGE);
        
        if (!temAtivo) {
            System.out.println("Não existe ativo cadastrado");
        }
    }

     public boolean alterarTicker(long id, String novoTicker){
        Ativo a = this.buscaPorId(id);
        if(a != null){
            a.setTicker(novoTicker);
            return true;
        }else{
            return false;
        }
    }
     
     public boolean alterarNomeEmpresa(String novoNome, long id){
        Ativo a = this.buscaPorId(id);
        if(a != null){
            a.setNomeEmpresa(novoNome);
            return true;
        }else{
            return false;
        }
    }
     
     public boolean remover(long id) {
        for (int i = 0; i < ativos.length; i++) {
            if (ativos[i] != null && ativos[i].getId() == id) {
                ativos[i] = null;
                return true;
            }
        }
        return false;

    }
        
        private int proximaPosicaoLivre() {
        for (int i = 0; i < ativos.length; i++) {
            if (ativos[i] == null) {
                return i;
            }

        }
        return -1;

    }
}
