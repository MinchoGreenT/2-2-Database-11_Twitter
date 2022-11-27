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

    // �̸� �Է�
    static class RegisterName extends JFrame {
        RegisterName() {
            setSize(400,600);
            setLayout(new GridLayout(2, 1));
            setBackground(Color.WHITE);
            setLocationRelativeTo(null);

            JPanel inputPanel = new JPanel(new GridLayout(5,1, 10, 10));
            inputPanel.setBorder(BorderFactory.createEmptyBorder(50,50,0,50));
            inputPanel.setBackground(Color.WHITE);

            JLabel inputDesc = new JLabel("ȸ������");
            inputDesc.setHorizontalAlignment(JLabel.CENTER);
            inputDesc.setFont(new Font("���� ���", Font.BOLD, 24));

            JLabel inputNameDesc = new JLabel("�̸��� �Է��ϼ���");
            inputNameDesc.setHorizontalAlignment(JLabel.CENTER);
            inputNameDesc.setFont(new Font("���� ���", Font.PLAIN, 12));

            JTextField inputName = new JTextField();

            JButton toInputId = new JButton("����");
            toInputId.setBackground(new Color(29,161,242));
            toInputId.setFont(new Font("���� ���", Font.BOLD, 12));
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

    // ID �Է�
    static class RegisterId extends JFrame {
        RegisterId() {
            setSize(400,600);
            setLayout(new GridLayout(2, 1));
            setBackground(Color.WHITE);
            setLocationRelativeTo(null);

            JPanel inputPanel = new JPanel(new GridLayout(5,1, 10, 10));
            inputPanel.setBorder(BorderFactory.createEmptyBorder(50,50,0,50));
            inputPanel.setBackground(Color.WHITE);

            JLabel inputDesc = new JLabel("ȸ������");
            inputDesc.setHorizontalAlignment(JLabel.CENTER);
            inputDesc.setFont(new Font("���� ���", Font.BOLD, 24));

            JLabel inputIdDesc = new JLabel("���̵� �Է��ϼ���");
            inputIdDesc.setHorizontalAlignment(JLabel.CENTER);
            inputIdDesc.setFont(new Font("���� ���", Font.PLAIN, 12));

            JTextField inputId = new JTextField();

            JButton toInputPw = new JButton("����");
            toInputPw.setBackground(new Color(29,161,242));
            toInputPw.setFont(new Font("���� ���", Font.BOLD, 12));
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

    // ��й�ȣ �Է�
    static class RegisterPw extends JFrame {
        RegisterPw() {
            setSize(400,600);
            setLayout(new GridLayout(2, 1));
            setBackground(Color.WHITE);
            setLocationRelativeTo(null);

            JPanel inputPanel = new JPanel(new GridLayout(5,1, 10, 10));
            inputPanel.setBorder(BorderFactory.createEmptyBorder(50,50,0,50));
            inputPanel.setBackground(Color.WHITE);

            JLabel inputDesc = new JLabel("ȸ������");
            inputDesc.setHorizontalAlignment(JLabel.CENTER);
            inputDesc.setFont(new Font("���� ���", Font.BOLD, 24));

            JLabel inputPwDesc = new JLabel("��й�ȣ�� �Է��ϼ���");
            inputPwDesc.setHorizontalAlignment(JLabel.CENTER);
            inputPwDesc.setFont(new Font("���� ���", Font.PLAIN, 12));

            JPasswordField inputPw = new JPasswordField();

            JButton registerFinish = new JButton("ȸ������");
            registerFinish.setBackground(new Color(29,161,242));
            registerFinish.setFont(new Font("���� ���", Font.BOLD, 12));
            registerFinish.setForeground(Color.WHITE);
            
            // ---------------------- ȸ������ ----------------------------
            registerFinish.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	pw = inputPw.getPassword();
                    dispose();
                    new Login();
                    // name, id, pw�� ���� ������ �Է��� �̸�, ���̵�, ��й�ȣ�� �����
                    // ����� �������� ���� ���� ���̺� �ű� ���� ��� 
                }
            });
            // ---------------------- ȸ������ ----------------------------
            
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
