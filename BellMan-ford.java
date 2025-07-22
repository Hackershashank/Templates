// Bellman-Ford is an algorithm to find the shortest path from a single source vertex to all other vertices in a weighted graph.
// It works with negative edge weights (unlike Dijkstra’s).
// It can detect negative weight cycles (a cycle whose total weight is negative).

// O(V * E) — slower than Dijkstra’s for dense graphs, but handles negatives.



import java.util.*;

class Edge {
    int source, dest, weight;
    Edge(int s, int d, int w) {
        source = s;
        dest = d;
        weight = w;
    }
}

public class BellmanFord {
    int V, E;
    Edge[] edges;

    BellmanFord(int v, int e) {
        V = v;
        E = e;
        edges = new Edge[E];
    }

    void bellmanFord(int src) {
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        // Relax edges V-1 times
        for (int i = 1; i < V; ++i) {
            for (Edge edge : edges) {
                int u = edge.source;
                int v = edge.dest;
                int w = edge.weight;
                if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                }
            }
        }

        // Check for negative-weight cycles
        for (Edge edge : edges) {
            int u = edge.source;
            int v = edge.dest;
            int w = edge.weight;
            if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]) {
                System.out.println("Graph contains negative weight cycle");
                return;
            }
        }

        // Print distances
        System.out.println("Vertex Distance from Source");
        for (int i = 0; i < V; ++i) {
            System.out.println(i + "\t\t" + dist[i]);
        }
    }

    public static void main(String[] args) {
        int V = 5; // Number of vertices
        int E = 8; // Number of edges

        BellmanFord graph = new BellmanFord(V, E);

        // add edges: graph.edges[i] = new Edge(u, v, w);
        graph.edges[0] = new Edge(0, 1, -1);
        graph.edges[1] = new Edge(0, 2, 4);
        graph.edges[2] = new Edge(1, 2, 3);
        graph.edges[3] = new Edge(1, 3, 2);
        graph.edges[4] = new Edge(1, 4, 2);
        graph.edges[5] = new Edge(3, 2, 5);
        graph.edges[6] = new Edge(3, 1, 1);
        graph.edges[7] = new Edge(4, 3, -3);

        graph.bellmanFord(0); // Source vertex is 0
    }
}
