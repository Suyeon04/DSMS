package java_test;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.InputStream;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.text.LayoutQueue;
 
public class Main {
   private Frame mainFrame;
   private JPanel subpanel;
   private JLabel headerLabel;
   private JLabel statusLabel;
   private Panel controlPanel;

   // Font
      Font font1 = new Font("esamanru Bold", Font.PLAIN, 50);
      Font font2 = new Font("esamanru Medium", Font.PLAIN, 25);
	  Font font3 = new Font("esamanru Light", Font.PLAIN, 16);
   public Main() {
      prepareGUI();
      showLabel();
   }
   
   public static void main(String[] args) {
      new Main();
   }
 
   private void prepareGUI() {
	      // Frame 셋팅
	      mainFrame = new Frame("DSMS");
	      mainFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
	      mainFrame.setResizable(false);
	      mainFrame.setLocationRelativeTo(null);
	      mainFrame.setLayout(new BorderLayout() );
	      mainFrame.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent) {
	            System.exit(0);
	         }
	      });
	      mainFrame.setVisible(true);

	      // 화면 가로 길이
	      int width = mainFrame.getWidth();
	      
	      // 상단 제목
	      headerLabel = new JLabel();
	      headerLabel.setText("DSMS");   
	      headerLabel.setHorizontalAlignment(JLabel.CENTER);
	      headerLabel.setFont(font1);
	      
	      // 하단 소제목
	      statusLabel = new JLabel();
	      statusLabel.setText("Department Store Manager Sales");
	      statusLabel.setHorizontalAlignment(JLabel.CENTER);
	      statusLabel.setFont(font2);
	      
	      // 버튼
	      controlPanel = new Panel();
	      controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 1000, 10));
	      
	      // 투명 레이아웃
	      JLabel blank = new JLabel();
	      
	      JPanel test = new JPanel(new GridLayout(3,1,0,-20));
	      test.setBackground(Color.orange);
	      test.setBorder(BorderFactory.createEmptyBorder(250, 1, 50, 1));
	      test.add(headerLabel);
	      test.add(blank);
	      test.add(statusLabel);
	      
	      subpanel = new JPanel(new BorderLayout());
	      subpanel.setBackground(Color.orange);
	      subpanel.add(test, BorderLayout.NORTH);
	      subpanel.add(controlPanel, BorderLayout.CENTER);
	      
	      mainFrame.add(subpanel);
   }
   private void showLabel() {
      // 버튼 생성
      JButton loginbtn = new JButton("  로그인  ");
      loginbtn.setBackground(Color.WHITE);
      loginbtn.setForeground(Color.BLACK);
      loginbtn.setFocusPainted(false); 
      loginbtn.setFont(font3);
      
      JButton joinbtn = new JButton("회원가입");
      joinbtn.setBackground(Color.WHITE);
      joinbtn.setForeground(Color.BLACK);
      joinbtn.setFocusPainted(false); 
      joinbtn.setFont(font3);
      
      // 버튼 클릭 이벤트
      loginbtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
              new login(); //프레임 전환
              mainFrame.setVisible(false);
         }
      });
      
      joinbtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
              new join(); //프레임 전환
              mainFrame.setVisible(false);
         }
      });

      controlPanel.add(loginbtn);
      controlPanel.add(joinbtn);
      
      mainFrame.setVisible(true);
   }
   
   
}