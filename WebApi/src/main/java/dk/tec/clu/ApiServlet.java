package dk.tec.clu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

@WebServlet(name = "apiServlet")
public class ApiServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, 
			             HttpServletResponse response) throws ServletException, IOException 
	{
		BufferedReader in = request.getReader();
		String json = in.readLine();
		System.out.println(json);
		
		ObjectMapper mapper = new ObjectMapper();
		
		Persons newPerson = mapper.readValue(json, Persons.class);
		System.out.println(newPerson.perName);
	
	}
	
	protected void doGet(HttpServletRequest request, 
						 HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out = response.getWriter();
		AnalyzeRequest analyze = new AnalyzeRequest(request.getPathInfo());
		
		
		ObjectMapper mapper = new ObjectMapper();
		DBTools db = new DBTools();
		
		switch(analyze.getMatch())
		{
			case PersonId:
			   int perId = analyze.getId();
			   Persons person = db.getPersonById(perId);
			   String json = mapper.writeValueAsString(person);
			   out.print(json);
			  
			   break;
			   
			case Person:
				//out.print(mapper.writeValueAsString(person));
				 break;
				 
			case NoMatch:
				out.print("Match: NoMatch");
				break;
		}		
	}
	
	@Override
	protected void doPut(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		
		
		super.doPut(req, resp);
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		super.doDelete(req, resp);
	}	
}
