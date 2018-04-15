package servlets;


import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import pojos.Commande;
import services.CommandeService;
import services.LivraisonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/suppressioncommande")
public class CommandeSuppressionServlet extends AbstractGenericServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine templateEngine = this.createTemplateEngine(req);
        Integer id = Integer.parseInt(req.getParameter("idcommande"));
        String confirmation = req.getParameter("confirmation");

        if (confirmation!=null && Boolean.parseBoolean(confirmation)){
            CommandeService.getInstance().supprimerCommande(id);
            resp.sendRedirect("home");
            return;
        }

        WebContext context = new WebContext(req,resp, req.getServletContext());
        templateEngine.process("confirmationsuppr",context,resp.getWriter());
    }
}
