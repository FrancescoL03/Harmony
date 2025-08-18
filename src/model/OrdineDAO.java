package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class OrdineDAO {
	
	public int doSave(Ordine o) {
		try(Connection conn = ConnPool.getConnection()){
			PreparedStatement ps = conn.prepareStatement("INSERT INTO ordine (utente, data, prezzo_totale,indirizzo, "
					+ "metodo_di_pagamento, numero_di_carta, costo_spedizione, numero_di_telefono) values (?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			
			ps.setInt(1, o.getUtenteID());
			ps.setDate(2,new Date(new GregorianCalendar().getTimeInMillis()));
			ps.setLong(3, o.getPrezzoTotale());
			ps.setString(4, o.getIndirizzo());
			ps.setString(5, o.getMetodoDiPagamento());
			ps.setString(6, o.getNumeroDiCarta());
			ps.setLong(7, o.getCostoSpedizione());
			ps.setString(8, o.getNumeroTelefono());
			
			
			if(ps.executeUpdate() != 1) {
				throw new RuntimeException("INSERT error!");
			}
			
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int id = rs.getInt(1);
			o.setID(id);
			return id;
		} catch(Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public Ordine doRetrieveById(int id){
		PreparedStatement ps = null;
		Ordine bean = new Ordine();
		
		try (Connection conn = ConnPool.getConnection()){
			ps = conn.prepareStatement("select * from ordine where id = ?");
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				bean.setID(rs.getInt("id"));
				bean.setUtenteID(rs.getInt("utente"));
				bean.setData(rs.getDate("data"));
				bean.setPrezzoTotale(rs.getLong("prezzo_totale"));
				bean.setIndirizzo(rs.getString("indirizzo"));
				bean.setMetodoDiPagamento(rs.getString("metodo_di_pagamento"));
				bean.setNumeroDiCarta(rs.getString("numero_di_carta"));
				bean.setCostoSpedizione(rs.getLong("costo_spedizione"));
				bean.setNumeroTelefono(rs.getString("numero_di_telefono"));
				return bean;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public List<Ordine> doRetrieveByUtenteId(int utenteID){
		PreparedStatement ps = null;
		
		
		try (Connection conn = ConnPool.getConnection()){
			ps = conn.prepareStatement("select * from ordine where utente = ?");
			ps.setInt(1, utenteID);
			ArrayList<Ordine> ordini = new ArrayList<>();
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Ordine bean = new Ordine();
				bean.setID(rs.getInt("id"));
				bean.setUtenteID(rs.getInt("utente"));
				bean.setData(rs.getDate("data"));
				bean.setPrezzoTotale(rs.getLong("prezzo_totale"));
				bean.setIndirizzo(rs.getString("indirizzo"));
				bean.setMetodoDiPagamento(rs.getString("metodo_di_pagamento"));
				bean.setNumeroDiCarta(rs.getString("numero_di_carta"));
				bean.setCostoSpedizione(rs.getLong("costo_spedizione"));
				bean.setNumeroTelefono(rs.getString("numero_di_telefono"));
				ordini.add(bean);
			}
			return ordini;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
