package servlets;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import pojos.User;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
// Servlet : Connexion et création de session
@WebServlet("/connexion")
public class ConnexionServlet extends AbstractGenericServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine templateEngine = this.createTemplateEngine(req);
        WebContext context = new WebContext(req, resp, getServletContext());

        if (req.getSession().getAttribute("user") != null){
            resp.sendRedirect("home");
        }

        if(req.getSession().getAttribute("UserCreationError") != null){
            context.setVariable("errormessage",req.getSession().getAttribute("UserConnexionError"));
            context.setVariable("email", (String) req.getSession().getAttribute("UserConnexionData"));
            context.setVariable("motDePasse", (String) req.getSession().getAttribute("UserConnexionData2"));

            req.getSession().removeAttribute("UserCreationError");
            req.getSession().removeAttribute("UserCreationData");
            req.getSession().removeAttribute("UserCreationData2");

        }

        else{
            context.setVariable("user",new User(null,null,null,null,null));
        }

        templateEngine.process("connexion", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String motDePasse = req.getParameter("motDePasse");

        User user = UserService.getInstance().getUserByEmailMDP(email,motDePasse);
        if (user==null){
            resp.sendRedirect("erreur");
        }else {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            resp.sendRedirect("home");
        }
    }
}
