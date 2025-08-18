package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UtenteDAO {
	public List<Utente> doRetrieveAll(int offset, int limit) {
		try (Connection con = ConnPool.getConnection()) {
			PreparedStatement ps = con
					.prepareStatement("SELECT id, nome, cognome, password, email, dataDiNascita, sesso, amministratore FROM utente LIMIT ?, ?");
			ps.setInt(1, offset);
			ps.setInt(2, limit);
			ArrayList<Utente> utenti = new ArrayList<>();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Utente u = new Utente();
				u.setID(rs.getInt(1));
				u.setNome(rs.getString(2));
				u.setCognome(rs.getString(3));
				u.setPassword(rs.getString(4));
				u.setEmail(rs.getString(5));
				u.setDataDiNascita(Date.valueOf(rs.getString(6)));
				u.setSesso(rs.getString(7));
				u.setPermessi(rs.getBoolean(8));
				utenti.add(u);
			}
			return utenti;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Utente doRetrieveById(int utenteID) {
		try (Connection con = ConnPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"SELECT id, nome, cognome, password, email, dataDiNascita, sesso, amministratore FROM utente WHERE id = ?");
			ps.setInt(1, utenteID);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Utente u = new Utente();
				u.setID(rs.getInt(1));
				u.setNome(rs.getString(2));
				u.setCognome(rs.getString(3));
				u.setPassword(rs.getString(4));
				u.setEmail(rs.getString(5));
				u.setDataDiNascita(Date.valueOf(rs.getString(6)));
				u.setSesso(rs.getString(7));
				u.setPermessi(rs.getBoolean(8));
				return u;
			}
			return null;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Utente doRetrieveByUsernamePassword(String username, String password) {
		try (Connection con = ConnPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"SELECT id, nome, cognome, password, email, dataDiNascita, sesso, amministratore FROM utente WHERE email=? AND password=?");
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Utente u = new Utente();
				u.setID(rs.getInt(1));
				u.setNome(rs.getString(2));
				u.setCognome(rs.getString(3));
				u.setPassword(rs.getString(4));
				u.setEmail(rs.getString(5));
				u.setDataDiNascita(Date.valueOf(rs.getString(6)));
				u.setSesso(rs.getString(7));
				u.setPermessi(rs.getBoolean(8));
				return u;
			}
			return null;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Utente doRetrieveByEmail(String email) {
		try (Connection con = ConnPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"SELECT id, nome, cognome, password, email, dataDiNascita, sesso, amministratore FROM utente WHERE email=?");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Utente u = new Utente();
				u.setID(rs.getInt(1));
				u.setNome(rs.getString(2));
				u.setCognome(rs.getString(3));
				u.setPassword(rs.getString(4));
				u.setEmail(rs.getString(5));
				u.setDataDiNascita(Date.valueOf(rs.getString(6)));
				u.setSesso(rs.getString(7));
				u.setPermessi(rs.getBoolean(8));
				return u;
			}
			return null;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void doSave(Utente utente) {
		try(Connection con = ConnPool.getConnection()){
			PreparedStatement ps = con.prepareStatement("INSERT INTO utente (nome, cognome, email, password, dataDiNascita, sesso) values (?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, utente.getNome());
			ps.setString(2, utente.getCognome());
			ps.setString(3, utente.getEmail());
			ps.setString(4, utente.getPassword());
			ps.setDate(5, utente.getDataDiNascita());
			ps.setString(6, utente.getSesso());
			
			if (ps.executeUpdate() != 1) {
				throw new RuntimeException("INSERT error.");
			}
			
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			utente.setID(rs.getInt(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void doDelete(int utenteID) {
		try(Connection con = ConnPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement("DELETE FROM utente WHERE id = ?");
			ps.setInt(1, utenteID);
			
			if(ps.executeUpdate() != 1) {
				throw new RuntimeException("DELETE error.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void doUpdate(Utente user) {
		try(Connection con = ConnPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement("UPDATE utente SET id=?, nome=?, cognome=?, password=?, dataDiNascita=?"
					+ ", sesso=? WHERE id = ?;");
			ps.setInt(1, user.getID());
			ps.setString(2, user.getNome());
			ps.setString(3, user.getCognome());
			ps.setString(4, user.getPassword());
			ps.setDate(5, user.getDataDiNascita());
			ps.setString(6, user.getSesso());
			ps.setInt(7, user.getID());
			
			if(ps.executeUpdate() != 1) {
				throw new RuntimeException("UPDATE error.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
}
