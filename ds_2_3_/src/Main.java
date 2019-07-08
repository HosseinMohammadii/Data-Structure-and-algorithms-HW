import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    private static int[][] matrix;
    private static int[][] mainMatrix;
    private static int[][] fireTime;
    private static int[][] fireTime2 = new int[105][105];
    private static Graph graph;
    private static Scanner s = new Scanner(System.in);
    private static int row_size ;
    private static int column_size ;
    private static int k ;
    private static int t_loc_x;
    private static int t_loc_y;
    private static int s_loc_x;
    private static int s_loc_y;
    private static int time = 0;
    public static int[] fx = {1, -1, 0, 0, 1, 1, -1, -1};
    public static int[] fy = {0, 0, 1, -1, 1, -1, 1, -1};
    public static int[] px = {1, -1, 0, 0};
    public static int[] py = {0, 0, 1, -1};
    private static boolean[][] vis = new boolean[105][105];
    private static LinkedList<Node> Q = new LinkedList<Node>();

    public static void main(String[] args) {
        row_size = s.nextInt();
        column_size = s.nextInt();
        k = s.nextInt();
        matrix = new int[row_size][column_size];
        mainMatrix = new int[row_size][column_size];
        fireTime = new int[row_size][column_size];
        String ss = s.nextLine();
        for(int i =0 ; i <105 ; i++) {
            for (int j = 0 ; j < 105 ; j++){
                vis[i][j] = false;
            }
        }
        for(int i = 0; i < row_size ; i++){
            ss = s.nextLine();
            for(int j = 0 ; j< column_size ; j++){
                if(ss.charAt(j)=='-'){
                    matrix[i][j]=0;
                    mainMatrix[i][j]=0;
                    fireTime[i][j]=-1;
                }
                else if(ss.charAt(j)=='f'){
                    matrix[i][j]=11;
                    mainMatrix[i][j]=11;
                    Q.addLast(new Node(i,j,0));
                    fireTime2[i][j]=0;
                    vis[i][j]=true;
                }
                else if(ss.charAt(j)=='t'){
                    matrix[i][j]=0;
                    mainMatrix[i][j]=0;
                    t_loc_x=i;
                    t_loc_y=j;
                    fireTime[i][j]=-1;
                }
                else if(ss.charAt(j)=='s') {
                    matrix[i][j] = 0;
                    mainMatrix[i][j]=0;
                    s_loc_x = i;
                    s_loc_y = j;
                    fireTime[i][j]=-1;
                }
            }
        }
        int max = Math.max(row_size,column_size);
        int x_dif = Math.abs((t_loc_x-s_loc_x));
        int y_dif = Math.abs((t_loc_y-s_loc_y));
        time = 0;
        while (time<=max){
            time=time+k;
            updateBurn();
        }
//        if(fireTime[t_loc_x][t_loc_y] <= x_dif+y_dif){
//            System.out.print("Impossible");
//            return;}
        bfs_f();
        graph = new Graph(row_size*column_size);
        initGraph();
        int destination=t_loc_x*column_size+t_loc_y;
        int source=s_loc_x*column_size+s_loc_y;
        LinkedList<Integer> list;
        int length;
        stt:
        do{
            time = -1;
            list = graph.shortestDistance(source,destination);
            length = list.size();
            if(length==0){
                System.out.println("Impossible");
                return;
            }

            for(int u = length; u>0 ; u--){
                time++;
                int node = list.pollLast();
                int i =node/column_size;
                int j = node%column_size;
                if(fireTime2[i][j]<=time){
                    deleteEdge(i,j);
                    continue stt;
                }
            }
            System.out.println(time);
            return;
        }
        while (true);

    }

//    public static void main(String[] args) {
//        row_size = s.nextInt();
//        column_size = s.nextInt();
//        k = s.nextInt();
//        matrix = new int[row_size][column_size];
//        mainMatrix = new int[row_size][column_size];
//        fireTime = new int[row_size][column_size];
//        String ss = s.nextLine();
//        for(int i = 0; i < row_size ; i++){
//            ss = s.nextLine();
//            for(int j = 0 ; j< column_size ; j++){
//                if(ss.charAt(j)=='-'){
//                    matrix[i][j]=0;
//                    mainMatrix[i][j]=0;
//                    fireTime[i][j]=-1;
//                }
//                else if(ss.charAt(j)=='f'){
//                    matrix[i][j]=11;
//                    mainMatrix[i][j]=11;
//                }
//                else if(ss.charAt(j)=='t'){
//                    matrix[i][j]=0;
//                    mainMatrix[i][j]=0;
//                    t_loc_x=i;
//                    t_loc_y=j;
//                    fireTime[i][j]=-1;
//                }
//                else if(ss.charAt(j)=='s') {
//                    matrix[i][j] = 0;
//                    mainMatrix[i][j]=0;
//                    s_loc_x = i;
//                    s_loc_y = j;
//                    fireTime[i][j]=-1;
//                }
//            }
//        }

