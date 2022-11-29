import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;

public class NewMessage extends JFrame {
	public static final int LINES = 10;
	public static final int CHAR_PER_LINE = 20;
	
	NewMessage(JFrame board, String writer, String receiver){
		setSize(400,150);
        setLocationRelativeTo(null);
        
		JPanel inputForm = new JPanel(new GridLayout(2,1,0,10));
		inputForm.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        inputForm.setBackground(Color.WHITE);
		
		JTextArea inputMessage = new JTextArea(LINES, CHAR_PER_LINE);
		JButton inputButton = new JButton("글쓰기");
		inputButton.setBackground(new Color(29,161,242));
		inputButton.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		inputButton.setForeground(Color.WHITE);
		inputButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Db_connection so = new Db_connection();
				LocalDateTime now = LocalDateTime.now();
				
				// 년, 월(문자열, 숫자), 일(월 기준, 년 기준), 요일(문자열, 숫자), 시, 분, 초 구하기     
				int year = now.getYear();  // 연도
		        int monthValue = now.getMonthValue();  // 월(숫자)
		        int dayOfMonth = now.getDayOfMonth();  // 일(월 기준)
		        int hour = now.getHour();
		        int minute = now.getMinute();
		        int second = now.getSecond();

		        String content = inputMessage.getText(); 
		        so.setWord(writer, receiver, content, year, monthValue, dayOfMonth, hour, minute, second);
				
				board.dispose();
				dispose();
				new Board(receiver);
			}
			
		});
		
		inputForm.add(inputMessage);
		inputForm.add(inputButton);
		
		add(inputForm);
		
		setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}
