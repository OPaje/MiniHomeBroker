/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import javax.swing.JOptionPane;

/**
 *
 * @author Stheffany
 */
public class ContaCorrenteDAO {
    
    ContaCorrente[] contas = new ContaCorrente[5];

    public ContaCorrente[] getContaCorrente() {
        return contas;
    }

    public ContaCorrenteDAO(Cliente[] cliente) {
        
        ContaCorrente c1 = new ContaCorrente();
        c1.setC(cliente[0]); // esse é o ADM
        c1.setSaldo(500000); // ao criar uma nova conta a bolsa ganha 500 mil
        c1.setDataCriacao(LocalDate.now());
        c1.setDataModificacao(LocalDate.now());
        this.adicionaConta(c1); // adicionando a conta ao vetor contas;
        
        ContaCorrente c2 = new ContaCorrente();
        c2.setC(cliente[1]);
        c2.setSaldo(20000); // ao criar uma conta nova o cliente recebe 20 mil;
        c2.setDataCriacao(LocalDate.now());
        c2.setDataModificacao(LocalDate.now());
        this.adicionaConta(c2);
        
        // fazer isso para os outros clientes
        
    }
    
    public boolean adicionaConta(ContaCorrente c) {
        int proximaPosicaoLivre = this.proximaPosicaoLivre();
        if (proximaPosicaoLivre != -1) {
            contas[proximaPosicaoLivre] = c;
            return true;
        } else {
            return false;
        }
    }
    
    public void depositar(double valor){
        contas[0].setSaldo(contas[0].getSaldo() + valor); // melhorar isso
    }

    public boolean sacar(double valor){
        if(this.saldo < valor){
            return false;
        }else{
            this.saldo = this.saldo - valor;
            return true;
        }
    }

    public String mostraSaldo(){
        return "Saldo conta do(a) " + this.contas[0].getC() + ": " + contas[0].getSaldo();
    }
    
    public boolean transfere(ContaCorrente destino, double valor){
        boolean retirou = this.sacar(valor);
        if(retirou == false){
        return false;
        }else{
            destino.depositar(valor);
            return true;
        }
    }

    
    void extrato(){
        
    }
    
    void pagamento(){
        //transferir para a conta do administrador?
    }    
    
     public void mostrarTodos() {
        boolean temConta = false;
        for (ContaCorrente c : contas) {
            if (c != null) {
                JOptionPane.showMessageDialog(null, c);
                temConta = true;
            }
        }
        if (!temConta) {
            System.out.println("Não existe conta cadastrada");
        }
    }
     
     private int proximaPosicaoLivre() {
        for (int i = 0; i < contas.length; i++) {
            if (contas[i] == null) {
                return i;
            }

        }
        return -1;

    }
     
}
