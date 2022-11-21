import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

            JLabel inputDesc = new JLabel("��й�ȣ ã��");
            inputDesc.setHorizontalAlignment(JLabel.CENTER);
            inputDesc.setFont(new Font("���� ���", Font.BOLD, 24));

            JLabel inputInfoDesc = new JLabel("�̸��� ���̵� �Է��ϼ���");
            inputInfoDesc.setHorizontalAlignment(JLabel.CENTER);
            inputInfoDesc.setFont(new Font("���� ���", Font.PLAIN, 12));

            JTextField inputName = new JTextField();
            JTextField inputId = new JTextField();

            JButton toChangePw = new JButton("����");
            toChangePw.setBackground(new Color(29,161,242));
            toChangePw.setFont(new Font("���� ���", Font.BOLD, 12));
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
            inputPanel.add(inputName);
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

            JLabel inputDesc = new JLabel("��й�ȣ ����");
            inputDesc.setHorizontalAlignment(JLabel.CENTER);
            inputDesc.setFont(new Font("���� ���", Font.BOLD, 24));

            JLabel inputPwDesc = new JLabel("�� ��й�ȣ�� �Է��ϼ���");
            inputPwDesc.setHorizontalAlignment(JLabel.CENTER);
            inputPwDesc.setFont(new Font("���� ���", Font.PLAIN, 12));

            JTextField inputPw = new JTextField();

            JButton toLogin = new JButton("���� �Ϸ�");
            toLogin.setBackground(new Color(29,161,242));
            toLogin.setFont(new Font("���� ���", Font.BOLD, 12));
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
