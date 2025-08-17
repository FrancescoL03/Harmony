package controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Utente;
import model.UtenteDAO;

@WebServlet("/Registrazione")
public class RegistrazioneServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private UtenteDAO utenteDAO = new UtenteDAO();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getSession().getAttribute("utente") != null) {
			throw new MyServletException("Utente loggato.");
		}
		
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String passwordConferma = request.getParameter("passwordConferma");
		String dataDiNascita = request.getParameter("dataDiNascita");
		String sesso = request.getParameter("sesso");
		
		if(!(nome!=null && nome.trim().length()>0 && nome.trim().length()<=30 && nome.matches("^[ a-zA-Z\u00c0-\u00ff\\.\\,\\-]+$"))) {
			throw new MyServletException("Errore nome");
		}
		
		if(!(cognome!=null && cognome.trim().length()>0 && cognome.trim().length()<=30 && cognome.matches("^[A-Za-z0-9]+(?:[ _-][A-Za-z0-9]+)*$"))) {
			throw new MyServletException("Errore cognome");
		}
		
		if(!(email!=null && email.trim().length() <=50 && email.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w+)+$"))) {
			throw new MyServletException("Errore email");
		}
		
		if(!(password!=null && password.length()>=8 && password.length()<=30 && !password.toUpperCase().equals(password) && !password.toLowerCase().equals(password) && password.matches(".*[0-9].*"))) {
			throw new MyServletException("Errore password");
		}
		
		if(!(passwordConferma.equals(password))) {
			throw new MyServletException("Password differenti");
		}
		
		if(!(dataDiNascita!=null)) {
			throw new MyServletException("Errore data di nascita");
		}
		
		if(!(sesso!=null)) {
			throw new MyServletException("Errore sesso");
		}
		
		Utente utente = new Utente();
		utente.setNome(nome);
		utente.setCognome(cognome);
		utente.setDataDiNascita(Date.valueOf(dataDiNascita));
		utente.setEmail(email);
		utente.setPassword(password);
		utente.setSesso(sesso);
		utente.setPermessi(false);
		utenteDAO.doSave(utente);
		request.getSession().setAttribute("utente", utente);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/registrazioneSuccesso.jsp");
		requestDispatcher.forward(request, response);
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
