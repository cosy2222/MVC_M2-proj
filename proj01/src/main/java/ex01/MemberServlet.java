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
		// Get ��û�� doHandle�� ������
		doHandle(req, resp);
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// POST ��û�� doHandle�� ������
		doHandle(req, resp);
	}
	
	protected void doHandle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Get �� Post ����� ��ûó��
		
		System.out.println("MemberServlet Controller �۵�");
		
		req.setCharacterEncoding("UTF-8");		// �ѱۃ������� ó��
		resp.setContentType("text/html;charset=UTF-8");  //  �������� ����ҋ� html ���
		
		// DAO ��ü ���� : DAO�� �޼ҵ� ȣ���� ���� ��ü ���� 
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = new MemberDTO();
		
		//��������� ���� �ٷ� HTML�������� ��� ( out ��ü�� �����ؾ��� ) 
		//Ŭ���̾�Ʈ�� ���������� HTML�� ����� ������
		PrintWriter out = resp.getWriter();
		
		// Client �� Form ���� �ѱ� ���� ���� �޴´�
			// command = "addMember" �� ��� : DB�� ���� Insert �Ѵ�
		 	// command = "delMember" �� ��� : DB�� ���� �����Ѵ� 
		String command = req.getParameter("command"); 
		
		if (command != null && command.equals("addMember")) {
			
			// form���� post������� �Ѿ���� ���� �޾Ƽ� ������ �Ҵ� 
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
				System.out.println("insert ����");
				resp.sendRedirect("/proj01/member.do");
			}else if (result ==0) {
				System.out.println("insert ����");
			}
			
			
		}else if (command != null && command.equals("delMember")) {
			//DB���� ���� Delete
			String id = req.getParameter("id");    
			int result =dao.deleteMember(id);
			
			if(result == 1) {
				System.out.println("���� ����");
				resp.sendRedirect("/proj01/member.do");
			}else if (result == 0 ) {
				System.out.println("���� ����");
			}
		}
		
		
		
		// DB�� t_member�� ���� �����ͼ� ��� 
		List<MemberDTO> list = dao.listMember();
		
		out.print("<html><body>");
		out.print("<table border = 1> <tr align = 'center'  bgcolor = 'lightgreen'>");
		out.print("<td>���̵� </td> <td> ��й�ȣ </td> <td> �̸� </td> <td> �̸��� </td> <td> ������ </td> <td> ���� </td> </tr> ");
		
		// �ι��� tr���� ���� �����鼭 list���� dto�� ������ getter�� ���
		for(int i = 0 ; i < list.size() ; i++) {
			dto = list.get(i);
			
			String id = dto.getId();
			String pwd = dto.getPwd();
			String name = dto.getName();
			String email = dto.getEmail();
			Date joinDate = dto.getJoinDate();
			
			out.print("<tr>  <td>" + id + "</td>" +  "<td>" + pwd + "</td>" +  "<td>" + name + "</td>" 
					  +  "<td>" + email + "</td>" +  "<td>" + joinDate + "</td>"
					  + "<td><a href = '/proj01/member.do?command=delMember&id=" + id +  "'>����</td> </tr>"); 
			
		}
		
		
		out.print("</table>");
		out.print("</body></html>");
		out.print( "<a href = '/proj01/memberForm.jsp'>ȸ�����</a>" );
		
		
		
		
		
	}

}
