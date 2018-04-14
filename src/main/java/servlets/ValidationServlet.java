package servlets;

import pojos.User;
import services.CommandeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

// Servlet : Validation
@WebServlet("/validation")
public class ValidationServlet extends AbstractGenericServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        Integer idUser = user.getIdUser();
        Integer idLivraison = Integer.parseInt(req.getParameter("idlivraison"));
        try{
            CommandeService.getInstance().addCommande(idUser,idLivraison);
            resp.sendRedirect("participationvalidee");
        }catch (IllegalArgumentException e) {
            req.getSession().setAttribute("ParticipationCreationError", e.getMessage());
            resp.sendRedirect("erreur");
        }
    }

}
