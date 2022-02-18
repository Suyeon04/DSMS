package java_test;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ChoiceFormat;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
 
class join extends Frame {
	private Frame mainFrame;
	private JPanel subpanel;
	private JLabel headerLabel;
	private Panel form;
	private Button b1;
	
	// ����
	Color color1 = new Color(15,76,130);
	mysql_1 m = new mysql_1();
	 
	private boolean idcheck = false;
	
	public join() {
	        prepareGUI();
	}
	
	private void prepareGUI() {
		// Font
		Font font1 = new Font("esamanru Bold", Font.PLAIN, 50);
		Font font2  = new Font("esamanru Light",Font.PLAIN, 16); 
		
		// Frame �� ���� ����
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
		
		// ��� ���� ����
		headerLabel = new JLabel();
		headerLabel.setHorizontalAlignment(JLabel.CENTER);
		headerLabel.setText("ȸ������");
		headerLabel.setFont(font1);
		headerLabel.setForeground(color1);
		

		Panel p = new Panel();
		p.setLayout(new FlowLayout(FlowLayout.CENTER, 1000, 10));

		form = new Panel(new GridLayout(7,4,0,5));
		form.setPreferredSize(new Dimension(800,300));
		
		JLabel blankL1 = new JLabel();
		JLabel blankR1 = new JLabel();
		JLabel label1 = new JLabel("�̸�");
		label1.setFont(font2);
		TextField tf1 = new TextField("", 15);
		tf1.selectAll();
		form.add(blankL1);
		form.add(label1);
		form.add(tf1);
		form.add(blankR1);
		
		JLabel blankL2 = new JLabel();
		Panel btnlabel = new Panel();
		btnlabel.setLayout(new BorderLayout());
		JButton b1 = new JButton("�ߺ�Ȯ��");
		b1.setFont(font2);
		b1.setBackground(Color.white);
		btnlabel.add(b1, BorderLayout.WEST);
		JLabel label2 = new JLabel("���̵�");
		label2.setFont(font2);
		TextField tf2 = new TextField("", 15);
		tf2.selectAll();
		form.add(blankL2);
		form.add(label2);
		form.add(tf2);
		form.add(btnlabel);
		mysql_1 m = new mysql_1();
		JLabel blankL3 = new JLabel();
		JLabel blankR3 = new JLabel();
		JLabel label3 = new JLabel("��й�ȣ");
		label3.setFont(font2);
		TextField tf3 = new TextField("", 15);
		tf3.selectAll(); // tf2.setEchoChar('*');
		form.add(blankL3);
		form.add(label3);
		form.add(tf3);
		form.add(blankR3);


		JLabel blankL4 = new JLabel();
		JLabel blankR4 = new JLabel();
		JLabel label4 = new JLabel("������");
		label4.setFont(font2);
		TextField tf4 = new TextField("", 15);
		tf4.selectAll(); 
		form.add(blankL4);
		form.add(label4);
		form.add(tf4);
		form.add(blankR4);
		

		JLabel blankL5 = new JLabel();
		JLabel blankR5 = new JLabel();
		JLabel label5 = new JLabel("���������޾�(%)");
		label5.setFont(font2);
		TextField tf5 = new TextField("", 15);
		tf5.selectAll(); 
		form.add(blankL5);
		form.add(label5);
		form.add(tf5);
		form.add(blankR5);

		JLabel blankL6 = new JLabel();
		JLabel blankR6 = new JLabel();
		Panel check = new Panel();
		JLabel label6 = new JLabel("����");
		label6.setFont(font2);
		CheckboxGroup g = new CheckboxGroup();
		JRadioButton ra1 = new JRadioButton("��", true);
		ra1.setFont(font2);
		ra1.setBackground(Color.white);
		JRadioButton ra2 = new JRadioButton("��", false);
		ra2.setFont(font2);
		ra2.setBackground(Color.white);
		ButtonGroup group = new ButtonGroup();
		
		group.add(ra1);
		group.add(ra2);

		check.add(ra1);
		check.add(ra2);
		form.add(blankL6);
		form.add(label6);
		form.add(check);
		form.add(blankR6);
		
		JButton b2 = new JButton("Ȯ��");
		b2.setFont(font2);
		b2.setBackground(Color.white);
		JButton b3 = new JButton("���");
		b3.setFont(font2);
		b3.setBackground(Color.white);

		JLabel blankL7 = new JLabel();
		JLabel blankR7 = new JLabel();
		JLabel label7 = new JLabel("���� ����");
		label7.setFont(font2);
		TextField tf7 = new TextField("", 15);
		tf7.selectAll(); 
		form.add(blankL7);
		form.add(label7);
		form.add(tf7);
		form.add(blankR7);

		// isString�߰�
	    isString is = new isString();
		
		// ���̵� �ߺ� üũ �̺�Ʈ
		class MouseListener extends MouseAdapter{     
		    public void mouseClicked(MouseEvent arg0) {
		    	String id = tf2.getText();
		    	boolean s = m.select(id);
		    	if(tf2.getText().length() == 0) {
					JOptionPane.showMessageDialog(null
							, "���̵� �Է����ּ���."
							, "DSMS"
							, JOptionPane.ERROR_MESSAGE
					);
	                 idcheck = false;
				} else if(m.select(tf2.getText())==false) {
					JOptionPane.showMessageDialog(null
		                       , "���Ұ����� ���̵� �Դϴ�."
		                       , "DSMS"
		                       , JOptionPane.ERROR_MESSAGE
		                 );
		                 idcheck = false;
	            } else if(!is.isString1(tf2.getText())) {            // ���� ó�� ���̵�
		                 JOptionPane.showMessageDialog(null
		                       , "���Ұ����� ���̵� �Դϴ�."
		                       , "DSMS"
		                       , JOptionPane.ERROR_MESSAGE
		                 );
		                 idcheck = false;
		        } else {
					JOptionPane.showMessageDialog(null
							, "��밡���� ���̵� �Դϴ�."
							, "DSMS"
							, JOptionPane.PLAIN_MESSAGE
					);
					idcheck = true;
				}
		    }
		}
        MouseListener listener = new MouseListener();    // �̺�Ʈ��ü 
        b1.addMouseListener(listener);  
        
		// ȸ������ Ŭ�� �̺�Ʈ
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ������ ���� ���� ����
				String name = tf1.getText();
				String id = tf2.getText();
				String pw = tf3.getText();
				String brand = tf4.getText();
				float money;
				int employee = (ra1.isSelected() == true) ? 1 : 0;
				
				// isNum �޼��� ȣ��
				isNum in = new isNum();
			    isString is = new isString();
				
				// ȸ������ ���� ó��
				if(name.length() == 0) {					// ���� ó�� �̸�
					JOptionPane.showMessageDialog(null
							, "�̸��� �Է����ּ���."
							, "DSMS"
							, JOptionPane.ERROR_MESSAGE
					);
				} else if(name.length() > 12) {
					JOptionPane.showMessageDialog(null
							, "�̸��� �ʹ� ��ϴ�. 12�� �̳��� �Է��� �ּ���."
							, "DSMS"
							, JOptionPane.ERROR_MESSAGE
					);
				} else if(!is.isString2(name)) {           
		               JOptionPane.showMessageDialog(null
		                       , "Ư������ �Ǵ� ������ �����ϰ� �ֽ��ϴ�. �ش� ���ڸ� �����ϰ� �ٽ� �Է��� �ּ���."
		                       , "DSMS"
		                       , JOptionPane.ERROR_MESSAGE
		                 );
	            } else if(id.length() == 0) {				// ���� ó�� ���̵�
					JOptionPane.showMessageDialog(null
							, "���̵� �Է����ּ���."
							, "DSMS"
							, JOptionPane.ERROR_MESSAGE
					);
	                 idcheck = false;
				} else if(id.length() < 5) {
					JOptionPane.showMessageDialog(null
							, "���̵� �ʹ� ª���ϴ�. 5~12�� �̳��� �Է��� �ּ���."
							, "DSMS"
							, JOptionPane.ERROR_MESSAGE
					);
	                 idcheck = false;
				} else if(id.length() > 12) {
					JOptionPane.showMessageDialog(null
							, "���̵� �ʹ� ��ϴ�. 5~12�� �̳��� �Է��� �ּ���."
							, "DSMS"
							, JOptionPane.ERROR_MESSAGE
					);
	                 idcheck = false;
				} else if(!is.isString1(id)) {           
		               JOptionPane.showMessageDialog(null
		                       , "���̵�� �����ڸ� ����Ͽ� �Է��� �ּ���."
		                       , "DSMS"
		                       , JOptionPane.ERROR_MESSAGE
		                 );
		                 idcheck = false;
	            } else if(!idcheck) {
					JOptionPane.showMessageDialog(null
							, "���̵� �ߺ� üũ�� ���ּ���."
							, "DSMS"
							, JOptionPane.ERROR_MESSAGE
					);
	                idcheck = false;
				} else if(pw.length() == 0) {				// ���� ó�� ��й�ȣ
					JOptionPane.showMessageDialog(null
							, "��й�ȣ�� �Է����ּ���."
							, "DSMS"
							, JOptionPane.ERROR_MESSAGE
					);
				} else if(pw.length() < 8) {
					JOptionPane.showMessageDialog(null
							, "��й�ȣ�� �ʹ� ª���ϴ�. 8~16�� �̳��� �Է��� �ּ���."
							, "DSMS"
							, JOptionPane.ERROR_MESSAGE
					);
				} else if(pw.length() > 16) {
					JOptionPane.showMessageDialog(null
							, "��й�ȣ�� �ʹ� ��ϴ�. 8~16�� �̳��� �Է��� �ּ���."
							, "DSMS"
							, JOptionPane.ERROR_MESSAGE
					);
				} else if(!is.isString1(pw)) {         
		               JOptionPane.showMessageDialog(null
		                       , "��й�ȣ�� �����ڸ� ����Ͽ� �Է��� �ּ���."
		                       , "DSMS"
		                       , JOptionPane.ERROR_MESSAGE
		                 );
	            } else if(brand.length() == 0) {			// ���� ó�� �귻��
					JOptionPane.showMessageDialog(null
							, "�귻�带 �Է����ּ���."
							, "DSMS"
							, JOptionPane.ERROR_MESSAGE
					);
				} else if(brand.length() > 16) {
					JOptionPane.showMessageDialog(null
							, "�������� �ʹ� ��ϴ�. 16�� �̳��� �Է��� �ּ���."
							, "DSMS"
							, JOptionPane.ERROR_MESSAGE
					);
				} else if((tf5.getText()).length() == 0) {	// ���� ó�� ���������޾�
					JOptionPane.showMessageDialog(null
							, "���������޾��� �Է����ּ���."
							, "Message"
							, JOptionPane.ERROR_MESSAGE
					);
				} else if(!in.isNum(tf5.getText())) {
					JOptionPane.showMessageDialog(null
							, "���������޾��� ���ڸ� �Է����ּ���."
							, "DSMS"
							, JOptionPane.ERROR_MESSAGE
					);
				} else if(employee == 1 && (tf7.getText()).length() == 0) {		// ���� ó�� ���� ����
						JOptionPane.showMessageDialog(null
								, "���� ������ �Է����ּ���."
								, "DSMS"
								, JOptionPane.ERROR_MESSAGE
						);
				} else if(employee == 1 && !in.isNum(tf7.getText())) {
					JOptionPane.showMessageDialog(null
							, "���� ������ ���ڸ� �Է����ּ���."
							, "DSMS"
							, JOptionPane.ERROR_MESSAGE
					);
				} else {
					money = Float.parseFloat(tf5.getText());
					if(employee == 1)employee = Integer.parseInt(tf7.getText());
					
	                m.tableInsert(name, id,pw, brand, money, employee);
					System.out.println("ȸ������ ����!!");
					System.out.println("�̸� : " + name);
					System.out.println("���̵� : " + id);
					System.out.println("��й�ȣ : " + pw);
					System.out.println("������ : " + brand);
					if(employee != 0) {
						System.out.println("���� ���� : " + employee);
						System.out.println("���������޾�(%) : " + money);
					}
					
					JOptionPane.showMessageDialog(null
							, "ȸ�������� �����մϴ�!"
							, "DSMS"
							, JOptionPane.PLAIN_MESSAGE
					);
					new Main();
			        mainFrame.setVisible(false);
				}
			}
		});
		b3.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            new Main();
	              mainFrame.setVisible(false);
	         }
		});
		ra1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				label7.setVisible(true);
				tf7.setVisible(true);
			}
		});
		
		ra2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				label7.setVisible(false);
				tf7.setVisible(false);
			}
		});
		
		JPanel btns = new JPanel(new FlowLayout());
	      btns.add(b2);
	      btns.add(b3);
	      btns.setBackground(Color.orange);
	      
	      p.add(form);
	      p.add(btns);
	      
	      subpanel = new JPanel();
	      subpanel.setBackground(Color.orange);
	      subpanel.setLayout(new BorderLayout(0, 100));
	      subpanel.setBorder(BorderFactory.createEmptyBorder(100 , 0, 0 , 0));// �� �� �� ��
	      subpanel.add(headerLabel, BorderLayout.NORTH);
	      subpanel.add(p);
	      
	      mainFrame.add(subpanel);
	      mainFrame.setVisible(true);
	}
}