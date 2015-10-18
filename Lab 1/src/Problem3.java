import java.io.*;
import java.util.*;

public class Problem3 {
    FastScanner in;
    PrintWriter out;
    int n, m, k;
    ArrayList<ArrayList<Integer>> go;
    ArrayList<ArrayList<Integer>> reversedGo;
    ArrayList<Integer> num = new ArrayList<>();
    int[] used;
    int[] prev;
    boolean[] can;
    public final static int MOD = 1000000007;

    void reversedDfs(int v) {
        for (int i = 0; i < reversedGo.get(v).size(); i++) {
            int u = reversedGo.get(v).get(i);

            if (used[u] == 0) {
                used[u] = 1;
                can[u] = true;
                reversedDfs(u);
            }
        }
    }

    boolean findCycle(int v) {
        used[v] = 1;

        for (int x : go.get(v)) {
            if (!can[x]) {
                continue;
            }

            if (used[x] == 0) {
                prev[x] = v;
                return findCycle(x);
            } else if (used[x] == 1) {
                return true;
            }
        }

        return false;
    }

    void dfsTopSort(int v) {
        used[v] = 1;

        for (int x : go.get(v)) {
            if (!can[x]) {
                continue;
            }

            if (used[x] == 0) {
                dfsTopSort(x);
            }
        }

        num.add(v);
    }

    void topSort() {
        Arrays.fill(used, 0);

        for (int i = 1; i <= n; i++) {
            if (can[i] && used[i] == 0) {
                dfsTopSort(i);
            }
        }

        num.add(0);
        Collections.reverse(num);
    }

    public void solve() throws IOException {
        n = in.nextInt();
        m = in.nextInt();
        k = in.nextInt();
        HashSet<Integer> term = new HashSet<>();
        can = new boolean[n + 1];
        go = new ArrayList<>();
        reversedGo = new ArrayList<>();
        used = new int[n + 1];
        prev = new int[n + 1];
        int[] ans = new int[n + 1];

        for (int i = 0; i < n + 1; i++) {
            go.add(new ArrayList<>());
            reversedGo.add(new ArrayList<>());
        }

        for (int i = 0; i < k; i++) {
            term.add(in.nextInt());
        }

        for (int i = 0; i < m; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            in.next();
            go.get(x).add(y);
            reversedGo.get(y).add(x);
        }

        Arrays.fill(used, 0);

        term.stream().filter(t -> used[t] == 0).forEach(t -> {
            can[t] = true;
            reversedDfs(t);
        });

        Arrays.fill(used, 0);

        if (findCycle(1)) {
            out.println(-1);
        } else {
            topSort();
            ans[1] = 1;

            for (int i = 1; i < num.size(); i++) {
                int curr = num.get(i);

                for (int j = 0; j < reversedGo.get(curr).size(); j++) {
                    int v = reversedGo.get(curr).get(j);

                    if (!can[v]) {
                        continue;
                    }

                    ans[curr] += ans[v];
                    ans[curr] %= MOD;
                }
            }

            int final_ans = 0;

            for (int t : term) {
                final_ans += ans[t];
                final_ans %= MOD;
            }

            out.println(final_ans);
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("problem3.in"));
            out = new PrintWriter(new File("problem3.out"));

            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(File f) {
            try {
                br = new BufferedReader(new FileReader(f));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }
    }

    public static void main(String[] arg) {
        new Problem3().run();
    }
}
