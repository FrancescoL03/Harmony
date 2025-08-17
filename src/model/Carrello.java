package model;

import java.util.Collection;
import java.util.LinkedHashMap;

public class Carrello {

	public static class ProdottoQuantita {
		private Prodotto prodotto;
		private int quantita;

		private ProdottoQuantita(Prodotto prodotto, int quantita) {
			this.prodotto = prodotto;
			this.quantita = quantita;
		}

		public ProdottoQuantita() {

		}

		public int getQuantita() {
			return quantita;
		}

		public void setQuantita(int quantita) {
			this.quantita = quantita;
		}
		
		public void setProdotto(Prodotto prodotto) {
			this.prodotto  = prodotto;
		}

		public Prodotto getProdotto() {
			return prodotto;
		}
		
		public long getPrezzoLong() {
			return quantita * prodotto.getPrezzo();
		}
		
		public String getPrezzoTot() {
			return String.format("%d", getPrezzoLong());
		}
	}
		//------------------------
		
		private LinkedHashMap<Integer, ProdottoQuantita> prodotti = new LinkedHashMap<>();
		
		public Collection<ProdottoQuantita> getProdotti(){
			return prodotti.values();
		}
		
		public ProdottoQuantita get(int prodID) {
			return prodotti.get(prodID);
		}
		
		public void put(Prodotto prodotto, int quantita) {
			prodotti.put(prodotto.getID(), new ProdottoQuantita(prodotto, quantita));
		}
		
		public ProdottoQuantita remove(int prodID) {
			return prodotti.remove(prodID);
		}
		
		public long getPrezzoTotLong() {
			return prodotti.values().stream().mapToLong(p -> p.getPrezzoLong()).sum();
		}
		
		public String getPrezzoTot() {
			return String.format("%d", getPrezzoTotLong());
		}
		
		@Override
		public String toString() {
			return "Carrello [prodotti=" + prodotti + ", getProdotti()=" + getProdotti() + ", getPrezzoTotFloat()="
					+ getPrezzoTotLong() + ", getPrezzoTot()=" + getPrezzoTot() + ", hashCode()=" + hashCode()
					+ ", getClass()=" + getClass() + ", toString()=" + super.toString() + "]";
		}
		

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((prodotti == null) ? 0 : prodotti.hashCode());
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
			Carrello other = (Carrello) obj;
			if (prodotti == null) {
				if (other.prodotti != null)
					return false;
			} else if (!prodotti.equals(other.prodotti))
				return false;
			return true;
		}

	}
