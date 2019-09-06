package game;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class StartPanel extends JPanel {
	private ImageIcon bgIcon = new ImageIcon("main.png");
	private ImageIcon playBtnIcon1 = new ImageIcon("playbtn1.png");
	private ImageIcon playBtnIcon2 = new ImageIcon("playbtn2.png");
	private ImageIcon exitBtnIcon1 = new ImageIcon("exitbtn1.png");
	private ImageIcon exitBtnIcon2 = new ImageIcon("exitbtn2.png");
	private GameMain GameMain;
	private JButton playBtn = new JButton();
	private JButton helpBtn = new JButton();
	private JButton exitBtn = new JButton();

	public StartPanel(GameMain GameMain) {
		setLayout(null);
		setSize(500, 800);
		setVisible(true);
		this.GameMain = GameMain;

		/* �����ϱ� */
		playBtn.setBounds(30, 600, 200, 100);
		playBtn.setBorderPainted(false); // ���� �̹��� �׵θ� ���߱�
		playBtn.setContentAreaFilled(false); // �̹��� �ٲ� �� ���� ��� ���߱�
		playBtn.setFocusPainted(false); // ��ư ������ �� ��ư ���� ����� �׵θ� ���߱�
		playBtn.setIcon(playBtnIcon1);
		playBtn.setRolloverIcon(playBtnIcon2);
		playBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new game_frame();
				setVisible(false);
			}

		});

		/* �����ϱ� */
		exitBtn.setBounds(250, 600, 200, 100);
		exitBtn.setBorderPainted(false);
		exitBtn.setContentAreaFilled(false);
		exitBtn.setFocusPainted(false);
		exitBtn.setIcon(exitBtnIcon1);
		exitBtn.setRolloverIcon(exitBtnIcon2);
		exitBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new menu();
			}
		});

		add(playBtn);
		add(exitBtn);

	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(bgIcon.getImage(), 0, 0, null); // Approach 1:Dispaly image at at full size
		Dimension d = getSize(); // Approach 2:ȭ�鿡 �̹��� ������ ����.
		g.drawImage(bgIcon.getImage(), 0, 0, d.width, d.height, null);
		setOpaque(false); // �׸��� ǥ���ϰ� ����.�����ϰ� ����
		super.paintComponent(g);
	}
}