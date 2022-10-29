/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.model;

/**
 *
 * @author jeanc
 */
public class MeusAtivosDAO {
    private MeusAtivos[] meusAtivos = new MeusAtivos[20];

    public MeusAtivos[] getMeusAtivos() {
        return meusAtivos;
    }

    public MeusAtivosDAO(AtivoDAO ativos, ContaCorrenteDAO contas) {
        MeusAtivos a = new MeusAtivos();
        a.setAtivo(ativos.buscaPorTicker("NTCO3"));
        a.setConta(contas.buscaPorId(2));
        a.setQtdAtivos(40);
        a.setValorPago(10);
        a.setTotalDinheiroAtivos(a.getQtdAtivos() * a.getValorPago());
        a.setCotacao(10);
        this.adicionaMeusAtivos(a);     
    }
 
    public boolean adicionaMeusAtivos(MeusAtivos a) {
       int proximaPosicaoLivre = this.proximaPosicaoLivre();
       if (proximaPosicaoLivre != -1) {
           meusAtivos[proximaPosicaoLivre] = a;
           return true;
       } else {
           return false;
       }
    }
     
    public void organizaMeusAtivos(OrdemExecucaoDAO ordens){
        OrdemExecucao[] todas = ordens.getOrdensExecucao();
        
        for (int i = 0; i < todas.length; i++) {
            if(todas[i] != null){
                if(todas[i].getOrdemCompra().getEstadoOrdem().equals("Total")){
                    MeusAtivos a = new MeusAtivos();
                    MeusAtivos a1 = new MeusAtivos();
                    //conta compra
                    a.setAtivo(todas[i].getOrdemCompra().getTicker());
                    a.setConta(todas[i].getContaCompra());
                    a.setQtdAtivos(todas[i].getQuantidade());
                    a.setTotalDinheiroAtivos(todas[i].getOrdemCompra().getValorTotal());
                    a.setCotacao(10);
                    a.setValorPago(todas[i].getOrdemCompra().getValor());
                    this.adicionaMeusAtivos(a);
                    
                    //conta venda  independe se foi parcial ou não
                    a1.setAtivo(todas[i].getOrdemVenda().getTicker());
                    a1.setConta(todas[i].getContaVenda());
                    a1.setQtdAtivos(todas[i].getOrdemVenda().getQuantidade() - todas[i].getQuantidade());
                    a1.setTotalDinheiroAtivos(0);
                    a1.setCotacao(10);
                    a1.setValorPago(0);
                    this.adicionaMeusAtivos(a1);
                    
                }else if(todas[i].getOrdemCompra().getEstadoOrdem().equals("Parcial")){
                    MeusAtivos a = new MeusAtivos();
                    MeusAtivos a1 = new MeusAtivos();
                    
                    //conta compra
                    a.setAtivo(todas[i].getOrdemCompra().getTicker());
                    a.setConta(todas[i].getContaCompra());
                    a.setQtdAtivos(todas[i].getQuantidade());
                    a.setTotalDinheiroAtivos(todas[i].getOrdemCompra().getValorTotal());
                    a.setCotacao(10);
                    a.setValorPago(todas[i].getOrdemCompra().getValor());
                    this.adicionaMeusAtivos(a);
                    
                    //conta venda
                    a1.setAtivo(todas[i].getOrdemVenda().getTicker());
                    a1.setConta(todas[i].getContaVenda());
                    a1.setQtdAtivos(0);
                    a1.setTotalDinheiroAtivos(0);
                    a1.setCotacao(10);
                    a1.setValorPago(0);
                    this.adicionaMeusAtivos(a1);
                    
                }
            }
            
        }
    }
    
     public MeusAtivos buscaPorIdConta(long id) {
        for (MeusAtivos a : meusAtivos) {
            if (a != null && a.getConta().getId()== id) {
                return a;
            }
        }
        return null;

    }
    
     public boolean remover(long id) {
        for (int i = 0; i < meusAtivos.length; i++) {
            if (meusAtivos[i] != null && meusAtivos[i].getId() == id) {
                meusAtivos[i] = null;
                return true;
            }
        }
        return false;

    }
    
    private int proximaPosicaoLivre() {
       for (int i = 0; i < meusAtivos.length; i++) {
           if (meusAtivos[i] == null) {
               return i;
           }
        }
       return -1;

    }
}
