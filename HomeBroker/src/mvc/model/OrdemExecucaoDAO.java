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
    
//    public Ordem[] procurarVenda(Ordem[] ordens){
//        Ordem[] ordemVenda = new Ordem[10];
// 
//        
//        for(int i=0; i<ordens.length; i++){
//            if(ordens[i].getTipoOrdem().equals("Venda") && ordens[i] != null){
//                ordemVenda[i] = ordens[i];
//                
//            }
//        }
//        return ordemVenda;
//    }
//    
//    public Ordem[] procurarCompra(Ordem[] ordens){
//        //Ordem[] todas = ordens.getOrdens();
//        Ordem[] ordemCompra = new Ordem[10];
//       
//        for(int i=0; i<ordens.length; i++){
//            if(ordens[i].getTipoOrdem().equals("Compra")){
//                ordemCompra[i] = ordens[i];
//                
//            }
//        }
//        return ordemCompra;
//    }
    
    public void executarOrdem(OrdemDAO ordens, ContaCorrenteDAO conta, MovimentaContaDAO m, MeusAtivosDAO meusAtivos){
        Ordem[] todas = ordens.getOrdens();
        Ordem[] ordensVenda = new Ordem[10];
        Ordem[] ordensCompra = new Ordem[10];
        
        for(int i=0; i<todas.length; i++){
            if(todas[i] != null){
                if(todas[i].getTipoOrdem().equals("Compra")){
                    ordensCompra[i] = todas[i]; // não tá inserindo nas posições de forma sequencial, arrumar isso
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
                            if(ordensVenda[i].getValor()== ordensCompra[j].getValor()){ // usei o valor pq senão for igual não há acordo, mudar depois
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

                                    conta.transfere(conta, ordensCompra[j].getValorTotal(), ordensCompra[j].getConta().getId(), ordensVenda[i].getConta().getId(), m);
                                    
                                    //criando meus ativos conta compra
                                   
                                    
                                    //criando meus ativos conta venda
                                    
     
                                    ordens.removerPorId(ordensVenda[i].getId());
                                    ordens.removerPorId(ordensCompra[j].getId()); // outro jeito de fazer
                  
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
                                    
                                    conta.transfere(conta, ordensVenda[i].getValorTotal(), ordensCompra[j].getConta().getId(), ordensVenda[i].getConta().getId(), m);
                                    
                                    ordens.removerPorId(ordensVenda[i].getId());

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
                                    
                                    conta.transfere(conta, ordensCompra[j].getValorTotal(), ordensCompra[j].getConta().getId(), ordensVenda[i].getConta().getId(), m);
                                    
                                    ordens.removerPorId(ordensCompra[j].getId());
                                }
                            }
                        }
                        
                    }
                }
                
            }
            
        }
    }
    
//    public void executarOrdem0(OrdemDAO ordens, ContaCorrenteDAO conta, MovimentaContaDAO m){
//        Ordem[] todas = ordens.getOrdens();
//        for(int i=0; i<todas.length; i++){
//            if(todas[i] != null){
//                if(todas[i].getTipoOrdem().equals("Ordem 0")){
//                    if(todas[i].getTicker().getTotalAtivos() == 0){
//                        todas[i].setEstadoOrdem("Não");
//                    }else{
//                        if(todas[i].getTicker().getTotalAtivos() < todas[i].getQuantidade()){
//                            double valor = todas[i].getValorTotal() * 0.9; // 10% de desconto
//                            OrdemExecucao o = new OrdemExecucao();
//                            todas[i].setEstadoOrdem("Parcial");
//                            
//                            o.setContaCompra(todas[i].getConta());
//                            o.setContaVenda(conta.buscaPorId(1)); // conta do ADM
//                            o.setOrdem(todas[i]);
//                            o.setQuantidade(todas[i].getTicker().getTotalAtivos());
//                            o.setDataCriacao(LocalDate.now());
//                            o.setDataModificacao(LocalDate.now());
//                            this.adiciona(o);
//                            
//                            conta.transfere(conta, valor, todas[i].getConta().getId(), 1, m);
//                            
//                            todas[i].getTicker().setTotalAtivos(0);
//                            
//                        }else{
//                            if(todas[i].getTicker().getTotalAtivos() == todas[i].getQuantidade()){
//                                double valor = todas[i].getValorTotal() * 0.9; // 10% de desconto
//                                OrdemExecucao o = new OrdemExecucao();
//                                todas[i].setEstadoOrdem("Total");
//
//                                o.setContaCompra(todas[i].getConta());
//                                o.setContaVenda(conta.buscaPorId(1)); // conta do ADM
//                                o.setOrdem(todas[i]);
//                                o.setQuantidade(todas[i].getTicker().getTotalAtivos());
//                                o.setDataCriacao(LocalDate.now());
//                                o.setDataModificacao(LocalDate.now());
//                                this.adiciona(o);
//
//                                conta.transfere(conta, valor, todas[i].getConta().getId(), 1, m);
//
//                                todas[i].getTicker().setTotalAtivos(0);
//                                
//                            }else if(todas[i].getTicker().getTotalAtivos() > todas[i].getQuantidade()){
//                                double valor = todas[i].getValorTotal() * 0.9; // 10% de desconto
//                                OrdemExecucao o = new OrdemExecucao();
//                                todas[i].setEstadoOrdem("Total");
//
//                                o.setContaCompra(todas[i].getConta());
//                                o.setContaVenda(conta.buscaPorId(1)); // conta do ADM
//                                o.setOrdem(todas[i]);
//                                o.setQuantidade(todas[i].getTicker().getTotalAtivos());
//                                o.setDataCriacao(LocalDate.now());
//                                o.setDataModificacao(LocalDate.now());
//                                this.adiciona(o);
//
//                                conta.transfere(conta, valor, todas[i].getConta().getId(), 1, m);
//
//                                todas[i].getTicker().setTotalAtivos(todas[i].getTicker().getTotalAtivos() - todas[i].getQuantidade());
//                            }
//                        }
//                        
//                    }
//                    
//                }
//            }
//        }
//    }
    
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
