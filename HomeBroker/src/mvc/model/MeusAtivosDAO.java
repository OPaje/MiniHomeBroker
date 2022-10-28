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
        a.setAtivos(ativos.buscaPorTicker("NTCO3"));
        a.setContas(contas.buscaPorId(2));
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
     
    private int proximaPosicaoLivre() {
       for (int i = 0; i < meusAtivos.length; i++) {
           if (meusAtivos[i] == null) {
               return i;
           }
        }
       return -1;

    }
    
}
