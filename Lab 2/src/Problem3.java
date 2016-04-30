import java.io.*;
import java.util.*;

public class Problem3 {
    FastScanner in;
    PrintWriter out;

    int n, m, k, start;
    boolean[] term;
    int g1[][];
    ArrayList<ArrayList<ArrayList<Integer>>> g2;
    boolean[] was;

    void removeUnreachable() {
        LinkedList<Integer> q = new LinkedList<>();
        was[start] = true;
        q.push(start);

        while (!q.isEmpty()) {
            int v = q.poll();

            for (int i = 0; i < 26; i++) {
                int u = g1[v][i];

                if (was[u]) {
                    continue;
                }

                was[u] = true;
                q.push(u);
            }
        }

        for (int i = 1; i <= n; i++) {
            if (was[i]) {
                continue;
            }

            for (int j = 0; j < 26; j++) {
                g1[i][j] = 0;
            }

            term[i] = false;
        }
    }

    void addHellVertex() {
        int v = ++n;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 26; j++) {
                if (g1[i][j] == 0) {
                    g1[i][j] = v;
                }
            }
        }

        for (int i = 0; i < 26; i++) {
            g1[v][i] = v;
        }
    }

    void minimizeAutomaton() {
        int[] classID = new int[n + 1];
        ArrayList<HashSet<Integer>> eqClasses = new ArrayList<>();
        ArrayList<Boolean> used = new ArrayList<>();
        ArrayList<Boolean> remaining = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            eqClasses.add(new HashSet<>());
            used.add(false);
            remaining.add(true);
        }

        for (int i = 0; i <= n; i++) {
            if (term[i]) {
                classID[i] = 1;
            } else {
                classID[i] = 0;
            }

            eqClasses.get(classID[i]).add(i);
        }

        LinkedList<Integer> q = new LinkedList<>();

        if (eqClasses.get(0).size() <= eqClasses.get(1).size()) {
            q.add(0);
            used.set(1, true);
        } else {
            q.add(1);
            used.set(0, true);
        }

        while (!q.isEmpty()) {
            int curr = q.poll();

            if (used.get(curr)) {
                continue;
            }

            for (int c = 0; c < 26; c++) {
                HashMap<Integer, Integer> qUsedClasses = new HashMap<>();
                HashSet<Integer> usedClasses = new HashSet<>();

                for (Integer elem : eqClasses.get(curr)) {
                    for (Integer r : g2.get(elem).get(c)) {
                        usedClasses.add(classID[r]);

                        if (qUsedClasses.containsKey(r)) {
                            qUsedClasses.put(r, qUsedClasses.get(r) + 1);
                        } else {
                            qUsedClasses.put(r, 1);
                        }
                    }
                }

                for (Integer elem : usedClasses) {
                    if (qUsedClasses.containsKey(elem) && eqClasses.get(elem).size() == qUsedClasses.get(elem)) {
                        continue;
                    }

                    HashSet<Integer> split1 = new HashSet<>();
                    HashSet<Integer> split2 = new HashSet<>();

                    for (Integer elem2 : eqClasses.get(elem)) {
                        if (eqClasses.get(curr).contains(g1[elem2][c])) {
                            split1.add(elem2);
                        } else {
                            split2.add(elem2);
                        }
                    }

                    if (split1.isEmpty() || split2.isEmpty()) {
                        continue;
                    }

                    for (int i = 0; i < 2; i++) {
                        used.add(true);
                        remaining.add(true);
                    }

                    int id1 = eqClasses.size();
                    int id2 = id1 + 1;
                    eqClasses.add(split1);
                    eqClasses.add(split2);

//                    if (elem % 2 == 1) {
                        eqClasses.get(elem).clear();
//                    }

                    remaining.set(elem, false);

                    for (Integer elem2 : split1) {
                        classID[elem2] = id1;
                    }

                    for (Integer elem2 : split2) {
                        classID[elem2] = id2;
                    }

                    if (used.get(elem)) {
                        if (split1.size() <= split2.size()) {
                            q.add(id1);
                            used.set(id1, false);
                        } else {
                            q.add(id2);
                            used.set(id2, false);
                        }
                    } else {
                        used.set(elem, true);
                        used.set(id1, false);
                        used.set(id2, false);
                        q.add(id1);
                        q.add(id2);
                    }
                }
            }
        }

        int[] newVertices = new int[remaining.size()];
        int newN = 0;

        for (int i = 0; i < remaining.size(); i++) {
            if (remaining.get(i)) {
                newVertices[i] = ++newN;
            }
        }

        boolean[] newTerm = new boolean[newN + 1];
        m = 0;
        k = 0;

        for (int i = 1; i <= n; i++) {
            if (term[i] && remaining.get(classID[i]) && !newTerm[newVertices[classID[i]]]) {
                newTerm[newVertices[classID[i]]] = true;
                k++;
            }
        }

        int[][] newG = new int[newN + 1][26];

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 26; j++) {
                if (g1[i][j] != 0) {
                    newG[newVertices[classID[i]]][j] = newVertices[classID[g1[i][j]]];
                }
            }
        }

        g1 = newG;
        term = newTerm;
        n = newN;
        start = newVertices[classID[start]];
    }

    void removeHellVertex() {
        int v = 0;

        for (int i = 1; i <= n; i++) {
            if (term[i]) {
                continue;
            }

            boolean found = true;

            for (int j = 0; j < 26; j++) {
                if (g1[i][j] != i && g1[i][j] != 0) {
                    found = false;
                    break;
                }
            }

            if (found) {
                v = i;
                break;
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 26; j++) {
                if (g1[i][j] == v) {
                    g1[i][j] = 0;
                }

                if (g1[i][j] == n) {
                    g1[i][j] = v;
                }

                if (g1[i][j] != 0) {
                    m++;
                }
            }
        }

        g1[v] = g1[n];
        term[v] = term[n];

        if (start == n) {
            start = v;
        }

        n--;
    }

    void setStart() {
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 26; j++) {
                if (g1[i][j] == start) {
                    g1[i][j] = 1;
                    continue;
                }

                if (g1[i][j] == 1) {
                    g1[i][j] = start;
                }
            }
        }

        int[] tmp = g1[start];
        g1[start] = g1[1];
        g1[1] = tmp;
        start = 1;
    }

    public void solve() throws IOException {
        n = in.nextInt();
        m = in.nextInt();
        k = in.nextInt();
        start = 1;
        term = new boolean[n + 1];
        was = new boolean[n + 1];
        g1 = new int[n + 1][26];
        g2 = new ArrayList<>();

        for (int i = 0; i < n + 1; i++) {
            g2.add(new ArrayList<>());

            for (int j = 0; j < 26; j++) {
                g2.get(i).add(new ArrayList<>());
            }
        }

        for (int i = 0; i < k; i++) {
            term[in.nextInt()] = true;
        }

        for (int i = 0; i < m; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            int c = (int) (in.next().charAt(0) - 'a');

            g1[x][c] = y;
            g2.get(y).get(c).add(x);
        }

        removeUnreachable();
        minimizeAutomaton();
        removeHellVertex();
        setStart();

        out.println(n + " " + m + " " + k);

        for (int i = 1; i <= n; i++) {
            if (term[i]) {
                out.print(i + " ");
            }
        }

        out.println();

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 26; j++) {
                if (g1[i][j] == 0) {
                    continue;
                }

                out.println(i + " " + g1[i][j] + " " + (char) (j + 'a'));
            }
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("minimization.in"));
            out = new PrintWriter(new File("minimization.out"));

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
