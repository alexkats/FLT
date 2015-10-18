import java.io.*;
import java.util.*;

public class Problem1 {
    FastScanner in;
    PrintWriter out;

    public void solve() throws IOException {
        String s = in.next();
        int n, m, k;
        n = in.nextInt();
        m = in.nextInt();
        k = in.nextInt();
        int[] term = new int[k];
        int[][] go = new int[n + 1][26];

        for (int i = 0; i < k; i++) {
            term[i] = in.nextInt();
        }

        for (int i = 0; i < m; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            char c = in.next().charAt(0);
            int z = c - 'a';
            go[x][z] = y;
        }

        int curr = 1;
        boolean found = true;
        boolean good = false;

        for (int i = 0; i < s.length(); i++) {
            if (go[curr][s.charAt(i) - 'a'] == 0) {
                found = false;
                break;
            }

            curr = go[curr][s.charAt(i) - 'a'];
        }

        for (int i = 0; i < k; i++) {
            if (term[i] == curr) {
                good = true;
                break;
            }
        }

        if (found && good) {
            out.println("Accepts");
        } else {
            out.println("Rejects");
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("problem1.in"));
            out = new PrintWriter(new File("problem1.out"));

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