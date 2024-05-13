package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Admin;
import model.DAOacces;
import model.Utilisateur;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Servlet implementation class AccueilCleaner
 */
@WebServlet(name = "controllerrechercheuser", urlPatterns = { "/controllerrechercheuser" })
public class ControllerRechercheUser extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerRechercheUser() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("user");
        ArrayList<Utilisateur> recherche = new ArrayList<Utilisateur>();

        Pattern numberPattern = Pattern.compile("^[0-9]+$");
        int id = 0;

        if (request.getParameter("name") != null) {
            Matcher matcher = numberPattern.matcher(request.getParameter("name"));
            if (matcher.find()) {
                String idStr = request.getParameter("name").substring(matcher.start(), matcher.end());
                id = Integer.parseInt(idStr);
            }

            // TODO Auto-generated method stub
            String strRecherche = "SELECT * FROM users WHERE first_name LIKE '%" + request.getParameter("name") + "%' "
                    + "OR second_name LIKE '%" + request.getParameter("name") + "%' "
                    + "OR username LIKE '%" + request.getParameter("name") + "%' "
                    + "OR mail LIKE '%" + request.getParameter("name") + "%' "
                    + "OR id_user LIKE " + id + ";";
            DAOacces bdd = new DAOacces("easy_clean", "toto", "titi");

            try {
                Statement stRecherche = bdd.getConnection().createStatement();
                ResultSet rsRecherche = stRecherche.executeQuery(strRecherche);

                while (rsRecherche.next()) {
                    if (rsRecherche.getString("role").equals("Cleaner")) {
                        recherche.add(admin.getCleanerById(rsRecherche.getInt("id_user")));
                    } else if (rsRecherche.getString("role").equals("Proprietaire")) {
                        recherche.add(admin.getProprietaireById(rsRecherche.getInt("id_user")));
                    }
                }
                bdd.disconnect();
            } catch (SQLException e) {

            }
        }

        session.setAttribute("recherche", recherche);
        getServletContext().getRequestDispatcher("/admin").forward(request, response);
    }

}
