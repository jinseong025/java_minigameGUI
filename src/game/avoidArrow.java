package game;
 
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
 
public class avoidArrow extends JFrame implements Runnable,KeyListener{

	int playerX,playerY;
	int arrowX,arrowY;
	int f_w,f_h;
	int cnt = 0;
	int score = 0;
	
	
	
	boolean play = true;
	boolean KeyLeft = false;
	boolean KeyRight = false;

	Thread th;
	Toolkit tk = Toolkit.getDefaultToolkit();

	Image player_img;
	Image arrow_img;
	Image BackGround_img;
	Image buffImage;
	Graphics buffg;

	ArrayList arrow_list = new ArrayList();
	arrow a;

	Container c; 
	JLabel scoreBoard = new JLabel();
	
	avoidArrow(){
		init();
		start();
		c = getContentPane();
		setTitle("avoidArrow");
		setSize(f_w,f_h);
		setVisible(true);
	}

	public void init() {
		playerX = 590;
		playerY = 650;
		arrowY = 100;
		f_w = 800;
		f_h = 800;
		score = 0;
		player_img = new ImageIcon("img/avoidArrow/player.png").getImage();
		arrow_img = new ImageIcon("img/avoidArrow/arrow.png").getImage();
		BackGround_img = new ImageIcon("img/avoidArrow/background.png").getImage();
	}
	
	public void start() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addKeyListener(this);
		th = new Thread(this);
		th.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while(play)
			{
				KeyProcess();
				ArrowProcess();
				repaint();
				Thread.sleep(10);
				cnt++;
			}
			new menu();
			setVisible(false);

			
		}catch (Exception e) {
		}
	}	

	public void ArrowProcess() {
		
		int x = (int)(Math.random()*800);

		
		//arrow speed
		if ((cnt % 30) == 0) {
			a = new arrow(x,0);
			arrow_list.add(a);
		}
		//arrow remove
		for (int i = 0; i < arrow_list.size(); ++i) {
			a = (arrow) arrow_list.get(i);
			a.move();
			if (a.y > f_h+20) {
				arrow_list.remove(i);
				score++;
			}
			if(a.x>playerX&&a.x<playerX+50&&a.y+50>playerY&&a.y<playerY+80) {
				System.out.println("collision" +score);
				play = false;
				Object obj = null;
				JOptionPane.showMessageDialog((Component)obj, "Game over" );
				}
		}
	}

	public void Draw_Arrow() {
		for(int i=0; i<arrow_list.size();++i) {
			a = (arrow)(arrow_list.get(i));
			buffg.drawImage(arrow_img, a.x, a.y,50,50,this);
		}
	}

	public void paint(Graphics g) {
		buffImage = createImage(f_w, f_h);
		buffg = buffImage.getGraphics();
		update(g);
	}

	public void update(Graphics g) {
			Draw_Background();
			Draw_Player(); // �뵆�젅�씠�뼱瑜� 洹몃━�뒗 硫붿냼�뱶 �씠由� 蹂�寃�
			Draw_Arrow();
			Draw_Score();
			g.drawImage(buffImage, 0, 0, this);
	}
	public void Draw_Background() {
		buffg.clearRect(0, 0, f_w, f_h);
		//image,x,y,xsize,ysize,this
		buffg.drawImage(BackGround_img, 0, 0, f_w, f_h, this);		
	}
	public void Draw_Player() {
		//image,x,y,xsize,ysize,this
		//buffg.clearRect(playerX,playerY,100,100);

		buffg.drawImage(player_img,playerX,playerY,100,100, this);
	}
	public void Draw_Score() {
		buffg.setFont(new Font("Default", Font.BOLD, 20));
		buffg.drawString("Score : " + score, 650, 50);
	}

	public void Draw_GameOver() {
		buffg.setFont(new Font("Default", Font.BOLD, 40));
		buffg.drawString("Game over", 400, 400);
	}
	
	
	public void KeyProcess() {
		if (KeyLeft == true) {
			if (playerX > 0)
				playerX -= 5;
		}
		if (KeyRight == true) {
			if (playerX + 100 < f_w)
				playerX += 5;
		}
	}
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			KeyLeft = true;
			break;
		case KeyEvent.VK_RIGHT:
			KeyRight = true;
			break;
		}
	}
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			KeyLeft = false;
			break;
		case KeyEvent.VK_RIGHT:
			KeyRight = false;
			break;
		}
	}
	public void keyTyped(KeyEvent e) {
	}
	

}


class arrow{
	int x; int y;
	arrow(int x, int y){
		this.x=x;
		this.y=y;
	}
	public void move() {
		y+=4;
	}
}

