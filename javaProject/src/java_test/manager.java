package java_test;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.annotation.Native;
import java.sql.Date;
import java.text.ChoiceFormat;
import java.time.LocalDate;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

 
class manager extends Frame {
	private Frame mainFrame;
	private JPanel leftpanel;
	private JPanel rightpanel;

	private JPanel panelL0;
	private JPanel panelL1;
	private JPanel panelL2;
	private JPanel panelL5;
	private JPanel panelL6;
	private JPanel panelL7;
	
	private Choice yearCh;

	private Font boldFont = new Font("esamanru Bold", Font.PLAIN, 35);
	private Font mediumFont = new Font("esamanru Medium", Font.PLAIN, 20);
	private Font lightFont = new Font("esamanru Light", Font.PLAIN, 16);
	
	
	mysql_1 m = new mysql_1();
	mysql_2 m2 = new mysql_2();
	mysql_3 m3 = new mysql_3();
	mysql_4 m4 = new mysql_4();
	bringMysql n;
	
	
	public manager(String id) {
		n = new bringMysql(id);
	    prepareGUI(id);  
	}

	private void prepareGUI(String id) {
		// Frame 에 세팅
		mainFrame = new Frame("DSMS");
		mainFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
		mainFrame.setResizable(false);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		
		leftpanel = new JPanel();
		leftpanel.setLayout(new FlowLayout());
		leftpanel.setBackground(Color.white);
		leftpanel.setBorder(BorderFactory.createEmptyBorder(50, 30, 0, 30));
		
		JPanel panelR = new JPanel();
		panelR.setBackground(Color.white);	
		panelR.setLayout(new GridLayout(3, 1, 0, 0));
		
		// profile img
        JLabel imgLabel = new JLabel();
        ImageIcon icon = new ImageIcon(Main.class.getResource("../image/profile.png"));
        Image img = icon.getImage();
    	Image updateImg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon updateIcon = new ImageIcon(updateImg);
        imgLabel.setIcon(updateIcon);
        imgLabel.setHorizontalAlignment(JLabel.CENTER);
        
        // side menu button
        String name = n.name;
        JLabel label = new JLabel(name+" 매니저님");
        label.setFont(mediumFont);
        label.setHorizontalAlignment(JLabel.CENTER);
        JLabel label2 = new JLabel("반갑습니다.");
        label2.setHorizontalAlignment(JLabel.CENTER);
        label2.setFont(mediumFont);
		JButton btn1 = new JButton("오늘 매출 및 지출 기록");
		btn1.setFont(mediumFont);
		btn1.setBackground(Color.white);
		btn1.setFocusPainted(false);  
		btn1.setContentAreaFilled(false);
		JButton btn2 = new JButton("이번 달 기록");
		btn2.setFont(mediumFont);
		btn2.setBackground(Color.white);
		btn2.setFocusPainted(false);  
		btn2.setContentAreaFilled(false);
		JButton btn0 = new JButton("매출 그래프(주)");
		btn0.setFont(mediumFont);
		btn0.setBackground(Color.white);
		btn0.setFocusPainted(false);
		btn0.setContentAreaFilled(false);
		JButton btn3 = new JButton("매출 그래프(달)");
		btn3.setFont(mediumFont);
		btn3.setBackground(Color.white);
		btn3.setFocusPainted(false);
		btn3.setContentAreaFilled(false);
		JButton btn6 = new JButton("실수령액 그래프(주)");
	    btn6.setFont(mediumFont);
	    btn6.setBackground(Color.white);
	    btn6.setFocusPainted(false); 
	    btn6.setContentAreaFilled(false);
		JButton btn4 = new JButton("실수령액 그래프(달)");
		btn4.setFont(mediumFont);
		btn4.setBackground(Color.white);
		btn4.setFocusPainted(false); 
		btn4.setContentAreaFilled(false);
		JButton btn5= new JButton("로그아웃");
		btn5.setFont(mediumFont);
		btn5.setBackground(Color.white);
		btn5.setFocusPainted(false); 		// 포커스 표시 설정
		btn5.setContentAreaFilled(false);	// 버튼 영역 배경 표시 설정
		
		JPanel btnlist = new JPanel(new GridLayout(7,1,0,0));
	      
		btnlist.add(btn1);
		btnlist.add(btn2);
		btnlist.add(btn0);
		btnlist.add(btn3);
		btnlist.add(btn6);
		btnlist.add(btn4);
		btnlist.add(btn5);   
		  
		JPanel text = new JPanel(new GridLayout(2,1,0,-250));
	    text.setBackground(Color.white);
	    text.setBorder(BorderFactory.createEmptyBorder(-100, 0, 0, 0));
	    text.add(label);
	    text.add(label2);
	      
	    panelR.add(imgLabel);
	    panelR.add(text);
	    panelR.add(btnlist);
		
		leftpanel.add(panelR);
		
		rightpanel = new JPanel();
		rightpanel.setBackground(Color.orange);		

		panelL0 = new JPanel();
		panelL1 = new JPanel();
		panelL2 = new JPanel();
		panelL5 = new JPanel();
		panelL6 = new JPanel();
		panelL7 = new JPanel();	
		
// 오늘 매출 및 지출 기록
		TodaySales0();
		TodaySales();
// 이번 달 기록
		MonthlySales(id);
// 매출 그래프 & 실수령액 그래프
		ResultPanel resultPanel = new ResultPanel();
		
		// 그래프 날짜 버튼 추가
	      int year = n.year; // 지금 년도 가져오기
	      int[] years = new int[10];
	      
	      yearCh = new Choice();
	      JButton yearbtn = new JButton("확인");
	      yearbtn.setBackground(Color.white);
	      
	      
	      for(int i=0; i<10; i++) {
	         years[i] = year-i;
	         yearCh.add(year-i+"년");
	      }

	      yearCh.setSize(100, 50);
	      yearCh.setLocation(520,270);
	      yearCh.setVisible(false);   
	      
	      yearbtn.setFont(lightFont);
	      yearbtn.setBounds(630, 272, 70, 25);
	      yearbtn.setVisible(false);   
	      
	      class MouseExitedListener extends MouseAdapter{     
	          public void mouseClicked(MouseEvent e) { 
	        	  int yearidx = yearCh.getSelectedIndex();
	              // 그래프 다시 그리기
	              if(resultPanel.title.equals("매출 그래프(달)")) {
	                 System.out.println(years[yearidx]+"년의 매출 그래프");
	                 int a1[] = new int[12];
	                 for(int i=1; i<=12; i++) {
	         			a1[i-1] = m3.bringGraph(id, year-yearidx, i)/1000000;
	         		}
	                 resultPanel.setScore(a1, "매출 그래프(달)", 1000, 14);
	              } else {
	                 System.out.println(years[yearidx]+"년의 실수령액 그래프");
	                 int a2[] = new int[12];
	                 for(int i=1; i<=12; i++) {
	         			n.bringItem3 = m3.bringItem(id,(year-yearidx)*100+i);
	         			a2[i-1] =  (((int)(n.bringItem3[0]*n.money/100) - n.bringItem3[1] - n.employee)/100000);
	         		}
	                 resultPanel.setScore(a2, "실수령액 그래프(달)", 100, 14);
	              }
	              
	              resultPanel.repaint();
	          }
	      }
	      MouseExitedListener listener1 = new MouseExitedListener();    // 이벤트객체 
	        yearbtn.addMouseListener(listener1);  
	      
	      mainFrame.add(yearCh);
	      mainFrame.add(yearbtn);
	      
// 마이페이지 1 비밀번호 체크
		JPanel panelL5 = new JPanel();
		panelL5.setLayout(new BorderLayout(0, 250));
		panelL5.setBackground(Color.orange);
		
		// title
		JLabel labelL5 = new JLabel("마이페이지");
		labelL5.setHorizontalAlignment(JLabel.CENTER);
		labelL5.setFont(boldFont);
		labelL5.setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 0));
		// body
		JLabel pl5Lable1 = new JLabel("비밀번호 입력");
		pl5Lable1.setFont(lightFont);
		TextField pl5Tf1 = new TextField("", 15);
		pl5Tf1.setEchoChar('*');
		pl5Tf1.selectAll(); 
		pl5Tf1.setFont(new Font("esamanru Light", Font.PLAIN, 18));
		
		JPanel pl5 = new JPanel();
		pl5.setLayout(new GridLayout(1,2,0,5));
		pl5.setBackground(Color.orange);

		pl5.add(pl5Lable1);
		pl5.add(pl5Tf1);

		JButton pl5b1 = new JButton("확인");
		pl5b1.setFont(lightFont);
		pl5b1.setBackground(Color.white);
		Panel p2 = new Panel();
		p2.add(pl5b1);

		JPanel panelL5sub = new JPanel();
		panelL5sub.setLayout(new GridLayout(3,1,0,100));

		panelL5sub.add(pl5);
		panelL5sub.add(p2);
		panelL5sub.setBackground(Color.orange);
		
		// 마이페이지 비밀번호 확인 버튼 이벤트
		pl5b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] item = n.item;
				String pw2 = pl5Tf1.getText();
				String pw = item[0];
				if(pw2.length() == 0) {
					JOptionPane.showMessageDialog(null
							, "비밀번호를 입력해주세요."
							, "DSMS"
							, JOptionPane.ERROR_MESSAGE
					);
				} else if(!(pw2.equals(pw))) {
					JOptionPane.showMessageDialog(null
							, "비밀번호 입력 오류. 다시 한번 확인 해주세요."
							, "DSMS"
							, JOptionPane.ERROR_MESSAGE
					);
				} else {
					JOptionPane.showMessageDialog(null
							, "본인 확인 되었습니다!"
							, "DSMS"
							, JOptionPane.PLAIN_MESSAGE
					);
					panelL5.setVisible(false);
					panelL6.setVisible(true);
				}
			}
		});
		panelL5.add(labelL5, BorderLayout.NORTH);
		panelL5.add(panelL5sub, BorderLayout.CENTER);
		panelL5.setVisible(false);
		
		rightpanel.add(panelL5);
