package servlets;

import exceptions.NFFRuntimeException;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.WebContext;
import pojos.User;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/inscription")
public class InscriptionServlet extends AbstractGenericServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        TemplateEngine templateEngine = this.createTemplateEngine(req);
        WebContext context = new WebContext(req, resp, getServletContext());
        if(req.getSession().getAttribute("UserCreationError") != null){
            context.setVariable("errormessage",req.getSession().getAttribute("UserCreationError"));
            context.setVariable("user", (User) req.getSession().getAttribute("UserCreationData"));

            req.getSession().removeAttribute("UserCreationError");
            req.getSession().removeAttribute("UserCreationData");
        }
        else{
            context.setVariable("user",new User(null,null,null,null,null));
        }

        templateEngine.process("inscription", context, resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String nom = req.getParameter("nom");
        String prenom = req.getParameter("prenom");
        String email = req.getParameter("email");
        String motDePasse = req.getParameter("motDePasse");

        User user = new User(null, nom,prenom,email,motDePasse);
        HttpSession session = req.getSession();
        session.setAttribute("user",user);

        try{
            UserService.getInstance().addUser(user);
            resp.sendRedirect("home");

        }catch (IllegalArgumentException e){
            req.getSession().setAttribute("UserCreationError", e.getMessage());
            req.getSession().setAttribute("UserCreationData", user);
            resp.sendRedirect("inscription");        }
    }
}
