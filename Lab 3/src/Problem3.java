/**
 * Created by Alex on 14.11.15.
 */

import java.io.*;
import java.util.*;

public class Problem3 {
    FastScanner in;
    PrintWriter out;

    int n;
    int start;
    HashSet<Integer> can = new HashSet<>();
    HashSet<Integer> tmp = new HashSet<>();
    HashSet<Integer> reach = new HashSet<>();
    ArrayList<HashSet<HashSet<Integer>>> rule = new ArrayList<>();
    boolean[] used;

    public void solve() throws IOException {
        n = in.nextInt();
        start = in.next().charAt(0) - 'A';
        used = new boolean[26];

        for (int i = 0; i < 26; i++) {
            rule.add(new HashSet<>());
        }

        for (int i = 0; i < n; i++) {
            String s = in.br.readLine();
            int from = s.charAt(0) - 'A';
            String to = "";

            if (s.length() <= 5) {
                can.add(from);
            } else {
                to = s.substring(5);
                tmp = new HashSet<>();

                for (int j = 0; j < to.length(); j++) {
                    tmp.add(to.charAt(j) - 'A');

                    if (Character.isUpperCase(to.charAt(j))) {
                        used[to.charAt(j) - 'A'] = true;
                    }
                }

                rule.get(from).add(tmp);
            }

            tmp = null;
            used[from] = true;
        }

        int prev = can.size();

        while (true) {
            for (int c = 0; c < 26; c++) {
                if (!used[c] || rule.get(c).isEmpty() || can.contains(c)) {
                    continue;
                }

                for (HashSet<Integer> elem : rule.get(c)) {
                    HashSet<Integer> tmp2 = new HashSet<>();

                    for (Integer elem2 : elem) {
                        if (Character.isLowerCase((char) (elem2 + 'A')) || can.contains(elem2)) {
                            tmp2.add(elem2);
                        }
                    }

                    if (tmp2.equals(elem)) {
                        can.add(c);
                    }
                }
            }

            if (prev == can.size()) {
                break;
            }

            prev = can.size();
        }

        boolean again = true;
        reach.add(start);

        while (again) {
            again = false;

            for (int c = 0; c < 26; c++) {
                if (!used[c] || rule.get(c).isEmpty() || !reach.contains(c)) {
                    continue;
                }

                for (HashSet<Integer> elem : rule.get(c)) {
                    for (int elem2 : elem) {
                        if (Character.isUpperCase((char) (elem2 + 'A')) && !reach.contains(elem2)) {
                            reach.add(elem2);
                            again = true;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < 26; i++) {
            if (!used[i]) {
                continue;
            }

            if (reach.contains(i) && can.contains(i)) {
                continue;
            }

            out.print((char) (i + 'A') + " ");
        }

        out.println();
    }

    public void run() {
        try {
            in = new FastScanner(new File("useless.in"));
            out = new PrintWriter(new File("useless.out"));

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
