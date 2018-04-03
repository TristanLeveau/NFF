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
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/livraisonsconnexion")
public class MesLivraisonsServlet extends AbstractGenericServlet{

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        TemplateEngine templateEngine = this.createTemplateEngine(req);
        WebContext context = new WebContext(req, resp, getServletContext());
        context.setVariable("email", new String());
        context.setVariable("motDePasse", new String());


        templateEngine.process("livraisonsconnexion", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String motDePasse = req.getParameter("motDePasse");

        HttpSession session = req.getSession();

        session.setAttribute("email",email);
        session.setAttribute("motDePasse",motDePasse);

        resp.sendRedirect("listelivraisonbyparticipant");

    }
}
