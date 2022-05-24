package ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member.do")
public class MemberServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Get 요청을 doHandle로 보낸다
		doHandle(req, resp);
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// POST 요청을 doHandle로 보낸다
		doHandle(req, resp);
	}
	
	protected void doHandle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Get 과 Post 모두의 요청처리
		
		System.out.println("MemberServlet Controller 작동");
		
		req.setCharacterEncoding("UTF-8");		// 한글꺠짐방지 처리
		resp.setContentType("text/html;charset=UTF-8");  //  브라우저에 출력할떄 html 출력
		
		// DAO 객체 생성 : DAO의 메소드 호출을 위한 객체 생성 
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = new MemberDTO();
		
		//출력페이지 없이 바로 HTML페이지로 출력 ( out 객체를 생성해야함 ) 
		//클라이언트의 웹브라우져에 HTML의 출력을 보낸다
		PrintWriter out = resp.getWriter();
		
		// Client 의 Form 에서 넘긴 변수 값을 받는다
			// command = "addMember" 일 경우 : DB에 값을 Insert 한다
		 	// command = "delMember" 일 경우 : DB에 값을 삭제한다 
		String command = req.getParameter("command"); 
		
		if (command != null && command.equals("addMember")) {
			
			// form에서 post방식으로 넘어오는 값을 받아서 변수에 할당 
			String id = req.getParameter("id");
			String pwd = req.getParameter("pwd");
			String name = req.getParameter("name");
			String email = req.getParameter("email");
			
			dto.setId(id);
			dto.setPwd(pwd);
			dto.setName(name);
			dto.setEmail(email);
			
			int result = dao.addMember(dto);
			dao.close();
			
			if(result ==1) {
				System.out.println("insert 성공");
				resp.sendRedirect("/proj01/member.do");
			}else if (result ==0) {
				System.out.println("insert 실패");
			}
			
			
		}else if (command != null && command.equals("delMember")) {
			//DB에서 값을 Delete
			String id = req.getParameter("id");    
			int result =dao.deleteMember(id);
			
			if(result == 1) {
				System.out.println("삭제 성공");
				resp.sendRedirect("/proj01/member.do");
			}else if (result == 0 ) {
				System.out.println("삭제 실패");
			}
		}
		
		
		
		// DB의 t_member의 값을 가져와서 출력 
		List<MemberDTO> list = dao.listMember();
		
		out.print("<html><body>");
		out.print("<table border = 1> <tr align = 'center'  bgcolor = 'lightgreen'>");
		out.print("<td>아이디 </td> <td> 비밀번호 </td> <td> 이름 </td> <td> 이메일 </td> <td> 가입일 </td> <td> 삭제 </td> </tr> ");
		
		// 두번쨰 tr에서 루프 돌리면서 list에서 dto를 꺼내서 getter로 출력
		for(int i = 0 ; i < list.size() ; i++) {
			dto = list.get(i);
			
			String id = dto.getId();
			String pwd = dto.getPwd();
			String name = dto.getName();
			String email = dto.getEmail();
			Date joinDate = dto.getJoinDate();
			
			out.print("<tr>  <td>" + id + "</td>" +  "<td>" + pwd + "</td>" +  "<td>" + name + "</td>" 
					  +  "<td>" + email + "</td>" +  "<td>" + joinDate + "</td>"
					  + "<td><a href = '/proj01/member.do?command=delMember&id=" + id +  "'>삭제</td> </tr>"); 
			
		}
		
		
		out.print("</table>");
		out.print("</body></html>");
		out.print( "<a href = '/proj01/memberForm.jsp'>회원등록</a>" );
		
		
		
		
		
	}

}
