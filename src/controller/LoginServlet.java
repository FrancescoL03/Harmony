package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Utente;
import model.UtenteDAO;

@WebServlet("/Login")
public class LoginServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private final UtenteDAO utenteDAO = new UtenteDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String email = request.getParameter("email");
    	String password = request.getParameter("password");
    	
    	Utente utente = null;
    	
    	if(email!=null && password != null) {
    		utente = utenteDAO.doRetrieveByUsernamePassword(email, password);
    	}
    	
    	if(utente == null) {
    		throw new MyServletException("Username e/o password non validi!");
    	}
    	
    	request.getSession().setAttribute("utente", utente);
    	
    	String dest = request.getHeader("referer");
		if (dest == null || dest.contains("/AdminUtenti") || dest.contains("/AdminUtenteDelete") || dest.contains("/Login") || dest.contains("/Logout") 
				|| dest.contains("/AdminUtenti") || dest.contains("/Ordini") || dest.contains("/Riepilogo") || dest.contains("/Registrazione") 
				|| dest.contains("/RegistrazioneForm") || dest.contains("/AdminProdotto") || dest.contains("AdminCategoria") 
				|| dest.contains("/ProfiloModifica") || dest.contains("/ProfiloUpdate") || dest.contains("/ProfiloUtente") 
				|| dest.trim().isEmpty()) {
    		dest = ".";
    	}
    	response.sendRedirect(dest);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
