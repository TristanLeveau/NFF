package servlets;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import services.LivraisonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/listelivraisonbyparticipant")
public class LivraisonByParticipantServlet extends AbstractGenericServlet{

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        TemplateEngine templateEngine = this.createTemplateEngine(req);
        WebContext context = new WebContext(req, resp, getServletContext());

        // SESSION
        HttpSession session = req.getSession();
        String email = session.getAttribute("email").toString();
        String motDePasse = session.getAttribute("motDePasse").toString();


        context.setVariable("listelivraisonsbyparticipant", LivraisonService.getInstance().ListeParticipantsByEmailMdp(email,motDePasse));
        try {
            context.setVariable("client", LivraisonService.getInstance().getClientByEmailMdp(email,motDePasse));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (LivraisonService.getInstance().ListeParticipantsByEmailMdp(email,motDePasse) == null) {
            resp.sendRedirect("erreur");
        }
        templateEngine.process("listelivraisonbyparticipant", context, resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);


    }
}
