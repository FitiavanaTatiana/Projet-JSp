package servlets;
import DAO.EmployeeDAO;
import com.Employee;
import com.Salaire;
import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import org.jfree.chart.ChartFactory;

import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.plot.PlotOrientation;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import java.util.Base64;



@WebServlet(name = "EmployeeServlet", value = "/")
public class EmployeeServlet extends HttpServlet {
    private final EmployeeDAO employeeDAO;

    public EmployeeServlet() {
        this.employeeDAO = new EmployeeDAO();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Logique de votre servlet pour la méthode GET
        String action = request.getServletPath();
        switch (action) {
            case "/new":
                showNewForm(request, response);
                break;
            case "/insert":
                insertEmployee(request, response);
                break;
            case "/edit":
                try {
                    showEditForm(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "/update":
                try {
                    updateEmployee(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "/histogramme":
                try {
                    generateAndShowHistogram(request,response);
                }
                catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                break;
            case "/delete":
                try {
                    deleteEmployee(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                try {
                    listEmployee(request, response);
                } catch (SQLException e) {
                    e.printStackTrace(); // Logger ou gérer cette exception correctement
                }
                break;
        }
    }
//histogramme
private void generateAndShowHistogram(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException,SQLException {

        Salaire salaire = employeeDAO.getSalairesMinMaxSum();

        // Créer le dataset
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(salaire.getMin(), "Salaire", "Minimum");
        dataset.addValue(salaire.getMax(), "Salaire", "Maximum");
        dataset.addValue(salaire.getSomme(), "Salaire", "Somme");

        // Créer le graphique à barres
        JFreeChart histogramChart = ChartFactory.createBarChart(
                "Histogramme de Salaire",
                "Catégorie",
                "Valeur",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

int with=600;
int heigth= 400;

        BufferedImage chartImg= histogramChart.createBufferedImage(with, heigth);

        ByteArrayOutputStream baos= new ByteArrayOutputStream();
        ImageIO.write(chartImg,"png",baos);
        byte[] imageData= baos.toByteArray();
        String base64Image= Base64.getEncoder().encodeToString(imageData);

        request.setAttribute("base64Image",base64Image);

        RequestDispatcher dispatcher=request.getRequestDispatcher("histogramme.jsp");
        dispatcher.forward(request,response);




}

    // Liste des employés
    private void listEmployee(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, SQLException, IOException {
      List<Employee> listEmployee = employeeDAO.selectAllEmployees();
        Salaire salaire = employeeDAO.getSalairesMinMaxSum();
        request.setAttribute("salaire", salaire);
        request.setAttribute("listEmployee", listEmployee);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Employeelist.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        int numEmp = Integer.parseInt(request.getParameter("numEmp"));
        Employee existingEmployee = employeeDAO.selectEmployee(numEmp);
        request.setAttribute("employee", existingEmployee);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Employeeform.jsp");
        dispatcher.forward(request, response);

    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("Employeeform.jsp");
        dispatcher.forward(request, response);
    }

    private void insertEmployee(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int numEmp = Integer.parseInt(request.getParameter("numEmp"));
        String nom = request.getParameter("nom");
        int nombrejour = Integer.parseInt(request.getParameter("nombrejour"));
        double taux_jounaliere = Double.parseDouble(request.getParameter("taux_jounaliere"));
        double salaire = nombrejour * taux_jounaliere;

        Employee newEmployee = new Employee(numEmp, nom, nombrejour, taux_jounaliere, salaire);
        employeeDAO.insertEmployee(newEmployee);
        response.sendRedirect("listEmployee");
    }

    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response)
            throws IOException, SQLException {
        try {
            int numEmp = Integer.parseInt(request.getParameter("numEmp"));
            employeeDAO.deleteEmployee(numEmp);
            response.sendRedirect("listEmployee");
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace(); // Logger ou gérer cette exception correctement
            response.sendRedirect("error.jsp");
        }
    }

    private void updateEmployee(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        int numEmp = Integer.parseInt(request.getParameter("numEmp"));
        String nom = request.getParameter("nom");
        int nombrejour = Integer.parseInt(request.getParameter("nombrejour"));
        double taux_jounaliere = Double.parseDouble(request.getParameter("taux_jounaliere"));
        double salaire =nombrejour * taux_jounaliere;

        Employee employee = new Employee(numEmp, nom, nombrejour, taux_jounaliere, salaire);
        employeeDAO.updateEmployee(employee);
        response.sendRedirect("listEmployee");
    }

    @Override


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Logique de votre servlet pour la méthode POST
        System.out.println("doPost method called");
        doGet(request, response);
    }
}