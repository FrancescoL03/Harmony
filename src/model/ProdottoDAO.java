package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Prodotto;

public class ProdottoDAO {

	public synchronized Prodotto doRetrieveById(int id) {
		
		PreparedStatement ps = null;
		Prodotto bean = new Prodotto();
		
		try (Connection conn = ConnPool.getConnection()){
			ps = conn.prepareStatement("SELECT prodotto.id, prodotto.nome, prodotto.prezzo, prodotto.categoria, prodotto.descrizione FROM prodotto JOIN Categoria ON Categoria.ID=prodotto.categoria WHERE prodotto.id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				bean.setID(rs.getInt("id"));
				bean.setNome(rs.getString("nome"));
				bean.setCategoria(rs.getString("categoria"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setPrezzo(rs.getLong("prezzo"));
				return bean;
			} 
			return null;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Prodotto> doRetrieveAll(int offset, int limit) {
		try (Connection con = ConnPool.getConnection()) {
			PreparedStatement ps = con
					.prepareStatement("SELECT id, nome, descrizione, prezzo FROM prodotto LIMIT ?, ?");
			ps.setInt(1, offset);
			ps.setInt(2, limit);
			ArrayList<Prodotto> prodotti = new ArrayList<>();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Prodotto p = new Prodotto();
				p.setID(rs.getInt(1));
				p.setNome(rs.getString(2));
				p.setDescrizione(rs.getString(3));
				p.setPrezzo(rs.getLong(4));
				prodotti.add(p);
			}
			return prodotti;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Prodotto> doRetrieveByCategoria(int categoria, int offset, int limit) {
		try (Connection con = ConnPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"SELECT prodotto.id, prodotto.nome, prodotto.descrizione, prezzo FROM prodotto JOIN categoria on prodotto.categoria=categoria.id WHERE categoria.id=? LIMIT ?, ?");
			ps.setInt(1, categoria);
			ps.setInt(2, offset);
			ps.setInt(3, limit);
			ArrayList<Prodotto> prodotti = new ArrayList<>();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Prodotto p = new Prodotto();
				p.setID(rs.getInt(1));
				p.setNome(rs.getString(2));
				p.setDescrizione(rs.getString(3));
				p.setPrezzo(rs.getLong(4));
				prodotti.add(p);
			}
			return prodotti;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Prodotto> doRetrieveByMatch(String against, int offset, int limit) {
		try (Connection con = ConnPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"SELECT id, nome, descrizione, prezzo FROM prodotto WHERE MATCH(nome, descrizione) AGAINST(?) LIMIT ?, ?");
			ps.setString(1, against);
			ps.setInt(2, offset);
			ps.setInt(3, limit);
			ArrayList<Prodotto> prodotti = new ArrayList<>();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Prodotto p = new Prodotto();
				p.setID(rs.getInt(1));
				p.setNome(rs.getString(2));
				p.setDescrizione(rs.getString(3));
				p.setPrezzo(rs.getLong(4));
				prodotti.add(p);
			}
			return prodotti;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public int doSave(Prodotto prodotto) {
		try(Connection con = ConnPool.getConnection()){
			PreparedStatement ps = con.prepareStatement("INSERT INTO prodotto (nome, prezzo, descrizione, categoria) VALUES(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, prodotto.getNome());
			ps.setFloat(2, prodotto.getPrezzo());
			ps.setString(3, prodotto.getDescrizione());
			ps.setInt(4, Integer.parseInt(prodotto.getCategoria()));
			if(ps.executeUpdate() != 1) {
				throw new RuntimeException("INSERT error!");
			}
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int id = rs.getInt(1);
			prodotto.setID(id);
			return id;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public void doDelete(int id) {
		try(Connection con = ConnPool.getConnection()){
			PreparedStatement ps = con.prepareStatement("DELETE FROM prodotto WHERE id=?");
			ps.setInt(1, id);
			if(ps.executeUpdate() != 1) {
				throw new RuntimeException("DELETE Error!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void doUpdate(Prodotto prodotto) {
		try (Connection con = ConnPool.getConnection()) {
			PreparedStatement ps = con
					.prepareStatement("UPDATE prodotto SET nome=?, descrizione=?, prezzo=?, categoria=? WHERE id=?");
			ps.setString(1, prodotto.getNome());
			ps.setString(2, prodotto.getDescrizione());
			ps.setFloat(3, prodotto.getPrezzo());
			ps.setInt(4, Integer.parseInt(prodotto.getCategoria()));
			ps.setInt(5, prodotto.getID());
			
			if (ps.executeUpdate() != 1) {
				throw new RuntimeException("UPDATE error.");
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}