// 마이페이지 2 보기		
		mypage2(id);
// 마이페이지 3 수정
		mypage3(id);

		// side menu event
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelL0.setVisible(true);
				panelL1.setVisible(false);
				panelL2.setVisible(false);
				panelL5.setVisible(false);
				panelL6.setVisible(false);
				panelL7.setVisible(false);
				rightpanel.setVisible(true);
				yearCh.setVisible(false);
		        yearbtn.setVisible(false); 
			}
		});
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelL0.setVisible(false);
				panelL1.setVisible(false);
				panelL2.setVisible(true);
				panelL5.setVisible(false);
				panelL6.setVisible(false);
				panelL7.setVisible(false);
				rightpanel.setVisible(true);
				yearCh.setVisible(false);
		        yearbtn.setVisible(false); 
			}
		});
		//여기다 여기
		btn0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelL0.setVisible(false);
				panelL1.setVisible(false);
				panelL2.setVisible(false);
				panelL5.setVisible(false);
				panelL6.setVisible(false);
				rightpanel.setVisible(false);
				yearCh.setVisible(false);
				yearbtn.setVisible(false);	
				// 그래프 생성
				int a[] = n.today_sales;
				
				resultPanel.setScore(a, "매출 그래프(주)", 50, 16);
				resultPanel.repaint();	
				mainFrame.add(resultPanel, BorderLayout.CENTER);
			}
		});
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelL0.setVisible(false);
				panelL1.setVisible(false);
				panelL2.setVisible(false);
				panelL5.setVisible(false);
				panelL6.setVisible(false);
				panelL7.setVisible(false);
				rightpanel.setVisible(false);
				yearCh.setVisible(true);
		        yearbtn.setVisible(true); 
		        yearbtn.revalidate();
	            yearbtn.repaint();
				// 그래프 생성
				int[] a = n.a;
				resultPanel.setScore(a, "매출 그래프(달)", 1000, 14);
				resultPanel.repaint();	
				
				mainFrame.add(resultPanel, BorderLayout.CENTER);
			}
		});
		btn6.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            panelL0.setVisible(false);
	            panelL1.setVisible(false);
	            panelL2.setVisible(false);
	            panelL5.setVisible(false);
	            panelL6.setVisible(false);
	            rightpanel.setVisible(false);   
	            yearCh.setVisible(false);   
	            yearbtn.setVisible(false);   
	            
	            int a2[] = n.totals;

	            resultPanel.setScore(a2, "실수령액 그래프(주)", 5, 16);
	            resultPanel.repaint();   
	            
	            mainFrame.add(resultPanel, BorderLayout.CENTER);
	         }
	    });
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelL0.setVisible(false);
				panelL1.setVisible(false);
				panelL2.setVisible(false);
				panelL5.setVisible(false);
				panelL6.setVisible(false);
				panelL7.setVisible(false);
				rightpanel.setVisible(false);
				yearCh.setVisible(true);
		        yearbtn.setVisible(true); 
		        yearbtn.revalidate();
	            yearbtn.repaint();
		        int[] a2 = n.b;
		        
				resultPanel.setScore(a2, "실수령액 그래프(달)", 100, 14);
				resultPanel.repaint();	
				
				mainFrame.add(resultPanel, BorderLayout.CENTER);
			}
		});
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelL0.setVisible(false);
				panelL1.setVisible(false);
				panelL2.setVisible(false);
				panelL5.setVisible(false);
				panelL6.setVisible(false);
				panelL7.setVisible(false);
				rightpanel.setVisible(true);
				yearCh.setVisible(false);
		        yearbtn.setVisible(false); 
				new Main();
			}
		});
		class MyMouseListener extends MouseAdapter{     
		    public void mouseClicked(MouseEvent arg0) { 
				panelL0.setVisible(false);
		        panelL1.setVisible(false);
				panelL2.setVisible(false);
				panelL5.setVisible(true);
				panelL6.setVisible(false);
				panelL7.setVisible(false);
				rightpanel.setVisible(true);
				yearCh.setVisible(false);
		        yearbtn.setVisible(false); 
		    }
		}
        MyMouseListener listener = new MyMouseListener();    // 이벤트객체 
        imgLabel.addMouseListener(listener);  
		
		mainFrame.add(leftpanel, BorderLayout.WEST);
		mainFrame.add(rightpanel, BorderLayout.CENTER);
		
		mainFrame.setVisible(true);
	}
	// 오늘 매출 및 기록 보기
   public void TodaySales0() {
      panelL0 = new JPanel();

      panelL0.setLayout(new BorderLayout());
      panelL0.setBackground(Color.orange);   
      
      JPanel test = new JPanel();
      test.setLayout(new GridLayout(2,1,0,100));
      
   // 값 가져오기
      int today_sale = n.today_sale;      			// 오늘 매출
      int today_expense = n.today_expenses;      	// 오늘 지출비
      int today_net = (int)(today_sale*n.money)/100-today_expense; 	// 총 
      
      // title
      JLabel labelL0 = new JLabel("오늘 매출 및 지출 기록");
      labelL0.setBorder(BorderFactory.createEmptyBorder(100 , 0 , 100, 0));
      labelL0.setHorizontalAlignment(JLabel.CENTER);
      labelL0.setFont(boldFont);
      
      // body
      JPanel pl0 = new JPanel(new GridLayout(4,3,-100,10));
      pl0.setBorder(BorderFactory.createEmptyBorder(60 , 100 , 60 , 100));
      
      // 여백
      JLabel blank1 = new  JLabel();
      JLabel blank2 = new  JLabel();
      JLabel blank3 = new  JLabel();
      
      JLabel pl0LableL1 = new JLabel("오늘 매출");
      pl0LableL1.setFont(mediumFont);
      JLabel pl0LableR1 = new JLabel(((today_sale/10000 != 0) ? Integer.toString(today_sale/10000)+"만 " : "") + ((today_sale%10000 != 0) ? Integer.toString(today_sale%10000) : "") + "원");
      pl0LableR1.setFont(mediumFont);
      pl0LableR1.setHorizontalAlignment(JLabel.RIGHT);
      pl0.add(pl0LableL1);
      pl0.add(blank1);
      pl0.add(pl0LableR1);
      
      JLabel pl0LableL2 = new JLabel("지출비");
      pl0LableL2.setFont(mediumFont);
      JLabel pl0LableR2 = new JLabel(((today_expense/10000 != 0) ? Integer.toString(today_expense/10000)+"만 " : "") + ((today_expense%10000 != 0) ? Integer.toString(today_expense%10000) : "") + "원");
      pl0LableR2.setFont(mediumFont);
      pl0LableR2.setHorizontalAlignment(JLabel.RIGHT);
      pl0.add(pl0LableL2);
      pl0.add(blank2);
      pl0.add(pl0LableR2);
      
      // 선
      JLabel lineL = new JLabel("-----------------------------------------------");
      JLabel lineC = new JLabel("-----------------------------------------------");
      JLabel lineR = new JLabel("-----------------------------------------------");
      lineR.setHorizontalAlignment(JLabel.RIGHT);
      pl0.add(lineL);
      pl0.add(lineC);
      pl0.add(lineR);
      
      JLabel pl0LableL3 = new JLabel("오늘 실수령액");
      pl0LableL3.setFont(mediumFont);
      pl0LableL3.setForeground(Color.black);
      JLabel pl0LableR3;
      if(today_net == 0) {
    	  pl0.setLayout(new GridLayout(4,3,-90,10));
    	  lineL = new JLabel("---------------------------------------------");
          lineC = new JLabel("---------------------------------------------");
          lineR = new JLabel("---------------------------------------------------------------------");
    	  pl0LableR3 = new JLabel("아직 기록하지 않았습니다");
    	  pl0LableR3.setForeground(Color.black);
      } else {
    	  pl0LableR3 = new JLabel(((today_net >= 0) ? "+" : "") + ((today_net/10000 != 0) ? Integer.toString(today_net/10000)+"만 " : "") + ((today_net%10000 != 0) ? Integer.toString(today_net%10000) : "") + "원");
    	  pl0LableR3.setForeground((today_net > 0) ? Color.red : Color.blue);
      }
      pl0LableR3.setFont(mediumFont);
      pl0LableR3.setHorizontalAlignment(JLabel.RIGHT);
      pl0.add(pl0LableL3);
      pl0.add(blank3);
      pl0.add(pl0LableR3);

      JButton pl0b1 = new JButton("수정");
      pl0b1.setFont(lightFont);
      pl0b1.setBackground(Color.white);
      
      // 수정 버튼 이벤트
      pl0b1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            System.out.println("오늘 매출 및 지출 기록 => 수정 버튼을 누르셨습니다.");
            panelL0.setVisible(false);
            panelL1.setVisible(true);
         }
      });
      
      Panel p0 = new Panel();
      p0.add(pl0b1);
      
      test.add(pl0);
      test.add(p0);
      test.setBackground(Color.orange);
      test.setVisible(true);
      
      panelL0.add(labelL0, BorderLayout.NORTH);
      panelL0.add(test, BorderLayout.CENTER);
      panelL0.setVisible(true);

      rightpanel.add(panelL0);
   }
