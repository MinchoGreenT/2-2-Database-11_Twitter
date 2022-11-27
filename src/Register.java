import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register extends JFrame {
    Register() {
        new RegisterName();
    }
    
    static String name;
    static String id;
    static char[] pw;

    // 이름 입력
    static class RegisterName extends JFrame {
        RegisterName() {
            setSize(400,600);
            setLayout(new GridLayout(2, 1));
            setBackground(Color.WHITE);
            setLocationRelativeTo(null);

            JPanel inputPanel = new JPanel(new GridLayout(5,1, 10, 10));
            inputPanel.setBorder(BorderFactory.createEmptyBorder(50,50,0,50));
            inputPanel.setBackground(Color.WHITE);

            JLabel inputDesc = new JLabel("회원가입");
            inputDesc.setHorizontalAlignment(JLabel.CENTER);
            inputDesc.setFont(new Font("맑은 고딕", Font.BOLD, 24));

            JLabel inputNameDesc = new JLabel("이름을 입력하세요");
            inputNameDesc.setHorizontalAlignment(JLabel.CENTER);
            inputNameDesc.setFont(new Font("맑은 고딕", Font.PLAIN, 12));

            JTextField inputName = new JTextField();

            JButton toInputId = new JButton("다음");
            toInputId.setBackground(new Color(29,161,242));
            toInputId.setFont(new Font("맑은 고딕", Font.BOLD, 12));
            toInputId.setForeground(Color.WHITE);
            toInputId.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	name = inputName.getText();
                    dispose();
                    new RegisterId();
                }
            });

            inputPanel.add(inputDesc);
            inputPanel.add(inputNameDesc);
            inputPanel.add(inputName);
            inputPanel.add(toInputId);

            JPanel margin = new JPanel();
            margin.setBackground(Color.WHITE);

            add(inputPanel);
            add(margin);
            setVisible(true);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
        }
    }

    // ID 입력
    static class RegisterId extends JFrame {
        RegisterId() {
            setSize(400,600);
            setLayout(new GridLayout(2, 1));
            setBackground(Color.WHITE);
            setLocationRelativeTo(null);

            JPanel inputPanel = new JPanel(new GridLayout(5,1, 10, 10));
            inputPanel.setBorder(BorderFactory.createEmptyBorder(50,50,0,50));
            inputPanel.setBackground(Color.WHITE);

            JLabel inputDesc = new JLabel("회원가입");
            inputDesc.setHorizontalAlignment(JLabel.CENTER);
            inputDesc.setFont(new Font("맑은 고딕", Font.BOLD, 24));

            JLabel inputIdDesc = new JLabel("아이디를 입력하세요");
            inputIdDesc.setHorizontalAlignment(JLabel.CENTER);
            inputIdDesc.setFont(new Font("맑은 고딕", Font.PLAIN, 12));

            JTextField inputId = new JTextField();

            JButton toInputPw = new JButton("다음");
            toInputPw.setBackground(new Color(29,161,242));
            toInputPw.setFont(new Font("맑은 고딕", Font.BOLD, 12));
            toInputPw.setForeground(Color.WHITE);
            toInputPw.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	id = inputId.getText();
                    dispose();
                    new RegisterPw();
                }
            });

            inputPanel.add(inputDesc);
            inputPanel.add(inputIdDesc);
            inputPanel.add(inputId);
            inputPanel.add(toInputPw);

            JPanel margin = new JPanel();
            margin.setBackground(Color.WHITE);

            add(inputPanel);
            add(margin);
            setVisible(true);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
        }
    }

    // 비밀번호 입력
    static class RegisterPw extends JFrame {
        RegisterPw() {
            setSize(400,600);
            setLayout(new GridLayout(2, 1));
            setBackground(Color.WHITE);
            setLocationRelativeTo(null);

            JPanel inputPanel = new JPanel(new GridLayout(5,1, 10, 10));
            inputPanel.setBorder(BorderFactory.createEmptyBorder(50,50,0,50));
            inputPanel.setBackground(Color.WHITE);

            JLabel inputDesc = new JLabel("회원가입");
            inputDesc.setHorizontalAlignment(JLabel.CENTER);
            inputDesc.setFont(new Font("맑은 고딕", Font.BOLD, 24));

            JLabel inputPwDesc = new JLabel("비밀번호를 입력하세요");
            inputPwDesc.setHorizontalAlignment(JLabel.CENTER);
            inputPwDesc.setFont(new Font("맑은 고딕", Font.PLAIN, 12));

            JPasswordField inputPw = new JPasswordField();

            JButton registerFinish = new JButton("회원가입");
            registerFinish.setBackground(new Color(29,161,242));
            registerFinish.setFont(new Font("맑은 고딕", Font.BOLD, 12));
            registerFinish.setForeground(Color.WHITE);
            
            // ---------------------- 회원가입 ----------------------------
            registerFinish.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	pw = inputPw.getPassword();
                    dispose();
                    new Login();
                    // name, id, pw에 각각 유저가 입력한 이름, 아이디, 비밀번호가 저장됨
                    // 저장된 변수들을 통해 유저 테이블에 신규 유저 등록 
                }
            });
            // ---------------------- 회원가입 ----------------------------
            
            inputPanel.add(inputDesc);
            inputPanel.add(inputPwDesc);
            inputPanel.add(inputPw);
            inputPanel.add(registerFinish);

            JPanel margin = new JPanel();
            margin.setBackground(Color.WHITE);

            add(inputPanel);
            add(margin);
            setVisible(true);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
        }
    }
}
