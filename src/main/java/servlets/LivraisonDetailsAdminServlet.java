package servlets;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import pojos.Livraison;
import services.CommandeService;
import services.LivraisonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// Servlet : Détails des livraisons côté admin.
@WebServlet("/livraisondetailsadmin")
public class LivraisonDetailsAdminServlet extends AbstractGenericServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        WebContext context = new WebContext(req, resp, getServletContext());


        Integer idLivraison = Integer.parseInt(req.getParameter("idlivraison"));
        Livraison livraison = LivraisonService.getInstance().getLivraison(idLivraison);
        context.setVariable("livraison", livraison);
        context.setVariable("commandes", CommandeService.getInstance().listCommandeByIdLivraison(idLivraison));
        if (livraison == null) {
            resp.sendRedirect("livraisonvalidee");
        }

        TemplateEngine templateEngine = this.createTemplateEngine(req);


        templateEngine.process("livraisondetailsadmin", context, resp.getWriter());    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
