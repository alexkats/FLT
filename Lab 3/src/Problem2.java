/**
 * Created by Alex on 14.11.15.
 */

import java.io.*;
import java.util.*;

public class Problem2 {
    FastScanner in;
    PrintWriter out;

    class Pair {
        int left;
        String right;

        Pair(int left, String right) {
            this.left = left;
            this.right = right;
        }
    }

    int n;
    int start;
    TreeSet<Integer> ans = new TreeSet<>();
    HashSet<Pair> have = new HashSet<>();

    public void solve() throws IOException {
        n = in.nextInt();
        start = in.next().charAt(0) - 'A';

        for (int i = 0; i < n; i++) {
            String s = in.br.readLine();
            int from = s.charAt(0) - 'A';
            String to = "";

            if (s.length() <= 5) {
                ans.add(from);
                continue;
            }

            to = s.substring(5);
            boolean bad = false;

            for (int j = 0; j < to.length(); j++) {
                if (Character.isLowerCase(to.charAt(j))) {
                    bad = true;
                    break;
                }
            }

            if (bad) {
                continue;
            }

            have.add(new Pair(from, to));
        }

        int prev = ans.size();

        while (true) {
            for (Pair elem : have) {
                boolean can = true;

                for (int i = 0; i < elem.right.length(); i++) {
                    if (!ans.contains(elem.right.charAt(i) - 'A')) {
                        can = false;
                        break;
                    }
                }

                if (!can) {
                    continue;
                }

                ans.add(elem.left);
            }

            if (prev == ans.size()) {
                break;
            }

            prev = ans.size();
        }

        for (int i : ans) {
            char c = (char) (i + 'A');
            out.print(c + " ");
        }

        out.println();
    }

    public void run() {
        try {
            in = new FastScanner(new File("epsilon.in"));
            out = new PrintWriter(new File("epsilon.out"));

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
