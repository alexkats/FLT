/**
 * Created by Alex on 17.12.15.
 */

import java.io.*;
import java.util.*;

public class Postfixlogic {
    PrintWriter out;

    public void solve() throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("2\n");

        sb.append("S 0 _ -> S _ > 0 >\n");
        sb.append("S 1 _ -> S _ > 1 >\n");

        sb.append("S a _ -> S a ^ _ <\n");
        sb.append("S o _ -> S o ^ _ <\n");

        sb.append("S o 0 -> S _ > _ ^\n");
        sb.append("S o 1 -> OR o ^ _ <\n");
        sb.append("S a 0 -> AND a ^ _ <\n");
        sb.append("S a 1 -> S _ > _ ^\n");

        sb.append("S _ 0 -> AC 0 ^ _ ^\n");
        sb.append("S _ 1 -> AC 1 ^ _ ^\n");
        sb.append("S _ _ -> S _ ^ _ <\n");

        sb.append("OR o 0 -> S _ > 1 >\n");
        sb.append("OR o 1 -> S _ > 1 >\n");
        sb.append("AND a 0 -> S _ > 0 >\n");
        sb.append("AND a 1 -> S _ > 0 >");

        out.println(sb);
    }

    public void run() {
        try {
            out = new PrintWriter(new File("postfixlogic.out"));

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
        new Postfixlogic().run();
    }
}
