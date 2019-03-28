import java.lang.Math;
import java.util.Vector;

public class Dijkstra {

    public void run(int n) {
        int[][] graph = new int[n][n];
        //Fill the edges in the graph
        fillRandomGraph(graph,0.6);
        //Fill an array with the nodes in the graph.
        Node[] myNodes = new Node[graph.length];
        for(int i = 0; i < graph.length; i++) {
            myNodes[i] = new Node(i);
        }

        //Run Dijkstra's Algorithms
        dijkstra(graph,myNodes);

        for(int i = 0; i < myNodes.length; i += 1){
            printPath(myNodes[i]);
        }
    }

    public void fillRandomGraph(int[][] graph,double p) {
        //Creates an edge in the graph with probability p
        for(int i = 0; i < graph.length; i++) {
            for(int j = i+1; j < graph.length; j++) {
                double rand = Math.random();
                if(rand < p) {
                    double multiplier = p * 10;
                    graph[i][j] = (int)(Math.random() * multiplier);
                    graph[j][i] = graph[i][j];
                }
                else{
                    graph[i][j] = 0;
                    graph[j][i] = 0;
                }
            }
        }
    }

    public void dijkstra(int[][] adj, Node[] nodes){
        Heap vertices = new Heap();
        nodes[0].key = 0;
        for(int i = 0; i < nodes.length; i += 1){
            vertices.add(nodes[i]);
        }
        while(!vertices.isEmpty()){
            Node current = vertices.remove();
            //This node has now been visitied
            current.visited = true;
            int fr = current.index;
            for(int to = 0; to < nodes.length; to += 1){
                //if there's a connection
                //then get the value of the weight between the nodes
                if(adj[fr][to] != 0){
                    int pathCost = adj[fr][to] + current.key;
                    if(pathCost < nodes[to].key){
                        vertices.decreaseKey(nodes[to],pathCost);
                        nodes[to].from = current;
                    }
                }
            }
        }
    }
    
    public void printPath(Node end){
        System.out.print(end.index+" ");
        Node prev = end.from;
        while(prev != null){
            System.out.print(prev.index+" ");
            prev = prev.from;
        }
        System.out.println();
    }

    public static void main(String [] args) {
        if(args.length > 0) new Dijkstra().run(Integer.parseInt(args[0]));
        else new Dijkstra().run(10);
    }
}