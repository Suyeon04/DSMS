package java_test;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ChoiceFormat;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
 
class login extends Frame {
	private Frame mainFrame;
	private JPanel subpanel;
	private JLabel headerLabel;
	private Button b1;
	
	private String id;
	private String pw;
	private String shop;
	
	// 색깔
	Color color1 = new Color(15,76,130);
	
	public login() {
	        prepareGUI();
	}
	
	private void prepareGUI() {
		// Font
		Font font1 = new Font("esamanru Bold", Font.PLAIN, 50);
		Font font2 = new Font("esamanru Light", Font.PLAIN, 16);
		// Frame 에 대한 셋팅
		mainFrame = new Frame("DSMS");
		mainFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
		mainFrame.setResizable(false);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setLayout(new BorderLayout());
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		
		// 상단 게임 제목
		headerLabel = new JLabel();
		headerLabel.setHorizontalAlignment(JLabel.CENTER);
		headerLabel.setText("로그인");
		headerLabel.setFont(font1);
		headerLabel.setForeground(color1);

		Panel p = new Panel();
		p.setLayout(new FlowLayout(FlowLayout.CENTER, 1000, 10));

		Panel form = new Panel(new GridLayout(3,2,0,5));
		
		JLabel label1 = new JLabel("아이디");
		label1.setFont(font2);
		TextField tf1 = new TextField("", 15);
		tf1.selectAll();
		form.add(label1);
		form.add(tf1);

		JLabel label2 = new JLabel("비밀번호");
		label2.setFont(font2);
		TextField tf2 = new TextField("", 15);
		tf2.setEchoChar('*');
		form.add(label2);
		form.add(tf2);

		// 투명 레이아웃
		  JLabel blank = new JLabel();      
		  JLabel blank2 = new JLabel();   
		  JLabel blank3 = new JLabel();
		  
		  form.add(blank2);
		  form.add(blank3);
		
		  JPanel btns = new JPanel(new FlowLayout());
		  
		  JButton b1 = new JButton("확인");
		  JButton b2 = new JButton("취소");
		  
		  b1.setBackground(Color.white);
		  b2.setBackground(Color.white);
		  b1.setFont(font2);
		  b2.setFont(font2);
		
		// 클릭 시 이벤트
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 데이터 저장 변수 선언
				String id = tf1.getText();
				String pw = tf2.getText();
				
				mysql_1 m = new mysql_1();
				String s = m.checkID(id,pw);
				String id1 = m.bringName(s);
				
				if(id.length() == 0) {
					JOptionPane.showMessageDialog(null
					, "아이디를 입력해주세요."
					, "DSMS"
					, JOptionPane.ERROR_MESSAGE
					);
				} else if(pw.length() == 0) {
					JOptionPane.showMessageDialog(null
					, "비밀번호를 입력해주세요."
					, "DSMS"
					, JOptionPane.ERROR_MESSAGE
					);
				} else if(s==null|| s==null) {
					JOptionPane.showMessageDialog(null
					, "아이디 또는 비밀번호 입력 오류. 다시 한번 확인 해주세요."
					, "DSMS"
					, JOptionPane.ERROR_MESSAGE
					);
				} else {
					JOptionPane.showMessageDialog(null
					, id1+"님 환영합니다!"
					, "DSMS"
					, JOptionPane.PLAIN_MESSAGE
					);
				new manager(s);
					     mainFrame.setVisible(false);
				}
				
			}
		});
		b2.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            new Main();
	              mainFrame.setVisible(false);
	         }
	      });
	      
      btns.add(b1);
      btns.add(b2);
      btns.setBackground(Color.orange);

      p.add(form);
      p.add(btns);
      p.setBackground(Color.orange);
      
      JPanel test = new JPanel(new GridLayout(3,1,0,0));
      test.setBackground(Color.orange);
      test.add(blank);
      test.add(headerLabel);
      test.add(p);
      
      
      subpanel = new JPanel();
      subpanel.setBackground(Color.orange);
      subpanel.setLayout(new GridLayout(2, 1));
      subpanel.add(test, BorderLayout.NORTH);
      
      mainFrame.add(subpanel);
      mainFrame.setVisible(true);
	}
}