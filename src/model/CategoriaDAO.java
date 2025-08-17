package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Categoria;

public class CategoriaDAO {

	public Categoria doRetrieveById(int id) throws ClassNotFoundException, SQLException {
		
		try (Connection conn = ConnPool.getConnection()){
			PreparedStatement preparedStatement = conn.prepareStatement("SELECT id, name FROM Categoria WHERE id=?");
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()) {
				Categoria cb = new Categoria();
				cb.setNome(rs.getString("nome"));
				cb.setID(rs.getInt("id"));
				return cb;
			}
			else return null;
		} catch(Exception e) {
			throw new RuntimeException();
		}
	}
	
	public List<Categoria> doRetrieveAll() {
		try (Connection con = ConnPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement("SELECT id, nome FROM categoria");
			ArrayList<Categoria> categorie = new ArrayList<>();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Categoria c = new Categoria();
				c.setID(rs.getInt(1));
				c.setNome(rs.getString(2));
				categorie.add(c);
			}
			return categorie;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Categoria> doRetrieveByProdotto(int idProdotto) {
		try (Connection con = ConnPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement("SELECT categoria.id, categoria.nome FROM categoria JOIN prodotto on categoria.id=prodotto.categoria WHERE prodotto.id=?");
			ps.setInt(1, idProdotto);
			ArrayList<Categoria> categorie = new ArrayList<>();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Categoria c = new Categoria();
				c.setID(rs.getInt(1));
				c.setNome(rs.getString(2));
				categorie.add(c);
			}
			return categorie;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void doSave(Categoria categoria) {
		try (Connection con = ConnPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement("INSERT INTO categoria (nome) VALUES(?)", Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, categoria.getNome());
			if (ps.executeUpdate() != 1) {
				throw new RuntimeException("INSERT error.");
			}
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			categoria.setID(rs.getInt(1));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void doDelete(int id) {
		try (Connection con = ConnPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement("DELETE FROM categoria WHERE id=?");
			ps.setInt(1, id);
			if (ps.executeUpdate() != 1) {
				throw new RuntimeException("DELETE error.");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void doUpdate(Categoria categoria) {
		try (Connection con = ConnPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement("UPDATE categoria SET nome=? WHERE id=?");
			ps.setString(1, categoria.getNome());
			ps.setInt(2, categoria.getID());
			if (ps.executeUpdate() != 1) {
				throw new RuntimeException("UPDATE error.");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
