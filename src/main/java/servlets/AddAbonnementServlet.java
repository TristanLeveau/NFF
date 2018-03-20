package servlets;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import pojos.Livraison;
import pojos.ParticipantALivrer;
import pojos.Semestre;
import services.LivraisonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/abonnement")
public class AddAbonnementServlet extends AbstractGenericServlet{
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine templateEngine = this.createTemplateEngine(req);
        WebContext context = new WebContext(req, resp, getServletContext());
        context.setVariable("semestres", Semestre.values());
        if(req.getSession().getAttribute("livraisonCreationError") != null) {
            context.setVariable("errorMessage", req.getSession().getAttribute("livraisonCreationError"));
            context.setVariable("livraison", (Livraison) req.getSession().getAttribute("livraisonCreationData"));

            req.getSession().removeAttribute("livraisonCreationError");
            req.getSession().removeAttribute("livraisonCreationData");
        } else {
            context.setVariable("participantabonnement", new ParticipantALivrer(null,null,null,null,null));
        }

        templateEngine.process("abonnement", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nom = req.getParameter("nom");
        String prenom = req.getParameter("prenom");
        String email = req.getParameter("email");
        String motDePasse = req.getParameter("motDePasse");

        ParticipantALivrer newParticipantALivrer = new ParticipantALivrer(null, nom, prenom,email, motDePasse);

        try {
            LivraisonService.getInstance().addAbonnement5(newParticipantALivrer);
            resp.sendRedirect("validationlivraison");
        }

        catch (IllegalArgumentException e) {
            req.getSession().setAttribute("ParticipationCreationError", e.getMessage());
            req.getSession().setAttribute("ParticipationCreationData", newParticipantALivrer);
            resp.sendRedirect("home");
        }

    }
}
