package servlets;

import pojos.Semestre;
//import services.LivraisonService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import services.LivraisonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

// Servlet : Page d'entrée sur le site, liste des livraisons
@WebServlet("/home")
public class HomeServlet extends AbstractGenericServlet {


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		TemplateEngine templateEngine = this.createTemplateEngine(req);
		WebContext context = new WebContext(req, resp, getServletContext());

		if (session.getAttribute("user")==null){
			resp.sendRedirect("deconnexion");
		}

		context.setVariable("livraisons", LivraisonService.getInstance().listAllLivraisons());

		templateEngine.process("home", context, resp.getWriter());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();

		if (session.getAttribute("user")==null){
			resp.sendRedirect("deconnexion");
		}
	}
	
}

