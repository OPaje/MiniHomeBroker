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
    
    public void executarOrdem(OrdemDAO ordens, ContaCorrenteDAO conta, MovimentaContaDAO m, MeusAtivosDAO meusAtivos, MovimentaContaDAO movimenta){
        int k = 0;
        Ordem[] todas = ordens.getOrdens();
        Ordem[] ordensVenda = new Ordem[10];
        Ordem[] ordensCompra = new Ordem[10];
        
        for(int i=0; i<todas.length; i++){
            if(todas[i] != null){
                if(todas[i].getTipoOrdem().equals("Compra")){
                    ordensCompra[k] = todas[i];
                    k++;
                }
            }
        }
        
        k = 0;
         for(int i=0; i<todas.length; i++){
             if(todas[i] != null){
                if(todas[i].getTipoOrdem().equals("Venda")){
                    ordensVenda[k] = todas[i];
                    k++;
                }
             }
        }
        
        
        for(int i=0; i<ordensVenda.length; i++){
            if(ordensVenda[i] != null){
                for(int j=0; j<ordensCompra.length; j++){
                    if(ordensCompra[j] != null){
                        if(ordensVenda[i].getTicker().equals(ordensCompra[j].getTicker())){
                            if(ordensVenda[i].getValor()== ordensCompra[j].getValor()){
                                if(ordensVenda[i].getQuantidade() == 0){ 
                                    ordensCompra[j].setEstadoOrdem("Não");

                                }else if(ordensVenda[i].getQuantidade() == ordensCompra[j].getQuantidade()){
                                    OrdemExecucao o = new OrdemExecucao();
                                    
                                                
                                    ordensCompra[j].setEstadoOrdem("Total");
                                    ordensVenda[i].setEstadoOrdem("Total");
                                    ordensVenda[i].setQuantidade(0);
                                    
                                    o.setOrdemCompra(ordensCompra[j]);
                                    o.setOrdemVenda(ordensVenda[i]);
                                    o.setQuantidade(ordensCompra[j].getQuantidade());
                                    o.setContaCompra(ordensCompra[j].getConta());
                                    o.setContaVenda(ordensVenda[i].getConta());
                                    o.setDataCriacao(LocalDate.now());
                                    o.setDataModificacao(LocalDate.now());

                                    this.adiciona(o);
                                    conta.transfere(conta, ordensCompra[j].getValorTotal(), ordensCompra[j].getConta().getId(), ordensVenda[i].getConta().getId());
                                    movimenta.novaMovimentacao("Débito", "Negociação de Ativos", ordensCompra[j].getValorTotal(), LocalDate.now(), LocalDate.now(), ordensCompra[j].getConta()); // movimento conta compra
                                    movimenta.novaMovimentacao("Débito", "Negociação de Ativos", ordensCompra[i].getValorTotal(), LocalDate.now(), LocalDate.now(), ordensVenda[i].getConta()); // movimento conta venda

                                    ordens.removerPorId(ordensVenda[i].getId());
                                    ordens.removerPorId(ordensCompra[j].getId()); 
                  
                                }else if(ordensVenda[i].getQuantidade() < ordensCompra[j].getQuantidade()){
                                    OrdemExecucao o = new OrdemExecucao();
                                    ordensCompra[j].setEstadoOrdem("Parcial");
                                    ordensVenda[i].setEstadoOrdem("Total");

                                    o.setOrdemCompra(ordensCompra[j]);
                                    o.setOrdemVenda(ordensVenda[i]);
                                    o.setQuantidade(ordensVenda[i].getQuantidade()); 
                                    ordensVenda[i].setQuantidade(0);
                                    o.setContaCompra(ordensCompra[j].getConta());
                                    o.setContaVenda(ordensVenda[i].getConta());
                                    o.setDataCriacao(LocalDate.now());
                                    o.setDataModificacao(LocalDate.now());

                                    this.adiciona(o);
                                    
                                    conta.transfere(conta, o.getQuantidade() * ordensCompra[j].getValor(), ordensCompra[j].getConta().getId(), ordensVenda[i].getConta().getId());
                                    
                                    movimenta.novaMovimentacao("Débito", "Negociação de Ativos", ordensCompra[j].getValorTotal(), LocalDate.now(), LocalDate.now(), ordensCompra[j].getConta()); // movimento conta compra
                                    movimenta.novaMovimentacao("Débito", "Negociação de Ativos", ordensCompra[i].getValorTotal(), LocalDate.now(), LocalDate.now(), ordensVenda[i].getConta()); // movimento conta venda

                                    
                                    ordens.removerPorId(ordensVenda[i].getId());
                                    ordens.removerPorId(ordensCompra[j].getId()); 

                                }else if(ordensVenda[i].getQuantidade() > ordensCompra[j].getQuantidade()){
                                    OrdemExecucao o = new OrdemExecucao();
                                    ordensCompra[j].setEstadoOrdem("Total");
                                    ordensVenda[i].setEstadoOrdem("Parcial");

                                    o.setOrdemCompra(ordensCompra[j]);
                                    o.setOrdemVenda(ordensVenda[i]);
                                    o.setQuantidade(ordensCompra[j].getQuantidade());
                                    ordensVenda[i].setQuantidade(ordensVenda[i].getQuantidade() - ordensCompra[j].getQuantidade());
                                    o.setContaCompra(ordensCompra[j].getConta());
                                    o.setContaVenda(ordensVenda[i].getConta());
                                    o.setDataCriacao(LocalDate.now());
                                    o.setDataModificacao(LocalDate.now());

                                    this.adiciona(o);
                                    
                                    conta.transfere(conta, ordensCompra[j].getValorTotal(), ordensCompra[j].getConta().getId(), ordensVenda[i].getConta().getId());
                                    
                                    movimenta.novaMovimentacao("Débito", "Negociação de Ativos", ordensCompra[j].getValorTotal(), LocalDate.now(), LocalDate.now(), ordensCompra[j].getConta()); // movimento conta compra
                                    movimenta.novaMovimentacao("Débito", "Negociação de Ativos", ordensCompra[i].getValorTotal(), LocalDate.now(), LocalDate.now(), ordensVenda[i].getConta()); // movimento conta venda

                                    ordens.removerPorId(ordensVenda[i].getId());
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
    
    public void mostraUltimaNegociacao(){
        int j = 0;
        for(int i=0; i<ordensExecucao.length;i++){
            if(ordensExecucao[i] != null){
                j = i; // pega a última posição com valor válido
            }
        }
        String ativo = ordensExecucao[j].getOrdemCompra().getTicker().getTicker(); // o primeiro ticker retorna o ativo
        double valor = ordensExecucao[j].getOrdemCompra().getValor();
        StringBuilder builder = new StringBuilder("");
        
        builder.append("\nAtivo: ").append(ativo).append("  Valor: ").append(valor);
        //builder.append("\nValor: ").append(valor);
        
        JOptionPane.showMessageDialog(null,builder.toString(),"Última Negociação",JOptionPane.INFORMATION_MESSAGE);
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
