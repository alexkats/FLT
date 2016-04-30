import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Problem5 {
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
    int start, last;
    HashSet<Integer> can = new HashSet<>();
    ArrayList<HashSet<Integer>> ends = new ArrayList<>();
    ArrayList<Pair> have = new ArrayList<>();
    boolean[][][] dp;

    public void solve() throws IOException {
        n = in.nextInt();
        last = 52;
        start = in.next().charAt(0) - 'A' + 26;

        for (int i = 0; i < 256; i++) {
            ends.add(new HashSet<>());
        }

        for (int i = 0; i < n; i++) {
            String s = in.br.readLine();
            int from = s.charAt(0) - 'A' + 26;

            if (s.length() <= 5) {
                can.add(from);
            } else {
                String to = s.substring(5);

                if (to.length() == 1) {
                    if (Character.isLowerCase(to.charAt(0))) {
                        ends.get(to.charAt(0) - 'a').add(from);
                    } else {
                        ends.get(to.charAt(0) - 'A' + 26).add(from);
                    }
                } else {
                    int curr = to.length();
                    String now;

                    while (--curr > 1) {
                        now = Character.toString((char) (last));

                        if (Character.isLowerCase(to.charAt(curr))) {
                            now += Character.toString((char) (to.charAt(curr) - 'a'));
                        } else {
                            now += Character.toString((char) (to.charAt(curr) - 'A' + 26));
                        }

                        have.add(new Pair(from, now));
                        from = last++;
                    }

                    if (Character.isLowerCase(to.charAt(0))) {
                        now = Character.toString((char) (to.charAt(0) - 'a'));
                    } else {
                        now = Character.toString((char) (to.charAt(0) - 'A' + 26));
                    }

                    if (Character.isLowerCase(to.charAt(1))) {
                        now += Character.toString((char) (to.charAt(1) - 'a'));
                    } else {
                        now += Character.toString((char) (to.charAt(1) - 'A' + 26));
                    }

                    have.add(new Pair(from, now));
                }
            }
        }

        int prev = can.size();

        while (true) {
            HashSet<Integer> tmp = new HashSet<>();

            for (int elem : can) {
                tmp.addAll(ends.get(elem).stream().collect(Collectors.toList()));
            }

            can.addAll(tmp.stream().collect(Collectors.toList()));

            have.stream().filter(elem -> can.contains((int) elem.right.charAt(0)) && can.contains((int) elem.right.charAt(1))).forEach(elem -> can.add(elem.left));

            if (prev == can.size()) {
                break;
            }

            prev = can.size();
        }

        String need = in.next();
        int len = need.length();
        dp = new boolean[len][len][256];

        for (int i = 0; i < len; i++) {
            int c = need.charAt(i) - 'a';
            dp[i][i][c] = true;

            for (int elem : ends.get(c)) {
                dp[i][i][elem] = true;
            }
        }

        for (int length = 0; length < len; length++) {
            for (int st = 0; st < len - length; st++) {
                for (int mid = 0; mid < st + length; mid++) {
                    for (Pair elem : have) {
                        int first = elem.right.charAt(0);
                        int second = elem.right.charAt(1);
                        dp[st][st + length][elem.left] = dp[st][mid][first] && dp[mid + 1][st + length][second];
                    }
                }

                for (int o = 0; o < 10; o++) {
                    boolean changed = false;

                    for (Pair elem : have) {
                        int first = elem.right.charAt(0);
                        int second = elem.right.charAt(1);

                        if ((dp[st][st + length][first] && can.contains(second) || (can.contains(first) && dp[st][st + length][second]))) {
                            if (!dp[st][st + length][elem.left]) {
                                dp[st][st + length][elem.left] = true;
                                changed = true;
                            }
                        }
                    }

                    for (int i = 0; i < 256; i++) {
                        if (dp[st][st + length][i]) {
                            for (int elem : ends.get(i)) {
                                if (!dp[st][st + length][elem]) {
                                    dp[st][st + length][elem] = true;
                                    changed = true;
                                }
                            }
                        }
                    }

                    if (!changed) {
                        break;
                    }
                }
            }
        }

        if (dp[0][len - 1][start]) {
            out.println("yes");
        } else {
            out.println("no");
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("cf.in"));
            out = new PrintWriter(new File("cf.out"));

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
    }

    public static void main(String[] arg) {
        new Problem5().run();
    }
}
