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
public class ContaCorrente{
    private long id;
    private double saldo;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;    
    private Cliente c;
    
    private static long serialContaCorrente;
    
    public ContaCorrente(){
        this.id = ++ContaCorrente.serialContaCorrente;
    }
            
    public long getId() {
        return id;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public LocalDate getDataModificacao() {
        return dataModificacao;
    }


    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public void setDataModificacao(LocalDate dataModificacao) {
        this.dataModificacao = dataModificacao;
    }

    public Cliente getC() {
        return c;
    }

    public void setC(Cliente c) {
        this.c = c;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ContaCorrente other = (ContaCorrente) obj;
        return this.id == other.id;
    }
    
    
    
    @Override
    public String toString() {
	return "\nID Conta = " + id + "\nData de Criacao = " + dataCriacao + 
	"\nData de Modificacao = " + dataModificacao + c.toString();
}


   

    
    
    
    
}