// 오늘 매출 및 기록 수정
	public void TodaySales() {
		panelL1 = new JPanel();

		panelL1.setLayout(new BorderLayout());
		panelL1.setBackground(Color.orange);	
		
		JPanel test = new JPanel();
		test.setLayout(new GridLayout(2,1,0,100));
		
		// title
		JLabel labelL1 = new JLabel("오늘 매출 및 지출 기록");
		labelL1.setBorder(BorderFactory.createEmptyBorder(100 , 0 , 100 , 0));
		labelL1.setHorizontalAlignment(JLabel.CENTER);
		labelL1.setFont(boldFont);
		
		// body 
		JPanel pl1 = new JPanel(new GridLayout(6,2,0,10));
		pl1.setBorder(BorderFactory.createEmptyBorder(60 , 100 , 60 , 100));
		
		JLabel pl1Lable1 = new JLabel("오늘 매출");
		pl1Lable1.setFont(lightFont);
		TextField pl1Tf1 = new TextField("", 4);
		pl1Tf1.selectAll();
		pl1.add(pl1Lable1);
		pl1.add(pl1Tf1);
		
		JLabel pl1Lable2 = new JLabel("기타 지출비");
		pl1Lable2.setFont(lightFont);
		Panel check1 = new Panel();
		JRadioButton pl1ra1 = new JRadioButton("유", true);
		pl1ra1.setFont(lightFont);
		JRadioButton pl1ra2 = new JRadioButton("무", false);
		pl1ra2.setFont(lightFont);
		ButtonGroup pl1group = new ButtonGroup();
		
		pl1group.add(pl1ra1);
		pl1group.add(pl1ra2);
		
		check1.add(pl1ra1);
		check1.add(pl1ra2);
		
		JLabel pl1blank = new JLabel();
		
		TextField pl1Tf2 = new TextField("", 15);
		pl1Tf2.selectAll(); 
		
		pl1.add(pl1Lable2);
		pl1.add(check1);
		pl1.add(pl1blank);
		pl1.add(pl1Tf2);
		
		Panel check2 = new Panel();
		JLabel pl1Lable3 = new JLabel("인건비(알바)");
		pl1Lable3.setFont(lightFont);
		JRadioButton ra1 = new JRadioButton("유", true);
		ra1.setFont(lightFont);
		JRadioButton ra2 = new JRadioButton("무", false);
		ra2.setFont(lightFont);
		ButtonGroup group = new ButtonGroup();
		
		group.add(ra1);
		group.add(ra2);

		check2.add(ra1);
		check2.add(ra2);
		pl1.add(pl1Lable3);
		pl1.add(check2);
		
		JLabel pl1Lable4 = new JLabel("알바 시급");
		pl1Lable4.setFont(lightFont);
		TextField pl1Tf4 = new TextField("", 15);
		pl1Tf4.selectAll(); 
		pl1.add(pl1Lable4);
		pl1.add(pl1Tf4);
		
		JLabel pl1Lable5 = new JLabel("알바 시간");
		pl1Lable5.setFont(lightFont);
		Choice ch = new Choice();
		for(int i=1; i<=24; i++) {
			ch.addItem(i+"시간");
		}
		pl1.add(pl1Lable5);
		pl1.add(ch);

		JButton pl1b1 = new JButton("확인");
		pl1b1.setFont(lightFont);
		pl1b1.setBackground(Color.white);
		JButton pl1b2 = new JButton("취소");
		pl1b2.setFont(lightFont);
		pl1b2.setBackground(Color.white);
		
		// 기타 지출비 유무 이벤트
		pl1ra1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pl1Tf2.setVisible(true);
			}
		});
		
		pl1ra2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pl1Tf2.setVisible(false);
			}
		});
		// 알바 유무 이벤트
		ra1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pl1Lable4.setVisible(true);
				pl1Tf4.setVisible(true);
				pl1Lable5.setVisible(true);
				ch.setVisible(true);
			}
		});
		
		ra2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pl1Lable4.setVisible(false);
				pl1Tf4.setVisible(false);
				pl1Lable5.setVisible(false);
				ch.setVisible(false);
			}
		});
		
		// 확인 버튼 이벤트
		pl1b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("오늘 매출 및 지출 기록 => 확인 버튼을 누르셨습니다.");
				// 데이터 저장 기능 추가해야함 + 예외 처리
	
				int today_sale; // 오늘 매출
				int today_gita = 0; // 기타 지출비
				boolean emp; // 알바 유무
				int expenses = 0; // 알바 시급
				int time; // 알바 시간
				
				//pl1group.add(pl1ra2);
				//pl1Tf2

				emp = (ra1.isSelected() == true) ? true : false;
				time = ch.getSelectedIndex() + 1;
				
				
				// isNum 메서드 호출
				isNum in = new isNum();
				
				if(pl1Tf1.getText().length() == 0) {		// 오늘 매출 예외 처리
					JOptionPane.showMessageDialog(null
							, "오늘 매출을 입력해주세요."
							, "DSMS"
							, JOptionPane.ERROR_MESSAGE
					);
				} else if(!in.isNum(pl1Tf1.getText())) {
					JOptionPane.showMessageDialog(null
							, "오늘 매출은 숫자만 입력해주세요."
							, "DSMS"
							, JOptionPane.ERROR_MESSAGE
					);
				} else if(pl1ra1.isSelected() == true && pl1Tf2.getText().length() == 0) {		// 오늘 매출 예외 처리
					JOptionPane.showMessageDialog(null
							, "기타 지출비를 입력해주세요."
							, "DSMS"
							, JOptionPane.ERROR_MESSAGE
					);
				} else if(pl1ra1.isSelected() == true && !in.isNum(pl1Tf2.getText())) {
					JOptionPane.showMessageDialog(null
							, "기타 지출비는 숫자만 입력해주세요."
							, "DSMS"
							, JOptionPane.ERROR_MESSAGE
					);
				} else if(emp && pl1Tf4.getText().length() == 0) {		// 오늘 매출 예외 처리
					JOptionPane.showMessageDialog(null
							, "알바 시급을 입력해주세요."
							, "DSMS"
							, JOptionPane.ERROR_MESSAGE
					);
				} else if(emp && !in.isNum(pl1Tf4.getText())) {
					JOptionPane.showMessageDialog(null
							, "알바 시급은 숫자만 입력해주세요."
							, "DSMS"
							, JOptionPane.ERROR_MESSAGE
					);
				} else {
					JOptionPane.showMessageDialog(null
							, "오늘 매출 및 지출을 기록하였습니다."
							, "DSMS"
							, JOptionPane.PLAIN_MESSAGE
					);
					today_sale = Integer.parseInt(pl1Tf1.getText());
					if(pl1ra1.isSelected() == true)
						today_gita = Integer.parseInt(pl1Tf2.getText());
					if(emp)expenses = Integer.parseInt(pl1Tf4.getText());
					expenses *= time;
					today_gita += expenses;
					
					int total = (int) (today_sale*n.money/100)-today_gita;
					m2.today(n.id,n.yearPlus,n.day,today_sale,today_gita);
					m4.today(n.id,n.day1,today_sale,today_gita,total);
					m4.UpdateToday(n.id, n.day1, today_sale, today_gita, total);
					n = new bringMysql(n.id);
					
					// 이번 달 기록 다시 그리기
					TodaySales0();
					MonthlySales(n.id);
					
					// 화면 전환
					panelL0.setVisible(true);
		            panelL1.setVisible(false);
				}
			}
		});
		pl1b2.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            // 화면 전환
	            panelL0.setVisible(true);
	            panelL1.setVisible(false);
	         }
	      });
      JPanel p1test = new JPanel(new FlowLayout());
      p1test.setBackground(Color.orange);
      JPanel p1_1 = new JPanel();
      JPanel p1_2 = new JPanel();
      p1_1.add(pl1b1);
      p1_2.add(pl1b2);
      p1test.add(pl1b1);
      p1test.add(pl1b2);
      
      test.add(pl1);
      test.add(p1test);
      test.setBackground(Color.orange);
      
      panelL1.add(labelL1, BorderLayout.NORTH);
      panelL1.add(test, BorderLayout.CENTER);
      panelL1.setVisible(false);

      rightpanel.add(panelL1);
	}
	public void MonthlySales(String id) {	// 이번 달 기록
		panelL2 = new JPanel();
		panelL2.setLayout(new BorderLayout(0, 100));
		panelL2.setBackground(Color.orange);	
		
		// ...
		
		// 값 가져오기
		int money1 =n.money1; // 매출액
		int money2 = ((int) (n.money1*n.money))/100; // 본사 지급액
		int money3 = n.money2+n.employee;  // 인건비 등 지출비
		int money4 = money2-money3; // 실수령액
		
		JLabel labelL2 = new JLabel("이번 달 기록("+n.month+"/"+n.day+"까지의 기록)");
		labelL2.setFont(boldFont);
		labelL2.setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 0));
		labelL2.setHorizontalAlignment(JLabel.CENTER);
		
		JPanel pl2 =new JPanel();
		pl2.setLayout(new GridLayout(5,3,-50,10));
	    pl2.setBorder(BorderFactory.createEmptyBorder(60 , 100 , 60 , 100));
		
	    // 여백
	    JLabel blank1 = new  JLabel();
	    JLabel blank2 = new  JLabel();
	    JLabel blank3 = new  JLabel();
	    JLabel blank4 = new  JLabel();
	    
	    JLabel pl2LbL1 = new JLabel("매출액");
	    pl2LbL1.setFont(mediumFont);
	    JLabel pl2LbR1 = new JLabel(((money1/10000 != 0) ? money1/10000+"만 " : "") + ((money1%10000 != 0) ? (money1%10000) : "") + "원");
	    pl2LbR1.setFont(mediumFont);
	    pl2LbR1.setHorizontalAlignment(JLabel.RIGHT);
	      
	    JLabel pl2LbL2 = new JLabel("본사 지급액");
	    pl2LbL2.setFont(mediumFont);
	    JLabel pl2LbR2 = new JLabel(((money2/10000 != 0) ? money2/10000+"만 " : "") + ((money2%10000 != 0) ? (money2%10000) : "") + "원");
	    pl2LbR2.setFont(mediumFont);
	    pl2LbR2.setHorizontalAlignment(JLabel.RIGHT);

	    JLabel pl2LbL3 = new JLabel("(인건비 등)지출비");
	    pl2LbL3.setFont(mediumFont);
	    JLabel pl2LbR3 = new JLabel(((money3/10000 != 0) ? money3/10000+"만 " : "") + ((money3%10000 != 0) ? (money3%10000) : "") + "원");
	    pl2LbR3.setFont(mediumFont);
	    pl2LbR3.setHorizontalAlignment(JLabel.RIGHT);

	    // 선
	    JLabel lineL = new JLabel("--------------------------------------");
	    JLabel lineC = new JLabel("--------------------------------------");
	    JLabel lineR = new JLabel("--------------------------------------");
	    lineR.setHorizontalAlignment(JLabel.RIGHT);

	    JLabel pl2LbL4 = new JLabel("실수령액");
	    pl2LbL4.setFont(mediumFont);
	    pl2LbL4.setForeground(Color.black);
	    JLabel pl2LbR4 = new JLabel();	    
	    pl2LbR4.setFont(mediumFont);
	    pl2LbR4.setHorizontalAlignment(JLabel.RIGHT);
	    if(money4 == 0) {
	    	pl2.setLayout(new GridLayout(4,3,-150,10));
	    	lineL = new JLabel("---------------------------------------------");
	        lineC = new JLabel("---------------------------------------------");
	        lineR = new JLabel("---------------------------------------------------------------------");
	        pl2LbR4.setText("아직 기록하지 않았습니다");
	        pl2LbR4.setForeground(Color.black);
	    } else {
	    	pl2LbR4.setText(((money4 >= 0) ? "+" : "") + ((money4/10000 != 0) ? Integer.toString(money4/10000)+"만 " : "") + ((money4%10000 != 0) ? Integer.toString(money4%10000) : "") + "원");
	    	pl2LbR4.setForeground((money4 > 0) ? Color.red : Color.blue);
	    }
	    
	    pl2.add(pl2LbL1);
	    pl2.add(blank1);
	    pl2.add(pl2LbR1);
	    pl2.add(pl2LbL2);
	    pl2.add(blank2);
	    pl2.add(pl2LbR2);
	    pl2.add(pl2LbL3);
	    pl2.add(blank3);
	    pl2.add(pl2LbR3);
		pl2.add(lineL);
		pl2.add(lineC);
		pl2.add(lineR);
	    pl2.add(pl2LbL4);
	    pl2.add(blank4);
	    pl2.add(pl2LbR4);
	      
	    panelL2.add(labelL2, BorderLayout.NORTH);
	    panelL2.add(pl2, BorderLayout.CENTER);
	    panelL2.setVisible(false);
	    rightpanel.add(panelL2);
	}
	
	public void mypage2(String id){	// 마이페이지 보기
		panelL6 = new JPanel();
		panelL6.setLayout(new BorderLayout(0, 100));
		panelL6.setBackground(Color.orange);
		
		String name = n.name;
		String brand = n.brand;
		String pw = n.pw;
		float money = n.money; 
		int employee = n.employee;
		
		// title
		JLabel labelL6 = new JLabel("마이페이지");
		labelL6.setFont(boldFont);
		labelL6.setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 0));
		labelL6.setHorizontalAlignment(JLabel.CENTER);
		// body
		JLabel pl6LableL1 = new JLabel("이름");
		pl6LableL1.setFont(lightFont);
		JLabel pl6LableR1 = new JLabel(name);
		pl6LableR1.setFont(lightFont);
		pl6LableR1.setHorizontalAlignment(JLabel.RIGHT);
		JLabel pl6LableL2 = new JLabel("아이디");
		pl6LableL2.setFont(lightFont);
		JLabel pl6LableR2 = new JLabel(id);
		pl6LableR2.setFont(lightFont);
		pl6LableR2.setHorizontalAlignment(JLabel.RIGHT);
		JLabel pl6LableL3 = new JLabel("비밀번호");
		pl6LableL3.setFont(lightFont);
		JLabel pl6LableR3 = new JLabel(pw);
		pl6LableR3.setFont(lightFont);
		pl6LableR3.setHorizontalAlignment(JLabel.RIGHT);
		JLabel pl6LableL4 = new JLabel("지점명");
		pl6LableL4.setFont(lightFont);
		JLabel pl6LableR4 = new JLabel(brand);
		pl6LableR4.setFont(lightFont);
		pl6LableR4.setHorizontalAlignment(JLabel.RIGHT);
		JLabel pl6LableL5 = new JLabel("매출대비지급액(%)");
		pl6LableL5.setFont(lightFont);
		JLabel pl6LableR5 = new JLabel(Float.toString(money));
		pl6LableR5.setFont(lightFont);
		pl6LableR5.setHorizontalAlignment(JLabel.RIGHT);
		JLabel pl6LableL6 = new JLabel("직원 유무");
		pl6LableL6.setFont(lightFont);
		JLabel pl6LableR6 = new JLabel((employee == 0) ? "무" : "유");
		pl6LableR6.setFont(lightFont);
		pl6LableR6.setHorizontalAlignment(JLabel.RIGHT);
		JLabel pl6LableL7 = new JLabel("직원 월급");
		pl6LableL7.setFont(lightFont);
		JLabel pl6LableR7 = new JLabel(Integer.toString(employee));
		pl6LableR7.setFont(lightFont);
		pl6LableR7.setHorizontalAlignment(JLabel.RIGHT);
			
		JPanel pl6 = new JPanel();
		pl6.setLayout(new GridLayout(8,2,40,10));
		pl6.setBorder(BorderFactory.createEmptyBorder(50,70,35,70));

		JButton pl6b1 = new JButton("정보 수정");
		pl6b1.setBackground(Color.white);
		
		pl6.add(pl6LableL1);
		pl6.add(pl6LableR1);
		pl6.add(pl6LableL2);
		pl6.add(pl6LableR2);
		pl6.add(pl6LableL3);
		pl6.add(pl6LableR3);
		pl6.add(pl6LableL4);
		pl6.add(pl6LableR4);
		pl6.add(pl6LableL5);
		pl6.add(pl6LableR5);
		pl6.add(pl6LableL6);
		pl6.add(pl6LableR6);
		pl6.add(pl6LableL7);
		pl6.add(pl6LableR7);
		pl6.add(pl6b1);
		
		JPanel pl6test = new JPanel();
		pl6test.add(pl6b1);
		pl6test.setBackground(Color.orange);
		
		JPanel test = new JPanel(new GridLayout(2, 1, 0, 100));
		test.setBackground(Color.orange);
		test.add(pl6);
		test.add(pl6test);
		
		// 마이페이지 정보 수정 버튼 이벤트
		pl6b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelL6.setVisible(false);
				panelL7.setVisible(true);
			}
		});
		
		panelL6.add(labelL6, BorderLayout.NORTH);
		panelL6.add(test, BorderLayout.CENTER);
		panelL6.setVisible(false);

		rightpanel.add(panelL6);
	}
	
	public void mypage3(String id){	// 마이페이지 정보 수정
		String name = n.name;
		String brand = n.brand;
		String pw = n.pw;
		float money = n.money; 
		int employee = n.employee;
		
		panelL7 = new JPanel();
		panelL7.setLayout(new BorderLayout(0, 100));
		panelL7.setBackground(Color.orange);
		
		// title
		JLabel labelL7 = new JLabel("정보 수정");
		labelL7.setFont(boldFont);
		labelL7.setHorizontalAlignment(JLabel.CENTER);
		labelL7.setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 0));
		
		// body
		JLabel pl7Lable1 = new JLabel("이름");
		pl7Lable1.setFont(lightFont);
		TextField pl7Tf1 = new TextField(name, 15);
		pl7Tf1.selectAll(); 
		
		JLabel pl7LableL2 = new JLabel("아이디");
		pl7LableL2.setFont(lightFont);
		JLabel pl7LableR2 = new JLabel(id);
		
		JLabel pl7Lable3 = new JLabel("비밀번호");
		pl7Lable3.setFont(lightFont);
		TextField pl7Tf3 = new TextField(pw, 15);
		pl7Tf3.selectAll(); 
		
		JLabel pl7Lable4 = new JLabel("지점명");
		pl7Lable4.setFont(lightFont);
		TextField pl7Tf4 = new TextField(brand, 15);
		pl7Tf4.selectAll(); 
		
		JLabel pl7Lable5 = new JLabel("매출대비지급액(%)");
		pl7Lable5.setFont(lightFont);
		TextField pl7Tf5 = new TextField(Float.toString(money), 15);
		pl7Tf5.selectAll(); 
		
		JPanel pl7check = new JPanel();
		JLabel pl7Lable6 = new JLabel("직원");
		pl7Lable6.setFont(lightFont);
		CheckboxGroup pl7g = new CheckboxGroup();
		JRadioButton pl7ra1 = new JRadioButton("유", (employee != 1) ? true : false);
		pl7ra1.setFont(lightFont);
		JRadioButton pl7ra2 = new JRadioButton("무", (employee == 1) ? true : false);
		pl7ra2.setFont(lightFont);
		ButtonGroup pl7group = new ButtonGroup();
		pl7group.add(pl7ra1);
		pl7group.add(pl7ra2);
		pl7check.add(pl7ra1);
		pl7check.add(pl7ra2);
	
		JLabel pl7Lable7 = new JLabel("직원 월급");
		pl7Lable7.setFont(lightFont);
		TextField pl7Tf7 = new TextField(Integer.toString(employee), 15);
	
		
		JPanel pl7 = new JPanel();
		pl7.setLayout(new GridLayout(8,2,0,5));
		pl7.setBorder(BorderFactory.createEmptyBorder(50, 70, 0, 70)); 
		
		JButton pl7b1 = new JButton("확인");
		pl7b1.setFont(lightFont);
		pl7b1.setBackground(Color.white);
		
		pl7.add(pl7Lable1);
		pl7.add(pl7Tf1);
		pl7.add(pl7LableL2);
		pl7.add(pl7LableR2);
		pl7.add(pl7Lable3);
		pl7.add(pl7Tf3);
		pl7.add(pl7Lable4);
		pl7.add(pl7Tf4);
		pl7.add(pl7Lable5);
		pl7.add(pl7Tf5);
		pl7.add(pl7Lable6);
		pl7.add(pl7check);
		pl7.add(pl7Lable7);
		pl7.add(pl7Tf7);
		pl7.add(pl7b1);
		
		JPanel pl7test = new JPanel();
		pl7test.add(pl7b1);
		pl7test.setBackground(Color.orange);
		pl7test.setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 0));
		JPanel test = new JPanel(new GridLayout(2,1,0,0));
		test.add(pl7);
		test.add(pl7test);

		// 마이페이지 정보 수정 직원 유무 이벤트 & 확인 버튼 & 예외처리
			pl7ra1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					pl7Lable7.setVisible(true);
					pl7Tf7.setVisible(true);
				}
			});
			
			pl7ra2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					pl7Lable7.setVisible(false);
					pl7Tf7.setVisible(false);
				}
			});
			pl7b1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String name = n.name;
					String brand = n.brand;
					String pw = n.pw;
					float money = n.money; 
					int employee = n.employee;
					
					String name2 = pl7Tf1.getText();
					String pw2 = pl7Tf3.getText();
					String brand2 = pl7Tf4.getText();
					float money2;
					int employee2 = (pl7ra1.isSelected() == true) ? 1 : 0;
					
				// isNum 메서드 호출
				isNum in = new isNum();
				
				if(name2.length() == 0) {
					JOptionPane.showMessageDialog(null
							, "이름을 입력해주세요."
							, "DSMS"
							, JOptionPane.ERROR_MESSAGE
					);
				} else if(pw2.length() == 0) {
					JOptionPane.showMessageDialog(null
							, "비밀번호를 입력해주세요."
							, "DSMS"
							, JOptionPane.ERROR_MESSAGE
					);
				} else if(brand2.length() == 0) {
					JOptionPane.showMessageDialog(null
							, "지점명을 입력해주세요."
							, "DSMS"
							, JOptionPane.ERROR_MESSAGE
					);
				} else if(pl7Tf5.getText().length() == 0) {	// 예외 처리 매출대비지급액
					JOptionPane.showMessageDialog(null
							, "매출대비지급액을 입력해주세요."
							, "Message"
							, JOptionPane.ERROR_MESSAGE
					);
				} else if(!in.isNum(pl7Tf5.getText())) {
					JOptionPane.showMessageDialog(null
							, "매출대비지급액은 숫자만 입력해주세요."
							, "DSMS"
							, JOptionPane.ERROR_MESSAGE
					);
				} else if(employee2 == 1 && (pl7Tf7.getText()).length() == 0) {		// 예외 처리 직원 월급
					JOptionPane.showMessageDialog(null
							, "직원 월급을 입력해주세요."
							, "DSMS"
							, JOptionPane.ERROR_MESSAGE
					);
				} else if(employee2 == 1 && !in.isNum(pl7Tf7.getText())) {
					JOptionPane.showMessageDialog(null
							, "직원 월급은 숫자만 입력해주세요."
							, "DSMS"
							, JOptionPane.ERROR_MESSAGE
					);
				} else {
					JOptionPane.showMessageDialog(null
							, "정상적으로 정보 수정 완료!"
							, "DSMS"
							, JOptionPane.PLAIN_MESSAGE
					);
					
					// 수정된 데이터 저장
					name = name2;
					pw = pw2;
					brand = brand2;
					money = Float.parseFloat(pl7Tf5.getText());
					if(employee2 == 1)
						employee = Integer.parseInt(pl7Tf7.getText());
					if(employee2 ==0)
						employee = 0;
					m.UpdateTable(name, id, pw, brand, money, employee);
					n = new bringMysql(n.id);
					// 마이페이지 정보 다시그리기
					mypage2(id);
					MonthlySales(id);
					
					panelL7.setVisible(false);
					panelL6.setVisible(true);
				}
			}
		});
		
		panelL7.add(labelL7, BorderLayout.NORTH);
		panelL7.add(test, BorderLayout.CENTER);
		panelL7.setVisible(false);
		
		rightpanel.add(panelL7);
	}
	// 그래프 그리기
		class ResultPanel extends JPanel{
			int a[] = new int[12];
			String title;
			int range1, range2;
			int today;
			
			public void paint(Graphics g) {
				g.setColor(Color.orange);
		         g.fillRect(0, 0, getWidth(), getHeight());
		         g.setColor(Color.white);
		         g.fillRect(100, 200, 1100, 500);
		         g.setColor(Color.BLACK);
		         g.setFont(new Font("esamanru Bold", Font.PLAIN, 35));
		         if(title.equals("실수령액 그래프(주)") || title.equals("실수령액 그래프(달)"))
		             g.drawString(title, 520, 135);
		         else
		            g.drawString(title, 550, 135);
		         g.setFont(new Font("esamanru Light", Font.PLAIN, 14));
		         g.drawString("(단위:만원)", 1100, 260);
		         g.setFont(new Font("esamanru Light", Font.PLAIN, 16));
		         g.drawLine(170, 650, 1170, 650);
		         
		         for(int i=1; i<=range2; i++) {
		            g.drawString(i*range1 + "", 107, 650-(20*i));
		            g.drawLine(170, 650-(20*i), 1170, 650-(20*i));
		         }
		         if(range2 > 14) {
		             g.drawLine(170,310,170,650);      //int x1, int y1, int x2, int y2
		          } else {
		             g.drawLine(170,350,170,650);      //int x1, int y1, int x2, int y2
		          }

		         String[] week = new String[]{"월", "화", "수", "목", "금", "토","일"};

		         if(title.indexOf("주") != -1) {
		            for(int i=0, cnt = today; i<7; i++) {
		               if(cnt == 7) cnt = 0;   
		               g.drawString(week[cnt++], i*130 + 260, 670);
		            }   
		            
		            g.setColor(Color.BLUE);
		            for(int i=0, cnt = today; i<7; i++) {
		            	 if(cnt == 7) cnt = 0;   
		               g.fillRect((i*130)+262, 650 - a[cnt]*2, 10 ,a[cnt]*2); 
		               cnt++;
		            }
		         }
		         else {
		            for(int i=1; i<=12; i++) {
		               g.drawString("  "+i+"월", i*80 + 140, 670);   
		            }   
		            g.setColor(Color.BLUE);
		            for(int i=1; i<=12; i++) {
		               g.fillRect((i*80)+155, 650 - a[i-1]*2, 10 ,a[i-1]*2);   
		            }
		         }
				
			}
		
			void setScore(int a[], String title, int range1, int range2) {
				for(int i=0; i<a.length; i++) {
					this.a[i] = a[i];
				}
				this.title = title;
				this.range1 = range1;
		        this.range2 = range2;

		        today = n.day1;
			
			}
	}
}