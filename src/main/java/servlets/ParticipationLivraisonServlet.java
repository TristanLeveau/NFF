package servlets;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import pojos.ParticipantALivrer;
import services.LivraisonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/participationlivraison")
public class ParticipationLivraisonServlet extends AbstractGenericServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine templateEngine = this.createTemplateEngine(req);
        WebContext context = new WebContext(req, resp, getServletContext());
        if(req.getSession().getAttribute("utilisateurCreationError") != null) {
            context.setVariable("errorMessage", req.getSession().getAttribute("utilisateurCreationError"));
            context.setVariable("participant",(ParticipantALivrer) req.getSession().getAttribute("utilisateurCreationData"));

            req.getSession().removeAttribute("ParticipantCreationError");
            req.getSession().removeAttribute("ParticipantCreationData");
        } else {
            context.setVariable("participant", new ParticipantALivrer( 0,null,null, null, null));
        }

        templateEngine.process("participationlivraison", context, resp.getWriter());
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nom = req.getParameter("nom");
        String prenom = req.getParameter("prenom");
        String email = req.getParameter("email");
        String motDePasse = req.getParameter("motDePasse");
        Integer idLivraison = Integer.parseInt(req.getParameter("idlivraison"));
        
        ParticipantALivrer newParticipantALivrer = new ParticipantALivrer(null, nom, prenom,email, motDePasse);

        try {
            LivraisonService.getInstance().addParticipantALivrer(newParticipantALivrer, idLivraison);
            resp.sendRedirect("validationlivraison");
        }

        catch (IllegalArgumentException e) {
            req.getSession().setAttribute("ParticipationCreationError", e.getMessage());
            req.getSession().setAttribute("ParticipationCreationData", newParticipantALivrer);
            resp.sendRedirect("home");
        }

    }

}
