import java.io.*;
import java.sql.*;

public class Db_connection {
	public Connection con;
	
	public Db_connection() {
		
		String url = "jdbc:mysql://localhost:3306/madang";
		String userid = "madang";
		String pwd = "1234";
		
		try {
			con = DriverManager.getConnection(url,userid,pwd);
			System.out.println("Connection Success!\n");
		} catch (SQLException e) {
			e.printStackTrace();
			}
	}
	
	public void sqlSetting() {
		String drop_all="DELETE FROM users";

		String get_infos_num="SELECT count(*) FROM loginInfo";
		String get_infos="SELECT nickName, id FROM loginInfo";

		try {
			Statement stmt1 = con.createStatement();
			PreparedStatement pstmt1 = con.prepareStatement(drop_all);

			
			int rs1 =pstmt1.executeUpdate();
			
			Integer nums =0;
			ResultSet rs=stmt1.executeQuery(get_infos_num);
			
			while(rs.next()) {
				nums = rs.getInt(1);
				System.out.println("유저수:" + nums.toString());
			}
			
			rs=stmt1.executeQuery(get_infos);
			
			String[] get_nick = new String[nums];
			String[] get_id = new String[nums];
			
			int i =0;
			while(rs.next()) {
				get_nick[i]= rs.getString(1);
				get_id[i]= rs.getString(2);
				i++;
			}
			String plus ="Insert INTO users value(?,?)";
			
			PreparedStatement pstmt = null;
	        try {
	        	i = 0;
	        	while(i < get_nick.length) {
		            pstmt = con.prepareStatement(plus);
		            pstmt.setString(1, get_nick[i].toString());
		            pstmt.setString(2, get_id[i] );
		            
		            int result = pstmt.executeUpdate();
		            i++;
		            if(result==1) {
		                System.out.println("Board데이터 삽입 성공!");
		            }
	        	}
	        }catch (Exception e) {
	            System.out.println("Board데이터 삽입 실패!");
	        }
			

		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String sqlUserNameById(String id) {
		
		String user_name="SELECT name FROM users Where id = '"+id+"'";

		try {
			Statement stmt1 = con.createStatement();
			ResultSet rs=stmt1.executeQuery(user_name);
			
			String name = "";
			while(rs.next()) {
				name = rs.getString(1);
			}
			

			return name;
		} catch(SQLException e) {
			e.printStackTrace();
			return new String();
		}
	}
	
	
	public String[] sqlUserName() {
		String userNum="SELECT count(*) FROM users";
		
		String users="SELECT name FROM users";

		try {
			Statement stmt1 = con.createStatement();
			ResultSet rs=stmt1.executeQuery(userNum);
			
			int num = 0;
			while(rs.next()) {
				num = rs.getInt(1);
			}
			
			String[] user_name = new String[num];

			System.out.println(" hihi \n"+num);
			
			int i =0;
			rs=stmt1.executeQuery(users);

			while(rs.next()) {
				
				System.out.println(rs.getString(1));
				user_name[i] = rs.getString(1);
				i++;
				
			}

			return user_name;
		} catch(SQLException e) {
			e.printStackTrace();
			return new String[1];
		}
	}
	public String[] sqlUserId() {
		String userNum="SELECT count(*) FROM loginInfo";
		
		String users="SELECT id FROM loginInfo";

		try {
			Statement stmt1 = con.createStatement();
			ResultSet rs=stmt1.executeQuery(userNum);
			
			int num = 0;
			while(rs.next()) {
				num = rs.getInt(1);
			}
			
			String[] user_id = new String[num];

			System.out.println(" hihi \n"+num);
			
			int i =0;
			rs=stmt1.executeQuery(users);

			while(rs.next()) {
				
				System.out.println(rs.getString(1));
				user_id[i] = rs.getString(1);
				i++;
				
			}

			return user_id;
		} catch(SQLException e) {
			e.printStackTrace();
			return new String[1];
		}
	}
	public String[] sqlUserIdBySearching(String searchId) {
		String userNum="SELECT count(*) FROM users where id like \'%"+searchId+"%\'";
		
		String users="SELECT id FROM users where id like \'%" + searchId + "%\'";

		try {
			Statement stmt1 = con.createStatement();
			ResultSet rs=stmt1.executeQuery(userNum);
			
			int num = 0;
			while(rs.next()) {
				num = rs.getInt(1);
			}
			
			String[] user_id = new String[num];

			System.out.println(" search \n"+num);
			
			int i =0;
			rs=stmt1.executeQuery(users);

			while(rs.next()) {
				
				System.out.println(rs.getString(1));
				user_id[i] = rs.getString(1);
				i++;
				
			}

			return user_id;
		} catch(SQLException e) {
			e.printStackTrace();
			return new String[1];
		}
	}
	
	public String[] sqlFollower(String id) {
		
		String[] followerInfo;
		
//		String QmyId ="SELECT id FROM users WHERE name =\"" + name+"\"";
//		String id = "";
//		
		
		int num = 0;

		try {
			Statement stmt = con.createStatement();
			
//			ResultSet rs=stmt.executeQuery(QmyId);
//			
//			//현재 사용자의 아이디를 확인
//			while(rs.next()) {
//				id = rs.getString(1);
//			}
			
			//팔로워 수
			String QmyFollower="SELECT from_id FROM follows WHERE to_id =\"" + id+"\"";
			
			String followerNum="SELECT count(from_id) FROM follows WHERE to_id =\"" + id+"\"";
			ResultSet rs=stmt.executeQuery(followerNum);

			int i=0;
			while(rs.next()) {
				
				num = rs.getInt(1);
				System.out.println("나를 팔로우하는 아이디"+num);

			}
			followerInfo = new String[num];
			
			rs=stmt.executeQuery(QmyFollower);

			
			i = 0;
			while(rs.next()) {
				
				System.out.println(rs.getString(1));
				followerInfo[i] = rs.getString(1);
				i++;
			
			}				

			
			return followerInfo;
		} catch(SQLException e) {
			e.printStackTrace();
			return new String[1];
		}
	}
	
public String[] sqlFollowee(String id) {
		
		String[] followeeInfo;
		
//		String QmyId ="SELECT id FROM users WHERE name =\"" + name+ "\"";
//		String id = "";
	
		
		int num = 0;

		try {
			Statement stmt2 = con.createStatement();
			
//			ResultSet rs=stmt2.executeQuery(QmyId);
//			
//			//현재 사용자의 아이디를 확인
//			while(rs.next()) {
//				id = rs.getString(1);
//			}
			
			//팔로잉 수
			String QmyFollowee="SELECT to_id FROM follows WHERE from_id =\"" + id+"\"";
			
			String followeeNum="SELECT count(to_id) FROM follows WHERE from_id =\"" + id+"\"";
			ResultSet rs=stmt2.executeQuery(followeeNum);

			int i=0;
			while(rs.next()) {
				
				num = rs.getInt(1);
				System.out.println("내가 팔로잉하는 아이디"+num);
			}
			followeeInfo = new String[num];
			
			rs=stmt2.executeQuery(QmyFollowee);

			
			i = 0;
			while(rs.next()) {
				
				System.out.println(rs.getString(1));
				followeeInfo[i] = rs.getString(1);
				i++;
			
			}				
			
			
			return followeeInfo;
		} catch(SQLException e) {
			e.printStackTrace();
			return new String[1];
		}
	}
public void sqlPlus(String myid, String toid) {
	
	String[] followerInfo;
	
	
//	String QmyId ="SELECT id FROM users WHERE name =\"" + name+ "\"";
//	String toid = "";
//		
	
	int num = 0;

	try {
		Statement stmt = con.createStatement();
		
//		ResultSet rs=stmt.executeQuery(QmyId);
//		
//		//현재 사용자의 아이디를 확인
//		while(rs.next()) {
//			toid = rs.getString(1);
//		}
		
		//팔로워 수
		String plus ="Insert INTO follows value(?,?)";
		
		PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(plus);
            pstmt.setString(1, myid.toString());
            pstmt.setString(2, toid );
            
            int result = pstmt.executeUpdate();
            if(result==1) {
                System.out.println("Board데이터 삽입 성공!");
                
            }
        }catch (Exception e) {
            System.out.println("Board데이터 삽입 실패!");
        }
	} catch(SQLException e) {
		e.printStackTrace();
	}
}
public void sqlremove(String myid, String toid) {
	
	String[] followerInfo;
	
	
//	String QmyId ="SELECT id FROM users WHERE name =\"" + name+ "\"";
//	String toid = "";
//		
//	
	int num = 0;

	try {
		Statement stmt = con.createStatement();
//		
//		ResultSet rs=stmt.executeQuery(QmyId);
//		
		//현재 사용자의 아이디를 확인
//		while(rs.next()) {
//			toid = rs.getString(1);
//		}
		
		
		String remove ="delete from follows where from_id = ? and to_id = ?";
		
		PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(remove);
            pstmt.setString(1, myid.toString());
            pstmt.setString(2, toid );
            
            int result = pstmt.executeUpdate();
            if(result==1) {
                System.out.println("Board데이터 삭제 성공!");
                
            }
        }catch (Exception e) {
            System.out.println("Board데이터 삽입 실패!");
        }
	} catch(SQLException e) {
		e.printStackTrace();
	}
}

}