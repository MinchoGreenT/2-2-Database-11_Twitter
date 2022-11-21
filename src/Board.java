import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.util.Objects;

public class Board extends JFrame {
    Board() {
        setSize(1200, 800);
        setLayout(new BorderLayout());

        JPanel userProfile = new JPanel(new GridLayout(1, 5));
        userProfile.setBorder(BorderFactory.createEmptyBorder(10,200,10,200));

        Image logo =new ImageIcon(Objects.requireNonNull(Main.class.getResource("/image/logo_profile.png"))).getImage();
        Image logoIcon = logo.getScaledInstance(100,100,Image.SCALE_SMOOTH);

        // userId�� userName�� ���� Board ���� ���ڷ� ���� ���� id�� ���ؼ� �������� ��
        String userId = "MintCC";
        String userName = "������";
        JLabel userInfo = new JLabel("<html>" + userName + "<br>@" + userId + "<html>");
        userInfo.setHorizontalAlignment(JLabel.CENTER);
        userInfo.setVerticalAlignment(JLabel.BOTTOM);
        userInfo.setFont(new Font("���� ���", Font.BOLD, 24));

        int followerNum = 100;
        int followingNum = 101;
        JLabel userFollowing = new JLabel("�ȷ��� " + followingNum);
        userFollowing.setHorizontalAlignment(JLabel.CENTER);
        userFollowing.setVerticalAlignment(JLabel.BOTTOM);
        userFollowing.setFont(new Font("���� ���", Font.BOLD, 24));
        JLabel userFollower = new JLabel("�ȷο� " + followerNum);
        userFollower.setHorizontalAlignment(JLabel.CENTER);
        userFollower.setVerticalAlignment(JLabel.BOTTOM);
        userFollower.setFont(new Font("���� ���", Font.BOLD, 24));

        JLabel margin = new JLabel("");

        userProfile.add(new JLabel(new ImageIcon(logoIcon)));
        userProfile.add(userInfo);
        userProfile.add(userFollowing);
        userProfile.add(userFollower);
        userProfile.add(margin);
        userProfile.setBackground(new Color(0x85BDFF));
        //-------------------------------------------------------- ������ ��

        // �޼������� ��µ� �г�, ��ũ�� ����
        JPanel userBoard = new JPanel();
        userBoard.setBackground(Color.WHITE);
        userBoard.setLayout(new BoxLayout(userBoard, BoxLayout.Y_AXIS));
        JScrollPane userBoardScroll = new JScrollPane(userBoard, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        userBoardScroll.getVerticalScrollBar().setUnitIncrement(20);

        // �� �ּ� �ϴܿ� �޼��� ��ü ���� �� ���� �ڵ尡 ��
        // messageBoard�� �ϳ��� �޼����� �����ϴ� "�г�"
        // �������� ���ڷδ� �޼��� id�� �־ �����ڰ� id�� ������ �г��� ������
        // ������ ���ο��� id�� �޼��� ������ �ҷ��ͼ� �г� �����ؾߵ�
        
        messageBoard hi = new messageBoard(1);
        userBoard.add(hi);
        
        //-------------------------------------------------------- �޼��� ��
        
        // ���̵� �˻��� ��Ʈ�� �����ִ� �ؽ�Ʈ�ʵ�
        // �˻� ����� ����� ��ũ�� ������ �ڽ����̾ƿ� �г�
        
        //-------------------------------------------------------- �˻�â ��
        
        

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

            // ����� �����ʻ��� �����κ��ε� ���� ��絵 ������ �ϳ�..?
            Image logo =new ImageIcon(Objects.requireNonNull(Main.class.getResource("/image/logo_message.png"))).getImage();
            Image logoIcon = logo.getScaledInstance(50,50,Image.SCALE_SMOOTH);

            // �� �� �����ڿ� ����� �̸�, ����� ID�� ����
            JLabel userInfo = new JLabel("<html>������<br>@MintCC<html>");
            userInfo.setHorizontalAlignment(JLabel.CENTER);
            userInfo.setVerticalAlignment(JLabel.BOTTOM);
            userInfo.setFont(new Font("���� ���", Font.BOLD, 24));

            JLabel margin = new JLabel("");

            // ����� �޼��� ������ ������ ��
            JLabel message = new JLabel("�ȳ��ϼ��� �ȳ��ϼ��� �ȳ��ϼ��� �ȳ��ϼ��� ");
            message.setFont(new Font("���� ���", Font.PLAIN, 24));


            user.add(new JLabel(new ImageIcon(logoIcon)));
            user.add(userInfo);

            messagePanel.add(user);
            messagePanel.add(message);
            
            add(messagePanel);
        }
    }
}