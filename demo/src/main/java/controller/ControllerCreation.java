package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.language.DaitchMokotoffSoundex;

import model.Cleaner;
import model.DAOacces;
import model.Proprietaire;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Servlet implementation class AccueilCleaner
 */
@WebServlet(name = "controllercreation", urlPatterns = { "/controllercreation" })
@MultipartConfig
public class ControllerCreation extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerCreation() {
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
        // TODO Auto-generated method stub

        String errorString = "";
        boolean errorFlag = false;

        // Regex declaration
        Pattern numberPattern = Pattern.compile("^[0-9]+$");
        Pattern letterPattern = Pattern.compile("^[a-zA-Z]+$");
        Pattern emailPattern = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
        Pattern phonePattern = Pattern.compile("^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$");

        // Parameter declaration
        String name = "";
        String secondname = "";
        String mail = "";
        int age = 0;
        String description = "Description vide pour le moment.";
        int phoneNumber = 0;
        String dateOfBirth = "";

        // Name check
        if (request.getParameter("nom") != null) {
            Matcher matcher = letterPattern.matcher(request.getParameter("nom"));
            if (!matcher.find()) {
                errorFlag = true;
                errorString = "nom invalide";
            } else {
                System.out.println("//NOM - " + request.getParameter("nom").substring(matcher.start(), matcher.end()));
                name = request.getParameter("nom").substring(matcher.start(), matcher.end());
            }
        } else {
            errorFlag = true;
            errorString = "nom null";
        }
        // Second name check
        if (request.getParameter("prenom") != null) {
            Matcher matcher = letterPattern.matcher(request.getParameter("prenom"));
            if (!matcher.find()) {
                errorFlag = true;
                errorString = "prenom invalide";
            } else {
                System.out.println(
                        "//PRENOM - " + request.getParameter("prenom").substring(matcher.start(), matcher.end()));
                secondname = request.getParameter("prenom").substring(matcher.start(), matcher.end());
            }
        } else {
            errorFlag = true;
            errorString = "prenom null";
        }

        // Username check
        if (request.getParameter("pseudo").isEmpty() || request.getParameter("pseudo") == null) {
            errorFlag = true;
            errorString = errorString + ",pseudo null";
        }

        // Password check
        if (request.getParameter("password").isEmpty() || request.getParameter("password") == null) {
            errorFlag = true;
            errorString = errorString + ",mdp null";
        }

        // Mail check
        if (request.getParameter("mail") != null) {
            Matcher matcher = emailPattern.matcher(request.getParameter("mail"));
            if (!matcher.find()) {
                errorFlag = true;
                errorString = errorString + ",email invalide";
            } else {
                System.out
                        .println("//MAIL - " + request.getParameter("mail").substring(matcher.start(), matcher.end()));
                mail = request.getParameter("mail").substring(matcher.start(), matcher.end());
            }
        } else {
            errorFlag = true;
            errorString = errorString + ",email null";
        }

        // Age check
        if (request.getParameter("age") != null) {
            Matcher matcher = numberPattern.matcher(request.getParameter("age"));
            if (!matcher.find()) {
                errorFlag = true;
                errorString = errorString + ",age invalide";
            } else {
                String ageStr = request.getParameter("age").substring(matcher.start(), matcher.end());
                age = Integer.parseInt(ageStr);
            }
        } else {
            errorFlag = true;
            errorString = errorString + ",age null";
        }

        // Description check
        if (!request.getParameter("bio").isEmpty() || request.getParameter("bio") != null) {
            description = request.getParameter("bio");
        }

        // Phone check
        if (request.getParameter("phonenumber") != null) {
            Matcher matcher = phonePattern.matcher(request.getParameter("phonenumber"));
            if (!matcher.find()) {
                errorFlag = true;
                errorString = errorString + ",numero de téléphone invalide";
            } else {
                String number = request.getParameter("phonenumber").substring(matcher.start(), matcher.end());
                phoneNumber = Integer.parseInt(number);
            }
        } else {
            errorFlag = true;
            errorString = errorString + ",numero de téléphone null";
        }

