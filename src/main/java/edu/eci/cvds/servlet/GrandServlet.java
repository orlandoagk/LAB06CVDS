package edu.eci.cvds.servlet;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Writer;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.eci.cvds.servlet.model.Todo;



@WebServlet(
	    urlPatterns = "/grandServlet"
	)
public class GrandServlet extends HttpServlet {
	 @Override
	 protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 Writer responseWriter = resp.getWriter();

		 try {
			 String id = req.getParameter("id");
			 Todo todo = Service.getTodo(Integer.parseInt(id));
			 List<Todo> todoList = Arrays.asList(todo);
			 resp.setStatus(HttpServletResponse.SC_OK);
			 responseWriter.write(Service.todosToHTMLTable(todoList));
			 responseWriter.flush();
		 } catch (FileNotFoundException e){
			 resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
		 } catch (NumberFormatException e) {
			 resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		 } catch (MalformedURLException e) {
			 resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		 } catch (Exception e) {
			 resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		 }



	 }
}
