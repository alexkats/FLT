import java.io.*;
import java.util.*;

public class SeeMany {
    FastScanner in;

    ArrayList<ArrayList<Character>> curr = new ArrayList<>();
    public static final String myFile = "sorting";

    class From {
        String from;
        ArrayList<Character> symbol_from;

        public From(String from, ArrayList<Character> symbol_from) {
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

                if (!curr.from.equals(this.from)) {
                    return false;
                }

                if (curr.symbol_from.size() != this.symbol_from.size()) {
                    return false;
                }

                for (int i = 0; i < curr.symbol_from.size(); i++) {
                    if (curr.symbol_from.get(i) != this.symbol_from.get(i)) {
                        return false;
                    }
                }

                return true;
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
        ArrayList<Character> symbol_to;
        ArrayList<Integer> change;

        public To(String to, ArrayList<Character> symbol_to, ArrayList<Integer> change) {
            this.to = to;
            this.symbol_to = symbol_to;
            this.change = change;
        }

        @Override
        public String toString() {
            return String.format("%s\n%s\n%s", to, symbol_to, change);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof To) {
                To curr = (To) obj;
                return (curr.to.equals(this.to) && curr.symbol_to.equals(this.symbol_to) && curr.change.equals(this.change));
            }

            return false;
        }

        @Override
        public int hashCode() {
            return 1;
        }
    }

    HashMap<From, To> map = new HashMap<>();
    int all;

    public void solve() throws IOException {
        in = new FastScanner(new File(myFile + ".out"));
        all = in.nextInt();
        System.out.println(all);

        for (int i = 0; i < all; i++) {
            curr.add(new ArrayList<>());
        }

        in = new FastScanner(new File(myFile + ".in"));

        try {
            while (true) {
                curr.get(0).add(in.next().charAt(0));
            }
        } catch (NullPointerException ignored) {

        }

        in = new FastScanner(new File(myFile + ".out"));
        in.br.readLine();

        try {
            while (true) {
                String from = in.next();
                ArrayList<Character> symbol_from = new ArrayList<>();

                for (int i = 0; i < all; i++) {
                    symbol_from.add(in.next().charAt(0));
                }

                in.next();
                String to = in.next();
                ArrayList<Character> symbol_to = new ArrayList<>();
                ArrayList<Character> go = new ArrayList<>();
                ArrayList<Integer> change = new ArrayList<>();

                for (int i = 0; i < all; i++) {
                    symbol_to.add(in.next().charAt(0));
                    go.add(in.next().charAt(0));

                    switch (go.get(i)) {
                        case '>':
                            change.add(1);
                            break;
                        case '<':
                            change.add(-1);
                            break;
                        case '^':
                            change.add(0);
                            break;
                    }
                }

                map.put(new From(from, symbol_from), new To(to, symbol_to, change));
            }
        } catch (NullPointerException ignored) {

        }

        ArrayList<Integer> num = new ArrayList<>();
        num.add(30);

        for (int i = 0; i < num.get(0); i++) {
            curr.get(0).add('_');
        }

        for (int i = 0; i < num.get(0); i++) {
            curr.get(0).add(0, '_');
        }

        for (int i = 1; i < all; i++) {
            while (curr.get(i).size() != curr.get(0).size()) {
                curr.get(i).add('_');
            }
        }


        for (int i = 1; i < all; i++) {
            num.add(30);
        }

        String curr_vertex = "S";
        System.out.println("Current vertex: " + curr_vertex);

        for (int k = 0; k < all; k++) {

            for (Character character : curr.get(k)) {
                System.out.print(character + " ");
            }

            System.out.println();

            for (int i = 0; i < num.get(k); i++) {
                System.out.print("  ");
            }

            System.out.println("^");
        }

        int it = 0;

        while (!"ac".equals(curr_vertex.toLowerCase())) {
            if (it++ == 100_000_000) {
                break;
            }

            ArrayList<Character> symbols = new ArrayList<>();

            for (int i = 0; i < all; i++) {
                symbols.add(curr.get(i).get(num.get(i)));
            }

            From now = new From(curr_vertex, symbols);
            To go = map.get(now);
            curr_vertex = go.to;

            for (int i = 0; i < all; i++) {
                curr.get(i).set(num.get(i), go.symbol_to.get(i));
                num.set(i, num.get(i) + go.change.get(i));
            }
            System.out.println("Current vertex: " + curr_vertex);

            for (int i = 0; i < all; i++) {
                System.out.println("Tape " + (i + 1) + ":");

                for (Character character : curr.get(i)) {
                    System.out.print(character + " ");
                }

                System.out.println();

                for (int k = 0; k < num.get(i); k++) {
                    System.out.print("  ");
                }

                System.out.println("^");
            }
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
        new SeeMany().run();
    }
}
