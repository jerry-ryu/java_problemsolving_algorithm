package ProgrammersBj.Bj_Virus;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class MySolution_useBFS {

	public static void main(String[] args) throws Exception {
		int count = 0; // 총 컴퓨터 수
		int connect = 0; // 직접 연결된 컴퓨터 쌍 개수

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String tmp = br.readLine();
		count = Integer.parseInt(tmp);

		tmp = br.readLine();
		connect = Integer.parseInt(tmp);

		// 직접연결된 컴퓨터 번호쌍을 저장한 이차원 배열
		int[][] connection = new int[connect][2];

		for (int i = 0; i < connect; i++) {
			tmp = br.readLine();
			String[] limit = tmp.split(" ");

			int a = Integer.parseInt(limit[0]) - 1;// 0번 부터 시작;
			int b = Integer.parseInt(limit[1]) - 1;
			connection[i][0] = a;
			connection[i][1] = b;
		}

		Tree T = new Tree(count);
		for (int i = 0; i < connect; i++) {
			T.addNodes(connection[i]);
		}

		BFS bfs = new BFS();
		System.out.println(bfs.bfs(T,T.graph[0]));
	}
}

class BFS {
	public int bfs(Tree T, Node N) {
		Queue<Node> queue = new LinkedList<>();
		int answer = 0;

		queue.add(T.graph[0]);
		T.graph[0].visit();
		while (!queue.isEmpty()) {
			Node tmpnode = queue.poll();
			answer++;
			ArrayList<Node> tmp = tmpnode.children;
			
			for(int i = 0; i<tmp.size(); i++) {
				if(tmp.get(i).visited()==false) {
					queue.add(tmp.get(i));
					tmp.get(i).visit();
				}
			}
		}
		
		
		
		return answer-1;
	}
}

class Tree {
	public Node[] graph;

	Tree(int count) {
		graph = new Node[count];
		for (int i = 0; i < count; i++) {
			graph[i] = new Node();
		}
	}

	public void addNodes(int[] tmp) {
		graph[tmp[0]].addchild(graph[tmp[1]]);
		graph[tmp[1]].addchild(graph[tmp[0]]);
	}
}

class Node {
	boolean visited;
	ArrayList<Node> children = new ArrayList<Node>();

	public void addchild(Node N) {
		children.add(N);
	}

	public boolean visited() {
		return visited;
	}

	public void visit() {
		visited = true;
	}
}