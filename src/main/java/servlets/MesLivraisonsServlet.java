package servlets;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import pojos.Livraison;
import pojos.Participant;
import services.LivraisonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/livraisonsconnexion")
public class MesLivraisonsServlet extends AbstractGenericServlet{
    public final String champEmail = "email";
    public final String champMotDePasse = "motDePasse";

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        TemplateEngine templateEngine = this.createTemplateEngine(req);
        WebContext context = new WebContext(req, resp, getServletContext());

        templateEngine.process("livraisonsconnexion", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter(champEmail);
        String motDePasse = req.getParameter(champMotDePasse);
        req.setAttribute("email",email);
        req.setAttribute("motDePasse", motDePasse);
        resp.sendRedirect("listelivraisonbyparticipant");

    }
}
