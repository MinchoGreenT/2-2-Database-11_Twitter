import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Board extends JFrame {
	
	static JFrame con;
	
	
	// 씅미니의 추가
	static String currentUser = "이승민";
	static String currentUserId ="tmdals";
	static String currentUserPw = "1234";
	
	Integer num_follower = 0;
	Integer num_followee = 0;
	Integer my_num_followee = 0;
	
	Db_connection DB = new Db_connection();

	 //
	
	
    Board(String userId) {
    	DB.sqlSetting();
    	
    	
    	//씅미닝 추가
    	
    	currentUser = DB.sqlUserNameById(userId);
    	currentUserId = userId;
    	
    	
    	String[] followerInfo;
		followerInfo = DB.sqlFollower(currentUserId);
		num_follower = followerInfo.length;
		
		String[] followeeInfo;
		followeeInfo = DB.sqlFollowee(currentUserId);
		num_followee = followeeInfo.length;
    	
		System.out.println(currentUser + currentUserId);
			
		String[] myfolloweeInfo;
		myfolloweeInfo = DB.sqlFollowee(Login.myId);
		my_num_followee = myfolloweeInfo.length;
    	
    	//
    	
    	
    	con = this;
        setSize(1200, 800);
        setLayout(new BorderLayout());

        JPanel userProfile = new JPanel(new GridLayout(1, 6));
        userProfile.setBorder(BorderFactory.createEmptyBorder(10,200,10,200));

        Image logo =new ImageIcon(Objects.requireNonNull(Main.class.getResource("/image/logo_profile.png"))).getImage();
        Image logoIcon = logo.getScaledInstance(100,100,Image.SCALE_SMOOTH);
        
        
        // -------------------------------- 프로필 패널 -------------------------------------
        String userName = currentUser;
        // userId를 인자로 받아올거니까 userId로 이름을 받아와서 userName으로 쓰면 되겠음
        JLabel userInfo = new JLabel("<html>" + userName + "<br>@" + userId + "<html>");
        userInfo.setForeground(Color.WHITE);
        userInfo.setHorizontalAlignment(JLabel.CENTER);
        userInfo.setVerticalAlignment(JLabel.CENTER);
        userInfo.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        
        // 여기 또한 인자로 받은 userId와 팔로잉 테이블을 이용해서 팔로워, 팔로잉 수를 가져오면 됨
        int followerNum = num_follower;
        int followingNum = num_followee;
        
        JLabel userFollowing = new JLabel("<html>팔로잉<br>" + followingNum + "<html>");
        userFollowing.setForeground(Color.WHITE);
        userFollowing.setHorizontalAlignment(JLabel.CENTER);
        userFollowing.setVerticalAlignment(JLabel.CENTER);
        userFollowing.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        
        JPanel userFollowingBoard = new JPanel();
        userFollowingBoard.setBackground(Color.WHITE);
        userFollowingBoard.setLayout(new BoxLayout(userFollowingBoard, BoxLayout.Y_AXIS));
        JScrollPane userFollowingBoardScroll = new JScrollPane(userFollowingBoard, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        userFollowingBoardScroll.getVerticalScrollBar().setUnitIncrement(20);
        

        JPanel userFollowerBoard = new JPanel();
        userFollowerBoard.setBackground(Color.WHITE);
        userFollowerBoard.setLayout(new BoxLayout(userFollowerBoard, BoxLayout.Y_AXIS));
        JScrollPane userFollowerBoardScroll = new JScrollPane(userFollowerBoard, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        userFollowerBoardScroll.getVerticalScrollBar().setUnitIncrement(20);
        
        
        
        
        
        
        
        JLabel userFollower = new JLabel("<html>팔로워<br>" + followerNum + "<html>");
        userFollower.setForeground(Color.WHITE);
        userFollower.setHorizontalAlignment(JLabel.CENTER);
        userFollower.setVerticalAlignment(JLabel.CENTER);
        userFollower.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        
     
        JButton follow = new JButton("팔로우");
        follow.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
        follow.setBackground(new Color(0x338BFF));
        follow.setFont(new Font("맑은 고딕", Font.BOLD, 24));
        follow.setForeground(Color.WHITE);
        
        String[] check_I_following = DB.sqlFollowee(Login.myId);

		if(Arrays.asList(check_I_following).contains(currentUserId) == true){
			follow.setText("팔로우취소");
			System.out.println("이 사람은 이미 팔로우 중입니다.");
		}
		
        follow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 본 공간에 팔로우 기능을 추가. 
				// 만약 본인의 보드일 경우 버튼은 보이지 않을 것이며 
				// 아닐 경우 보드 생성자의 인자인 userId를 Main.myId가 팔로우 하게 됨
				String identi = e.getActionCommand();
				JButton thisB = (JButton)e.getSource();
				

				  
						
				if(identi.equals("팔로우")) {
					//Integer num = Integer.parseInt(identi.substring(6)); 
					thisB.setBackground(Color.cyan);
					String plus_id = thisB.getName();
					
					DB.sqlPlus(Login.myId,currentUserId);
					
					System.out.println("dd"+Login.myId.toString()+"ddd"+currentUserId);
					
					String[] currentfollowerInfo;
					currentfollowerInfo = DB.sqlFollower(currentUserId); //나의 팔로워
					Integer num_currentfollower = currentfollowerInfo.length;
					userFollower.setText("<html>팔로워<br>" + num_currentfollower + "<html>");	

					
					String[] currentfolloweeInfo;
					currentfolloweeInfo = DB.sqlFollowee(currentUserId); //내가 팔로워
					Integer num_currentfollowee = currentfolloweeInfo.length;
					userFollowing.setText("<html>팔로잉<br>" + num_currentfollowee + "<html>");
					
					String[] followerInfo;
					followerInfo = DB.sqlFollower(Login.myId); //나의 팔로워
					num_follower = followerInfo.length;
					
					String[] followeeInfo;
					followeeInfo = DB.sqlFollowee(Login.myId); //내가 팔로워
					num_followee = followeeInfo.length;
//					
					thisB.setText("팔로우취소");
//					
//					printUsers(followeeInfo);
//					printMyUsers(followeeInfo);
					if(num_currentfollower >0) {

					userFollowerBoard.removeAll();
					for(int i = 0; i < num_currentfollower; i++) {
						String find_follower_name = DB.sqlUserNameById(currentfollowerInfo[i]);
						
						
						FollowBoard yeahap = new FollowBoard(find_follower_name, currentfollowerInfo[i]);
				        userFollowerBoard.add(yeahap);

					}
					userFollowerBoardScroll.setViewportView(userFollowerBoard);
					}
					
					if(num_currentfollowee >0) {

					userFollowingBoard.removeAll();
					for(int i = 0; i < num_currentfollowee; i++) {
						String find_followee_name = DB.sqlUserNameById(currentfolloweeInfo[i]);
						
						
						FollowBoard yeahap = new FollowBoard(find_followee_name, currentfolloweeInfo[i]);
				        userFollowingBoard.add(yeahap);

					}
					userFollowingBoardScroll.setViewportView(userFollowingBoard);
					}
					//userFollowingBoardScroll.add(userFollowingBoard);
					
				}
				else if(identi.equals("팔로우취소")) {
					//Integer num = Integer.parseInt(identi.substring(6)); 
					thisB.setBackground(Color.magenta);

					String remove_id = thisB.getName();
					
					DB.sqlremove(Login.myId,currentUserId);
					thisB.setText("팔로우");
					
					
					
					String[] currentfollowerInfo;
					currentfollowerInfo = DB.sqlFollower(currentUserId); //나의 팔로워
					Integer num_currentfollower = currentfollowerInfo.length;
					userFollower.setText("<html>팔로워<br>" + num_currentfollower + "<html>");	

					
					String[] currentfolloweeInfo;
					currentfolloweeInfo = DB.sqlFollowee(currentUserId); //내가 팔로워
					Integer num_currentfollowee = currentfolloweeInfo.length;
					userFollowing.setText("<html>팔로잉<br>" + num_currentfollowee + "<html>");
					
					
					
					
					

					String[] followerInfo;
					followerInfo = DB.sqlFollower(Login.myId); //나의 팔로워
					num_follower = followerInfo.length;
					
					
					String[] followeeInfo;
					followeeInfo = DB.sqlFollowee(Login.myId); //내가 팔로워
					num_follower = followeeInfo.length;
					
					
					if(num_currentfollower >0) {

					userFollowerBoard.removeAll();
					for(int i = 0; i < num_currentfollower; i++) {
						String find_follower_name = DB.sqlUserNameById(currentfollowerInfo[i]);
						
						
						FollowBoard yeahap = new FollowBoard(find_follower_name, currentfollowerInfo[i]);
				        userFollowerBoard.add(yeahap);

					}
					userFollowerBoardScroll.setViewportView(userFollowerBoard);
					}else {
						userFollowerBoard.removeAll();

					}
					if(num_currentfollowee >0) {

					userFollowingBoard.removeAll();
					for(int i = 0; i < num_currentfollowee; i++) {
						String find_followee_name = DB.sqlUserNameById(currentfolloweeInfo[i]);
						
						
						FollowBoard yeahap = new FollowBoard(find_followee_name, currentfolloweeInfo[i]);
				        userFollowingBoard.add(yeahap);

					}
					userFollowingBoardScroll.setViewportView(userFollowingBoard);
					}else {
						userFollowingBoard.removeAll();

					}
					
					
				}
				//SwingUtilities.updateComponentTreeUI(this)
			}

        });
        
        if(userId.equals(Login.myId)) {
        	follow.setVisible(false);
        }
        
        JPanel option = new JPanel(new GridLayout(2,1));
        option.setBackground(new Color(0x97C2FF));
        
        JButton search = new JButton("검색");
        search.setFont(new Font("맑은 고딕", Font.BOLD, 24));
        search.setBorderPainted(false);
        search.setContentAreaFilled(false);
        search.setFocusPainted(false);
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Search(con);
            }
        });
        
        JButton newMessage = new JButton("글쓰기");
        newMessage.setFont(new Font("맑은 고딕", Font.BOLD, 24));
        newMessage.setBorderPainted(false);
        newMessage.setContentAreaFilled(false);
        newMessage.setFocusPainted(false);
        newMessage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	if(follow.getText().equals("팔로우취소") || Login.myId.equals(currentUserId)){
        			new NewMessage(con, Login.myId, currentUserId);
            
        		}
            }
          });
        
       
        
        
        option.add(search);
        option.add(newMessage);
        
        
        
        JButton home = new JButton("내 홈");
        home.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
        home.setBackground(new Color(0x338BFF));
        home.setFont(new Font("맑은 고딕", Font.BOLD, 24));
        home.setForeground(Color.WHITE);
        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	con.dispose();
                new Board(Login.myId);
            }
        });
        
        // 여긴 프로필사진 넣는곳인데 그냥 트위터 사진으로 함
        userProfile.add(new JLabel(new ImageIcon(logoIcon)));
        userProfile.add(userInfo);
        userProfile.add(userFollowing);
        userProfile.add(userFollower);
        userProfile.add(option);
        userProfile.add(follow);
        userProfile.add(home);
        userProfile.setBackground(new Color(0x97C2FF));
        // -------------------------------- 프로필 패널 끝 -------------------------------------

        // 메세지들이 출력될 패널, 스크롤 가능
        JPanel userBoard = new JPanel();
        userBoard.setBackground(Color.WHITE);
        userBoard.setLayout(new BoxLayout(userBoard, BoxLayout.Y_AXIS));
        JScrollPane userBoardScroll = new JScrollPane(userBoard, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        userBoardScroll.getVerticalScrollBar().setUnitIncrement(20);

        // ----------------- 메세지 보드 ----------------------------------
        // 본 주석 하단에 메세지 객체 생성 및 삽입 코드가 들어감
        // messageBoard는 하나의 메세지를 구성하는 "패널"
        // 생성자의 인자로는 메세지 id를 넣어서 생성자가 id를 가지고 패널을 구성함
        // 생성자 내부에서 id로 메세지 정보를 불러와서 패널 구성해야됨
        
        String[][] wordList = new String[100][3];
		wordList = DB.getWord(currentUserId);
		int count = 0;

        while(wordList[count][0] != null){
			JTextArea textArea1 = new JTextArea();
			textArea1.setText(wordList[count][1]);

            messageBoard hi = new messageBoard(wordList[count][0], textArea1, wordList[count][2]);
            count++;
            
            userBoard.add(hi);
		}
        
        //-------------------------------------------------------- 메세지 끝
        
      //-------------------------------------------------------- 팔로잉/팔로워
        
//        JPanel userFollowingBoard = new JPanel();
//        userFollowingBoard.setBackground(Color.WHITE);
//        userFollowingBoard.setLayout(new BoxLayout(userFollowingBoard, BoxLayout.Y_AXIS));
//        JScrollPane userFollowingBoardScroll = new JScrollPane(userFollowingBoard, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//        userFollowingBoardScroll.getVerticalScrollBar().setUnitIncrement(20);
        
	     // 여기에 팔로잉 유저들을 불러와서 출력하면 되겠습니다
	     // userFollowingBoard.add(new FollowBoard(유저이름, 유저아이디) 형식으로 추가
	     // 반복문을 통해서 팔로잉 수만큼 추가해주면 됩니다
		
        for(int i = 0; i < num_followee; i++) {
			String find_followee_name = DB.sqlUserNameById(followeeInfo[i]);
			
			
			FollowBoard yeah = new FollowBoard(find_followee_name, followeeInfo[i]);
	        userFollowingBoard.add(yeah);

		}
        
       
//        
//        JPanel userFollowerBoard = new JPanel();
//        userFollowerBoard.setBackground(Color.WHITE);
//        userFollowerBoard.setLayout(new BoxLayout(userFollowerBoard, BoxLayout.Y_AXIS));
//        JScrollPane userFollowerBoardScroll = new JScrollPane(userFollowerBoard, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//        userFollowerBoardScroll.getVerticalScrollBar().setUnitIncrement(20);
        
        ////////////////////////////////////////////
        
        // 팔로워도 동일한 방식으로 진행하면 되겠습니다.
        for(int i = 0; i < num_follower; i++) {
			String find_follower_name = DB.sqlUserNameById(followerInfo[i]);
			
			
			FollowBoard yeahap = new FollowBoard(find_follower_name, followerInfo[i]);
	        userFollowerBoard.add(yeahap);

		}
        
        //-------------------------------------------------------- 팔로잉/팔로워 끝
        
        add(userFollowingBoardScroll, BorderLayout.WEST);
        add(userFollowerBoardScroll, BorderLayout.EAST);
        add(userBoardScroll, BorderLayout.CENTER);
        add(userProfile, BorderLayout.NORTH);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    static class messageBoard extends JPanel {
        messageBoard(String writerId, JTextArea textArea1, String time) {
            setBackground(Color.white);
            setSize(800, 200);
            
            JPanel messagePanel = new JPanel(new GridLayout(2,1));
            messagePanel.setBorder(new TitledBorder(new LineBorder(Color.black,5)));
            messagePanel.setBackground(Color.white);

            JPanel user = new JPanel(new FlowLayout(FlowLayout.LEFT));
            user.setBackground(Color.white);

            // 여기는 프로필사진 생성부분인데 그냥 트위터 사진으로 대체
            Image logo =new ImageIcon(Objects.requireNonNull(Main.class.getResource("/image/logo_message.png"))).getImage();
            Image logoIcon = logo.getScaledInstance(50,50,Image.SCALE_SMOOTH);
            
            
            // --------------------------- 메세지 내용 ------------------------
            
            // 본 라벨 생성자에 사용자 이름, 사용자 ID를 넣음
            JLabel userInfo = new JLabel(writerId);
            userInfo.setHorizontalAlignment(JLabel.CENTER);
            userInfo.setVerticalAlignment(JLabel.BOTTOM);
            userInfo.setFont(new Font("맑은 고딕", Font.BOLD, 24));
            
            String[] timeSet = time.split("\\.");
        	String printTime = timeSet[0] + "년 " + timeSet[1] + "월 " + timeSet[2] + "일 " + timeSet[3] + "시 " + timeSet[4] + "분";       	
        	JLabel timeLabel = new JLabel(printTime);

            // 여기는 메세지 내용을 넣으면 됨
            JTextArea message = textArea1;
            message.setFont(new Font("맑은 고딕", Font.PLAIN, 24));
            
            // --------------------------- 메세지 내용 ------------------------
            user.add(new JLabel(new ImageIcon(logoIcon)));
            user.add(userInfo);
            user.add(timeLabel);

            messagePanel.add(user);
            messagePanel.add(message);
            
            add(messagePanel);
        }
    }
    
    static class FollowBoard extends JButton {
    	FollowBoard(String username, String userId) {
    		setAlignmentX(Component.CENTER_ALIGNMENT);
    		setPreferredSize(new Dimension(200, 150));
            setFont(new Font("맑은 고딕", Font.BOLD, 12));
            setBorderPainted(false);
            setContentAreaFilled(false);
            setFocusPainted(false);
    		
            setText(username + "@" + userId);
            addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					con.dispose();
					new Board(userId);
				}
            	
            });
    	}
    }
}