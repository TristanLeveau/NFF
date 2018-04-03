package servlets;

import pojos.Livraison;
import pojos.Participant;
import services.LivraisonService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/livraisondetail")
public class LivraisonDetailsServlet extends AbstractGenericServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		WebContext context = new WebContext(req, resp, getServletContext());

		Integer idLivraison = Integer.parseInt(req.getParameter("idlivraison"));
		Livraison livraison = LivraisonService.getInstance().getLivraison(idLivraison);
		HttpSession session = req.getSession();
		session.setAttribute("livraison", livraison);


		context.setVariable("livraison", livraison);
		context.setVariable("participants", LivraisonService.getInstance().ListeParticipants(idLivraison));
		if (livraison == null) {
			resp.sendRedirect("home");
		}

		TemplateEngine templateEngine = this.createTemplateEngine(req);


		templateEngine.process("livraisondetail", context, resp.getWriter());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
		Integer idLivraison = Integer.parseInt(req.getParameter("idlivraison"));
		String date = req.getParameter("date");
		HttpSession session = req.getSession();
		session.setAttribute("idlivraison",idLivraison);
		session.setAttribute("date",date);
	}
}



