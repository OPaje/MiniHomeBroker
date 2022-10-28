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
public class OrdemExecucaoDAO {
   private OrdemExecucao[] ordensExecucao = new OrdemExecucao[10];

    public OrdemExecucao[] getOrdensExecucao() {
        return ordensExecucao;
    }
    
    public Ordem[] procurarVenda(Ordem[] ordens){
        Ordem[] ordemVenda = new Ordem[10];
 
        
        for(int i=0; i<ordens.length; i++){
            if(ordens[i].getTipoOrdem().equals("Venda") && ordens[i] != null){
                ordemVenda[i] = ordens[i];
                
            }
        }
        return ordemVenda;
    }
    
    public Ordem[] procurarCompra(Ordem[] ordens){
        //Ordem[] todas = ordens.getOrdens();
        Ordem[] ordemCompra = new Ordem[10];
       
        for(int i=0; i<ordens.length; i++){
            if(ordens[i].getTipoOrdem().equals("Compra")){
                ordemCompra[i] = ordens[i];
                
            }
        }
        return ordemCompra;
    }
    
    public void executarOrdem(OrdemDAO ordens, ContaCorrenteDAO conta){
        Ordem[] todas = ordens.getOrdens();
        Ordem[] ordensVenda = new Ordem[10];
        Ordem[] ordensCompra = new Ordem[10];
        
        for(int i=0; i<todas.length; i++){
            if(todas[i] != null){
                if(todas[i].getTipoOrdem().equals("Compra")){
                    ordensCompra[i] = todas[i]; // não tá inserindo nas posições de forma sequencial
                }
            }
        }
        
         for(int i=0; i<todas.length; i++){
             if(todas[i] != null){
                if(todas[i].getTipoOrdem().equals("Venda")){
                    ordensVenda[i] = todas[i];
                }
             }
        }
        
        
        for(int i=0; i<ordensVenda.length; i++){
            if(ordensVenda[i] != null){
                for(int j=0; j<ordensCompra.length; j++){
                    if(ordensCompra[j] != null){
                        if(ordensVenda[i].getTicker().equals(ordensCompra[j].getTicker())){
                            if(ordensVenda[i].getValor()== ordensCompra[j].getValor()){ // usei o valor pq senão for igual não há acordo
                                if(ordensVenda[i].getQuantidade() == 0){ // pode ser mudado para cima?
                                    ordensCompra[j].setEstadoOrdem("Não");

                                }else if(ordensVenda[i].getQuantidade() == ordensCompra[j].getQuantidade()){
                                    OrdemExecucao o = new OrdemExecucao();
                                    ordensCompra[j].setEstadoOrdem("Total");
                                    ordensVenda[i].setEstadoOrdem("Total");
                                    ordensVenda[i].setQuantidade(0);

                                    o.setOrdem(ordensCompra[j]);
                                    o.setQuantidade(ordensCompra[j].getQuantidade());
                                    o.setContaCompra(ordensCompra[j].getConta());
                                    o.setContaVenda(ordensVenda[i].getConta());
                                    o.setDataCriacao(LocalDate.now());
                                    o.setDataModificacao(LocalDate.now());
                                    
                                    
                                    
                                    this.adiciona(o);

                                    conta.transfere(conta, ordensCompra[j].getValorTotal(), ordensCompra[j].getConta().getId(), ordensVenda[i].getConta().getId());
                                    
                                    ordens.removerPorId(ordensVenda[i].getId());
                                    ordens.removerPorId(o.getOrdem().getId()); // outro jeito de fazer
                                    // esse if tá funcionando
                                }else if(ordensVenda[i].getQuantidade() < ordensCompra[j].getQuantidade()){
                                    OrdemExecucao o = new OrdemExecucao();
                                    ordensCompra[j].setEstadoOrdem("Parcial");
                                    ordensVenda[i].setEstadoOrdem("Total");

                                    o.setOrdem(ordensCompra[j]);
                                    o.setQuantidade(ordensVenda[i].getQuantidade()); 
                                    ordensVenda[i].setQuantidade(0);
                                    o.setContaCompra(ordensCompra[j].getConta());
                                    o.setContaVenda(ordensVenda[i].getConta());
                                    o.setDataCriacao(LocalDate.now());
                                    o.setDataModificacao(LocalDate.now());

                                    this.adiciona(o);
                                    
                                    conta.transfere(conta, ordensVenda[i].getValorTotal(), ordensCompra[j].getConta().getId(), ordensVenda[i].getConta().getId());
                                    
                                    ordens.removerPorId(ordensVenda[i].getId());

                                }else if(ordensVenda[i].getQuantidade() > ordensCompra[j].getQuantidade()){
                                    OrdemExecucao o = new OrdemExecucao();
                                    ordensCompra[j].setEstadoOrdem("Total");
                                    ordensVenda[i].setEstadoOrdem("Parcial");

                                    o.setOrdem(ordensCompra[j]);
                                    o.setQuantidade(ordensCompra[j].getQuantidade());
                                    ordensVenda[i].setQuantidade(ordensVenda[i].getQuantidade() - ordensCompra[j].getQuantidade());
                                    o.setContaCompra(ordensCompra[j].getConta());
                                    o.setContaVenda(ordensVenda[i].getConta());
                                    o.setDataCriacao(LocalDate.now());
                                    o.setDataModificacao(LocalDate.now());

                                    this.adiciona(o);
                                    
                                    conta.transfere(conta, ordensCompra[j].getValorTotal(), ordensCompra[j].getConta().getId(), ordensVenda[i].getConta().getId());
                                    
                                    ordens.removerPorId(ordensCompra[j].getId());
                                }
                            }
                        }
                        
                    }
                }
                
            }
            
        }
    }
    
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
