import java.io.*;
import java.util.*;

@SuppressWarnings("unchecked")
public class Problem2 {
    FastScanner in;
    PrintWriter out;

    int n, m, k;
    int[][][] go;

    public void solve() throws IOException {
        String s = in.next();
        n = in.nextInt();
        m = in.nextInt();
        k = in.nextInt();
        HashSet<Integer> term = new HashSet<>();
        HashSet<Integer> prev = new HashSet<>();
        HashSet<Integer> curr = new HashSet<>();
        go = new int[n + 1][26][m];
        int[][] count = new int[n + 1][26];

        for (int i = 0; i < k; i++) {
            term.add(in.nextInt());
        }

        for (int i = 0; i < m; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            int z = in.next().charAt(0) - 'a';
            go[x][z][count[x][z]++] = y;
        }

        boolean found = false;
        prev.add(1);

        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 'a';
            curr = new HashSet<>();

            for (int v : prev) {
                for (int j = 0; j < count[v][c]; j++) {
                    curr.add(go[v][c][j]);
                }
            }

            prev = curr;
        }

        for (int v : curr) {
            if (term.contains(v)) {
                found = true;
                break;
            }
        }

        if (found) {
            out.println("Accepts");
        } else {
            out.println("Rejects");
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("problem2.in"));
            out = new PrintWriter(new File("problem2.out"));

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
        new Problem2().run();
    }
}
