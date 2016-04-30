import java.io.*;
import java.util.*;

public class Problem4 {

    FastScanner in;
    PrintWriter out;

    class Pair {
        int set, ch;
        public Pair(int set, int ch) {
            this.set = set;
            this.ch = ch;
        }
    }

    int n, m, k;
    Matrix[] aSet;
    Matrix[] bSet;
    Matrix[] toMove;
    LinkedList<Integer> q = new LinkedList<>();
    Integer[] map;
    int minStates;
    int[] stateToSet;
    int[][] delta;
    List<Integer>[] matrix;
    List<String> transitions;
    List<Integer> minTerminal;
    boolean[] isTerminal;
    boolean[] allow;
    List<Integer>[][] delta1;

    public void solve() throws IOException {
        n = in.nextInt();
        m = in.nextInt();
        k = in.nextInt();
        n++;
        stateToSet = new int[n];
        isTerminal = new boolean[n];
        delta = new int[n][26];
        delta1 = new Matrix[n][26];
        aSet = new Matrix[n + 1];
        LinkedList<Pair> queue = new LinkedList<>();
        toMove = new Matrix[n + 1];
        List<Integer> toMoveSet = new LinkedList<>();
        boolean[] isToMove = new boolean[n];
        boolean[] isToMoveSet = new boolean[n];
        Matrix.MyIterator[] inA = new Matrix.MyIterator[n];
        Matrix.MyIterator[] inB = new Matrix.MyIterator[n];
        bSet = new Matrix[n + 1];
        boolean inQueue[][] = new boolean[n + 1][26];
        int counter = 2;
        for (int i = 0; i < k; i++) {
            isTerminal[in.nextInt() - 1] = true;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 26; j++) {
                delta[i][j] = n - 1;
            }
        }
        matrix = new Matrix[n];
        for (int i = 0; i < m; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            u--;
            v--;
            char ch = in.next().charAt(0);
            int c = ch - 'a';
            delta[u][c] = v;
            if (matrix[v] == null) {
                matrix[v] = new Matrix();
            }
            matrix[v].add(u);
        }
        allow = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (allow[i] || !isTerminal[i]) {
                continue;
            }
            q.clear();
            q.add(i);
            while (!q.isEmpty()) {
                int state = q.poll();
                allow[state] = true;
                if (matrix[state] != null) {
                    for (Integer v : matrix[state]) {
                        if (!allow[v]) {
                            allow[v] = true;
                            q.add(v);
                        }
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 26; j++) {
                if (!allow[delta[i][j]]) {
                    delta[i][j] = n - 1;
                }
                if (delta1[delta[i][j]][j] == null) {
                    delta1[delta[i][j]][j] = new Matrix();
                }
                delta1[delta[i][j]][j].add(i);

            }
        }
        for (int i = 0; i < 2; i++) {
            bSet[i] = new Matrix();
            aSet[i] = new Matrix();
            toMove[i] = new Matrix();
        }
        for (int i = 0; i < n; i++) {
            int b = isTerminal[i] ? 0 : 1;
            inB[i] = bSet[b].add2(i);
            stateToSet[i] = b;
            if ((matrix[i] != null && !matrix[i].isEmpty()) || i == n - 1) {
                inA[i] = aSet[b].add2(i);
            }
        }
        for (int i = 0; i < 26; i++) {
            if ((aSet[1].size() < aSet[0].size() && stateToSet[n - 1] != 1)) {
                queue.add(new Pair(1, i));
                inQueue[1][i] = true;
            } else {
                queue.add(new Pair(0, i));
                inQueue[0][i] = true;
            }
        }
        while (!queue.isEmpty()) {
            Pair p = queue.poll();
            int set = p.set;
            int ch = p.ch;
            inQueue[set][ch] = false;
            for (Integer i1 : aSet[set]) {
                if (delta1[i1][ch] != null && !delta1[i1][ch].isEmpty()) {
                    for (Integer i : delta1[i1][ch]) {
                        if (!isToMove[i]) {
                            toMove[stateToSet[i]].add(i);
                            isToMove[i] = true;
                        }
                        if (!isToMoveSet[stateToSet[i]]) {
                            toMoveSet.add(stateToSet[i]);
                            isToMoveSet[stateToSet[i]] = true;
                        }
                    }
                }
            }
            for (Integer i : toMoveSet) {
                if (toMove[i].size() == bSet[i].size()) {
                    for (Integer j : toMove[i]) {
                        isToMove[j] = false;
                    }
                    toMove[i] = new Matrix();
                    isToMoveSet[i] = false;
                }
            }
            Iterator<Integer> iterator = toMoveSet.iterator();
            while (iterator.hasNext()) {
                int i = iterator.next();
                if (!isToMoveSet[i]) {
                    iterator.remove();
                    continue;
                }
                Iterator<Integer> j = toMove[i].iterator();
                bSet[counter] = new Matrix();
                aSet[counter] = new Matrix();
                toMove[counter] = new Matrix();
                while (j.hasNext()) {
                    int j1 = j.next();
                    j.remove();
                    isToMove[j1] = false;
                    Matrix.MyIterator b = inB[j1];
                    b.remove();
                    inB[j1] = bSet[counter].add2(j1);
                    stateToSet[j1] = counter;
                    if (inA[j1] != null) {
                        b = inA[j1];
                        b.remove();
                        inA[j1] = aSet[counter].add2(j1);
                    }
                }
                for (int c = 0; c < 26; c++) {
                    if (inQueue[i][c] || (aSet[counter].size() < aSet[i].size() && stateToSet[n - 1] != counter) || stateToSet[n - 1] == i) {
                        queue.add(new Pair(counter, c));
                        inQueue[counter][c] = true;
                    } else {
                        queue.add(new Pair(i, c));
                        inQueue[i][c] = true;
                    }
                }
                counter++;
                isToMoveSet[i] = false;
                iterator.remove();
            }
        }
        map = new Integer[counter];
        map[stateToSet[0]] = 0;
        minStates = 1;
        transitions = new LinkedList<>();
        minTerminal = new LinkedList<>();
        if (isTerminal[0]) {
            minTerminal.add(0);
        }
        q.clear();
        q.add(0);
        while (!q.isEmpty()) {
            int state = q.poll();
            for (int i = 0; i < 26; i++) {
                if (delta[state][i] < n - 1) {
                    int v = delta[state][i];
                    if (map[stateToSet[v]] == null) {
                        map[stateToSet[v]] = minStates++;
                        if (isTerminal[v]) {
                            minTerminal.add(map[stateToSet[v]]);
                        }
                        q.add(v);
                    }
                    transitions.add((map[stateToSet[state]] + 1) + " " + (map[stateToSet[v]] + 1) + " " + (char) (i + 'a'));
                }
            }
        }
        if (minStates == 1 && transitions.size() == 0 && minTerminal.size() == 0) {
            out.println("0 0 0\n");
            return;
        }
        out.println(minStates + " " + transitions.size() + " " + minTerminal.size());
        for (Integer i : minTerminal) {
            out.print((i + 1) + " ");
        }
        out.println();
        for (String s : transitions) {
            out.println(s);
        }
    }


