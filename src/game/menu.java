package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class menu extends JFrame {
	menu(){
		setTitle("game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280,800);
		
		MainPanel p = new MainPanel();
		p.setLayout(null);				//layout setting
		
		JButton b1 = new JButton("avoid arrows");
		p.add(b1);
		JButton b2 = new JButton("Minesweeper");
		p.add(b2);
		JButton b3 = new JButton("Mole catching");
		p.add(b3);
		JButton b4 = new JButton("BaseBall");
		p.add(b4);
		JButton b5 = new JButton("Shooting Game");
		p.add(b5);
			
		//setting button font
		b1.setFont(new Font("",Font.BOLD,20));																																																		;
		b2.setFont(new Font("",Font.BOLD,20));
		b3.setFont(new Font("",Font.BOLD,20));
		b4.setFont(new Font("",Font.BOLD,20));
		b5.setFont(new Font("",Font.BOLD,20));

		//setting button background color	
		b1.setBackground(new Color(95,95,95));
		b2.setBackground(new Color(95,95,95));
		b3.setBackground(new Color(95,95,95));
		b4.setBackground(new Color(95,95,95));
		b5.setBackground(new Color(95,95,95));
		
		//setting button locate //setBounds(x, y, width, height);
		b1.setBounds(100,100,250,70);
		b2.setBounds(100,200,250,70);
		b3.setBounds(100,300,250,70);
		b4.setBounds(100,400,250,70);
		b5.setBounds(100,500,250,70);
		
		
		//event
		b1.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				new avoidArrow();
				setVisible(false);
			}
		});
		
		b2.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				new Boom();
				setVisible(false);
			}
		});
		b3.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				new MiniGame();
				setVisible(false);
			}
		});
		b4.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				new BaseBallGame();
				setVisible(false);
			}
		});
		b5.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				new GameMain();
				setVisible(false);
			}
		});
		
		add(p);
		setVisible(true);
	}
	
	class MainPanel extends JPanel{
		ImageIcon bg_img = new ImageIcon("img/background.jpg");
		Image bg = bg_img.getImage();
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(bg, 0, 0, getWidth() ,getHeight(), this);
		}
			
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new menu();
	}

}
