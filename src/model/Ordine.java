package model;

import java.sql.Date;

public class Ordine {
	
	public int getID() {
		return ID;
	}
	
	public void setID(int iD) {
		ID = iD;
	}
	
	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data = data;
	}
	
	public long getPrezzoTotale() {
		return prezzoTotale;
	}
	
	public void setPrezzoTotale(long prezzoTotale) {
		this.prezzoTotale = prezzoTotale;
	}
	
	public String getMetodoDiPagamento() {
		return metodoDiPagamento;
	}
	
	public void setMetodoDiPagamento(String metodoDiPagamento) {
		this.metodoDiPagamento = metodoDiPagamento;
	}
	
	public long getCostoSpedizione() {
		return costoSpedizione;
	}
	
	public void setCostoSpedizione(long costoSpedizione) {
		this.costoSpedizione = costoSpedizione;
	}
	
	public String getNumeroTelefono() {
		return numeroTelefono;
	}
	
	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}
	
	public String getNumeroDiCarta() {
		return numeroDiCarta;
	}

	public void setNumeroDiCarta(String numeroDiCarta) {
		this.numeroDiCarta = numeroDiCarta;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public int getUtenteID() {
		return utenteID;
	}

	public void setUtenteID(int utenteID) {
		this.utenteID = utenteID;
	}

	private int ID;
	private int utenteID;
	private Date data;
	private long prezzoTotale;
	private String indirizzo;
	private String metodoDiPagamento;
	private String numeroDiCarta;
	private long costoSpedizione;
	private String numeroTelefono;
}
