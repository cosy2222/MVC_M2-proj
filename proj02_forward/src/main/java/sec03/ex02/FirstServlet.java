package sec03.ex02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/first0004")  // dispacher를 사용한 페이지 이동
public class FirstServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		req.setAttribute("address", "경기도 의왕시 왕곡동");
		req.setAttribute("email", "aaa@aaa.com");
		
		RequestDispatcher dis = req.getRequestDispatcher("second0004");
		dis.forward(req, resp);
		
		
		
		
	}
	
}
