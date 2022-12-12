/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.model;

/**
 *
 * @author jeanc
 */
public class MeusAtivos {
    private long id;
    private int qtdAtivos;
    private double valorPago;
    private double cotacao;
    private double totalDinheiroAtivos;
    private Ativo ativo;
    private ContaCorrente conta;
    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public double getCotacao() {
        return cotacao;
    }

    public void setCotacao(double cotacao) {
        this.cotacao = cotacao;
    }
    
    public int getQtdAtivos() {
        return qtdAtivos;
    }

    public void setQtdAtivos(int qtdAtivos) {
        this.qtdAtivos = qtdAtivos;
    }

    public double getTotalDinheiroAtivos() {
        return totalDinheiroAtivos;
    }

    public void setTotalDinheiroAtivos(double totalDinheiroAtivos) {
        this.totalDinheiroAtivos = totalDinheiroAtivos;
    }

    public Ativo getAtivo() {
        return ativo;
    }

    public void setAtivo(Ativo ativo) {
        this.ativo = ativo;
    }

    public ContaCorrente getConta() {
        return conta;
    }

    public void setConta(ContaCorrente conta) {
        this.conta = conta;
    }
    
    @Override
    public String toString() {
        return  "id=" + id + "\nqtdAtivos=" + qtdAtivos + "\ntotalDinheiroAtivos=" + totalDinheiroAtivos + "\nativo=" + ativo.getTicker();
    }

    
    
    
    
}
