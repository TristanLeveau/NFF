package servlets;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import pojos.Commande;
import pojos.Livraison;
import pojos.User;
import services.CommandeService;
import services.LivraisonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/livraisondetails")
public class ParticipationLivraisonServlet extends AbstractGenericServlet{

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        TemplateEngine templateEngine = this.createTemplateEngine(req);

        WebContext context = new WebContext(req, resp, getServletContext());

        Integer idLivraison = Integer.parseInt(req.getParameter("idlivraison"));
        Livraison livraison = LivraisonService.getInstance().getLivraison(idLivraison);

        context.setVariable("livraison", livraison);
        templateEngine.process("livraisondetails", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        Integer idUser = user.getIdUser();
        Integer idLivraison = Integer.parseInt(req.getParameter("idlivraison"));
        CommandeService.getInstance().addCommande(idUser,idLivraison);
        resp.sendRedirect("participationvalidee");

    }
}
