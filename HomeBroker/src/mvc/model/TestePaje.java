/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package mvc.model;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author jeanc
 */
public class TestePaje {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        OrdemDAO ordemDAO = new OrdemDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        ContaCorrenteDAO contaCorrenteDAO = new ContaCorrenteDAO();
        List<ContaCorrente> contas = contaCorrenteDAO.getLista();
        Cliente c = contas.get(1).getC();
        AtivoDAO ativoDAO = new AtivoDAO();
        Ordem o1 = new Ordem();
        List<Ordem> ordens = null;
        ordens = ordemDAO.lista(null);
        List<Ordem> ordensCompra = new ArrayList<>();
        MeusAtivosDAO meusAtivosDAO = new MeusAtivosDAO();
        List<MeusAtivos> meus = meusAtivosDAO.buscaPorID(2);
        OrdemExecucaoDAO ordemExecucaoDAO = new OrdemExecucaoDAO();
        int qtdAtivos = 0;
        double dividendos;
        
        
//        for(MeusAtivos a : meus){
//            if(a.getAtivo().getTicker().equals("NTCO3")){
//                qtdAtivos += a.getQtdAtivos();
//            }
//        }
//        
//        System.out.println(qtdAtivos);
//        dividendos = qtdAtivos * 3;   // 30% do valor do ativo
//        System.out.println(dividendos);
        
        try {
            ordemExecucaoDAO.mostraUltimaNegociacao();
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "O ativo ainda não possui negociações ", "Erro", JOptionPane.INFORMATION_MESSAGE);
        }
        
        //ordensCompra.add(ordens.get(0));
        
//        for (Ordem o : ordens) {
//            if(o.getTipoOrdem().equals("Compra")){
//                System.out.println(o.toString());     
//                ordensCompra.add(o);
//            }
//        }
//        
//        for(int i=0; i<ordens.size(); i++){
//            if(ordens.get(i) != null){
//                if(ordens.get(i).getTipoOrdem().equals("Compra")){
//                    ordensCompra.add(ordens.get(i));
//                    System.out.println(ordensCompra.get(i).toString());
//                }
//            }
//        }
        
        /////// Teste Ordem Execução ///////
//        OrdemExecucaoDAO ordemExecucaoDAO = new OrdemExecucaoDAO();
//        MovimentaContaDAO movimenta = new MovimentaContaDAO();
//        
//        try {
//            ordemExecucaoDAO.executarOrdem(ordemDAO, contaCorrenteDAO, movimenta);
//            
//        } catch (SQLException e) {
//            System.out.println("Não deu certo");
//        }
        
//        c = clienteDAO.getClientes();
//        for (Cliente cliente : c) {
//            System.out.println(cliente.toString());
//        }
        
//        o1.setConta(contaCorrenteDAO.buscaPorId(1));
//        o1.setTipoOrdem("Venda");
//        o1.setTicker(ativoDAO.buscaPorTicker("NTCO3"));
//        o1.setQuantidade(40);
//        o1.setValor(10);
//        o1.setEstadoOrdem("VVVVV");
//        o1.setValorTotal(o1.getValor() * o1.getQuantidade());
//        o1.setDataCriacao(LocalDate.now());
//        o1.setDataModificacao(LocalDate.now());
//        
//        //ordemDAO.adicionaOrdem(o1);
//        Ordem o = ordens.get(0);
//        System.out.println(o.getConta().getC());
//        System.out.println(c.toString());
////        ordemDAO.mostrarTodos();
//        try {
//            //ordemDAO.exclui(4);
//            JOptionPane.showMessageDialog(null, ordemDAO.bookOfertas(1), "Book de Ofertas", 0);
//            
////        for (Ordem ordem : ordens) {
////            System.out.println(ordem);
////        }
//        } catch (SQLException ex) {
//            Logger.getLogger(TestePaje.class.getName()).log(Level.SEVERE, null, ex);
//            System.out.println("Deu problema");
//        }
    }
    
}
