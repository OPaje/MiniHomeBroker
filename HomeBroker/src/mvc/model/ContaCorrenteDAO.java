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
        
        ContaCorrente c3 = new ContaCorrente();
        c3.setC(cliente[2]);
        c3.setSaldo(20000); // ao criar uma conta nova o cliente recebe 20 mil;
        c3.setDataCriacao(LocalDate.now());
        c3.setDataModificacao(LocalDate.now());
        this.adicionaConta(c3);
        
        ContaCorrente c4 = new ContaCorrente();
        c4.setC(cliente[2]);
        c4.setSaldo(20000); // ao criar uma conta nova o cliente recebe 20 mil;
        c4.setDataCriacao(LocalDate.now());
        c4.setDataModificacao(LocalDate.now());
        this.adicionaConta(c4);
        
        
        
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
    
    // apagar essa função
     public ContaCorrente buscaPorId(long id) {
        for (ContaCorrente c : contas) {
            if (c != null && c.getId() == id) {
                return c;
            }
        }
        return null;

    }
    
    public boolean depositar(long id, double valor, ContaCorrenteDAO contas){
        ContaCorrente c; 
        c = contas.buscaPorId(id);
        if(c != null){
            c.setSaldo(c.getSaldo() + valor);
            return true;
        }else{
            return false;
        }
    }

    public boolean sacar(ContaCorrente conta, double valor){
        if(conta.getSaldo() < valor){
            return false;
        }else{
            conta.setSaldo(conta.getSaldo() - valor);
            return true;
        }
    }

    public String mostraSaldo(long id, ContaCorrenteDAO contas){
        ContaCorrente c = contas.buscaPorId(id);
        
        return "Saldo conta do(a) " + c.getC().getNome() + ": " + c.getSaldo();
    }
    
    public boolean transfere(ContaCorrenteDAO contas, double valor, long idOrigem, long idDestino){ 
        ContaCorrente origem = contas.buscaPorId(idOrigem);
        boolean retirou = contas.sacar(origem, valor);
        if(retirou == false){
        return false;
        }else{
            contas.depositar(idDestino, valor, contas);
            return true;
        }
    }

    
    void extrato(){
        
    }
    
    void pagamento(){
        // pagamento da mensalidade;
    }    
    
      public void mostrarTodos() {
        boolean temConta = false;
        StringBuilder builder = new StringBuilder("");
        
        for (ContaCorrente c : contas) {
            if (c != null) {
                builder.append(c.toString());
                temConta = true;
            }
        }
        
        JOptionPane.showMessageDialog(null,builder.toString(),"Contas",JOptionPane.INFORMATION_MESSAGE);
        
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