        // Date check
        if (request.getParameter("dateofbirth") == null) {
            errorFlag = true;
            errorString = errorString + ",date de naissance invalide";
        }

        if (request.getParameter("role").equals("cleaner")) {
            Matcher matcher = numberPattern.matcher(request.getParameter("salary"));
            if (!matcher.find()) {
                errorFlag = true;
                errorString = errorString + ",salaire invalide";
            }
        }

        if (!errorFlag) {
            if (request.getParameter("role").equals("cleaner")) {
                Cleaner user = new Cleaner(name, secondname, request.getParameter("pseudo"), mail,
                        request.getParameter("password"), age, request.getParameter("bio"), phoneNumber,
                        request.getParameter("dateofbirth"), 0, 0, 0, Integer.valueOf(request.getParameter("salary")));

                InputStream file = request.getPart("avatar").getInputStream();
                Files.copy(file, new File(getServletContext().getRealPath(getServletInfo())
                        + "\\image\\profile_picture\\" + user.getUsername() + ".png").toPath(),
                        StandardCopyOption.REPLACE_EXISTING);

                HttpSession session = request.getSession();
                session.setAttribute("user", user);

                DAOacces bdd = new DAOacces("easy_clean", "toto", "titi");
                try {
                    String strQuery = "INSERT INTO users (first_name,second_name,username,mail,password,profil_picture,age,bio,phone_number,date_of_birth,note,nb_mission,perimeter,tarif_horaire,role) VALUES('"
                            + user.getFirstName() + "','" + user.getSecondName() + "','" + user.getUsername() + "','"
                            + user.getEmail() + "','" + user.getPassword() + "','" + user.getUsername() + ".png',"
                            + user.getAge() + ",'" + user.getDescription() + "'," + user.getPhoneNumber() + ",'"
                            + user.getDateOfBirth() + "'," + user.getGlobalGrade() + "," + user.getMissionDone() + ","
                            + user.getPerimeter() + "," + user.getSalaryPerHour() + ",'" + user.getStatut() + "');";
                    Statement stLogin = bdd.getConnection().createStatement();
                    stLogin.executeUpdate(strQuery);

                    getServletContext().getRequestDispatcher("/cleaner").forward(request, response);
                } catch (SQLException e) {
                    System.err.println("Erreur");
                    e.printStackTrace();
                }
                bdd.disconnect();
            } else if (request.getParameter("role").equals("proprietaire")) {
                Proprietaire user = new Proprietaire(name, secondname, request.getParameter("pseudo"), mail,
                        request.getParameter("password"), age, request.getParameter("bio"), phoneNumber,
                        request.getParameter("dateofbirth"), 0);

                InputStream file = request.getPart("avatar").getInputStream();
                Files.copy(file, new File(getServletContext().getRealPath(getServletInfo())
                        + "\\image\\profile_picture\\" + user.getUsername() + ".png").toPath(),
                        StandardCopyOption.REPLACE_EXISTING);

                HttpSession session = request.getSession();
                session.setAttribute("user", user);

                DAOacces bdd = new DAOacces("easy_clean", "toto", "titi");
                try {
                    String strQuery = "INSERT INTO users (first_name,second_name,username,mail,password,profil_picture,age,bio,phone_number,date_of_birth,note,role) VALUES('"
                            + user.getFirstName() + "','" + user.getSecondName() + "','" + user.getUsername() + "','"
                            + user.getEmail() + "','" + user.getPassword() + "','" + user.getUsername() + ".png',"
                            + user.getAge() + ",'" + user.getDescription() + "'," + user.getPhoneNumber() + ",'"
                            + user.getDateOfBirth() + "','" + user.getGlobalGrade() + "','" + user.getStatut() + "');";
                    Statement stLogin = bdd.getConnection().createStatement();
                    stLogin.executeUpdate(strQuery);

                    getServletContext().getRequestDispatcher("/proprietaire").forward(request, response);
                } catch (SQLException e) {
                    System.err.println("Erreur");
                    e.printStackTrace();
                }
                bdd.disconnect();
            }
        } else {
            System.out.println(errorString);
            getServletContext().getRequestDispatcher("/createaccount").forward(request, response);
        }
    }
}
