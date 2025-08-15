package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Ordine;
import model.OrdineDAO;
import model.Utente;

@WebServlet("/Ordini")
public class OrdiniServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private OrdineDAO ordineDAO = new OrdineDAO();


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utente u = null;
		if(request.getSession().getAttribute("utente") == null) {
			throw new MyServletException("Errore! Utente non loggato.");
		} else {
			u = (Utente) request.getSession().getAttribute("utente");
		}
		
		int utenteID = Integer.parseInt(request.getParameter("utenteID"));

		if(!u.getPermessi() && utenteID != u.getID()) {
			throw new MyServletException("Errore! Utente non autorizzato!");
		}
		
		List<Ordine> ordini = ordineDAO.doRetrieveByUtenteId(utenteID);
		request.setAttribute("ordini", ordini);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/ordini.jsp");
		requestDispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
