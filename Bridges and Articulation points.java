import java.util.*;

public class Main {
    static void dfsBridge(List<List<Integer>> adj, int node, int par, boolean vis[],
                          int tin[], int low[], List<int[]> bridges, int timer[]) {
        vis[node] = true;
        tin[node] = low[node] = timer[0]++;
        for (int it : adj.get(node)) {
            if (it == par) continue;
            if (!vis[it]) {
                dfsBridge(adj, it, node, vis, tin, low, bridges, timer);
                low[node] = Math.min(low[node], low[it]);
                if (low[it] > tin[node]) {
                    int u = Math.min(it, node);
                    int v = Math.max(it, node);
                    bridges.add(new int[]{u, v});
                }
            } else {1:1 vscode


Test against custom input

                // FIXED: Use tin[it] here, not low[it]
                low[node] = Math.min(low[node], tin[it]);
            }
        }
    }

    static void dfsArticulation(List<List<Integer>> adj, int node, int par, boolean vis[],
                                int tin[], int low[], boolean isArti[], int timer[]) {
        vis[node] = true;
        tin[node] = low[node] = timer[0]++;
        int childCount = 0;
        for (int it : adj.get(node)) {
            if (it == par) continue;
            if (!vis[it]) {
                dfsArticulation(adj, it, node, vis, tin, low, isArti, timer);
                low[node] = Math.min(low[node], low[it]);
                if (low[it] >= tin[node] && par != -1)
                    isArti[node] = true;
                childCount++;
            } else {
                low[node] = Math.min(low[node], tin[it]);
            }
        }
        if (par == -1 && childCount > 1)
            isArti[node] = true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++)
            adj.add(new ArrayList<>());

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        // ---- FIND BRIDGES ----
        boolean[] vis = new boolean[n];
        int[] tin = new int[n];
        int[] low = new int[n];
        int[] timer = new int[1];
        List<int[]> bridges = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (!vis[i])
                dfsBridge(adj, i, -1, vis, tin, low, bridges, timer);
        }

        // ---- FIND ARTICULATION POINTS ----
        vis = new boolean[n];
        tin = new int[n];
        low = new int[n];
        timer = new int[1];
        timer[0] = 0;
        boolean[] isArti = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!vis[i])
                dfsArticulation(adj, i, -1, vis, tin, low, isArti, timer);
        }

        // Collect articulation points
        List<Integer> arti = new ArrayList<>();
        for (int i = 0; i < n; i++)
            if (isArti[i])
                arti.add(i);

        // ---- SORT & PRINT RESULTS ----
        Collections.sort(arti);
        Collections.sort(bridges, (a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });

        System.out.println(arti.size());
        for (int x : arti) System.out.print(x + " ");
        System.out.println();

        System.out.println(bridges.size());
        for (int[] b : bridges)
            System.out.println(b[0] + " " + b[1]);
    }
}
