package controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Utente;
import model.UtenteDAO;


@WebServlet("/ProfiloUpdate")
public class ProfiloUpdateServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private final UtenteDAO utenteDAO = new UtenteDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utente utente = (Utente) request.getSession().getAttribute("utente");
		String idStr = request.getParameter("utenteID");
		
		if(utente == null || utente.getID()!=utenteDAO.doRetrieveById(Integer.parseInt(idStr)).getID()) {
			throw new MyServletException("Errore! Utente non loggato.");
		}
		
		if(idStr!=null) {
			Utente user;
			String nome = request.getParameter("nome");
			String cognome = request.getParameter("cognome");
			String password = request.getParameter("password");
			String passwordConferma = request.getParameter("passwordConferma");
			String dataDiNascita = request.getParameter("dataDiNascita");
			String sesso = request.getParameter("sesso");
			
			if(nome!=null && cognome!=null && password!=null && passwordConferma.equals(password) && dataDiNascita!=null && sesso!=null) {
				user = new Utente();
				user.setNome(nome);
				user.setCognome(cognome);
				user.setPassword(password);
				user.setDataDiNascita(Date.valueOf(dataDiNascita));
				user.setSesso(sesso);
				user.setID(Integer.parseInt(idStr));
				
				utenteDAO.doUpdate(user);
				
				if(user.getSesso().equalsIgnoreCase("m"))
					sesso = "Uomo";
				else if(user.getSesso().equalsIgnoreCase("f"))
					sesso= "Donna";
				else
					sesso = "Altro";
				
				request.setAttribute("sesso", sesso);
				request.setAttribute("utente", utenteDAO.doRetrieveById(Integer.parseInt(idStr)));
				request.setAttribute("notifica", "Account modificato con successo.");
				request.getRequestDispatcher("WEB-INF/profiloutente.jsp").forward(request, response);
			}
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
