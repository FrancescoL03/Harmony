package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Carrello;
import model.Utente;


@WebServlet("/Riepilogo")
public class RiepilogoServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utente u = (Utente) request.getSession().getAttribute("utente");
		if(u==null) {
			throw new MyServletException("Errore! Utente non loggato!");
		}
		
		Carrello cart = (Carrello) request.getSession().getAttribute("carrello");
		if(cart.getPrezzoTotLong() < 999) {
			request.setAttribute("costoSpedizione", 49);
		} else
			request.setAttribute("costoSpedizione", 0);
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/checkout.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
