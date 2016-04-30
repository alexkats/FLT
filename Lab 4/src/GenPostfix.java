/**
 * Created by Alex on 18.12.15.
 */

import java.io.*;
import java.util.*;

public class GenPostfix {
    FastScanner in;
    PrintWriter out;

    void dfs(StringBuilder sb, int depth) {
        if (depth > 20) {
            return;
        }

        if (depth > 10) {
            out.println(sb);
        }

        StringBuilder sb1 = new StringBuilder(sb);
        StringBuilder sb2 = new StringBuilder(sb);
        StringBuilder sb3 = new StringBuilder(sb);
        StringBuilder sb4 = new StringBuilder(sb);

        sb1.append(" 0 a");
        sb2.append(" 0 o");
        sb3.append(" 1 a");
        sb4.append(" 1 o");

        dfs(sb1, depth + 2);
        dfs(sb2, depth + 2);
        dfs(sb3, depth + 2);
        dfs(sb4, depth + 2);
    }

    public void solve() throws IOException {
        StringBuilder sb0 = new StringBuilder("0");
        StringBuilder sb1 = new StringBuilder("1");

        dfs(sb0, 1);
        dfs(sb1, 1);
    }

    public void run() {
        try {
            out = new PrintWriter(new File("all.out"));

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
        new GenPostfix().run();
    }
}
