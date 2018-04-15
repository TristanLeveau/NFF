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

@WebServlet("/listelivraisonssupprimees")
public class ListLivraisonsSupprimeesServlet extends AbstractGenericServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine templateEngine = this.createTemplateEngine(req);
        WebContext context = new WebContext(req, resp, getServletContext());


        context.setVariable("livraisonssupprimees", LivraisonService.getInstance().listLivraisonsSupprimees());

        templateEngine.process("listelivraisonssupprimees", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        if (session.getAttribute("user")==null){
            resp.sendRedirect("deconnexion");
        }
    }

}
