package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Carrello;
import model.Carrello.ProdottoQuantita;
import model.ProdottoDAO;


@WebServlet("/Carrello")
public class CarrelloServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private final ProdottoDAO prodottoDAO = new ProdottoDAO();

		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			
			HttpSession session = request.getSession();
			Carrello carrello = (Carrello) session.getAttribute("carrello");
			
			//Carrello non esistente -> lo creo
			if (carrello == null) {
				carrello = new Carrello();
				session.setAttribute("carrello", carrello);
			}
			
			String prodottoIDStr = request.getParameter("prodottoID");
			if (prodottoIDStr != null) {
				int prodottoID = Integer.parseInt(prodottoIDStr);
				
				//Verifico la quantità da aggiungere (se non esiste lo rimuovo)
				String quantitaStr = request.getParameter("quantita");
				if (quantitaStr != null) {
					int quantita = Integer.parseInt(quantitaStr);

					ProdottoQuantita prodQuant = carrello.get(prodottoID);
					
					//Se prodQuant != null significa che il prodotto era nel carrello -> aumento la quantità, altrimenti la setto
					if (prodQuant != null) {
						prodQuant.setQuantita(prodQuant.getQuantita() + quantita);
					} else {
						carrello.put(prodottoDAO.doRetrieveById(prodottoID), quantita);
					}
					
					//SetNumStr = 0 -> rimuovi elemento dal carrello
				} else {
					String setNumStr = request.getParameter("setNum");
					if (setNumStr != null) {
						int setNum = Integer.parseInt(setNumStr);
						if (setNum <= 0) {
							carrello.remove(prodottoID);
						}
					}
				}
			}

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/carrello.jsp");
			requestDispatcher.forward(request, response);
		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			doGet(request, response);
		}
}
