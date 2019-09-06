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
		// Game 진행 초기화
		baseballRandNum = new int[3];
		baseballAnswer = new int[3];
		count = 0;
		ballcount = 0;
		strikecount = 0;
		rand = new Random();

		// title
		setTitle("숫자야구게임");

		// 버튼 초기화
		bt_new = new JButton("새게임");
		bt_clear = new JButton("지우기");
		bt_answer = new JButton("정답");
		bt_exit = new JButton("나가기");

		// textArea초기화 + 스크롤 붙이기
		ta = new JTextArea();
		ta.setText("\n\n\n\n\t\t<숫자야구게임>");
	     ta.append("\n\t    1. 게임시작을 위해서 새게임버튼을 클릭");
	     ta.append("\n\t    2. 입력에 중복되지않는 세자리 수를 입력후 엔터");
	     ta.append("\n\t    3. 숫자와 자릿수를 맞추면 Strike");
	     ta.append("\n\t       숫자만 맞추면 Ball이 추가됩니다.");
	     ta.append("\n\t    4. 정답을 보고 싶으면 정답버튼을 클릭");
		ta.setEditable(false);// textArea에 직접입력 불가

		scroll_bar = new JScrollPane(ta);
		scroll_bar.setBorder(new BevelBorder(BevelBorder.LOWERED));

		// textField초기화 + 라벨 초기화
		tf = new JTextField(40);
		tf.setEditable(false);// 최초에 입력 불가
		//tf setActionCommand
		//tf.setActionCommand("텍스트필드");

		lb = new JLabel("입력: ");

		// 레이아웃 초기화
		pnl_text = new JPanel();
		pnl_text_area = new JPanel();
		pnl_text_field = new JPanel();

		pnl_button = new JPanel();

		// 화면구성
		// 좌측화면 구성
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

		// 우측화면 구성
		pnl_button.setLayout(new GridLayout(7, 1, 40, 40));
		pnl_button.add(new JLabel());
		pnl_button.add(bt_new);
		pnl_button.add(bt_clear);
		pnl_button.add(bt_answer);
		pnl_button.add(bt_exit);
		pnl_button.setBackground(Color.orange);

		// 전체화면 구성
		setLayout(new BorderLayout());
		add("Center", pnl_text);
		add("East", pnl_button);

		// 화면 보이기
		setBounds(300, 300, 600, 500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// 연결자, 최초 상태 호출
		eventUp();
	}

	private void eventUp() {
		// 연결자(감시자)
		bt_new.addActionListener(this);
		bt_clear.addActionListener(this);
		bt_answer.addActionListener(this);
		bt_exit.addActionListener(this);
		
		tf.addActionListener(this);
		
		// 최초 상태
		bt_clear.setEnabled(false);
		bt_answer.setEnabled(false);
		
		tf.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!tf.isEditable())
					showDialog("새 게임을 눌러주세요");
			}
		});
		
		if(tf.getText().equals(""))
			bt_clear.setEnabled(false);
	}//eventUp end
	
	private void showDialog(String msg){//메시지 출력
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
		if (obj == bt_new) {//새게임 버튼 클릭
			ta.setText("게임이 시작되었습니다.\n");
			tf.setText("");
			tf.setEditable(true);
			bt_clear.setEnabled(true);
			bt_answer.setEnabled(true);
			tf.requestFocus();
			createNum();
		} else if (obj == bt_clear) {//지우기 버튼 클릭
			ta.setText("");
			bt_clear.setEnabled(false);
		} else if (obj == bt_answer) {//정답 버튼 클릭
			String answermsg="정답은 "+baseballRandNum[0]+baseballRandNum[1]+baseballRandNum[2]+"입니다!";
			ta.append(answermsg+"\n");
			showDialog(answermsg);
			bt_answer.setEnabled(false);// 정답출력후 정답 버튼 비활성화
			bt_clear.setEnabled(true);// 정답출력후 지우기 버튼 활성화
			tf.setText("");//TextField 비우기
			tf.setEditable(false);
			return;
		} else if (obj == bt_exit) {//종료 버튼 클릭
			new menu();
			setVisible(false);
		} // JButton 동작
	}//customButtonAction end

	private void createNum(){
		count = 0;// 정답입력 횟수 초기화
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
		if (obj == tf) {//텍스트 필드에 입력시

			String answerStr = tf.getText();
			if(!answerStr.matches("[1-9]{3}")){
				JOptionPane.showMessageDialog(this, "0과 문자를 제외한 세자리숫자를 입력해주세요");
				tf.setText("");
				return;
			} // 세자리 숫자 제외의 입력 방지

			for (int i = 0; i < baseballAnswer.length; i++) {
				baseballAnswer[i] = answerStr.charAt(i)-48;
			}//입력 숫자 String ---> int 변환
			
			if(duplicateNum()) //중복된 숫자 발견시 리턴
				return;

			continueGame();//게임 진행
		} // textField 동작
	}//compareNum end
	
	private boolean duplicateNum(){
		for (int i = 0; i < baseballAnswer.length - 1; i++) {
			for (int j = i + 1; j < baseballAnswer.length; j++) {
				if (baseballAnswer[i] == baseballAnswer[j]) {
					JOptionPane.showMessageDialog(this, "중복되지 않는 숫자를 입력해주세요");
					tf.setText("");
					return true;
				}
			}
		} // 입력 세자리수 중복 방지
		return false;
	}//duplicateNum end
	
	private void continueGame(){
		ballcount = 0;// 볼 횟수 매번 초기화
		strikecount = 0;// 스트라이크 횟수 매번 초기화
		count++;//숫자입력후 게임진행횟수 증가
		bt_clear.setEnabled(true);//숫자 입력후 지우기 버튼 활성화
		
		for (int i = 0; i < baseballRandNum.length; i++) {
			for (int j = 0; j < baseballAnswer.length; j++) {
				if (baseballRandNum[i] == baseballAnswer[j]) {
					if (i == j)
						strikecount++;
					else
						ballcount++;
				}
			}
		} // 스트라이크 볼 측정

		if (strikecount == 3) {
			//메시지 출력, 팝업
			String msg="축하합니다~!!^^\n"+baseballAnswer[0]+baseballAnswer[1]+ baseballAnswer[2]+"는 정답입니다!";
			ta.append(msg+"\n");
			showDialog(msg);
			// 게임종료후 버튼, 텍스트 필드 비활성화
			tf.setEditable(false);
			bt_answer.setEnabled(false);
			tf.setText("");// textField 비우기
			return;
		}else{
			ta.append(count + "회 : " + baseballAnswer[0] + baseballAnswer[1] + baseballAnswer[2]);
			ta.append(" : " + ballcount + "볼 " + strikecount + "스트라이크\n");
		}

		tf.setText("");// textField 비우기
	}

}