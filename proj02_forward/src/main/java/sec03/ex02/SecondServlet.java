package sec03.ex02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/second0004")
public class SecondServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		String address = (String) req.getAttribute("address");
		String email = (String) req.getAttribute("email");
		
		out.print("<html><body>");
		out.print("RequestDispatcher를 사용한 페이지 이동 <br><br>");
		out.print("주소 : " + address + "<br><br>");
		out.print("이메일 : " + email + "<br><br>");
		out.print("</body></html>");
			
		
	}

	
	
}
