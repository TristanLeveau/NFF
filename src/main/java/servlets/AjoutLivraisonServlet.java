package servlets;


import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import pojos.Livraison;
import pojos.User;
import services.LivraisonService;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
// Servlet : Ajout d'une livraison
@WebServlet("/livraisonadd")
public class AjoutLivraisonServlet extends AbstractGenericServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine templateEngine = this.createTemplateEngine(req);
        WebContext context = new WebContext(req, resp, getServletContext());
        if(req.getSession().getAttribute("UserCreationError") != null){
            context.setVariable("errormessage",req.getSession().getAttribute("LivraisonCreationError"));
            context.setVariable("livraison", (Livraison) req.getSession().getAttribute("LivraisonCreationData"));

            req.getSession().removeAttribute("LivraisonCreationError");
            req.getSession().removeAttribute("LivraisonCreationData");
        }
        else{
            context.setVariable("livraison",new Livraison(null,null,null));
        }

        templateEngine.process("livraisonadd", context, resp.getWriter());
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String date = req.getParameter("date");
        String contenu = req.getParameter("contenu");

        HttpSession session = req.getSession();

        if (session.getAttribute("user")==null){
            resp.sendRedirect("deconnexion");
        }


        Livraison livraison = new Livraison(null, date, contenu);
        if (livraison==null ){
            resp.sendRedirect("erreurchamp");
        }
        try{
                LivraisonService.getInstance().addLivraison(livraison);
                resp.sendRedirect("home");
        }catch (IllegalArgumentException e){
            req.getSession().setAttribute("UserCreationError", e.getMessage());
            req.getSession().setAttribute("UserCreationData", livraison);
            resp.sendRedirect("livraisonadd");
        }



    }


}

