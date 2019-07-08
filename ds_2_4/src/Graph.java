import javafx.util.Pair;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class Graph
{
	private int V;
//	private LinkedList[] adj;
	private int[][] adj;
	public Graph(int V)
	{
		this.V = V;
//		adj = Tangible.Arrays.initializeWithDefaultlistInstances(V);
//		adj = new LinkedList[V];
		adj = new int[V][V];
	}
	public final void addEdge(int v, int w)
	{
//		adj[v].add(w); // Add w to v’s list.
//		adj[w].add(v); // Since the graph is undirected
		adj[v][w]=1;
		adj[w][v]=1;
	}

	public final void longestPathLength()
	{
		Pair<Integer, Integer> t1 ;
		Pair<Integer, Integer> t2 ;
//C++ TO JAVA CONVERTER TODO TASK: The following line was determined to be a copy assignment (rather than a reference assignment) - this should be verified and a 'copyFrom' method should be created:
//ORIGINAL LINE: t1 = bfs(0);
		t1 = bfs(0);
		t2 = bfs(t1.getKey());

		System.out.print("Longest path is from ");
		System.out.print(t1.getKey());
		System.out.print(" to ");
		System.out.print(t2.getKey());
		System.out.print(" of length ");
		System.out.print(t2.getValue());
	}

	//  method returns farthest node and its distance from node u 
	public  Pair<Integer, Integer> bfs(int u)
	{
		//  mark all distance with -1
		int[] dis = new int[V];
//C++ TO JAVA CONVERTER TODO TASK: The memory management function 'memset' has no equivalent in Java:
//		memset(dis, -1, (Integer.SIZE / Byte.SIZE));

		Queue q = new Queue(V*V);
		q.enQueue(u);

		dis[u] = 0;

		while (!q.isEmpty())
		{
			int t = q.deQueue();

//			Iterator it = adj[t].listIterator();
//			while(it.hasnext)
			for (int itr = 0 ; itr < V ; itr++ ) {
				if (adj[t][itr] == 1) {
					int v = adj[t][itr];
//				int v =(int) it.next();

					if (dis[v] == -1) {
						q.enQueue(v);
						dis[v] = dis[t] + 1;
					}
				}
			}
		}

		int maxDis = 0;
		int nodeIdx = 0;

		//  get farthest node distance and its index
		for (int i = 0; i < V; i++)
		{
			if (dis[i] > maxDis)
			{
				maxDis = dis[i];
				nodeIdx = i;
			}
		}
		return new Pair<>(nodeIdx, maxDis);
	}
							   // node from u with its distance 
}

class Queue {
	int[] queue;
	int rear , front;
	int size;

	public Queue(int size){
		front = rear = -1;
		this.size = size;
		queue = new int[size];
	}
	public void enQueue(int value){
		if ((front == 0 && rear == size-1) ||
				(rear == (front-1)%(size-1)))
		{
			return;
		}

		else if (front == -1) /* Insert First Element */
		{
			front = rear = 0;
			queue[rear] = value;
		}

		else if (rear == size-1 && front != 0)
		{
			rear = 0;
			queue[rear] = value;
		}

		else
		{
			rear++;
			queue[rear] = value;
		}
	}

	public int deQueue()
	{
		if (front == -1)
		{
			return Integer.MIN_VALUE;
		}
		int data = queue[front];
		queue[front] = -1;
		if (front == rear)
		{
			front = -1;
			rear = -1;
		}
		else if (front == size-1)
			front = 0;
		else
			front++;

		return data;
	}

	public int firstElement()
	{
		if (front == -1)
		{
			return Integer.MIN_VALUE;
		}
		int data = queue[front];
		queue[front] = -1;
		return data;
	}
	public boolean isEmpty(){
		return front==-1;
	}
}