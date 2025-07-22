// Prim’s algorithm finds a Minimum Spanning Tree for a connected, weighted, undirected graph.
// It starts from any node and grows the MST by always choosing the minimum weight edge that connects a node inside the MST to a node outside the MST.
// It’s similar to Dijkstra’s algorithm, but instead of shortest paths, it builds an MST.

// O(V²) with adjacency matrix.
// O(E log V) with min-heap / priority queue and adjacency list.


import java.util.*;

class Edge {
    int dest, weight;

    Edge(int dest, int weight) {
        this.dest = dest;
        this.weight = weight;
    }
}

class Node implements Comparable<Node> {
    int vertex, key;

    Node(int vertex, int key) {
        this.vertex = vertex;
        this.key = key;
    }

    public int compareTo(Node other) {
        return this.key - other.key;
    }
}

public class PrimsPQ {
    int V;
    List<List<Edge>> adj;

    PrimsPQ(int V) {
        this.V = V;
        adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
    }

    void addEdge(int u, int v, int weight) {
        adj.get(u).add(new Edge(v, weight));
        adj.get(v).add(new Edge(u, weight)); // Undirected
    }

    void primMST() {
        boolean[] inMST = new boolean[V];
        int[] key = new int[V];
        int[] parent = new int[V];

        Arrays.fill(key, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);

        PriorityQueue<Node> pq = new PriorityQueue<>();

        key[0] = 0;
        pq.add(new Node(0, key[0]));

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int u = node.vertex;

            inMST[u] = true;

            for (Edge edge : adj.get(u)) {
                int v = edge.dest;
                int weight = edge.weight;

                if (!inMST[v] && weight < key[v]) {
                    key[v] = weight;
                    parent[v] = u;
                    pq.add(new Node(v, key[v]));
                }
            }
        }

        // Print MST
        System.out.println("Edge \tWeight");
        for (int i = 1; i < V; i++) {
            System.out.println(parent[i] + " - " + i + "\t" + key[i]);
        }
    }

    public static void main(String[] args) {
        int V = 5;
        PrimsPQ graph = new PrimsPQ(V);

        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 3, 6);
        graph.addEdge(1, 2, 3);
        graph.addEdge(1, 3, 8);
        graph.addEdge(1, 4, 5);
        graph.addEdge(2, 4, 7);
        graph.addEdge(3, 4, 9);

        graph.primMST();
    }
}
