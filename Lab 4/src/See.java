import java.io.*;
import java.util.*;

public class See {
    FastScanner in;

    ArrayList<Character> curr = new ArrayList<>();
    public static final String myFile = "factorial";

    class From {
        String from;
        char symbol_from;

        public From(String from, char symbol_from) {
            this.from = from;
            this.symbol_from = symbol_from;
        }

        @Override
        public String toString() {
            return String.format("%s\n%s", from, symbol_from);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof From) {
                From curr = (From) obj;
                return (curr.from.equals(this.from) && curr.symbol_from == this.symbol_from);
            }

            return false;
        }

        @Override
        public int hashCode() {
            return 1;
        }
    }

    class To {
        String to;
        char symbol_to;
        int change;

        public To(String to, char symbol_to, int change) {
            this.to = to;
            this.symbol_to = symbol_to;
            this.change = change;
        }

        @Override
        public String toString() {
            return String.format("%s\n%s\n%d", to, symbol_to, change);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof To) {
                To curr = (To) obj;
                return (curr.to.equals(this.to) && curr.symbol_to == this.symbol_to && curr.change == this.change);
            }

            return false;
        }

        @Override
        public int hashCode() {
            return 1;
        }
    }

    HashMap<From, To> map = new HashMap<>();

    public void solve() throws IOException {
        try {
            while (true) {
                curr.add(in.next().charAt(0));
            }
        } catch (NullPointerException ignored) {

        }

        in = new FastScanner(new File(myFile + ".out"));
        String tmp = in.br.readLine();
        String curr_vertex = tmp.substring(tmp.indexOf(":") + 2);
        tmp = in.br.readLine();
        String ac = tmp.substring(tmp.indexOf(":") + 2);
        in.br.readLine();
        in.br.readLine();

        try {
            while (true) {
                String from = in.next();
                char symbol_from = in.next().charAt(0);
                in.next();
                String to = in.next();
                char symbol_to = in.next().charAt(0);
                char go = in.next().charAt(0);
                int change = 0;

                switch (go) {
                    case '>':
                        change = 1;
                        break;
                    case '<':
                        change = -1;
                        break;
                }

                map.put(new From(from, symbol_from), new To(to, symbol_to, change));
            }
        } catch (NullPointerException ignored) {

        }

        int num = 100;

        for (int i = 0; i < num; i++) {
            curr.add('_');
        }

        for (int i = 0; i < num; i++) {
            curr.add(0, '_');
        }

        System.out.println("Current vertex: " + curr_vertex);

        for (Character character : curr) {
            System.out.print(character + " ");
        }

        System.out.println();

        for (int i = 0; i < num; i++) {
            System.out.print("  ");
        }

        System.out.println("^");

        int it = 0;

        while (!ac.equals(curr_vertex)) {
            if (it++ == 10_000_000) {
                break;
            }

            From now = new From(curr_vertex, curr.get(num));
            To go = map.get(now);
            curr_vertex = go.to;
            curr.set(num, go.symbol_to);
            num += go.change;
            System.out.println("Current vertex: " + curr_vertex);

            for (Character character : curr) {
                System.out.print(character + " ");
            }

            System.out.println();

            for (int i = 0; i < num; i++) {
                System.out.print("  ");
            }

            System.out.println("^");
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File(myFile + ".in"));

            solve();
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

        String next() throws NullPointerException {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return st.nextToken();
        }

        int nextInt() throws NullPointerException {
            return Integer.parseInt(next());
        }
    }

    public static void main(String[] arg) {
        new See().run();
    }
}
