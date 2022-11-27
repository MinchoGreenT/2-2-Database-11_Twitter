import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Board extends JFrame {
	
	static JFrame con;
	
    Board(String userId) {
    	con = this;
        setSize(1200, 800);
        setLayout(new BorderLayout());

        JPanel userProfile = new JPanel(new GridLayout(1, 6));
        userProfile.setBorder(BorderFactory.createEmptyBorder(10,200,10,200));

        Image logo =new ImageIcon(Objects.requireNonNull(Main.class.getResource("/image/logo_profile.png"))).getImage();
        Image logoIcon = logo.getScaledInstance(100,100,Image.SCALE_SMOOTH);

        // -------------------------------- ������ �г� -------------------------------------
        String userName = "������";
        // userId�� ���ڷ� �޾ƿðŴϱ� userId�� �̸��� �޾ƿͼ� userName���� ���� �ǰ���
        JLabel userInfo = new JLabel("<html>" + userName + "<br>@" + userId + "<html>");
        userInfo.setForeground(Color.WHITE);
        userInfo.setHorizontalAlignment(JLabel.CENTER);
        userInfo.setVerticalAlignment(JLabel.CENTER);
        userInfo.setFont(new Font("���� ���", Font.BOLD, 20));
        
        // ���� ���� ���ڷ� ���� userId�� �ȷ��� ���̺��� �̿��ؼ� �ȷο�, �ȷ��� ���� �������� ��
        int followerNum = 100;
        int followingNum = 101;
        
        JLabel userFollowing = new JLabel("<html>�ȷ���<br>" + followingNum + "<html>");
        userFollowing.setForeground(Color.WHITE);
        userFollowing.setHorizontalAlignment(JLabel.CENTER);
        userFollowing.setVerticalAlignment(JLabel.CENTER);
        userFollowing.setFont(new Font("���� ���", Font.BOLD, 20));
        
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
        follow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// �� ������ �ȷο� ����� �߰�. 
				// ���� ������ ������ ��� ��ư�� ������ ���� ���̸� 
				// �ƴ� ��� ���� �������� ������ userId�� Main.myId�� �ȷο� �ϰ� ��
			}
        });
        if(userId == Login.myId) {
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
                new NewMessage(con, userId);
            }
        });
        
        option.add(search);
        option.add(newMessage);
        
        // ���� �����ʻ��� �ִ°��ε� �׳� Ʈ���� �������� ��
        userProfile.add(new JLabel(new ImageIcon(logoIcon)));
        userProfile.add(userInfo);
        userProfile.add(userFollowing);
        userProfile.add(userFollower);
        userProfile.add(option);
        userProfile.add(follow);
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
        
        messageBoard hi = new messageBoard(1);
        userBoard.add(hi);
        userBoard.add(new messageBoard(1));
        userBoard.add(new messageBoard(1));
        userBoard.add(new messageBoard(1));
        userBoard.add(new messageBoard(1));
        userBoard.add(new messageBoard(1));
        userBoard.add(new messageBoard(1));
        userBoard.add(new messageBoard(1));
        userBoard.add(new messageBoard(1));
        
        //-------------------------------------------------------- �޼��� ��
        
      //-------------------------------------------------------- �ȷ���/�ȷο�
        
        JPanel userFollowingBoard = new JPanel();
        userFollowingBoard.setBackground(Color.WHITE);
        userFollowingBoard.setLayout(new BoxLayout(userFollowingBoard, BoxLayout.Y_AXIS));
        JScrollPane userFollowingBoardScroll = new JScrollPane(userFollowingBoard, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        userFollowingBoardScroll.getVerticalScrollBar().setUnitIncrement(20);
        
	     // ���⿡ �ȷ��� �������� �ҷ��ͼ� ����ϸ� �ǰڽ��ϴ�
	     // userFollowingBoard.add(new FollowBoard(�����̸�, �������̵�) �������� �߰�
	     // �ݺ����� ���ؼ� �ȷ��� ����ŭ �߰����ָ� �˴ϴ�
        FollowBoard yeah = new FollowBoard("�̸�", "���̵�");
        userFollowingBoard.add(yeah);
        userFollowingBoard.add(new FollowBoard("�̸�","�ƾƾ������̵�"));
        
        JPanel userFollowerBoard = new JPanel();
        userFollowerBoard.setBackground(Color.WHITE);
        userFollowerBoard.setLayout(new BoxLayout(userFollowerBoard, BoxLayout.Y_AXIS));
        JScrollPane userFollowerBoardScroll = new JScrollPane(userFollowerBoard, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        userFollowerBoardScroll.getVerticalScrollBar().setUnitIncrement(20);
        
        // �ȷο��� ������ ������� �����ϸ� �ǰڽ��ϴ�.
        FollowBoard yeahap = new FollowBoard("�̸�","���̵�");
        userFollowerBoard.add(yeahap);
        
        //-------------------------------------------------------- �ȷ���/�ȷο� ��
        
        add(userFollowingBoardScroll, BorderLayout.WEST);
        add(userFollowerBoardScroll, BorderLayout.EAST);
        add(userBoardScroll, BorderLayout.CENTER);
        add(userProfile, BorderLayout.NORTH);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    static class messageBoard extends JPanel {
        messageBoard(int id) {
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
            JLabel userInfo = new JLabel("<html>������<br>@MintCC<html>");
            userInfo.setHorizontalAlignment(JLabel.CENTER);
            userInfo.setVerticalAlignment(JLabel.BOTTOM);
            userInfo.setFont(new Font("���� ���", Font.BOLD, 24));
            

            // ����� �޼��� ������ ������ ��
            JLabel message = new JLabel("�ȳ��ϼ��� �ȳ��ϼ��� �ȳ��ϼ��� �ȳ��ϼ��� ");
            message.setFont(new Font("���� ���", Font.PLAIN, 24));
            
            // --------------------------- �޼��� ���� ------------------------
            user.add(new JLabel(new ImageIcon(logoIcon)));
            user.add(userInfo);

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