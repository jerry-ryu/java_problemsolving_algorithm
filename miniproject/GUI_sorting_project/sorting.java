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

	// 콤보박스 버튼 설정
	private void setButton() {
		JPanel jp = new JPanel();
		jp.setPreferredSize(new Dimension(500, 60));
		jp.setBackground(Color.white);
		String str[] = { "선택 정렬", "삽입 정렬", "버블 정렬", "합병 정렬", "퀵 정렬", "힙 정렬" };
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
				} else if (menu.equals("선택 정렬")) {

					SelectionSort();
				} else if (menu.equals("삽입 정렬")) {

					InsertionSort();
				} else if (menu.equals("합병 정렬")) {
					int[] tmp = new int[panellist.length];

					MergeSort(0, panellist.length - 1, tmp);
				} else if(menu.equals("퀵 정렬")){
					Quicksort();
				}

			}

		});
		JLabel label = new JLabel("숫자 정렬 방법: ");
		jp.add(label);
		jp.add(com);
		jp.setBounds(5, 5, 500, 60);
		c.add(jp);
	}

	// 정렬될 패널 설정
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

	// 버블정렬
	private void BubbleSort() {

		try {
			new Thread() {
				public void run() {
					for (int i = panellist.length - 1; i > 0; i--) {
						// 0 ~ (i-1)까지 반복
						for (int j = 0; j < i; j++) {
							panellist[j].setBackground(Color.red);
							panellist[j + 1].setBackground(Color.red);
							// j번째와 j+1번째의 요소가 크기 순이 아니면 교환
							if (panellist[j].getHeight() > panellist[j + 1].getHeight()) {
								int tmp = panellist[j].getHeight();
								panellist[j].setSize(1, panellist[j + 1].getHeight());
								panellist[j + 1].setSize(1, tmp);
							}
							repaint();

							try {
								Thread.sleep(1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							panellist[j].setBackground(Color.white);
							panellist[j + 1].setBackground(Color.white);
							repaint();
						}
					}
					for (int i = 0; i < panellist.length; i++) {
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
					for (int i = 0; i < panellist.length; i++) {
						panellist[i].setBackground(Color.white);
					}
					repaint();
				}
			}.start();
		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	// 선택정렬
	private void SelectionSort() {

		try {
			new Thread() {
				public void run() {
					int indexMin;
					for (int i = 0; i < panellist.length - 1; i++) {
						indexMin = i;
						panellist[i].setBackground(Color.red);
						for (int j = i + 1; j < panellist.length; j++) {
							panellist[j].setBackground(Color.red);
							repaint();
							if (panellist[j].getHeight() < panellist[indexMin].getHeight()) {
								panellist[indexMin].setBackground(Color.white);
								indexMin = j;
								repaint();
							} else {
								panellist[j].setBackground(Color.white);
								repaint();
							}

							try {
								Thread.sleep(1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						int tmp = panellist[i].getHeight();
						panellist[i].setSize(1, panellist[indexMin].getHeight());
						panellist[indexMin].setSize(1, tmp);

						panellist[i].setBackground(Color.white);
						panellist[indexMin].setBackground(Color.white);
						repaint();
						try {
							Thread.sleep(1);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					for (int i = 0; i < panellist.length; i++) {
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
					for (int i = 0; i < panellist.length; i++) {
						panellist[i].setBackground(Color.white);
					}
					repaint();
				}
			}.start();
		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	// 삽입정렬
	private void InsertionSort() {

		try {
			new Thread() {
				public void run() {
					for (int i = 1; i < panellist.length; i++) {
						int j = i - 1;
						int present = i;
						while (j >= 0 && panellist[j].getHeight() > panellist[present].getHeight()) {
							panellist[present].setBackground(Color.red);
							panellist[j].setBackground(Color.red);
							repaint();
							try {
								Thread.sleep(1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							int tmp = panellist[present].getHeight();
							panellist[present].setSize(1, panellist[j].getHeight());
							panellist[j].setSize(1, tmp);
							panellist[present].setBackground(Color.white);
							panellist[j].setBackground(Color.white);
							repaint();
							present = j;
							j--;
						}
						repaint();
					}
					for (int i = 0; i < panellist.length; i++) {
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
					for (int i = 0; i < panellist.length; i++) {
						panellist[i].setBackground(Color.white);
					}
					repaint();
				}
			}.start();
		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	// 합병정렬-1 (쓰레드 생성 및 정렬 확인)
	private void MergeSort(int start, int end, int[] tmp) {
		try {
			new Thread() {
				public void run() {
					Merge(start, end, tmp);
					for (int i = 0; i < panellist.length; i++) {
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
					for (int i = 0; i < panellist.length; i++) {
						panellist[i].setBackground(Color.white);
					}
					repaint();
					
				}

			}.start();
		} catch (Exception e) {

			e.printStackTrace();

		}

	}
    
	//합병정렬-2 (숫자 정렬)
	private void Merge(int start, int end, int[] tmp) {
		if (start < end) {
			int mid = (start + end) / 2;
			Merge(start, mid, tmp);
			Merge(mid + 1, end, tmp);

			int p = start;
			int q = mid + 1;
			int idx = p;

			while (p <= mid || q <= end) {
				if (q > end || (p <= mid && panellist[p].getHeight() < panellist[q].getHeight())) {
					tmp[idx++] = panellist[p++].getHeight();
				} else {
					tmp[idx++] = panellist[q++].getHeight();
				}
			}
			for (int i = start; i <= end; i++) {
				panellist[i].setSize(1, tmp[i]);
			}
			for (int i = start; i <= end; i++) {
				panellist[i].setBackground(Color.red);
				repaint();
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			for (int i = start; i <= end; i++) {
				panellist[i].setBackground(Color.white);
				repaint();
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	//퀵정렬-1 (쓰레드 생성 및 정렬 확인)
	private void Quicksort() {
		try {
			new Thread() {
				public void run() {
					quick(0, panellist.length-1);
					for (int i = 0; i < panellist.length; i++) {
						panellist[i].setBackground(Color.green);
						try {
							Thread.sleep(3);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						repaint();
					}
					for (int i = 0; i < panellist.length; i++) {
						panellist[i].setBackground(Color.white);
					}
					repaint();
					
				}

			}.start();
		} catch (Exception e) {

			e.printStackTrace();

		}

	}
	
	//퀵정렬-2 (숫자 정렬)
	private void quick(int low, int high) {
		if(low>=high) return;
		
		int mid = partition(low,high);
		quick(low, mid-1);
		quick(mid, high);
	
	}
	
	//퀵정렬-3 (pivot 구하기)
	private int partition(int low, int high) {
		
		int pivot = panellist[(low+high)/2].getHeight();
		panellist[(low+high)/2].setBackground(Color.blue);
		while(low<=high) {
			while(panellist[low].getHeight() < pivot) low++;
			while(panellist[high].getHeight() > pivot) high--;
			
			if(low<=high) {
				swap(low, high);
				low++;
				high--;
			}
		}
		return low;
	}

	//힙정렬
	
	// 랜덤
	private void Random() {
		List<Integer> tmplist = new ArrayList<Integer>();
		for (int i : numlist) {
			tmplist.add(i);
		}

		Collections.shuffle(tmplist);

		for (int i = 0; i < tmplist.size(); i++) {
			numlist[i] = tmplist.get(i);
		}

		for (int i = 0; i < panellist.length; i++) {
			panellist[i].setSize(1, numlist[i]);
		}
	}

	//swap
	private void swap(int a, int b) {
		panellist[a].setBackground(Color.red);
		panellist[b].setBackground(Color.red);
		repaint();
		int tmp = panellist[a].getHeight();
		panellist[a].setSize(1, panellist[b].getHeight());
		panellist[b].setSize(1, tmp);
		
		try {
			Thread.sleep(3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		panellist[a].setBackground(Color.white);
		panellist[b].setBackground(Color.white);
		
	}
	
	// main
	public static void main(String[] args) {
		new sorting();
	}
}
