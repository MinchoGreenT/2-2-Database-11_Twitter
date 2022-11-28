import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Search extends JFrame {
	
	static JFrame con;
	static JFrame board;
	Db_connection DB = new Db_connection();
	
	
	Search(JFrame userBoard) {
		setSize(400, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        con = this;
        board = userBoard;
        JPanel inputForm = new JPanel(new FlowLayout(FlowLayout.CENTER));
        inputForm.setBackground(Color.WHITE);
        inputForm.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        
        JTextField inputSearch = new JTextField(20);
        JButton inputButton = new JButton("�˻�");
        inputButton.setBackground(new Color(29,161,242));
        inputButton.setFont(new Font("���� ���", Font.BOLD, 12));
        inputButton.setForeground(Color.WHITE);
        
        inputForm.add(inputSearch);
        inputForm.add(inputButton);
        
        JPanel userList = new JPanel();
        userList.setBackground(Color.WHITE);
        userList.setLayout(new BoxLayout(userList, BoxLayout.Y_AXIS));
        JScrollPane userListScroll = new JScrollPane(userList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        userListScroll.getVerticalScrollBar().setUnitIncrement(20);
        
        // ------------------------- �˻� ��ư �̺�Ʈ ----------------------------
        inputButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				userList.removeAll();
				// inputSearch.getText() �� ���� �˻�� �ް�
				// ���� �˻�� ���� �������� ¥�� ����Ʈ�� ���� ����
				// userList.add(new UserBoard(�̸�, ���̵�) �������� ���
				String searchId = inputSearch.getText();
				
				String[] searchIdList;
			
				
				if(searchId.equals("")) {
					searchIdList = DB.sqlUserId();
					 System.out.println("�ƾ�?");

				}else {
					searchIdList = DB.sqlUserIdBySearching(searchId);
					 System.out.println("���פ�");
				}
				
				int num = searchIdList.length;
				
				for(int i = 0; i < num; i++) {
					 userList.add(new UserBoard(DB.sqlUserNameById(searchIdList[i]), searchIdList[i]));
					 System.out.println("name: "+DB.sqlUserNameById(searchIdList[i])+" id: "+searchIdList[i]);
				}
				
				userListScroll.setViewportView(userList);

				
			}
        });

        // ���ϴ� ���� ���
        String[] searchIdList;
		searchIdList = DB.sqlUserId();
		int num = searchIdList.length;
		
        for(int i = 0; i < num; i++) {
			 userList.add(new UserBoard(DB.sqlUserNameById(searchIdList[i]), searchIdList[i]));
			 System.out.println("name: "+DB.sqlUserNameById(searchIdList[i])+" id: "+searchIdList[i]);
		}
        
        
        UserBoard yeah = new UserBoard("�̸�", "���̵�");
        userList.add(yeah);
        userList.add(new UserBoard("�̸�","�ƾƾ������̵�"));
        // ------------------------- �˻� ��ư �̺�Ʈ ----------------------------
        
        add(inputForm, BorderLayout.NORTH);
        add(userListScroll, BorderLayout.CENTER);
        
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	static class UserBoard extends JButton {
    	UserBoard(String username, String userId) {
    		setAlignmentX(Component.CENTER_ALIGNMENT);
    		setPreferredSize(new Dimension(200, 150));
            setFont(new Font("���� ���", Font.BOLD, 12));
            setBorderPainted(false);
            setContentAreaFilled(false);
            setFocusPainted(false);
    		
            setText(username + "@" + userId);
            
            addActionListener(new ActionListener( ) {

				@Override
				public void actionPerformed(ActionEvent e) {
					board.dispose();
					con.dispose();
					new Board(userId);
				}
            });
    	}
    }
}
