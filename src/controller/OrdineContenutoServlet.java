package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Carrello.ProdottoQuantita;
import model.ComposizioneDAO;
import model.Ordine;
import model.OrdineDAO;
import model.Utente;

@WebServlet("/OrdineContenuto")
public class OrdineContenutoServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private ComposizioneDAO composizioneDAO = new ComposizioneDAO();
	private OrdineDAO ordineDAO = new OrdineDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utente u = null;
		if(request.getSession().getAttribute("utente") == null) {
			throw new MyServletException("Errore! Utente non loggato.");
		} else {
			u = (Utente) request.getSession().getAttribute("utente");
		}
		
		int ordineID = Integer.parseInt(request.getParameter("ordineID"));
		Ordine ord = ordineDAO.doRetrieveById(ordineID);
		
		if(!u.getPermessi() && ord.getUtenteID() != u.getID()) {
			throw new MyServletException("Errore! Utente non autorizzato!");
		}
		
		List<ProdottoQuantita> prodQuant = composizioneDAO.doRetrieveByOrdineId(ordineID);
		request.setAttribute("ordine", ord);
		request.setAttribute("prodottiOrdine", prodQuant);
		
		request.getRequestDispatcher("WEB-INF/ordineContenuto.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
