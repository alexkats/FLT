import java.io.*;

public class Zero {
    PrintWriter out;

    public void solve() throws IOException {
        String s = "start: s\n" + "accept: ac\n" +
                "reject: rj\n" +
                "blank: _\n" +
                "s _ -> ac _ ^\n" +
                "s 0 -> n _ >\n" +
                "n 0 -> s _ >\n" +
                "n _ -> rj _ >";

        out.println(s);
    }

    public void run() {
        try {
            out = new PrintWriter(new File("zero.out"));

            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] arg) {
        new Zero().run();
    }
}
