package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Utente;
import model.UtenteDAO;

@WebServlet("/ProfiloModifica")
public class ProfiloModificaServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private final UtenteDAO utenteDAO = new UtenteDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utente utente = (Utente) request.getSession().getAttribute("utente");
		String idStr = request.getParameter("utenteID");
		
		if(utente == null || utente.getID()!=utenteDAO.doRetrieveById(Integer.parseInt(idStr)).getID()) {
			throw new MyServletException("Utente non autorizzato.");
		}
		
		if(idStr != null) {
			if(request.getParameter("rimuovi")!=null) {
				//Eliminazione utente
				int id = Integer.parseInt(idStr);
				utenteDAO.doDelete(id);
				request.getSession().setAttribute("utente", null);
				
				String dest = request.getHeader("referer");
				if (dest == null || dest.contains("/ProfiloModifica") || dest.contains("/ProfiloUpdate") || dest.contains("/ProfiloUtente") 
						|| dest.trim().isEmpty()) {
		    		dest = ".";
		    	}
		    	response.sendRedirect(dest);
			} else if(request.getParameter("modifica")!=null){
				
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/profiloutenteupdate.jsp");
				requestDispatcher.forward(request, response);
			}
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
