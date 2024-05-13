package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DAOacces;
import model.Property;
import model.Proprietaire;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Servlet implementation class AccueilCleaner
 */
@WebServlet(name = "ControllerAddProperty", urlPatterns = { "/controlleraddproperty" })
public class ControllerAddProperty extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerAddProperty() {
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
        boolean isError = false;
        String errorString = "ERREUR :";

        HttpSession session = request.getSession();
        Proprietaire user = (Proprietaire) session.getAttribute("user");

        String adress = "";
        String ville = "";
        int codePostal = 0;
        int digicode = 0;
        int surface = 0;

        if (request.getParameter("adress").equals("")) {
            isError = true;
            errorString = errorString + "adresse non valide,";
        } else {
            adress = request.getParameter("adress");
        }

        if (request.getParameter("ville").equals("")) {
            isError = true;
            errorString = errorString + "ville non valide,";
        } else {
            ville = request.getParameter("ville");
        }

        if (request.getParameter("codePostal").equals("")) {
            isError = true;
            errorString = errorString + "code postal non valide,";
        } else {
            codePostal = Integer.valueOf(request.getParameter("codePostal"));
        }

        if (!request.getParameter("digicode").equals("")) {
            digicode = Integer.valueOf(request.getParameter("digicode"));
        }

        if (request.getParameter("surface").equals("")) {
            isError = true;
            errorString = errorString + "surface non valide,";
        } else {
            surface = Integer.valueOf(request.getParameter("surface"));
        }

        if (!isError) {
            DAOacces bdd = new DAOacces("easy_clean", "toto", "titi");
            try {
                Statement stProperty = bdd.getConnection().createStatement();
                System.out.println("Adress: " + adress + ",Ville: " + ville + ",Id: " + user.getId() + ",Digicode: "
                        + digicode + ",Code postal: " + codePostal + ",Surface :" + surface);
                String strQuery = "INSERT INTO propriete (adress, code_entrer,proprietaire_id,ville,code_postal,surface) VALUES ('"
                        + adress + "'," + digicode + "," + user.getId() + ",'" + ville + "'," + codePostal + ","
                        + surface + ");";
                stProperty.executeUpdate(strQuery);

                String strSelectNewProperty = "SELECT * FROM propriete WHERE adress ='" + adress + "'AND ville ='"
                        + ville + "'AND code_postal =" + codePostal + ";";
                ResultSet rsProperty = stProperty.executeQuery(strSelectNewProperty);

                int idProperty = 0;

                while (rsProperty.next()) {
                    idProperty = rsProperty.getInt("propriete_id");
                }

                String fullAdress = adress + " " + ville + " " + codePostal;
                user.setProperties(new Property(idProperty, fullAdress, digicode, (int) surface));

                getServletContext().getRequestDispatcher("/proprietaire").forward(request, response);
            } catch (SQLException e) {
                System.err.println("Erreur");
                e.printStackTrace();
            }
        } else {
            response.getWriter().append(errorString);
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
