package servlets;

import pojos.Participant;
import pojos.Livraison;
import services.LivraisonService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/inscription")
public class InscriptionServlet extends AbstractGenericServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine templateEngine = this.createTemplateEngine(req);
        WebContext context = new WebContext(req, resp, getServletContext());
        if(req.getSession().getAttribute("utilisateurCreationError") != null) {
            context.setVariable("errorMessage", req.getSession().getAttribute("utilisateurCreationError"));
            context.setVariable("participant",(Participant) req.getSession().getAttribute("utilisateurCreationData"));

            req.getSession().removeAttribute("ParticipantCreationError");
            req.getSession().removeAttribute("ParticipantCreationData");
        } else {
            context.setVariable("participant", new Participant( null,null,null, null, null));
        }

        templateEngine.process("inscription", context, resp.getWriter());
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         String nom = req.getParameter("nom");
         String prenom = req.getParameter("prenom");
         String email = req.getParameter("email");
         String motDePasse = req.getParameter("motDePasse");

        Participant newParticipant = new Participant(null, nom, prenom,email, motDePasse);

        try {
            LivraisonService.getInstance().addParticipant(newParticipant);
            resp.sendRedirect("home");
        }

        catch (IllegalArgumentException e) {
            req.getSession().setAttribute("ParticipationCreationError", e.getMessage());
            req.getSession().setAttribute("ParticipationCreationData", newParticipant);
            resp.sendRedirect("home");
        }

    }

}
