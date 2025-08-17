package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UtenteDAO;


@WebServlet("/VerificaEmail")
public class VerificaEmailServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private UtenteDAO utenteDAO = new UtenteDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/xml");
		String email = request.getParameter("email");
		if(email != null && utenteDAO.doRetrieveByEmail(email) == null) {
			response.getWriter().append("<ok/>");
		} else if(utenteDAO.doRetrieveByEmail(email) != null) {
			response.getWriter().append("<used/>");
		}
		else {
			response.getWriter().append("<no/>");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
