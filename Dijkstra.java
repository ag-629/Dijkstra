import java.lang.Math;
import java.util.Vector;

public class Dijkstra {

    public void run(int n) {
        int[][] graph = new int[n][n];
        fillRandomGraph(graph);

        Node[] myNodes = new Node[graph.length];
        for(int i = 0; i < graph.length; i++) {
            myNodes[i] = new Node(i);
        }
        Node[] nodes = new Node[9];
        for(int i = 0; i < 9; i += 1){
            nodes[i] = new Node(i);
        }
        dijkstra(graph,nodes);
        for(int i = 0; i < nodes.length; i += 1){
            printPath(nodes[i]);
        }
    }

    public void print(int[][] graph) {
        for(int i = 0; i < graph.length; i++) {
            for(int j = 0; j < graph.length; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }
    }


	public void fillRandomGraph(int[][] graph) {
		for(int i = 0; i < graph.length; i++) {
            for(int j = i+1; j < graph.length; j++) {
                double rand = Math.random();
                if(rand < 0.5) {
                    graph[i][j] = (int)(Math.random() * 50);
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
            //set visited now
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