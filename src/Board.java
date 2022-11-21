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

        // userId와 userName는 추후 Board 생성 인자로 받을 유저 id를 통해서 가져오면 됨
        String userId = "MintCC";
        String userName = "이은섭";
        JLabel userInfo = new JLabel("<html>" + userName + "<br>@" + userId + "<html>");
        userInfo.setHorizontalAlignment(JLabel.CENTER);
        userInfo.setVerticalAlignment(JLabel.BOTTOM);
        userInfo.setFont(new Font("맑은 고딕", Font.BOLD, 24));

        int followerNum = 100;
        int followingNum = 101;
        JLabel userFollowing = new JLabel("팔로잉 " + followingNum);
        userFollowing.setHorizontalAlignment(JLabel.CENTER);
        userFollowing.setVerticalAlignment(JLabel.BOTTOM);
        userFollowing.setFont(new Font("맑은 고딕", Font.BOLD, 24));
        JLabel userFollower = new JLabel("팔로워 " + followerNum);
        userFollower.setHorizontalAlignment(JLabel.CENTER);
        userFollower.setVerticalAlignment(JLabel.BOTTOM);
        userFollower.setFont(new Font("맑은 고딕", Font.BOLD, 24));

        JLabel margin = new JLabel("");

        userProfile.add(new JLabel(new ImageIcon(logoIcon)));
        userProfile.add(userInfo);
        userProfile.add(userFollowing);
        userProfile.add(userFollower);
        userProfile.add(margin);
        userProfile.setBackground(new Color(0x85BDFF));
        //-------------------------------------------------------- 프로필 끝

        // 메세지들이 출력될 패널, 스크롤 가능
        JPanel userBoard = new JPanel();
        userBoard.setBackground(Color.WHITE);
        userBoard.setLayout(new BoxLayout(userBoard, BoxLayout.Y_AXIS));
        JScrollPane userBoardScroll = new JScrollPane(userBoard, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        userBoardScroll.getVerticalScrollBar().setUnitIncrement(20);

        // 본 주석 하단에 메세지 객체 생성 및 삽입 코드가 들어감
        // messageBoard는 하나의 메세지를 구성하는 "패널"
        // 생성자의 인자로는 메세지 id를 넣어서 생성자가 id를 가지고 패널을 구성함
        // 생성자 내부에서 id로 메세지 정보를 불러와서 패널 구성해야됨
        
        messageBoard hi = new messageBoard(1);
        userBoard.add(hi);
        
        //-------------------------------------------------------- 메세지 끝
        
        // 아이디 검색라 힌트가 써져있는 텍스트필드
        // 검색 결과를 출력할 스크롤 가능한 박스레이아웃 패널
        
        //-------------------------------------------------------- 검색창 끝
        
        

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

            // 여기는 프로필사진 생성부분인데 프사 배사도 구현을 하나..?
            Image logo =new ImageIcon(Objects.requireNonNull(Main.class.getResource("/image/logo_message.png"))).getImage();
            Image logoIcon = logo.getScaledInstance(50,50,Image.SCALE_SMOOTH);

            // 본 라벨 생성자에 사용자 이름, 사용자 ID를 넣음
            JLabel userInfo = new JLabel("<html>이은섭<br>@MintCC<html>");
            userInfo.setHorizontalAlignment(JLabel.CENTER);
            userInfo.setVerticalAlignment(JLabel.BOTTOM);
            userInfo.setFont(new Font("맑은 고딕", Font.BOLD, 24));

            JLabel margin = new JLabel("");

            // 여기는 메세지 내용을 넣으면 됨
            JLabel message = new JLabel("안녕하세요 안녕하세요 안녕하세요 안녕하세요 ");
            message.setFont(new Font("맑은 고딕", Font.PLAIN, 24));


            user.add(new JLabel(new ImageIcon(logoIcon)));
            user.add(userInfo);

            messagePanel.add(user);
            messagePanel.add(message);
            
            add(messagePanel);
        }
    }
}