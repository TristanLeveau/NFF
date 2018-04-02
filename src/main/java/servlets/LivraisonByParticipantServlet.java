package servlets;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import services.LivraisonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/listelivraisonbyparticipant")
public class LivraisonByParticipantServlet extends AbstractGenericServlet{

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine templateEngine = this.createTemplateEngine(req);
        WebContext context = new WebContext(req, resp, getServletContext());

        String email = req.getParameter("email");
        String motDePasse = req.getParameter("motDePasse");
        context.setVariable("listelivraisonsbyparticipant", LivraisonService.getInstance().ListeParticipantsByEmailMdp(email,motDePasse));
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
