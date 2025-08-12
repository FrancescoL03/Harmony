package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Logout")
public class LogoutServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().removeAttribute("utente");

		String dest = request.getHeader("referer");
		if (dest == null || dest.contains("/Logout") || dest.contains("/AdminUtenteDelete") || dest.contains("/AdminUtenti") 
				|| dest.contains("/OrdineContenuto") || dest.contains("/Ordini") || dest.contains("/Riepilogo") 
				|| dest.contains("/Registrazione") || dest.contains("/RegistrazioneForm") || dest.contains("/AdminProdotto") 
				|| dest.contains("AdminCategoria") || dest.contains("/ProfiloUtente") || dest.contains("/Login") 
				|| dest.contains("/ProfiloModifica") || dest.contains("/ProfiloUpdate") || dest.trim().isEmpty()) {
			dest = ".";
		}
		response.sendRedirect(dest);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
