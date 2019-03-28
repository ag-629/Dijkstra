public class Node implements Comparable<Node>{
    //keep track of which node was on the path right before this node
    Node from;
    int index, key;
    boolean visited;

    public int compareTo(Node other) {
        if(this.key < other.key) return -1;
        else if(this.key > other.key) return 1;
        else return 0;
    }

    public Node(int i) {
        index = i;
        key = Integer.MAX_VALUE;
        visited = false;
        from = null;
    }
}
