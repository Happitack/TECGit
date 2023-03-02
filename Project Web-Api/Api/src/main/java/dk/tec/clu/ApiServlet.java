package dk.tec.clu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

@WebServlet("/ApiServlet/*")
public class ApiServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private DBTool dbTool;

    public ApiServlet() {
        super();
        dbTool = new DBTool();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();

        AnalyzeRequest analyze = new AnalyzeRequest(request.getPathInfo());

        switch (analyze.getLevel()) {
            case PersonId:
                int perID = analyze.getId();
                Person person = dbTool.getPersonById(perID);
                if (person == null) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                } else {
                    response.setContentType("application/json");
                    out.print(mapper.writeValueAsString(person));
                }
                break;
            case Person:
			List<Person> persons = null;
			try {
				persons = dbTool.getAllPersons();
			} catch (SQLException e) {
		        e.printStackTrace();
		        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				e.printStackTrace();
			}
                response.setContentType("application/json");
                out.print(mapper.writeValueAsString(persons));
                break;
            default:
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        BufferedReader reader = request.getReader();
        ObjectMapper mapper = new ObjectMapper();

        Person person = mapper.readValue(reader, Person.class);

        try {
            dbTool.addPerson(person);
            response.setStatus(HttpServletResponse.SC_CREATED);
        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        BufferedReader reader = request.getReader();
        ObjectMapper mapper = new ObjectMapper();

        Person person = mapper.readValue(reader, Person.class);

        try {
            dbTool.updatePerson(person);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        AnalyzeRequest analyze = new AnalyzeRequest(request.getPathInfo());
        int perID = analyze.getId();

        try {
            dbTool.deletePerson(perID);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}