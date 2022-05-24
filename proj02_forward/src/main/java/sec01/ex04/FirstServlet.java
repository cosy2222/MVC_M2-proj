package sec01.ex04;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/first04")  // dispacher�� ����� ������ �̵�
public class FirstServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
	//	RequestDispatcher dis = req.getRequestDispatcher("second04");
		RequestDispatcher dis = req.getRequestDispatcher("index.jsp");
		dis.forward(req, resp);
		
		
		
		
	}
	
}
