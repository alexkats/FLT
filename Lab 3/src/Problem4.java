/**
 * Created by Alex on 14.11.15.
 */

import java.io.*;
import java.util.*;

public class Problem4 {
    FastScanner in;
    PrintWriter out;

    public static final int MOD = (int) 1e9 + 7;

    class Pair {
        int left;
        String right;

        Pair(int left, String right) {
            this.left = left;
            this.right = right;
        }
    }

    ArrayList<Pair> have = new ArrayList<>();
    long[][][] dp;

    public void solve() throws IOException {
        int n = in.nextInt();
        int start = in.next().charAt(0) - 'A';

        for (int i = 0; i < n; i++) {
            int from = in.next().charAt(0) - 'A';
            in.next();
            String to = in.next();
            have.add(new Pair(from, to));
        }

        String need = in.next();
        int len = need.length();
        dp = new long[len][len][26];

        for (int i = 0; i < len; i++) {
            for (Pair elem : have) {
                int curr1 = need.charAt(i) - 'A';
                int curr2 = elem.right.charAt(0) - 'A';

                if (curr1 == curr2) {
                    dp[i][i][elem.left]++;
                }
            }
        }

        for (int length = 0; length < len; length++) {
            for (int st = 0; st < len - length; st++) {
                for (int mid = 0; mid < st + length; mid++) {
                    for (Pair elem : have) {
                        if (elem.right.length() == 1) {
                            continue;
                        }
                        int first = elem.right.charAt(0) - 'A';
                        int second = elem.right.charAt(1) - 'A';
                        dp[st][st+length][elem.left] += dp[st][mid][first] * dp[mid + 1][st + length][second];
                        dp[st][st+length][elem.left] %= MOD;
                    }
                }
            }
        }

        out.println(dp[0][len - 1][start]);
    }

    public void run() {
        try {
            in = new FastScanner(new File("nfc.in"));
            out = new PrintWriter(new File("nfc.out"));

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
        new Problem4().run();
    }
}
