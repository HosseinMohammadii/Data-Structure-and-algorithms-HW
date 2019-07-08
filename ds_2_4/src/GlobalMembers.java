public class GlobalMembers
{
	// Driver code to test above methods 
	public static void main(String[] args)
	{
		// Create a graph given in the example 
		Graph2 g = new Graph2(10);
		g.addEdge(0, 1);
		g.addEdge(1, 2);
		g.addEdge(2, 3);
		g.addEdge(2, 9);
		g.addEdge(2, 4);
		g.addEdge(4, 5);
		g.addEdge(1, 6);
		g.addEdge(6, 7);
		g.addEdge(6, 8);

//		g.addEdge(0,1);
//		g.addEdge(0,2);
//		g.addEdge(0,3);
//		g.addEdge(3,4);
		g.longestPathLength();
	}
}