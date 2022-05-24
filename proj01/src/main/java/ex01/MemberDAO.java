package ex01;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class MemberDAO extends DBConnPool {
		//DB�� Connection ��ü�� ����ؼ� ���� �ߺ��ڵ带 ������ �� �ִ� 
	
	public MemberDAO() {
		super();     //�θ�Ŭ���� �⺻������ ȣ�� 
	}
	
	
	
	// List.jsp ����� ���� select 
		// ���ڵ� 1���� DTO�� ����
		// DTO�� List : Vector(��Ƽ������) , ArrayList(�̱� ������)
	
	public List<MemberDTO> listMember() {
		
		MemberDTO dto = new MemberDTO();
		List<MemberDTO> list = new ArrayList<>();
		
		String query = "select * from t_member";
		
		try {
			psmt = con.prepareStatement(query);
			rs = psmt.executeQuery();      //rs�� DB���� Select �� ����� (���ڵ��) �� ����
			
			// rs�� ����� ���ڵ� ���� DTO�� ������ list�� add
			while(rs.next()) {
				MemberDTO dto2 = new MemberDTO();
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joindate = rs.getDate("joindate");
				
				dto2.setId(id);
				dto2.setPwd(pwd);
				dto2.setName(name);
				dto2.setEmail(email);
				dto2.setJoinDate(joindate);
				
				list.add(dto2);
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	
	
	
	// Insert.jsp   DB�� Insert
	public int addMember (MemberDTO dto) {
		
		int result = 0;
		
		String query = "insert into t_member(id , pwd , name , email) "
					 + "values (? , ? , ? , ? )" ;
		
		// DTO�� �Ѿ���� ������ ������ getter�� ȣ���ؼ� ������ �Ҵ�
		String id = dto.getId();
		String pwd = dto.getPwd();
		String name = dto.getName();
		String email = dto.getEmail();
				
		
		try {
			
			psmt = con.prepareStatement(query);
			
			psmt.setString(1, id);
			psmt.setString(2, pwd);
			psmt.setString(3, name);
			psmt.setString(4, email);
			
			result = psmt.executeUpdate();
			
			System.out.println("insert ����");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	
	
	// Delete.jsp   DB���� Delete
	
	public int deleteMember (String id) {
		
		int result = 0;
		
		String query = "delete t_member where id = ?";
		
		try {
			
			psmt = con.prepareStatement(query);
			
			psmt.setString(1, id);
			
			result = psmt.executeUpdate();
			
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("�� ������ ���� �߻� ");
		}
		
		return result;
	}
	
	
	
	
	

}
