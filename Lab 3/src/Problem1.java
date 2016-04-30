/**
 * Created by Alex on 14.11.15.
 */

import java.io.*;
import java.util.*;

public class Problem1 {
    FastScanner in;
    PrintWriter out;

    int n;
    int start;
    int[][][] go;

    public void solve() throws IOException {
        n = in.nextInt();
        start = in.next().charAt(0) - 'A' + 1;
        go = new int[27][26][n];
        int[][] count = new int[27][26];

        for (int i = 0; i < n; i++) {
            int from = in.next().charAt(0) - 'A' + 1;
            in.next();
            String to = in.next();
            go[from][to.charAt(0) - 'a'][count[from][to.charAt(0) - 'a']++] = (to.length() == 1 ? 0 : (to.charAt(1) - 'A' + 1));
        }

        int m = in.nextInt();

        for (int T = 0; T < m; T++) {
            boolean found = false;
            String s = in.next();
            HashSet<Integer> curr = new HashSet<>();
            HashSet<Integer> prev = new HashSet<>();
            prev.add(start);

            for (int i = 0; i < s.length(); i++) {
                int c = s.charAt(i) - 'a';
                curr = new HashSet<>();

                for (int v : prev) {
                    for (int j = 0; j < count[v][c]; j++) {
                        if (s.length() == i + 1 || go[v][c][j] != 0) {
                            curr.add(go[v][c][j]);
                        }
                    }
                }

                prev = curr;
            }

            for (int v : curr) {
                if (v == 0) {
                    found = true;
                    break;
                }
            }

            if (found) {
                out.println("yes");
            } else {
                out.println("no");
            }
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("automaton.in"));
            out = new PrintWriter(new File("automaton.out"));

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
        new Problem1().run();
    }
}
