package servlets;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// Servlet : Page d'acceuil
@WebServlet("/accueil")
public class AccueilServlet extends AbstractGenericServlet{

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine templateEngine = this.createTemplateEngine(req);
        WebContext context = new WebContext(req, resp, getServletContext());
        if (req.getSession().getAttribute("user") == null){
            resp.sendRedirect("deconnexion");
        }
        templateEngine.process("accueil", context, resp.getWriter());
    }
}