//    }
    private static void updateBurn(){
        if(time % k == 0){
            for(int i = 0; i < row_size ; i++){
                for(int j = 0 ; j < column_size ; j++){
                    if(matrix[i][j]==11) {
                        burnAround(i,j);
                    }
                }
            }
            for(int i = 0; i < row_size ; i++){
                for(int j = 0 ; j < column_size ; j++){
                    if(matrix[i][j]==10) {
                        matrix[i][j]=11;
                    }
                }
            }
//            display();
//            display2();
        }

    }

    private static void burnAround(int i , int j){
        matrix[i][j] = 12;
        for(int u = -1 ; u < 2 ; u++){
            for(int v = -1 ; v < 2 ; v++){
                if(i+u > -1 && i+u < row_size){
                    if(j+v > -1 && j+v < column_size){
                        if(matrix[i+u][j+v]==0){
                            fireTime[i+u][j+v]=time;
                            matrix[i+u][j+v]=10;
                        }
                    }
                }
            }
        }
    }

    private static void initGraph(){
        for(int i = 0; i < row_size ; i++){
            for(int j = 0 ; j < column_size ; j++){
                if(mainMatrix[i][j]==0 ) {
                    initAround(i,j);
                }
            }
        }
    }

    private static void initAround(int i , int j){
        int h = i*column_size+j;
        if(j>0 && (mainMatrix[i][j-1]==0))
            graph.addEdge2(h,h-1);
        if(j<column_size-1 && (mainMatrix[i][j+1]==0))
            graph.addEdge2(h,h+1);
        if(i>0 && (mainMatrix[i-1][j]==0))
            graph.addEdge2(h,h-column_size);
        if(i<row_size-1 && (mainMatrix[i+1][j]==0))
            graph.addEdge2(h,h+column_size);
    }

    public static void showGraph(){
        for(int i = 0; i < row_size*column_size ; i++){
            System.out.println("Node " + i + " :");
            for(int p = 0 ; p<graph.adj[i].size();p++)
                System.out.println(graph.adj[i].get(p));
        }
    }

    private static void deleteEdge(int nodeI , int nodeJ ){
        int node = nodeI*column_size+nodeJ;
        if(nodeJ>0 )
            graph.deleteEdge(node,node-1);
        if(nodeJ<column_size-1 )
            graph.deleteEdge(node,node+1);
        if(nodeI>0 )
            graph.deleteEdge(node,node-column_size);
        if(nodeI<row_size-1)
            graph.deleteEdge(node,node+column_size);

    }

    private static void deleteEdge2(int nodeI , int nodeJ){
        graph.deleteEdge2(nodeI*column_size+nodeJ);
    }

    private static void bfs_f(){
        for(int i =0 ; i <105 ; i++) {
            for (int j = 0 ; j < 105 ; j++){
                if(!vis[i][j])
                    fireTime2[i][j] = Integer.MAX_VALUE;
            }
        }

        Node p = new Node();
        Node q = new Node();

        int i;
        int j;
        while (!Q.isEmpty())
        {
            p.x = Q.getFirst().x;
            p.y = Q.getFirst().y;
            p.step = Q.getFirst().step;
            Q.removeFirst();
            for (i = 0;i < 8;i++)
            {
                q.x = p.x;
                q.y = p.y;
                q.step = p.step;
                q.x += fx[i];
                q.y += fy[i];
                q.step+=k;
                if (q.x < 0 || q.y < 0 || q.x >= row_size || q.y >= column_size)
                {
                    continue;
                }
                if (vis[q.x][q.y] == false )
                {
                    vis[q.x][q.y] = true;
                    fireTime2[q.x][q.y]=q.step;
                    Q.addLast(new Node(q.x,q.y,q.step));
                }
            }
        }
    }

    public static void displayFire(){
        System.out.println("-----------------");
        for(int u = 0 ; u < row_size ; u++){
            for(int v = 0 ; v < column_size ; v++){
                if(fireTime[u][v]==-1)
                    System.out.print("x ");
                else
                    System.out.print(fireTime[u][v]+" ");
            }
            System.out.println();
        }
    }

    public static void displayFire2(){
        System.out.println("-----------------");
        for(int u = 0 ; u < row_size ; u++){
            for(int v = 0 ; v < column_size ; v++){
                if(fireTime[u][v]==-1)
                    System.out.print("x ");
                else
                    System.out.print(fireTime2[u][v]+" ");
            }
            System.out.println();
        }
    }

    public static void display(){
        System.out.println("-----------------------------------------------");
        for(int u = 0 ; u < row_size ; u++){
            for(int v = 0 ; v < column_size ; v++){
                if(matrix[u][v]==11)
                    System.out.print("f ");
                else if(matrix[u][v]==12)
                    System.out.print("v ");
                else if(matrix[u][v]==0)
                    System.out.print("- ");
                else if(matrix[u][v]==6)
                    System.out.print("s ");
                else if(matrix[u][v]==66)
                    System.out.print("O ");
                else if(matrix[u][v]==5)
                    System.out.print("t ");
                else if(matrix[u][v]==10)
                    System.out.print("f ");
            }
            System.out.println();
        }
    }

}

