package servlets;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import pojos.Livraison;
import pojos.ParticipantALivrer;
import services.LivraisonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/participation")
public class ParticipationServlet extends AbstractGenericServlet{

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer idLivraison = Integer.parseInt(req.getParameter("id"));
        String date = req.getParameter("date");
        TemplateEngine templateEngine = this.createTemplateEngine(req);
        WebContext context = new WebContext(req, resp, getServletContext());

        if(req.getSession().getAttribute("utilisateurCreationError") != null) {

            context.setVariable("errorMessage", req.getSession().getAttribute("utilisateurCreationError"));
            context.setVariable("livraison", (Livraison) req.getSession().getAttribute("utilisateurCreationData"));
            req.getSession().removeAttribute("livraisonCreationError");
            req.getSession().removeAttribute("livraisonCreationData");

        }

        else {

            context.setVariable("newParticipantALivrer", new ParticipantALivrer(0,idLivraison,null, null, null,date));

        }

        templateEngine.process("participation", context, resp.getWriter());
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String motDePasse = req.getParameter("motDePasse");
        Integer idLivraison = Integer.parseInt(req.getParameter("id"));
        String date = req.getParameter("date");

        ParticipantALivrer newParticipantALivrer;

        try {
             newParticipantALivrer = LivraisonService.getInstance().verificationInscription(email,motDePasse);
            LivraisonService.getInstance().addParticipantALivrer(newParticipantALivrer,idLivraison,date,newParticipantALivrer.getIdParticipant());
            resp.sendRedirect(String.format("livraisondetail?id=%d",idLivraison));
        }

        catch (IllegalArgumentException e) {
            req.getSession().setAttribute("ParticipationCreationError", e.getMessage());
            resp.sendRedirect(String.format("participation?id=%d",idLivraison));
        }

    }
}
