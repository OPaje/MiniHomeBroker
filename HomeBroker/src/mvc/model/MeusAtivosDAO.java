/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jeanc
 */
public class MeusAtivosDAO {
     
    public MeusAtivos adicionaMeusAtivos(MeusAtivos elemento) {
        String sql = "insert into meus_ativos "
                + "(qtde_ativo,valor_pago_ativo,cotacao_ativo, total_din_ativo, atv_meus_atv, ccorrente_meus_atv" 
                + " values (?,?,?,?,?,?)";

        try ( Connection connection = new ConnectionFactory().getConnection();  PreparedStatement stmt = connection.prepareStatement(sql)) {
            // seta os valores
            stmt.setInt(1, elemento.getQtdAtivos());
            stmt.setDouble(2, elemento.getValorPago());
            stmt.setDouble(3, elemento.getCotacao());
            stmt.setDouble(4, elemento.getTotalDinheiroAtivos());
            stmt.setLong(5, elemento.getAtivo().getId());
            stmt.setLong(6, elemento.getConta().getId());
            
            stmt.execute();

            System.out.println("Elemento inserido com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        return elemento;
    }
   
     
    public void organizaMeusAtivos(OrdemExecucaoDAO ordens) throws SQLException{
        List<OrdemExecucao> todas = ordens.lista(null);
        
        for (int i = 0; i < todas.size(); i++) {
            if(todas.get(i) != null){
                if(todas.get(i).getOrdemCompra().getEstadoOrdem().equals("Total")){
                    MeusAtivos a = new MeusAtivos();
                    MeusAtivos a1 = new MeusAtivos();
                    // verificar se essa conta já tem Meus Ativos
                    //conta compra
                    a.setAtivo(todas.get(i).getOrdemCompra().getTicker());
                    a.setConta(todas.get(i).getContaCompra());
                    a.setQtdAtivos(todas.get(i).getQuantidade());
                    a.setTotalDinheiroAtivos(todas.get(i).getOrdemCompra().getValorTotal());
                    a.setCotacao(10);
                    a.setValorPago(todas.get(i).getOrdemCompra().getValor());
                    this.adicionaMeusAtivos(a);
                    
                    //conta venda  independe se foi parcial ou não
                    a1.setAtivo(todas.get(i).getOrdemVenda().getTicker());
                    a1.setConta(todas.get(i).getContaVenda());
                    a1.setQtdAtivos(0);
                    a1.setTotalDinheiroAtivos(0);
                    a1.setCotacao(10);
                    a1.setValorPago(0);
                    this.adicionaMeusAtivos(a1);
                    
                }else if(todas.get(i).getOrdemCompra().getEstadoOrdem().equals("Parcial")){
                    MeusAtivos a = new MeusAtivos();
                    MeusAtivos a1 = new MeusAtivos();
                    
                    //conta compra
                    a.setAtivo(todas.get(i).getOrdemCompra().getTicker());
                    a.setConta(todas.get(i).getContaCompra());
                    a.setQtdAtivos(todas.get(i).getQuantidade());
                    a.setTotalDinheiroAtivos(todas.get(i).getOrdemCompra().getValor() * todas.get(i).getQuantidade());
                    a.setCotacao(10);
                    a.setValorPago(todas.get(i).getOrdemCompra().getValor());
                    this.adicionaMeusAtivos(a);
                    
                    //conta venda
                    a1.setAtivo(todas.get(i).getOrdemVenda().getTicker());
                    a1.setConta(todas.get(i).getContaVenda());
                    a1.setQtdAtivos(0);
                    a1.setTotalDinheiroAtivos(0);
                    a1.setCotacao(10);
                    a1.setValorPago(0);
                    this.adicionaMeusAtivos(a1);
                    
                }
            }
            
        }
    }
    
    public List<MeusAtivos> buscaPorID(long code) {
            ContaCorrenteDAO contaCorrenteDAO = new ContaCorrenteDAO();
            AtivoDAO ativoDAO = new AtivoDAO();
            List<MeusAtivos> meus = new ArrayList<>();
        try (Connection connection = new ConnectionFactory().getConnection();
            PreparedStatement ps = createPreparedStatement(connection, code);
            ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                long id = rs.getLong("idativo");
                int quantidade = rs.getInt("qtde_ativo");
                double valor = rs.getDouble("valor_pago_ativo");
                double cotacao = rs.getDouble("cotacao_ativo");
                double totalPago = rs.getDouble("total_din_ativo");
                long idAtivo = rs.getLong("atv_meus_atv");
                long idConta = rs.getLong("ccorrente_meus_atv");
                
                ContaCorrente conta = contaCorrenteDAO.buscaPorId(idConta);
                Ativo ativo = ativoDAO.buscaPorID(idAtivo);

                MeusAtivos meusAtivos = new MeusAtivos();
                meusAtivos.setId(id); 
                meusAtivos.setQtdAtivos(quantidade);
                meusAtivos.setValorPago(valor);
                meusAtivos.setCotacao(cotacao);
                meusAtivos.setTotalDinheiroAtivos(totalPago);
                meusAtivos.setAtivo(ativo);
                meusAtivos.setConta(conta);
                meus.add(meusAtivos);
  
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return meus;
    }
    
    public void exclui(long id) {
        String sql = "delete from meus_ativos where idativo = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);
            
            stmt.execute();
            
            System.out.println("Elemento excluído com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    
    private PreparedStatement createPreparedStatement(Connection con, long id) throws SQLException {
        String sql = "select * from meus_ativos where ccorrente_meus_atv = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setLong(1, id);
        return ps;
    }
    
}
