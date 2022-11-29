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
	
	
	// ���̴��� �߰�
	static String currentUser = "�̽¹�";
	static String currentUserId ="tmdals";
	static String currentUserPw = "1234";
	
	Integer num_follower = 0;
	Integer num_followee = 0;
	Integer my_num_followee = 0;
	
	Db_connection DB = new Db_connection();

	 //
	
	
    Board(String userId) {
    	DB.sqlSetting();
    	
    	
    	//���̴� �߰�
    	
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
        
        
        // -------------------------------- ������ �г� -------------------------------------
        String userName = currentUser;
        // userId�� ���ڷ� �޾ƿðŴϱ� userId�� �̸��� �޾ƿͼ� userName���� ���� �ǰ���
        JLabel userInfo = new JLabel("<html>" + userName + "<br>@" + userId + "<html>");
        userInfo.setForeground(Color.WHITE);
        userInfo.setHorizontalAlignment(JLabel.CENTER);
        userInfo.setVerticalAlignment(JLabel.CENTER);
        userInfo.setFont(new Font("���� ���", Font.BOLD, 20));
        
        // ���� ���� ���ڷ� ���� userId�� �ȷ��� ���̺��� �̿��ؼ� �ȷο�, �ȷ��� ���� �������� ��
        int followerNum = num_follower;
        int followingNum = num_followee;
        
        JLabel userFollowing = new JLabel("<html>�ȷ���<br>" + followingNum + "<html>");
        userFollowing.setForeground(Color.WHITE);
        userFollowing.setHorizontalAlignment(JLabel.CENTER);
        userFollowing.setVerticalAlignment(JLabel.CENTER);
        userFollowing.setFont(new Font("���� ���", Font.BOLD, 20));
        
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
        
        
        
        
        
        
        
        JLabel userFollower = new JLabel("<html>�ȷο�<br>" + followerNum + "<html>");
        userFollower.setForeground(Color.WHITE);
        userFollower.setHorizontalAlignment(JLabel.CENTER);
        userFollower.setVerticalAlignment(JLabel.CENTER);
        userFollower.setFont(new Font("���� ���", Font.BOLD, 20));
        
     
        JButton follow = new JButton("�ȷο�");
        follow.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
        follow.setBackground(new Color(0x338BFF));
        follow.setFont(new Font("���� ���", Font.BOLD, 24));
        follow.setForeground(Color.WHITE);
        
        String[] check_I_following = DB.sqlFollowee(Login.myId);

		if(Arrays.asList(check_I_following).contains(currentUserId) == true){
			follow.setText("�ȷο����");
			System.out.println("�� ����� �̹� �ȷο� ���Դϴ�.");
		}
		
        follow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// �� ������ �ȷο� ����� �߰�. 
				// ���� ������ ������ ��� ��ư�� ������ ���� ���̸� 
				// �ƴ� ��� ���� �������� ������ userId�� Main.myId�� �ȷο� �ϰ� ��
				String identi = e.getActionCommand();
				JButton thisB = (JButton)e.getSource();
				

				  
						
				if(identi.equals("�ȷο�")) {
					//Integer num = Integer.parseInt(identi.substring(6)); 
					thisB.setBackground(Color.cyan);
					String plus_id = thisB.getName();
					
					DB.sqlPlus(Login.myId,currentUserId);
					
					System.out.println("dd"+Login.myId.toString()+"ddd"+currentUserId);
					
					String[] currentfollowerInfo;
					currentfollowerInfo = DB.sqlFollower(currentUserId); //���� �ȷο�
					Integer num_currentfollower = currentfollowerInfo.length;
					userFollower.setText("<html>�ȷο�<br>" + num_currentfollower + "<html>");	

					
					String[] currentfolloweeInfo;
					currentfolloweeInfo = DB.sqlFollowee(currentUserId); //���� �ȷο�
					Integer num_currentfollowee = currentfolloweeInfo.length;
					userFollowing.setText("<html>�ȷ���<br>" + num_currentfollowee + "<html>");
					
					String[] followerInfo;
					followerInfo = DB.sqlFollower(Login.myId); //���� �ȷο�
					num_follower = followerInfo.length;
					
					String[] followeeInfo;
					followeeInfo = DB.sqlFollowee(Login.myId); //���� �ȷο�
					num_followee = followeeInfo.length;
//					
					thisB.setText("�ȷο����");
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
				else if(identi.equals("�ȷο����")) {
					//Integer num = Integer.parseInt(identi.substring(6)); 
					thisB.setBackground(Color.magenta);

					String remove_id = thisB.getName();
					
					DB.sqlremove(Login.myId,currentUserId);
					thisB.setText("�ȷο�");
					
					
					
					String[] currentfollowerInfo;
					currentfollowerInfo = DB.sqlFollower(currentUserId); //���� �ȷο�
					Integer num_currentfollower = currentfollowerInfo.length;
					userFollower.setText("<html>�ȷο�<br>" + num_currentfollower + "<html>");	

					
					String[] currentfolloweeInfo;
					currentfolloweeInfo = DB.sqlFollowee(currentUserId); //���� �ȷο�
					Integer num_currentfollowee = currentfolloweeInfo.length;
					userFollowing.setText("<html>�ȷ���<br>" + num_currentfollowee + "<html>");
					
					
					
					
					

					String[] followerInfo;
					followerInfo = DB.sqlFollower(Login.myId); //���� �ȷο�
					num_follower = followerInfo.length;
					
					
					String[] followeeInfo;
					followeeInfo = DB.sqlFollowee(Login.myId); //���� �ȷο�
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
        
        JButton search = new JButton("�˻�");
        search.setFont(new Font("���� ���", Font.BOLD, 24));
        search.setBorderPainted(false);
        search.setContentAreaFilled(false);
        search.setFocusPainted(false);
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Search(con);
            }
        });
        
        JButton newMessage = new JButton("�۾���");
        newMessage.setFont(new Font("���� ���", Font.BOLD, 24));
        newMessage.setBorderPainted(false);
        newMessage.setContentAreaFilled(false);
        newMessage.setFocusPainted(false);
        newMessage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	if(follow.getText().equals("�ȷο����") || Login.myId.equals(currentUserId)){
        			new NewMessage(con, Login.myId, currentUserId);
            
        		}
            }
          });
        
       
        
        
        option.add(search);
        option.add(newMessage);
        
        
        
        JButton home = new JButton("�� Ȩ");
        home.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
        home.setBackground(new Color(0x338BFF));
        home.setFont(new Font("���� ���", Font.BOLD, 24));
        home.setForeground(Color.WHITE);
        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	con.dispose();
                new Board(Login.myId);
            }
        });
        
        // ���� �����ʻ��� �ִ°��ε� �׳� Ʈ���� �������� ��
        userProfile.add(new JLabel(new ImageIcon(logoIcon)));
        userProfile.add(userInfo);
        userProfile.add(userFollowing);
        userProfile.add(userFollower);
        userProfile.add(option);
        userProfile.add(follow);
        userProfile.add(home);
        userProfile.setBackground(new Color(0x97C2FF));
        // -------------------------------- ������ �г� �� -------------------------------------

        // �޼������� ��µ� �г�, ��ũ�� ����
        JPanel userBoard = new JPanel();
        userBoard.setBackground(Color.WHITE);
        userBoard.setLayout(new BoxLayout(userBoard, BoxLayout.Y_AXIS));
        JScrollPane userBoardScroll = new JScrollPane(userBoard, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        userBoardScroll.getVerticalScrollBar().setUnitIncrement(20);

        // ----------------- �޼��� ���� ----------------------------------
        // �� �ּ� �ϴܿ� �޼��� ��ü ���� �� ���� �ڵ尡 ��
        // messageBoard�� �ϳ��� �޼����� �����ϴ� "�г�"
        // �������� ���ڷδ� �޼��� id�� �־ �����ڰ� id�� ������ �г��� ������
        // ������ ���ο��� id�� �޼��� ������ �ҷ��ͼ� �г� �����ؾߵ�
        
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
        
        //-------------------------------------------------------- �޼��� ��
        
      //-------------------------------------------------------- �ȷ���/�ȷο�
        
//        JPanel userFollowingBoard = new JPanel();
//        userFollowingBoard.setBackground(Color.WHITE);
//        userFollowingBoard.setLayout(new BoxLayout(userFollowingBoard, BoxLayout.Y_AXIS));
//        JScrollPane userFollowingBoardScroll = new JScrollPane(userFollowingBoard, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//        userFollowingBoardScroll.getVerticalScrollBar().setUnitIncrement(20);
        
	     // ���⿡ �ȷ��� �������� �ҷ��ͼ� ����ϸ� �ǰڽ��ϴ�
	     // userFollowingBoard.add(new FollowBoard(�����̸�, �������̵�) �������� �߰�
	     // �ݺ����� ���ؼ� �ȷ��� ����ŭ �߰����ָ� �˴ϴ�
		
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
        
        // �ȷο��� ������ ������� �����ϸ� �ǰڽ��ϴ�.
        for(int i = 0; i < num_follower; i++) {
			String find_follower_name = DB.sqlUserNameById(followerInfo[i]);
			
			
			FollowBoard yeahap = new FollowBoard(find_follower_name, followerInfo[i]);
	        userFollowerBoard.add(yeahap);

		}
        
        //-------------------------------------------------------- �ȷ���/�ȷο� ��
        
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

            // ����� �����ʻ��� �����κ��ε� �׳� Ʈ���� �������� ��ü
            Image logo =new ImageIcon(Objects.requireNonNull(Main.class.getResource("/image/logo_message.png"))).getImage();
            Image logoIcon = logo.getScaledInstance(50,50,Image.SCALE_SMOOTH);
            
            
            // --------------------------- �޼��� ���� ------------------------
            
            // �� �� �����ڿ� ����� �̸�, ����� ID�� ����
            JLabel userInfo = new JLabel(writerId);
            userInfo.setHorizontalAlignment(JLabel.CENTER);
            userInfo.setVerticalAlignment(JLabel.BOTTOM);
            userInfo.setFont(new Font("���� ���", Font.BOLD, 24));
            
            String[] timeSet = time.split("\\.");
        	String printTime = timeSet[0] + "�� " + timeSet[1] + "�� " + timeSet[2] + "�� " + timeSet[3] + "�� " + timeSet[4] + "��";       	
        	JLabel timeLabel = new JLabel(printTime);

            // ����� �޼��� ������ ������ ��
            JTextArea message = textArea1;
            message.setFont(new Font("���� ���", Font.PLAIN, 24));
            
            // --------------------------- �޼��� ���� ------------------------
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
            setFont(new Font("���� ���", Font.BOLD, 12));
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