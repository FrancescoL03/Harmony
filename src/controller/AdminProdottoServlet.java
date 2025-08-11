package controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.Prodotto;
import model.ProdottoDAO;
import model.Utente;

@MultipartConfig
@WebServlet("/AdminProdotto")
public class AdminProdottoServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private final ProdottoDAO prodottoDAO = new ProdottoDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utente utente = (Utente) request.getSession().getAttribute("utente");
		if(utente == null || !utente.getPermessi()) {
			throw new MyServletException("Utente non autorizzato");
		}
		
		String idstr = request.getParameter("prodottoID");
		if(idstr != null) {
			if(request.getParameter("rimuovi")!=null) {
				//Eliminazione prodotto
				int id = Integer.parseInt(idstr);
				prodottoDAO.doDelete(id);
				Path path = Paths.get(getServletContext().getRealPath("/assets/img/prodotti/" + id + ".jpg"));
				try {
					Files.delete(path);
				} catch(Exception exc) {
					exc.printStackTrace();
				}
				request.setAttribute("notifica", "Prodotto rimosso con successo");
			} else {
				Prodotto prodotto;
				String nome = request.getParameter("nome");
				String descrizione = request.getParameter("descrizione");
				String prezzo = request.getParameter("prezzo");
				String categoria = request.getParameter("categoria");
				String path;
				
				if(nome!=null && descrizione!=null && prezzo!=null && categoria!=null) {
					//modifica o aggiunta prodotto
					prodotto = new Prodotto();
					prodotto.setNome(nome);
					prodotto.setDescrizione(descrizione);
					prodotto.setPrezzo(Long.parseLong(prezzo));
					prodotto.setCategoria(categoria);
					
					if(idstr.isEmpty()) {
						int id = prodottoDAO.doSave(prodotto);
						try {
							Part uploadedFile = request.getPart("immagine");
							String nomeFoto = uploadedFile.getSubmittedFileName();

							String extension = "";
							int i = nomeFoto.lastIndexOf('.');
							if (i > 0) {
								extension = nomeFoto.substring(i+1);
							}
							path = getServletContext().getRealPath("/assets/img/prodotti/" + id + "." + extension);
							InputStream content = uploadedFile.getInputStream();
							
							Files.copy(content, Paths.get(path));
						} catch (Exception e) {
							e.printStackTrace();
						}
						request.setAttribute("notifica", "Prodotto aggiunto con successo");
					} else {
						//modifica prodotto esistente
						prodotto.setID(Integer.parseInt(idstr));
						prodottoDAO.doUpdate(prodotto);
						request.setAttribute("notifica", "Prodotto modificato con successo");
					}
				} else {
					int id = Integer.parseInt(idstr);
					prodotto = prodottoDAO.doRetrieveById(id);
				}
				request.setAttribute("prodotto", prodotto);
			}
		}

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/adminprodotto.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
