import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Objects;

public class Login extends JFrame {
	
	static String myId;
	
    Login(){
        setSize(400, 600);
        setLayout(new GridLayout(3,1));
        setLocationRelativeTo(null);

        JPanel logoPanel = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridLayout(4,1, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(50,80,0,80));
        JPanel optionPanel = new JPanel(new GridLayout(5,1,10,10));
        optionPanel.setBorder(BorderFactory.createEmptyBorder(0,120,0,120));

        logoPanel.setBackground(Color.WHITE);
        inputPanel.setBackground(Color.WHITE);
        optionPanel.setBackground(Color.WHITE);

        // 상단 로고 이미지 추가
        Image logo = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/image/logo.png"))).getImage();
        Image logoIcon = logo.getScaledInstance(150,150,Image.SCALE_SMOOTH);
        logoPanel.add(new JLabel(new ImageIcon(logoIcon)), BorderLayout.SOUTH);

        JLabel inputDesc = new JLabel();
        JTextField idField = new JTextField(20);
        JPasswordField pwField = new JPasswordField(20);

        // 텍스트필드 힌트
        Font gainFont = new Font("맑은 고딕", Font.PLAIN, 15);
        Font lostFont = new Font("맑은 고딕", Font.PLAIN, 15);

        idField.setText("아이디를 입력하세요");
        idField.setFont(lostFont);
        idField.setForeground(Color.GRAY);
        idField.addFocusListener(new FocusListener() {	// 텍스트 필드 포커스 시 이벤트

            @Override
            public void focusLost(FocusEvent e) {	// 포커스를 잃었을 때,
                if (idField.getText().equals("")) {
                    idField.setText("아이디를 입력하세요");
                    idField.setFont(lostFont);
                    idField.setForeground(Color.GRAY);
                }
            }

            @Override
            public void focusGained(FocusEvent e) {	// 포커스를 얻었을 때,
                if (idField.getText().equals("아이디를 입력하세요")) {
                    idField.setText("");
                    idField.setFont(gainFont);
                    idField.setForeground(Color.BLACK);
                }
            }
        });

        // 안내 텍스트 설정
        inputDesc.setText("아이디와 비밀번호를 입력하세요");
        inputDesc.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
        inputDesc.setHorizontalAlignment(JLabel.CENTER);

        inputPanel.add(inputDesc);
        inputPanel.add(idField);
        inputPanel.add(pwField);

        // ---------------------- 로그인 ----------------------------
        JButton loginButton = new JButton("로그인");
        loginButton.setBackground(new Color(29,161,242));
        loginButton.setFont(new Font("맑은 고딕", Font.BOLD, 12));
        loginButton.setForeground(Color.WHITE);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String pw = "";
                for(char pwd : pwField.getPassword()) {
                	pw += pwd;
                }
                
                
                if(Db_connection.Login(id, pw) == 0) {
                	myId = id;
                	new Board(myId);              	
                	dispose();
                }
                else {
                	inputDesc.setText("아이디와 비밀번호가 맞지 않습니다!");
                }
                // idField.getText() : String
                // pwField.getPassword() : char[]
                
                // 가져온 정보로 회원정보 확인 후 로그인 성공 시 보드로 넘어가면 됩니다
                // 필요한 코드는 조건문으로 회원정보를 확인하는 코드
                // 성공 시 하단 코드를 실행
                
                // 이하는 로그인 성공 시 접속한 아이디를 저장하고 해당 아이디의 보드를 띄우는 코드
                
            }
        });
        // ---------------------- 로그인 ----------------------------

        JButton registerButton = new JButton("회원가입");
        registerButton.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
        registerButton.setBackground(Color.white);
        registerButton.setBorderPainted(false);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Register();
            }
        });

        JLabel margin = new JLabel("");

        JButton pwFinderButton = new JButton("비밀번호 찾기");
        pwFinderButton.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
        pwFinderButton.setBackground(Color.white);
        pwFinderButton.setBorderPainted(false);
        pwFinderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new PwFinder();
            }
        });

        optionPanel.add(loginButton);
        optionPanel.add(registerButton);
        optionPanel.add(margin);
        optionPanel.add(margin);
        optionPanel.add(pwFinderButton);

        add(logoPanel);
        add(inputPanel);
        add(optionPanel);

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}