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
    private static long serial;
    private int qtdAtivos;
    private double valorPago;
    private double cotacao;
    private double totalDinheiroAtivos;
    private Ativo ativo;
    private ContaCorrente conta;
    
    public MeusAtivos(){
        id = ++MeusAtivos.serial;
    }

    public long getId() {
        return id;
    }
    
    
    public int getQtdAtivos() {
        return qtdAtivos;
    }

    public void setQtdAtivos(int qtdAtivos) {
        this.qtdAtivos = qtdAtivos;
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

    public double getTotalDinheiroAtivos() {
        return totalDinheiroAtivos;
    }

    public void setTotalDinheiroAtivos(double totalDinheiroAtivos) {
        this.totalDinheiroAtivos = totalDinheiroAtivos;
    }

    public Ativo getAtivos() {
        return ativo;
    }

    public void setAtivos(Ativo ativos) {
        this.ativo = ativos;
    }

    public ContaCorrente getContas() {
        return conta;
    }

    public void setContas(ContaCorrente contas) {
        this.conta = contas;
    }

    @Override
    public String toString() {
        return  "id=" + id + "\nqtdAtivos=" + qtdAtivos + "\nvalorPago=" + valorPago + "\ncotacao=" + cotacao + "\ntotalDinheiroAtivos=" + totalDinheiroAtivos + 
                "\nativo=" + ativo.getTicker() + "\nconta=" + conta.getC().getNome();
    }

    
    
    
    
}
