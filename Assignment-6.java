package labMST;

import java.io.*;
import java.util.*;

class Assignment {

    static class Graph {
        private int n; // No. of vertices
        private List<List<Integer>> adj; // An array of adjacency lists
        private Map<String, Integer> weightMap;

        public Graph(int n) {
            this.n = n;
            this.adj = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                this.adj.add(new LinkedList<>());
            }
            this.weightMap = new HashMap<>();
        }

        public void addEdge(int u, int v) {
            this.adj.get(u).add(v);
        }

        public void addEdgeWeight(int u, int v, int w) {
            String e = u + "," + v;
            this.weightMap.put(e, w);
        }

        public int getWeight(int u, int v) {
            String e = u + "," + v;
            return this.weightMap.get(e);
        }

        public List<Integer> getAdjacent(int u) {
            return this.adj.get(u);
        }

        public int getLength() {
            return this.n;
        }

        public void show() {
            for (int v = 0; v < n; v++) {
                System.out.print(v + ":");
                for (int i : this.adj.get(v)) {
                    System.out.print(i + ";");
                }
                System.out.println();
            }
        }
    }

    static class QNode {
        int id;
        int key;
        boolean isValid;
        int pi;

        public QNode() {
            id = -1;
            key = -1;
            isValid = true;
            pi = -1;
        }
    }

    // Implement Prim's Algorithm for finding the Minimum Spanning Tree.
    // Return the QNode for each vertex in the graph, indicating the parent (pi)
    // of every vertex in the resulting tree. Other fields of QNode are optional
    // but may be useful in your implementation.
    public static List<QNode> Prim(Graph g) {
    	  int n = g.getLength();
    	  List<QNode> qNodes = new ArrayList<>();
    	  PriorityQueue<QNode> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.key));

    	    // Initialize QNodes and Priority Queue
    	    for (int i = 0; i < n; i++) {
    	        QNode node = new QNode();
    	        node.id = i;
    	        node.key = (i == 0) ? 0 : Integer.MAX_VALUE; // Start with vertex 0
    	        node.pi = -1;
    	        qNodes.add(node);
    	        pq.add(node);
    	    }

    	    boolean[] inMST = new boolean[n]; // To check if a node is already in the MST

    	    while (!pq.isEmpty()) {
    	        QNode current = pq.poll();

    	        if (inMST[current.id]) continue; // Skip if already in MST

    	        inMST[current.id] = true;

    	        for (int neighbor : g.getAdjacent(current.id)) {
    	            int weight = g.getWeight(current.id, neighbor);
    	            QNode neighborNode = qNodes.get(neighbor);

    	            if (!inMST[neighbor] && weight < neighborNode.key) {
    	                pq.remove(neighborNode); // Remove old version
    	                neighborNode.key = weight;
    	                neighborNode.pi = current.id; // Update the parent
    	                pq.add(neighborNode);
    	            }
    	        }
    	    }

    	    return qNodes;
    	
    }

    public static void run(String inputPath) {
        try (BufferedReader br = new BufferedReader(new FileReader(inputPath))) {

            // Read graph
            // n is # of nodes. nodes are indexed by 0, 1, 2, ..., n -1
            int n = Integer.parseInt(br.readLine().trim());

            // # of edges.
            int m = Integer.parseInt(br.readLine().trim());

            // Build the graph based on the edges on remaining input lines
            Graph g = new Graph(n);
            for (int i = 0; i < m; i++) {
                String[] parts = br.readLine().trim().split(" ");
                int s = Integer.parseInt(parts[0]);
                int t = Integer.parseInt(parts[1]);
                g.addEdge(s, t);
                int w = Integer.parseInt(parts[2]);
                g.addEdgeWeight(s, t, w);
                g.addEdge(t, s);
                g.addEdgeWeight(t, s, w);
            }

            // Your implementation must return a list of QNode. One QNode for
            // each vertex, in the order 0, 1, 2, ..., n-1
            List<QNode> pQNode = Prim(g);

            // The correctness of your implementation will be judged by printing
            // the parent of each vertex, line by line
            for (int i = 1; i < n; i++) {
                System.out.println(pQNode.get(i).pi);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
