import java.security.PublicKey;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int size;
        size = s.nextInt();
        Graph2 g = new Graph2(size);
        while(size > 1){
            g.addEdge(s.nextInt()-1 ,s.nextInt()-1);
            size--;
        }
        g.longestPathLength();
    }
}

class Graph2
{
    private int V;
    private LinkedList<Integer>[] adj;
    public Graph2(int V)
    {
        this.V = V;
        adj = new LinkedList[V];
        for(int y = 0 ; y < V ; y++)
            adj[y] = new LinkedList<>();
    }
    public final void addEdge(int v, int w)
    {
        adj[v].add(w); // Add w to vâ€™s list.
        adj[w].add(v); // Since the graph is undirected
    }

    public final void longestPathLength()
    {
        Pair t1 ;
        Pair t2 ;
        t1 = bfs(0);
        t2 = bfs(t1.getKey());
        System.out.print(t2.getValue());
    }

    //  method returns farthest node and its distance from node u
    public  Pair bfs(int u)
    {
        //  mark all distance with -1
        int[] dis = new int[V];

        for(int x = 0 ; x < V ; x++){
            dis[x] = -1;
        }

        LinkedList <Integer> q = new LinkedList();
        q.addLast(u);

        dis[u] = 0;

        while (!q.isEmpty())
        {
            int t = q.poll();
            int p =0;
            while(p<adj[t].size())
            {
                int v =adj[t].get(p);
                if (dis[v] == -1) {
                    q.addFirst(v);
                    dis[v] = dis[t] + 1;
                }
                p++;
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
        return new Pair(nodeIdx, maxDis);
    }
    // node from u with its distance
}

class Pair{
    int key;
    int value;
    public Pair(int key , int value){
        this.key=key;
        this.value=value;
    }

    public int getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }
}