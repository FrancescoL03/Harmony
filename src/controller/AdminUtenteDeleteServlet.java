package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Utente;
import model.UtenteDAO;

@WebServlet("/AdminUtenteDelete")
public class AdminUtenteDeleteServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private final UtenteDAO utenteDAO = new UtenteDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		checkAdmin(request);
		
		Utente u = null;
		
		String utenteIDStr = request.getParameter("utenteID");
		if(utenteIDStr != null) {
			int utenteID = Integer.parseInt(utenteIDStr);
			u = utenteDAO.doRetrieveById(utenteID);
			if(u == null || u.getPermessi()) {
				throw new MyServletException("Errore! Non si può rimuovere un admin.");
			}
			utenteDAO.doDelete(utenteID);
		}
		
		List<Utente> utenti = utenteDAO.doRetrieveAll(0, 10);
		request.setAttribute("utenti", utenti);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/adminutenti.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
