package model;

import java.sql.Date;

public class Utente {

	@Override
	public String toString() {
		return "Utente [ID=" + ID + ", nome=" + nome + ", cognome=" + cognome + ", email=" + email + ", pwhash="
				+ pwhash + ", dataDiNascita=" + dataDiNascita + ", sesso=" + sesso + ", permessi=" + permessi + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID;
		result = prime * result + ((cognome == null) ? 0 : cognome.hashCode());
		result = prime * result + ((dataDiNascita == null) ? 0 : dataDiNascita.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((permessi == null) ? 0 : permessi.hashCode());
		result = prime * result + ((pwhash == null) ? 0 : pwhash.hashCode());
		result = prime * result + ((sesso == null) ? 0 : sesso.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Utente other = (Utente) obj;
		if (ID != other.ID)
			return false;
		if (cognome == null) {
			if (other.cognome != null)
				return false;
		} else if (!cognome.equals(other.cognome))
			return false;
		if (dataDiNascita == null) {
			if (other.dataDiNascita != null)
				return false;
		} else if (!dataDiNascita.equals(other.dataDiNascita))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (permessi == null) {
			if (other.permessi != null)
				return false;
		} else if (!permessi.equals(other.permessi))
			return false;
		if (pwhash == null) {
			if (other.pwhash != null)
				return false;
		} else if (!pwhash.equals(other.pwhash))
			return false;
		if (sesso == null) {
			if (other.sesso != null)
				return false;
		} else if (!sesso.equals(other.sesso))
			return false;
		return true;
	}



	public String getPassword() {
		return pwhash;
	}


	public void setPassword(String password) {
		this.pwhash = password;
	}


	public int getID() {
		return ID;
	}
	
	public void setID(int iD) {
		ID = iD;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public Date getDataDiNascita() {
		return dataDiNascita;
	}
	
	public void setDataDiNascita(Date dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}
	
	public String getSesso() {
		return sesso;
	}
	
	public void setSesso(String sesso) {
		this.sesso = sesso;
	}
	
	public void setPermessi(Boolean permission) {
		this.permessi = permission;
	}
	
	public Boolean getPermessi() {
		return permessi;
	}
	
	/*public boolean checkPassword(String text) {
		try {
			return PasswordHash.validatePassword(text, pwhash);
		} catch(Exception e) {
			return false;
		}
	}*/
	
	
	private int ID;
	private String nome;
	private String cognome;
	private String email;
	private String pwhash;
	private Date dataDiNascita;
	private String sesso;
	private Boolean permessi;
	
}
