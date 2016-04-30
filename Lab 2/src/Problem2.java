/**
 * Created by Alex on 02.11.15.
 */

import java.io.*;
import java.util.*;

public class Problem2 {
    FastScanner in;
    PrintWriter out;

    class Pair {
        int a, b;

        Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    public void solve() throws IOException {
        int n1, m1, k1;
        n1 = in.nextInt();
        m1 = in.nextInt();
        k1 = in.nextInt();
        boolean[] term1 = new boolean[n1 + 1];
        int[][] g1 = new int[n1 + 1][26];

        for (int i = 0; i < k1; i++) {
            term1[in.nextInt()] = true;
        }

        for (int i = 0; i < m1; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            int c = in.next().charAt(0) - 'a';

            g1[x][c] = y;
        }

        int n2, m2, k2;
        n2 = in.nextInt();
        m2 = in.nextInt();
        k2 = in.nextInt();
        boolean[] term2 = new boolean[n2 + 1];
        int[][] g2 = new int[n2 + 1][26];

        for (int i = 0; i < k2; i++) {
            term2[in.nextInt()] = true;
        }

        for (int i = 0; i < m2; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            int c = in.next().charAt(0) - 'a';

            g2[x][c] = y;
        }

        LinkedList<Pair> q = new LinkedList<>();
        q.add(new Pair(1, 1));
        boolean[] used1 = new boolean[n1 + 1];
        boolean[] used2 = new boolean[n2 + 1];
        used1[1] = true;
        used2[1] = true;
        boolean good = true;

        while (!q.isEmpty()) {
            Pair curr = q.poll();

            if (term1[curr.a] != term2[curr.b]) {
                good = false;
                break;
            }

            for (int i = 0; i < 26; i++) {
                if (used1[g1[curr.a][i]] && used2[g2[curr.b][i]]) {
                    continue;
                }

                q.add(new Pair(g1[curr.a][i], g2[curr.b][i]));
                used1[g1[curr.a][i]] = true;
                used2[g2[curr.b][i]] = true;
            }
        }

        if (good) {
            out.println("YES");
        } else {
            out.println("NO");
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("equivalence.in"));
            out = new PrintWriter(new File("equivalence.out"));

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
