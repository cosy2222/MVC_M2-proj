package sec02.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/second001")
public class SecondServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		String name = req.getParameter("name");
		String pwd = req.getParameter("pwd");
		
		out.print("<html><body>");
		out.print("�̸� : " + name + "<br><br>");
		out.print("��й�ȣ : " + pwd);
		out.print("</body></html>");
			
		
	}

	
	
}