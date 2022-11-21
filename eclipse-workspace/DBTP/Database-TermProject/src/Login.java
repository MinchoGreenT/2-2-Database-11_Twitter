import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Objects;

public class Login extends JFrame {
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

        // ��� �ΰ� �̹��� �߰�
        Image logo =new ImageIcon(Objects.requireNonNull(Main.class.getResource("/image/logo.png"))).getImage();
        Image logoIcon = logo.getScaledInstance(150,150,Image.SCALE_SMOOTH);
        logoPanel.add(new JLabel(new ImageIcon(logoIcon)), BorderLayout.SOUTH);

        JLabel inputDesc = new JLabel();
        JTextField idField = new JTextField(20);
        JPasswordField pwField = new JPasswordField(20);

        // �ؽ�Ʈ�ʵ� ��Ʈ
        Font gainFont = new Font("���� ���", Font.PLAIN, 15);
        Font lostFont = new Font("���� ���", Font.PLAIN, 15);

        idField.setText("���̵� �Է��ϼ���");
        idField.setFont(lostFont);
        idField.setForeground(Color.GRAY);
        idField.addFocusListener(new FocusListener() {	// �ؽ�Ʈ �ʵ� ��Ŀ�� �� �̺�Ʈ

            @Override
            public void focusLost(FocusEvent e) {	// ��Ŀ���� �Ҿ��� ��,
                if (idField.getText().equals("")) {
                    idField.setText("���̵� �Է��ϼ���");
                    idField.setFont(lostFont);
                    idField.setForeground(Color.GRAY);
                }
            }

            @Override
            public void focusGained(FocusEvent e) {	// ��Ŀ���� ����� ��,
                if (idField.getText().equals("���̵� �Է��ϼ���")) {
                    idField.setText("");
                    idField.setFont(gainFont);
                    idField.setForeground(Color.BLACK);
                }
            }
        });

        // �ȳ� �ؽ�Ʈ ����
        inputDesc.setText("���̵�� ��й�ȣ�� �Է��ϼ���");
        inputDesc.setFont(new Font("���� ���", Font.PLAIN, 12));
        inputDesc.setHorizontalAlignment(JLabel.CENTER);

        inputPanel.add(inputDesc);
        inputPanel.add(idField);
        inputPanel.add(pwField);

        JButton loginButton = new JButton("�α���");
        loginButton.setFont(new Font("���� ���", Font.PLAIN, 12));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Board();
            }
        });

        JButton registerButton = new JButton("ȸ������");
        registerButton.setFont(new Font("���� ���", Font.PLAIN, 12));
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

        JButton pwFinderButton = new JButton("��й�ȣ ã��");
        pwFinderButton.setFont(new Font("���� ���", Font.PLAIN, 12));
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