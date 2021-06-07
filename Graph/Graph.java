package Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Graph {
	
	Tree tree;

	Graph(int[][]arr,int size){
		
		this.tree = new Tree(size, arr);
		
	}

	
	// 큐를 사용한 너비 우선 탐색
	private void BFS() {
		Queue<Node> queue = new LinkedList<>();

		queue.add(tree.get_nodes(0));
		tree.get_nodes(0).visit();
		
		while(!queue.isEmpty()) {
			Node tmpnode = queue.poll();
			System.out.println(tmpnode.name());
			ArrayList<Node> tmp = tmpnode.get_nearby_node();
			
			
			for(int i = 0; i<tmp.size(); i++) {
				if(tmp.get(i).isvisited()==false) {
					queue.add(tmp.get(i));
					tmp.get(i).visit();
				}
			}
		}
	}
	
	//스택을 사용한 깊이 우선 탐색
	private void DFS() {
		Stack<Node> stack = new Stack<>();
		
		stack.add(tree.get_nodes(0));
		tree.get_nodes(0).visit();
		
		while(!stack.isEmpty()) {
			Node tmpnode = stack.pop();
			System.out.println(tmpnode.name());
			ArrayList<Node> tmp = tmpnode.get_nearby_node();
			
			for(int i = 0; i<tmp.size(); i++) {
				if(tmp.get(i).isvisited()==false) {
					stack.add(tmp.get(i));
					tmp.get(i).visit();
				}
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] arr = {{0,9},{1,2},{1,3},{2,3},{2,4},{3,6},{3,7},{2,5},{4,8},{5,9}};
		Graph g = new Graph(arr,10);		
		g.BFS();
		//g.DFS();
		
	}

}

// 노드
class Node {
	int name;
	ArrayList<Node> nearby; // 인접 노드를 저장하는 배열
	boolean marked; // 방문했는지를 알려주는 boolean

	Node(int name) {
		this.name =name;
		nearby = new ArrayList<Node>();
		marked = false; // 초기에는 방문하지 않았음
	}
	
	public int name() {
		return this.name;
	}

	// 인접 노드 추가
	public void add_nearby_node(Node N) {
		nearby.add(N);
	}

	// 인접 노드를 저장한 배열 반환
	public ArrayList<Node> get_nearby_node() {
		return nearby;
	}

	// 방문했는지 알려주는 boolean 반환
	public boolean isvisited() {
		return marked;
	}

	// Node 방문했음
	public void visit() {
		marked = true;
	}
}

class Tree {
	ArrayList<Node> nodes; // 총 노드를 저장하는 배열

	Tree(int size, int[][] arr) {
		nodes = new ArrayList<Node>();

		for (int i = 0; i < size; i++) {
			nodes.add(new Node(i));
		}

		for (int i = 0; i < arr.length; i++) {
			// 서로 연결된 각각의 노드를 인접 노드에 추가
			nodes.get(arr[i][0]).add_nearby_node(nodes.get(arr[i][1]));
			nodes.get(arr[i][1]).add_nearby_node(nodes.get(arr[i][0]));
		}
	}
	
	public Node get_nodes(int i) {
		return nodes.get(i);
	}
}
