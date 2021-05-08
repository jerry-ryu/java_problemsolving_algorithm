package Java_GUI_Sorting;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

import javax.swing.*;

public class sorting extends JFrame {

	private static final long serialVersionUID = 1L;
	Container c = getContentPane();
	JPanel[] panellist = new JPanel[230];
	int[] numlist = new int[230];

	sorting() {

		setSize(500, 600);

		c.setLayout(null);
		c.setBackground(Color.black);

		setButton();
		setMenu();
		setPanel();
		Random();

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	// 메뉴바 설정
	private void setMenu() {
		JMenuBar jb = new JMenuBar();
		JMenu m1, m2;
		JMenuItem t1, t2, t3;

		m1 = new JMenu("파일");
		t1 = new JMenuItem("새로만들기"); // 새창 열기
		t1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new sorting();
			}
		});
		t2 = new JMenuItem("랜덤"); // 숫자 다시 섞기
		t2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Random();
			}
		});
		

		m2 = new JMenu("도움말");
		t3 = new JMenuItem("실행 방법"); // 새로운 창으로 실행방법 소개
		t3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(c, "1~230 숫자들을 그래픽적으로 정렬해주는 프로그램입니다", "Info",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});

		jb.add(m1);
		jb.add(m2);
		m1.add(t1);
		m1.add(t2);
		m2.add(t3);

		setJMenuBar(jb);
	}

	private void setButton() {
		JPanel jp = new JPanel();
		jp.setPreferredSize(new Dimension(500, 60));
		jp.setBackground(Color.white);
		String str[] = { "선택 정렬", "삽입 정렬", "버블 정렬", "합병 정렬 ", "퀵 정렬", "힙 정렬" };
		JComboBox<String> com = new JComboBox<String>();
		for (int i = 0; i < str.length; i++) {
			com.addItem(str[i]);
		}
		com.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				String menu = com.getSelectedItem().toString();
				if (menu.equals("버블 정렬")) {

					BubbleSort();
				}
			}

		});
		JLabel label = new JLabel("숫자 정렬 방법: ");
		jp.add(label);
		jp.add(com);
		jp.setBounds(5, 5, 500, 60);
		c.add(jp);
	}

	private void setPanel() {
		for (int i = 1; i < numlist.length + 1; i++) {
			numlist[i - 1] = i;
		}
		for (int i = 0; i < panellist.length; i++) {
			JPanel tmp = new JPanel();
			tmp.setBackground(Color.white);
			panellist[i] = tmp;
			panellist[i].setBounds(10 + i * 2, 100, 1, numlist[i]);
			c.add(panellist[i]);
		}

	}

	private void BubbleSort() {

		try {
			new Thread() {
				public void run() {
					for (int i = panellist.length-1; i > 0; i--) {
						// 0 ~ (i-1)까지 반복
						for (int j = 0; j < i; j++) {
							panellist[j].setBackground(Color.red);
							panellist[j + 1].setBackground(Color.red);
							// j번째와 j+1번째의 요소가 크기 순이 아니면 교환
							if (panellist[j].getHeight() < panellist[j + 1].getHeight()) {
								int tmp = panellist[j].getHeight();
								panellist[j].setSize(1, panellist[j + 1].getHeight());
								panellist[j + 1].setSize(1, tmp);
							}
							c.add(panellist[j]);
							c.add(panellist[j + 1]);

							repaint();
							panellist[j].setBackground(Color.white);
							panellist[j + 1].setBackground(Color.white);
							repaint();
						}
					}
					for(int i = 0; i<panellist.length; i++) {
						panellist[i].setBackground(Color.green);
						c.add(panellist[i]);
						try {
							Thread.sleep(3);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						repaint();
					}
					for(int i = 0; i<panellist.length; i++) {
						panellist[i].setBackground(Color.white);
					}
					repaint();
				}
			}.start();
		} catch (Exception e) {

			e.printStackTrace();

		}

	}
	
	private void Random() {
		List<Integer> tmplist = new ArrayList<Integer>();
		for(int i:numlist) {
			tmplist.add(i);
		}
		
		Collections.shuffle(tmplist);
		
		for(int i=0; i<tmplist.size();i++) {
			numlist[i] = tmplist.get(i);
		}
		
		for(int i=0; i<panellist.length; i++) {
			panellist[i].setSize(1, numlist[i]);
		}
	}
	

	public static void main(String[] args) {
		new sorting();
	}
}
