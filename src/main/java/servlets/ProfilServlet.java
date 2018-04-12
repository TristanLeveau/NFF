package servlets;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import pojos.CommandeParUser;
import pojos.User;
import services.CommandeService;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/profil")
public class ProfilServlet extends AbstractGenericServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        TemplateEngine templateEngine = this.createTemplateEngine(req);
        WebContext context = new WebContext(req, resp, getServletContext());
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if (user==null){
            resp.sendRedirect("deconnexion");
        }

        else {
            Integer idUser = user.getIdUser();
            context.setVariable("listelivraisonuser", CommandeService.getInstance().listCommandeByUser(idUser));
            templateEngine.process("profil", context, resp.getWriter());

        }
    }
}
