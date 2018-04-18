package servlets;

import pojos.Semestre;
import services.LivraisonService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/listeclients")
public class ListeClientsServlet extends AbstractGenericServlet {

    private static final long serialVersionUID = 5402133218271984030L;

   /* @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine templateEngine = this.createTemplateEngine(req);

        WebContext context = new WebContext(req, resp, getServletContext());
        context.setVariable("participants", LivraisonService.getInstance().ListeParticipants());
        templateEngine.process("listeclients", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.sendRedirect("listeclients");
    }
    */
}

