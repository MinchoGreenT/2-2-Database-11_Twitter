import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class PwFinder extends JFrame {
    PwFinder() {
        new InputInfo();
    }

    static class InputInfo extends JFrame {
        InputInfo() {
            setSize(400,600);
            setLayout(new GridLayout(2, 1));
            setBackground(Color.WHITE);
            setLocationRelativeTo(null);

            JPanel inputPanel = new JPanel(new GridLayout(5,1, 10, 10));
            inputPanel.setBorder(BorderFactory.createEmptyBorder(50,50,0,50));
            inputPanel.setBackground(Color.WHITE);

            JLabel inputDesc = new JLabel("비밀번호 찾기");
            inputDesc.setHorizontalAlignment(JLabel.CENTER);
            inputDesc.setFont(new Font("맑은 고딕", Font.BOLD, 24));

            JLabel inputInfoDesc = new JLabel("아이디를 입력하세요");
            inputInfoDesc.setHorizontalAlignment(JLabel.CENTER);
            inputInfoDesc.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
            
            JTextField inputId = new JTextField();
            
            Font gainFont = new Font("맑은 고딕", Font.PLAIN, 15);
            Font lostFont = new Font("맑은 고딕", Font.PLAIN, 15);

            inputId.setText("아이디를 입력하세요");
            inputId.setFont(lostFont);
            inputId.setForeground(Color.GRAY);
            inputId.addFocusListener(new FocusListener() {	// 텍스트 필드 포커스 시 이벤트

                @Override
                public void focusLost(FocusEvent e) {	// 포커스를 잃었을 때,
                    if (inputId.getText().equals("")) {
                    	inputId.setText("아이디를 입력하세요");
                    	inputId.setFont(lostFont);
                    	inputId.setForeground(Color.GRAY);
                    }
                }

                @Override
                public void focusGained(FocusEvent e) {	// 포커스를 얻었을 때,
                    if (inputId.getText().equals("아이디를 입력하세요")) {
                    	inputId.setText("");
                    	inputId.setFont(gainFont);
                    	inputId.setForeground(Color.BLACK);
                    }
                }
            });

            JButton toChangePw = new JButton("다음");
            toChangePw.setBackground(new Color(29,161,242));
            toChangePw.setFont(new Font("맑은 고딕", Font.BOLD, 12));
            toChangePw.setForeground(Color.WHITE);
            toChangePw.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    new ChangePw();
                }
            });

            inputPanel.add(inputDesc);
            inputPanel.add(inputInfoDesc);
            inputPanel.add(inputId);
            inputPanel.add(toChangePw);

            JPanel margin = new JPanel();
            margin.setBackground(Color.WHITE);

            add(inputPanel);
            add(margin);
            setVisible(true);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
        }
    }

    static class ChangePw extends JFrame {
        ChangePw() {
            setSize(400,600);
            setLayout(new GridLayout(2, 1));
            setBackground(Color.WHITE);
            setLocationRelativeTo(null);

            JPanel inputPanel = new JPanel(new GridLayout(5,1, 10, 10));
            inputPanel.setBorder(BorderFactory.createEmptyBorder(50,50,0,50));
            inputPanel.setBackground(Color.WHITE);

            JLabel inputDesc = new JLabel("비밀번호 변경");
            inputDesc.setHorizontalAlignment(JLabel.CENTER);
            inputDesc.setFont(new Font("맑은 고딕", Font.BOLD, 24));

            JLabel inputPwDesc = new JLabel("새 비밀번호를 입력하세요");
            inputPwDesc.setHorizontalAlignment(JLabel.CENTER);
            inputPwDesc.setFont(new Font("맑은 고딕", Font.PLAIN, 12));

            JPasswordField inputPw = new JPasswordField();

            JButton toLogin = new JButton("변경 완료");
            toLogin.setBackground(new Color(29,161,242));
            toLogin.setFont(new Font("맑은 고딕", Font.BOLD, 12));
            toLogin.setForeground(Color.WHITE);
            toLogin.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    new Login();
                }
            });

            inputPanel.add(inputDesc);
            inputPanel.add(inputPwDesc);
            inputPanel.add(inputPw);
            inputPanel.add(toLogin);

            JPanel margin = new JPanel();
            margin.setBackground(Color.WHITE);

            add(inputPanel);
            add(margin);
            setVisible(true);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
        }
    }
}
