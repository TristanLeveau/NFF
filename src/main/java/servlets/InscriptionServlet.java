package servlets;

import pojos.Client;
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


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine templateEngine = this.createTemplateEngine(req);
        WebContext context = new WebContext(req, resp, getServletContext());
        if(req.getSession().getAttribute("utilisateurCreationError") != null) {
            context.setVariable("errorMessage", req.getSession().getAttribute("utilisateurCreationError"));
            context.setVariable("client",(Client) req.getSession().getAttribute("utilisateurCreationData"));

            req.getSession().removeAttribute("ClientCreationError");
            req.getSession().removeAttribute("ClientCreationData");
        } else {
            context.setVariable("client", new Client( null,null,null, null, null));
        }

        templateEngine.process("inscription", context, resp.getWriter());
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         String nom = req.getParameter("nom");
         String prenom = req.getParameter("prenom");
         String email = req.getParameter("email");
         String motDePasse = req.getParameter("motDePasse");

        Client newClient = new Client(null, nom, prenom,email, motDePasse);

        try {
            LivraisonService.getInstance().addClient(newClient);
            resp.sendRedirect("home");
        }

        catch (IllegalArgumentException e) {
            req.getSession().setAttribute("ParticipationCreationError", e.getMessage());
            req.getSession().setAttribute("ParticipationCreationData", newClient);
            resp.sendRedirect("home");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
