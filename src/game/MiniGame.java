package game;
 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;
 
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
 
public class MiniGame extends JFrame implements ActionListener,Runnable{
JButton start = new JButton("Start"); //시작 버튼
JButton exit = new JButton("Exit"); //시작 버튼

JButton set[] = new JButton[12]; //두더지 나올 버튼
ImageIcon icon = new ImageIcon("Image.png"); //버튼에 나타날 두더지 사진
ImageIcon icon2 = new ImageIcon("null.png"); //null_ddu
JLabel score = new JLabel("Score : 0");
JLabel time = new JLabel("Time 0:30");
Container c = getContentPane();
JPanel PanelD = new JPanel(); //가운데에 set[] 버튼 놓을 패널
JPanel PanelSc = new JPanel(); //밑부분 왼쪽 점수레이블 넣을 패널
JPanel PanelSt = new JPanel(); //밑부분 오른쪽에 시작버튼 넣을 패널
int ran1 = 0;
int ran2 = 0;
int cnt = -1; //Score를 나타낼 변수
int n = 30; //초를 나타내는 변수

MiniGame(){
setTitle("두더지 잡기");
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
c.setLayout(new BorderLayout(10,10));
time.setFont(new Font("Arial",Font.BOLD,20));
score.setFont(new Font("Arial",Font.BOLD,20));

c.add(time,BorderLayout.NORTH);

c.add(PanelD,BorderLayout.CENTER);
PanelD.setLayout(new GridLayout(3,4));
for(int i=0;i<set.length; i++) {

set[i] = new JButton();
PanelD.add(set[i],BorderLayout.CENTER);
set[i].setIcon(icon2);
set[i].setBorderPainted(false);
set[i].setFocusPainted(false);
set[i].setBackground(Color.DARK_GRAY);

}
c.add(PanelSc,BorderLayout.SOUTH);
PanelSc.setLayout(new GridLayout(1,2));
PanelSc.add(score);

PanelSc.add(PanelSt);
PanelSt.setLayout(new FlowLayout(FlowLayout.RIGHT));
PanelSt.add(start);
PanelSt.add(exit);
 
exit.addMouseListener(new MouseAdapter(){
@Override
public void mouseClicked(MouseEvent e) {
new menu();
setVisible(false);
}
});


PanelD.setBackground(Color.DARK_GRAY);
PanelSt.setBackground(Color.LIGHT_GRAY);
PanelSc.setBackground(Color.LIGHT_GRAY);
c.setBackground(Color.LIGHT_GRAY);
 
Start();

setSize(500,500);
setVisible(true);
}
//시작 버튼 누르면 실행
public void Start() {
on();
start.addActionListener(this);
for(int i=0; i<set.length; i++)
set[i].addActionListener(this);
}

@Override
public void actionPerformed(ActionEvent e){
if(e.getSource() == start) {
Thread th = new Thread(this);
th.start();
random(0);
}
for(int i=0; i<set.length; i++) {
if(e.getSource() == set[i])
random(i);
}
}

private void random(int i) {
if (i != ran1 && i != ran2) return; //랜덤으로 나온 두더지 버튼을 클릭했는지 확인
cnt ++;

Timer tm = new Timer();
TimerTask m_task = new TimerTask() {
@Override
public void run() {
set[ran1].setIcon(icon2); //null
ran1 = (int)(Math.random() * set.length);
set[ran1].setIcon(icon);
}
};
tm.schedule(m_task, 1500);

set[ran2].setIcon(icon2); //null
ran2 = (int)(Math.random() * set.length);
set[ran2].setIcon(icon);

score.setText("Score : "+cnt*10);
}
 
//타이머
@Override
public void run() {
n = 30;
while(true) {
try{
Thread.sleep(1000); //1초
}catch(InterruptedException e) {};
n--;
if(n == 0) { //시간 초과 후 두더지 안나오게 하고 시간초 부분을 게임오버로 출력+버튼 클릭 못하게 하기
set[ran1].setIcon(icon2); //null
set[ran2].setIcon(icon2); //null
time.setText("Game Over !!");
off();
break;
}
time.setText("Time 0:"+n);
}
}

public void off() {
for(int i=0; i<set.length; i++)
set[i].setEnabled(false);
}

public void on() {
for(int i=0; i<set.length; i++)
set[i].setEnabled(true);
}
 
}