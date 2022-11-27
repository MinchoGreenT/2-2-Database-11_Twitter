import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class NewMessage extends JFrame {
	NewMessage(JFrame board, String userId){
		setSize(400,150);
        setLocationRelativeTo(null);
        
		JPanel inputForm = new JPanel(new GridLayout(2,1,0,10));
		inputForm.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        inputForm.setBackground(Color.WHITE);
		
		JTextField inputMessage = new JTextField(20);
		JButton inputButton = new JButton("±Û¾²±â");
		inputButton.setBackground(new Color(29,161,242));
		inputButton.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		inputButton.setForeground(Color.WHITE);
		inputButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				board.dispose();
				dispose();
				new Board(userId);
			}
			
		});
		
		inputForm.add(inputMessage);
		inputForm.add(inputButton);
		
		add(inputForm);
		
		setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}
