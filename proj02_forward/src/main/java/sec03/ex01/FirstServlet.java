package sec03.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/first0001")   // ?????? ?ѱ???
public class FirstServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		//request ??ü?? setAttribute() ?? ?????ؼ? ?????? ???? ?ѱ? 
		req.setAttribute("address", "???⵵ ?ǿս? ?հ");
		req.setAttribute("email", "aaa@aaa.com");
		
		resp.sendRedirect("second0001");
		
		
	}
	
}
