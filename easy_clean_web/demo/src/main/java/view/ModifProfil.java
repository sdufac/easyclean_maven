package view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cleaner;
import model.HTMLfunction;
import model.Utilisateur;

@WebServlet(name = "ModifProfil", urlPatterns = { "/ModifProfil" })

public class ModifProfil extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ModifProfil() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        String[] tabMissions = HTMLfunction.modifProfil(null, user, user.getMissions());
        try (PrintWriter out = response.getWriter()) {
            response.setContentType("text/html;charset=UTF-8");
            HTMLfunction.profilUser(out, (Cleaner) session.getAttribute("user"));
            response.getWriter().append("<h2>Accueil Proprietaire</h2><hr>"
                    + "<div id=\"mission\"><h3>Vos missions en attente</h3>"
                    + tabMissions[0]
                    + "<hr>"
                    + "<h3>Vos missions en cours</h3>"
                    + tabMissions[1]
                    + "<hr>"
                    + "<h3>Votre historique de missions</h3>"
                    + tabMissions[2]
                    + "</div>"
                    + "<div id=\"form\">"
                    + "<h3>Poster une mission</h3>"
                    + "<form action=\"controllerproprietaire\" method=\"POST\" enctype=\"application/x-www-form-urlencoded\">"
                    + "</div>");
        }
    }
}
