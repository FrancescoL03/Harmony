package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Carrello;
import model.Carrello.ProdottoQuantita;
import model.Composizione;
import model.ComposizioneDAO;
import model.Ordine;
import model.OrdineDAO;
import model.Utente;


@WebServlet("/Checkout")
public class CheckoutServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private OrdineDAO ordineDAO = new OrdineDAO();
	private ComposizioneDAO composizioneDAO= new ComposizioneDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Carrello cart = (Carrello) request.getSession().getAttribute("carrello");
		Ordine ordine = new Ordine();
		
		Utente utente = (Utente) request.getSession().getAttribute("utente");
		int utenteID = utente.getID();
		String prezzoTotale = request.getParameter("prezzoTotale");
		String indirizzo = request.getParameter("indirizzo");
		String metodoDiPagamento = request.getParameter("metodoPagamento");
		String numeroDiCarta = request.getParameter("numeroDiCarta");
		String costoSpedizione = request.getParameter("costoSpedizione");
		String telefono = request.getParameter("telefono");
		
		ordine.setUtenteID(utenteID);
		ordine.setPrezzoTotale(Long.parseLong(prezzoTotale));
		ordine.setIndirizzo(indirizzo);
		ordine.setMetodoDiPagamento(metodoDiPagamento);
		ordine.setNumeroDiCarta(numeroDiCarta);
		ordine.setCostoSpedizione(Long.parseLong(costoSpedizione));
		ordine.setNumeroTelefono(telefono);
		
		//INSERT in Ordine tutte queste cose sopra
		int idOrdine = ordineDAO.doSave(ordine);
		if(idOrdine == -1)
			throw new MyServletException("Errore nel checkout.");
				
		ArrayList<ProdottoQuantita> array = new ArrayList<>(cart.getProdotti());
		
		for(int i=0; i<array.size();i++) {
			ProdottoQuantita prodQuant = array.get(i);
			Composizione comp = new Composizione();
			comp.setOrdineID(idOrdine);
			comp.setProdottoID(prodQuant.getProdotto().getID());
			comp.setQuantita(prodQuant.getQuantita());
			composizioneDAO.doSave(comp);
		}
		
		request.getSession().removeAttribute("carrello");
		request.getRequestDispatcher("WEB-INF/ordineSuccess.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
