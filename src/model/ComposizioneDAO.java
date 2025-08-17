package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Carrello.ProdottoQuantita;

public class ComposizioneDAO {
	
	public void doSave(Composizione comp){
		try(Connection conn = ConnPool.getConnection()){
			PreparedStatement ps = conn.prepareStatement("INSERT INTO composizione (prodotto, ordine, quantita) VALUES (?, ?, ?)");
			ps.setInt(1, comp.getProdottoID());
			ps.setInt(2, comp.getOrdineID());
			ps.setInt(3, comp.getQuantita());
			if(ps.executeUpdate() != 1) {
				throw new RuntimeException("INSERT error!");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<ProdottoQuantita> doRetrieveByOrdineId(int ordineID){
		ProdottoDAO prodDAO= new ProdottoDAO();
		ArrayList<ProdottoQuantita> prodottiOrdine = new ArrayList<ProdottoQuantita>();
		
		PreparedStatement ps = null;
		try (Connection conn = ConnPool.getConnection()){
			ps = conn.prepareStatement("SELECT composizione.prodotto, composizione.quantita FROM ordine JOIN composizione ON ordine.id=composizione.ordine WHERE ordine.id=?");
			ps.setInt(1, ordineID);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				ProdottoQuantita bean = new ProdottoQuantita();
				bean.setQuantita(rs.getInt("quantita"));
				
				int idProdotto= rs.getInt("prodotto");
				Prodotto prod = prodDAO.doRetrieveById(idProdotto);
				bean.setProdotto(prod);
				prodottiOrdine.add(bean);
			} 
			return prodottiOrdine;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}		
	}
}
