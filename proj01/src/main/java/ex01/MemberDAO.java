package ex01;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class MemberDAO extends DBConnPool {
		//DB를 Connection 객체를 상속해서 쓰면 중복코드를 방지할 수 있다 
	
	public MemberDAO() {
		super();     //부모클래스 기본생성자 호출 
	}
	
	
	
	// List.jsp 출력을 위한 select 
		// 레코드 1개를 DTO에 저장
		// DTO를 List : Vector(멀티쓰레드) , ArrayList(싱글 쓰레드)
	
	public List<MemberDTO> listMember() {
		
		MemberDTO dto = new MemberDTO();
		List<MemberDTO> list = new ArrayList<>();
		
		String query = "select * from t_member";
		
		try {
			psmt = con.prepareStatement(query);
			rs = psmt.executeQuery();      //rs는 DB에서 Select 한 결과값 (레코드셋) 을 저장
			
			// rs에 저장된 레코드 셋을 DTO에 저장후 list에 add
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
	
	
	
	
	// Insert.jsp   DB에 Insert
	public int addMember (MemberDTO dto) {
		
		int result = 0;
		
		String query = "insert into t_member(id , pwd , name , email) "
					 + "values (? , ? , ? , ? )" ;
		
		// DTO의 넘어오는 변수의 값들을 getter로 호출해서 변수에 할당
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
			
			System.out.println("insert 성공");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	
	
	// Delete.jsp   DB에서 Delete
	
	public int deleteMember (String id) {
		
		int result = 0;
		
		String query = "delete t_member where id = ?";
		
		try {
			
			psmt = con.prepareStatement(query);
			
			psmt.setString(1, id);
			
			result = psmt.executeUpdate();
			
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("글 삭제시 예외 발생 ");
		}
		
		return result;
	}
	
	
	
	
	

}
