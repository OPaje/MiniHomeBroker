/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.model;

import java.time.LocalDate;

/**
 *
 * @author Stheffany
 */
public class MovimentaContaDAO {
    MovimentaConta[] movimentos = new MovimentaConta[10];

    public MovimentaConta[] getMovimentos() {
        return movimentos;
    }
    
    //informações para o crud de movimentação de conta (facilita na emissão do extrato)
    public void criarMovimento(String tipo, String descricao, double valor, ContaCorrente conta){
        MovimentaConta movimenta = new MovimentaConta();
        movimenta.setConta(conta);
        movimenta.setTipoMovimento(tipo);
        movimenta.setDescricao(descricao);
        movimenta.setValor(valor);
        movimenta.setDataCriacao(LocalDate.now());
        movimenta.setDataModificacao(LocalDate.now());
        
        this.adicionaMovimento(movimenta);
    }
    
    //procura uma posição livre no vetor de movimentação
     public boolean adicionaMovimento(MovimentaConta m) {
        int proximaPosicaoLivre = this.proximaPosicaoLivre();
        if (proximaPosicaoLivre != -1) {
            movimentos[proximaPosicaoLivre] = m;
            return true;
        } else {
            return false;
        }
    }
     
    public MovimentaConta[] retornaMovimentosConta(long id){
        MovimentaConta[] m = new MovimentaConta[10];
        int j = 0;
        for (int i = 0; i < movimentos.length; i++) {
            if(movimentos[i] != null){
                if(movimentos[i].getConta().getId() == id){
                    m[j] = movimentos[i];
                    j++;
                }
                
            } 
        }
        return m;
    }
    
     public boolean remover(long id) {
        for (int i = 0; i < movimentos.length; i++) {
            if (movimentos[i] != null && movimentos[i].getId() == id) {
                movimentos[i] = null;
                return true;
            }
        }
        return false;

    }

    private int proximaPosicaoLivre() {
        for (int i = 0; i < movimentos.length; i++) {
            if (movimentos[i] == null) {
                return i;
            }

        }
        return -1;

    }
}