class Graph
{
    private int V;
    int[] pred ;
    int[] dist ;
    public LinkedList<Integer>[] adj;
    public Graph(int V)
    {
        this.V = V;
        adj = new LinkedList[V];
        for(int y = 0 ; y < V ; y++){
            adj[y] = new LinkedList<>();
        }
    }

    public final void addEdge(int v, int w)
    {
        adj[v].add(w); // Add w to v’s list.
        adj[w].add(v); // Since the graph is undirected
    }

    public void addEdge2(int v, int w)
    {
        adj[v].add(w);
//        System.out.println("Added V : " + v + " W : " + w);// Add w to v’s list.
    }

    public final void deleteEdge(int v , int w){
        int t=0;
        for(int i = 0 ; i<adj[v].size();i++){
            if(adj[v].get(i)==w){
                t=i;
                break;
            }
        }
        if(!adj[v].isEmpty())
            adj[v].remove(t);
        for(int i = 0 ; i<adj[w].size();i++){
            if(adj[w].get(i)==v){
                t=i;
                break;
            }
        }
        if(!adj[w].isEmpty())
            adj[w].remove(t);
    }

    public  void deleteEdge2(int node ){
        adj[node].clear();
    }

    public  boolean bfs(int src , int dest)
    {

        boolean[] visited = new boolean[V];
        pred = new int[V];
        dist = new int[V];

        LinkedList <Integer> q = new LinkedList();

        for(int x = 0 ; x < V ; x++){
            visited[x] = false;
            dist[x] = -1;
            pred[x] = -1;
        }

        visited[src] = true;
        dist[src] = 0;

        q.addLast(src);



        while (!q.isEmpty())
        {
            int t = q.poll();
            for (int i = 0; i < adj[t].size(); i++) {
                if (!visited[adj[t].get(i)]) {
                    visited[adj[t].get(i)] = true;
                    dist[adj[t].get(i)] = dist[t] + 1;
                    pred[adj[t].get(i)] = t;
                    q.addLast(adj[t].get(i));

                    if (adj[t].get(i) == dest)
                        return true;
                }
            }
        }
        return false;
    }
    // node from u with its distance
    public LinkedList<Integer> shortestDistance(int src , int dest){

        if(!bfs(src, dest)){
            return new LinkedList<>();
        }
        LinkedList<Integer> path = new LinkedList<>();
        int crawl = dest;
        path.addLast(crawl);
        while (pred[crawl] != -1){
            path.addLast(pred[crawl]);
            crawl = pred[crawl];
        }

        return path;

    }

    public static void main(String[] args) {
        Graph g = new Graph(12);
        g.addEdge( 0, 1);
        g.addEdge( 0, 9);
        g.addEdge( 9, 10);
        g.addEdge( 10, 11);
        g.addEdge( 11, 6);
        g.addEdge( 1, 4);
        g.addEdge( 8, 7);
        g.addEdge( 4, 5);
        g.addEdge( 4, 3);
        g.addEdge( 4, 7);
        g.addEdge(6,8);
//        g.addEdge(6,7);
        LinkedList<Integer> oo = g.shortestDistance(0,8);
        while (!oo.isEmpty()){
            System.out.print(oo.pollLast()+" , ");
        }
        System.out.println();
        g.deleteEdge(4,3);
        oo = g.shortestDistance(0,8);
        while (!oo.isEmpty()){
            System.out.print(oo.pollLast()+" , ");
        }

    }
}
class Node{
    public int x;
    public int y;
    public int step;
    public Node(int x , int y , int step){
        this.x=x;
        this.y=y;
        this.step=step;
    }
    public Node(){}
}