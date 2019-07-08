import java.security.acl.LastOwnerException;
import java.util.Scanner;

public class Main {
    static long[][] weight;
    static long[][] city;
    static int y;
    static int x;
    static long INFINITY = Long.MAX_VALUE/2;
    public static void main(String[] args) {
        Scanner s =  new Scanner(System.in);
        weight = new long[1001][1001];
        city = new long[1001][1001];
        x = s.nextInt();
        y = s.nextInt();
        int size = s.nextInt();
        int w;
        int m;
        int i = 0;
        while(i < size){

            w = s.nextInt();
            m = s.nextInt();
            i++;
            int [] arr = new int[m];
            for(int u = 0 ; u < m ; u++){
               arr[u]=s.nextInt();
            }

            for(int p = 0 ; p<m ; p++){
                for(int o = p+1 ; o < m ; o++) {
                    if (weight[arr[p]][arr[o]] == 0){
                        weight[arr[p]][arr[o]] = w ;
                        city[arr[p]][arr[o]] = o-p ;
                    }
                    else{
                        if(w < weight[arr[p]][arr[o]]){
                            weight[arr[p]][arr[o]] = w ;
                            city[arr[p]][arr[o]] = o-p ;
                        }
                        if(w == weight[arr[p]][arr[o]]){
                            if((o-p)<city[arr[p]][arr[o]])
                                city[arr[p]][arr[o]] = o-p ;
                        }
                    }
                }
            }
        }


        dijkstra(weight,1001,x,y);
//        System.out.println(Integer.MAX_VALUE);
//        System.out.println(Long.MAX_VALUE);
//        System.out.println(-1 + " " + -1);
    }
    static void dijkstra(long adjacencyMatrix[][], int numberOfNodes, int sourceNode){
        long[] dist = new long[1001];
        boolean[] mark = new boolean[1001];
        long[] p = new long[1001];
        for(int i = 0 ; i < numberOfNodes ; i++){
            dist[i] = Long.MAX_VALUE/4;
        }
        int cnode = sourceNode;
        dist[cnode]= 0 ;
        int minNode;
        long minCost;
        long minCity;
        while(true){
            minCost=Long.MAX_VALUE/4;
            minNode = -1;
            minCity = -1;
            for(int i = 0 ; i < numberOfNodes ; i++){
                if ((dist[cnode] + adjacencyMatrix[cnode][i]) == dist[i] && p[cnode] + city[cnode][i] < p[i]) {
                    p[i] = p[cnode] + city[cnode][i];
                    for(int v = 0 ; v < numberOfNodes ; v++){
                        mark[v] = false;
                    }
                }
                if(!mark[i]) {
                    if (adjacencyMatrix[cnode][i] != 0) {
                        if ((dist[cnode] + adjacencyMatrix[cnode][i]) == dist[i]) {
                            p[i] = Math.min(p[i], p[cnode] + city[cnode][i]);
                        }
                        if ((dist[cnode] + adjacencyMatrix[cnode][i]) < dist[i] && adjacencyMatrix[cnode][i] >0) {
                            dist[i] = dist[cnode] + adjacencyMatrix[cnode][i];
                            p[i] = p[cnode] + city[cnode][i];
                        }

                        if (dist[i] < minCost) {
                            minNode = i;
                            minCost = dist[i];
                            minCity = p[i];
                        }
                        else if(dist[i] == minCost){
                            if(p[i]<minCity){
                                minNode = i;
                                minCity = p[i];
                            }
                        }
                    }
                }
            }


            mark[cnode]=true;
            if(minNode==-1)
                break;
            cnode = minNode;
            if(cnode == y){
                System.out.println(dist[y] + " " + p[y]);
                return;
            }
        }

        System.out.println(-1 + " " + -1);

    }

    static void dijkstra(long Graph[][], int n, int s, int d)
    {

        int i, v, count;
        int u;
        long dist[] = new long[n];
        boolean Blackened[] = new boolean[n];
        int pathLength[] = new int[n];
        int parent[]  = new int[n];

        // The parent Of the source vertex is always equal to nill
        parent[s] = -1;

        // first, we initialize all distances to infinity.
        for (i = 0; i < n; i++){
            dist[i] = INFINITY;
            Blackened[i] = false;
            pathLength[i] = 0;
        }

        dist[s] = 0;
        for (count = 0; count < n - 1; count++) {
            u = MinDistance(dist, Blackened , n);

            // if MinDistance() returns INFINITY, then the graph is not
            // connected and we have traversed all of the vertices in the
            // connected component of the source vertex, so it can reduce
            // the time complexity sometimes
            // In a directed graph, it means that the source vertex
            // is not a root
            if (u == -1)
                break;
            else {

                // Mark the vertex as Blackened
                Blackened[u] = true;
                for (v = 0; v < n; v++) {
                    if (!Blackened[v] && Graph[u][v]>0 && dist[u] + Graph[u][v] < dist[v]) {
                        parent[v] = u;
                        pathLength[v] = (int) (pathLength[parent[v]] + city[parent[v]][v]);
                        dist[v] = dist[u] + Graph[u][v];
                    }
                    else if (!Blackened[v] && Graph[u][v] >0 && dist[u] + Graph[u][v] == dist[v] && pathLength[u] + city[u][v] < pathLength[v]) {
                        parent[v] = u;
                        pathLength[v] = (int) (pathLength[u] + city[u][v]);
                    }
                }
            }
        }

        // Printing the path
        if (dist[d] != INFINITY)
            System.out.println(dist[d] + " " + pathLength[d]);
        else
            System.out.println(-1 + " " + -1);
    }

    static int MinDistance(long[] dist, boolean[] Blackened , int n)
    {
        long min = INFINITY/4;
        int min_index = Integer.MAX_VALUE/4;
        for (int v = 0; v < n; v++)
            if (!Blackened[v] && dist[v] < min) {
                min = dist[v];
                min_index = v;
            }
        if(min == INFINITY/4)
            return -1;
        else
            return min_index;
    }
}