    class Matrix extends AbstractList<Integer> {
        MyIterator head, tail;
        int size;

        public Matrix() {
            head = new MyIterator(null, null, null);
            tail = new MyIterator(null, head, null);
            head.next = tail;
        }

        @Override
        public boolean isEmpty() {
            return head.next == tail;
        }

        class MyIterator {
            MyIterator next, prev;
            Integer data;

            public boolean hasNext() {
                return next != null && next != tail;
            }

            public MyIterator(MyIterator next, MyIterator prev, Integer data) {
                this.data = data;
                if (prev != null) {
                    prev.next = this;
                }
                if (next != null) {
                    next.prev = this;
                }
                this.next = next;
                this.prev = prev;
                Matrix.this.size++;
            }

            public void remove() {
                prev.next = next;
                next.prev = prev;
                Matrix.this.size--;
            }
        }

        @Override
        public boolean add(Integer e) {
            new MyIterator(tail, tail.prev, e);
            return true;
        }

        public MyIterator add2(Integer e) {
            return new MyIterator(tail, tail.prev, e);
        }

        @Override
        public Iterator<Integer> iterator() {
            return new Iterator<Integer>() {
                MyIterator cur = head;

                @Override
                public boolean hasNext() {
                    return cur.hasNext();
                }

                @Override
                public Integer next() {
                    cur = cur.next;
                    return cur.data;
                }

                @Override
                public void remove() {
                    if (cur != head && cur != tail) {
                        cur.remove();
                    } else {
                        throw (new IllegalStateException());
                    }
                }
            };
        }

        @Override
        public Integer remove(int index) {
            if (index < size) {
                MyIterator cur = head;
                index++;
                while (index -->= 0) {
                    cur = cur.next;
                }
                cur.remove();
                return cur.data;
            } else {
                return null;
            }
        }

        @Override
        public Integer get(int arg0) {
            if (arg0 < size) {
                MyIterator cur = head;
                for (; arg0 >= 0; arg0--) {
                    cur = cur.next;
                }
                return cur.data;
            } else {
                return null;
            }
        }

        @Override
        public int size() {
            return size;
        }

    }


    public void run() {
        try {
            in = new FastScanner(new File("fastminimization.in"));
            out = new PrintWriter(new File("fastminimization.out"));

            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new Problem4().run();
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
}
