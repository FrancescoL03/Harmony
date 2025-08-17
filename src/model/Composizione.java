package model;

public class Composizione {
	
	public int getProdottoID() {
		return prodottoID;
	}
	
	public void setProdottoID(int prodottoID) {
		this.prodottoID = prodottoID;
	}
	
	public int getOrdineID() {
		return ordineID;
	}
	
	public void setOrdineID(int ordineID) {
		this.ordineID = ordineID;
	}
	
	public int getQuantita() {
		return quantita;
	}
	
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	
	int prodottoID;
	int ordineID;
	int quantita;
}
