package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Utente;
import model.UtenteDAO;


@WebServlet("/ProfiloUtente")
public class ProfiloUtenteServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private final UtenteDAO utenteDAO = new UtenteDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utente utente = null;
		String utenteIdStr = request.getParameter("utenteID");
		
		if(utenteIdStr == null || request.getSession().getAttribute("utente") == null) {
			throw new MyServletException("Errore!");
		}
		int utenteID = Integer.parseInt(utenteIdStr);
		
		utente = utenteDAO.doRetrieveById(utenteID);
		
		String sesso;
		
		if(utente.getSesso().equalsIgnoreCase("m"))
			sesso = "Uomo";
		else if(utente.getSesso().equalsIgnoreCase("f"))
			sesso= "Donna";
		else
			sesso = "Altro";
		
		if(utente!=null) {
			request.setAttribute("sesso", sesso);
			request.setAttribute("utente", utente);
		}
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/profiloutente.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
