package servlets;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import services.LivraisonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/activation")
public class LivraisonActivationServlet extends AbstractGenericServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine templateEngine = this.createTemplateEngine(req);
        Integer id = Integer.parseInt(req.getParameter("idlivraison"));
        String confirmation = req.getParameter("confirmation");
        if (confirmation!=null && Boolean.parseBoolean(confirmation)){
            LivraisonService.getInstance().activerLivraison(id);
            resp.sendRedirect("home");
            return;
        }

        WebContext context = new WebContext(req,resp, req.getServletContext());
        context.setVariable("livraison", LivraisonService.getInstance().getLivraison(id));
        templateEngine.process("confirmationsuppr",context,resp.getWriter());
    }
}
