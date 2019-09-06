package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Popup;
import javax.swing.border.BevelBorder;

public class BaseBallGame extends JFrame implements ActionListener {
	JButton bt_new, bt_clear, bt_answer, bt_exit;
	JTextArea ta;
	JTextField tf;
	JLabel lb;
	JScrollPane scroll_bar;
	JPanel pnl_text, pnl_text_area, pnl_text_field;
	JPanel pnl_button;

	int[] baseballRandNum;
	int[] baseballAnswer;
	int count, ballcount, strikecount;
	Random rand;

	public BaseBallGame() {
		// Game ���� �ʱ�ȭ
		baseballRandNum = new int[3];
		baseballAnswer = new int[3];
		count = 0;
		ballcount = 0;
		strikecount = 0;
		rand = new Random();

		// title
		setTitle("���ھ߱�����");

		// ��ư �ʱ�ȭ
		bt_new = new JButton("������");
		bt_clear = new JButton("�����");
		bt_answer = new JButton("����");
		bt_exit = new JButton("������");

		// textArea�ʱ�ȭ + ��ũ�� ���̱�
		ta = new JTextArea();
		ta.setText("\n\n\n\n\t\t<���ھ߱�����>");
	     ta.append("\n\t    1. ���ӽ����� ���ؼ� �����ӹ�ư�� Ŭ��");
	     ta.append("\n\t    2. �Է¿� �ߺ������ʴ� ���ڸ� ���� �Է��� ����");
	     ta.append("\n\t    3. ���ڿ� �ڸ����� ���߸� Strike");
	     ta.append("\n\t       ���ڸ� ���߸� Ball�� �߰��˴ϴ�.");
	     ta.append("\n\t    4. ������ ���� ������ �����ư�� Ŭ��");
		ta.setEditable(false);// textArea�� �����Է� �Ұ�

		scroll_bar = new JScrollPane(ta);
		scroll_bar.setBorder(new BevelBorder(BevelBorder.LOWERED));

		// textField�ʱ�ȭ + �� �ʱ�ȭ
		tf = new JTextField(40);
		tf.setEditable(false);// ���ʿ� �Է� �Ұ�
		//tf setActionCommand
		//tf.setActionCommand("�ؽ�Ʈ�ʵ�");

		lb = new JLabel("�Է�: ");

		// ���̾ƿ� �ʱ�ȭ
		pnl_text = new JPanel();
		pnl_text_area = new JPanel();
		pnl_text_field = new JPanel();

		pnl_button = new JPanel();

		// ȭ�鱸��
		// ����ȭ�� ����
		pnl_text_field.setLayout(new FlowLayout());
		pnl_text_field.add(lb);
		pnl_text_field.add(tf);
		pnl_text_field.setBackground(Color.orange);

		pnl_text_area.setLayout(new BorderLayout(10, 10));
		pnl_text_area.add("North", new JLabel());
		pnl_text_area.add("South", new JLabel());
		pnl_text_area.add("East", new JLabel());
		pnl_text_area.add("West", new JLabel());
		pnl_text_area.add("Center", scroll_bar);
		pnl_text_area.setBackground(Color.orange);

		pnl_text.setLayout(new BorderLayout(10, 10));
		pnl_text.add("Center", pnl_text_area);
		pnl_text.add("South", pnl_text_field);
		pnl_text.setBackground(Color.orange);

		// ����ȭ�� ����
		pnl_button.setLayout(new GridLayout(7, 1, 40, 40));
		pnl_button.add(new JLabel());
		pnl_button.add(bt_new);
		pnl_button.add(bt_clear);
		pnl_button.add(bt_answer);
		pnl_button.add(bt_exit);
		pnl_button.setBackground(Color.orange);

		// ��üȭ�� ����
		setLayout(new BorderLayout());
		add("Center", pnl_text);
		add("East", pnl_button);

		// ȭ�� ���̱�
		setBounds(300, 300, 600, 500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// ������, ���� ���� ȣ��
		eventUp();
	}

	private void eventUp() {
		// ������(������)
		bt_new.addActionListener(this);
		bt_clear.addActionListener(this);
		bt_answer.addActionListener(this);
		bt_exit.addActionListener(this);
		
		tf.addActionListener(this);
		
		// ���� ����
		bt_clear.setEnabled(false);
		bt_answer.setEnabled(false);
		
		tf.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!tf.isEditable())
					showDialog("�� ������ �����ּ���");
			}
		});
		
		if(tf.getText().equals(""))
			bt_clear.setEnabled(false);
	}//eventUp end
	
	private void showDialog(String msg){//�޽��� ���
		JOptionPane.showMessageDialog(this, msg);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj instanceof JButton)
			clickButton(obj);
		else
			compareNum(obj);
	}//handler end
	
	public void initText(){
		ta.setText("");
	}
	
	private void clickButton(Object obj) {
		if (obj == bt_new) {//������ ��ư Ŭ��
			ta.setText("������ ���۵Ǿ����ϴ�.\n");
			tf.setText("");
			tf.setEditable(true);
			bt_clear.setEnabled(true);
			bt_answer.setEnabled(true);
			tf.requestFocus();
			createNum();
		} else if (obj == bt_clear) {//����� ��ư Ŭ��
			ta.setText("");
			bt_clear.setEnabled(false);
		} else if (obj == bt_answer) {//���� ��ư Ŭ��
			String answermsg="������ "+baseballRandNum[0]+baseballRandNum[1]+baseballRandNum[2]+"�Դϴ�!";
			ta.append(answermsg+"\n");
			showDialog(answermsg);
			bt_answer.setEnabled(false);// ��������� ���� ��ư ��Ȱ��ȭ
			bt_clear.setEnabled(true);// ��������� ����� ��ư Ȱ��ȭ
			tf.setText("");//TextField ����
			tf.setEditable(false);
			return;
		} else if (obj == bt_exit) {//���� ��ư Ŭ��
			new menu();
			setVisible(false);
		} // JButton ����
	}//customButtonAction end

	private void createNum(){
		count = 0;// �����Է� Ƚ�� �ʱ�ȭ
		for (int i = 0; i < baseballRandNum.length; i++) {
			int randNum = rand.nextInt(9)+1;
			if (i == 1 && randNum == baseballRandNum[i - 1]
				|| i == 2 && randNum == baseballRandNum[i - 1]
				|| i == 2 && randNum == baseballRandNum[i - 2]) {
				--i;
				continue;
			}
			baseballRandNum[i] = randNum;
		}
	}//createRandNum end
	
	private void compareNum(Object obj) {
		if (obj == tf) {//�ؽ�Ʈ �ʵ忡 �Է½�

			String answerStr = tf.getText();
			if(!answerStr.matches("[1-9]{3}")){
				JOptionPane.showMessageDialog(this, "0�� ���ڸ� ������ ���ڸ����ڸ� �Է����ּ���");
				tf.setText("");
				return;
			} // ���ڸ� ���� ������ �Է� ����

			for (int i = 0; i < baseballAnswer.length; i++) {
				baseballAnswer[i] = answerStr.charAt(i)-48;
			}//�Է� ���� String ---> int ��ȯ
			
			if(duplicateNum()) //�ߺ��� ���� �߽߰� ����
				return;

			continueGame();//���� ����
		} // textField ����
	}//compareNum end
	
	private boolean duplicateNum(){
		for (int i = 0; i < baseballAnswer.length - 1; i++) {
			for (int j = i + 1; j < baseballAnswer.length; j++) {
				if (baseballAnswer[i] == baseballAnswer[j]) {
					JOptionPane.showMessageDialog(this, "�ߺ����� �ʴ� ���ڸ� �Է����ּ���");
					tf.setText("");
					return true;
				}
			}
		} // �Է� ���ڸ��� �ߺ� ����
		return false;
	}//duplicateNum end
	
	private void continueGame(){
		ballcount = 0;// �� Ƚ�� �Ź� �ʱ�ȭ
		strikecount = 0;// ��Ʈ����ũ Ƚ�� �Ź� �ʱ�ȭ
		count++;//�����Է��� ��������Ƚ�� ����
		bt_clear.setEnabled(true);//���� �Է��� ����� ��ư Ȱ��ȭ
		
		for (int i = 0; i < baseballRandNum.length; i++) {
			for (int j = 0; j < baseballAnswer.length; j++) {
				if (baseballRandNum[i] == baseballAnswer[j]) {
					if (i == j)
						strikecount++;
					else
						ballcount++;
				}
			}
		} // ��Ʈ����ũ �� ����

		if (strikecount == 3) {
			//�޽��� ���, �˾�
			String msg="�����մϴ�~!!^^\n"+baseballAnswer[0]+baseballAnswer[1]+ baseballAnswer[2]+"�� �����Դϴ�!";
			ta.append(msg+"\n");
			showDialog(msg);
			// ���������� ��ư, �ؽ�Ʈ �ʵ� ��Ȱ��ȭ
			tf.setEditable(false);
			bt_answer.setEnabled(false);
			tf.setText("");// textField ����
			return;
		}else{
			ta.append(count + "ȸ : " + baseballAnswer[0] + baseballAnswer[1] + baseballAnswer[2]);
			ta.append(" : " + ballcount + "�� " + strikecount + "��Ʈ����ũ\n");
		}

		tf.setText("");// textField ����
	}

}