package servlets;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import pojos.Livraison;
import services.LivraisonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/livraisondetailadmin")
public class LivraisonDetailsAdminServlet extends AbstractGenericServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        WebContext context = new WebContext(req, resp, getServletContext());


        Integer idLivraison = Integer.parseInt(req.getParameter("id"));
        Livraison livraison = LivraisonService.getInstance().getLivraison(idLivraison);
        context.setVariable("livraison", livraison);
        context.setVariable("participants", LivraisonService.getInstance().ListeParticipants(idLivraison));
        if (livraison == null) {
            resp.sendRedirect("home");
        }

        TemplateEngine templateEngine = this.createTemplateEngine(req);


        templateEngine.process("livraisondetailadmin", context, resp.getWriter());
    }

}
