import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Aplusb {
    PrintWriter out;

    public void solve() throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("start: s\n");
        sb.append("accept: ac\n");
        sb.append("reject: rj\n");
        sb.append("blank: _\n");

        sb.append("s 0 -> s 0 >\n");
        sb.append("s 1 -> s 1 >\n");
        sb.append("s + -> t + >\n");
        sb.append("t 0 -> copy_0_first 2 >\n");
        sb.append("t 1 -> copy_1_first 2 >\n");
        sb.append("copy_0_first 0 -> copy_0 0 >\n");
        sb.append("copy_1_first 0 -> copy_0 1 >\n");
        sb.append("copy_0_first 1 -> copy_1 0 >\n");
        sb.append("copy_1_first 1 -> copy_1 1 >\n");
        sb.append("copy_0_first _ -> go 0 ^\n");
        sb.append("copy_1_first _ -> go 1 ^\n");

        sb.append("copy_0 0 -> copy_0 0 >\n");
        sb.append("copy_0 1 -> copy_1 0 >\n");
        sb.append("copy_0 _ -> go 0 ^\n");
        sb.append("copy_1 0 -> copy_0 1 >\n");
        sb.append("copy_1 1 -> copy_1 1 >\n");
        sb.append("copy_1 _ -> go 1 ^\n");

        sb.append("go 0 -> go0l _ <\n");
        sb.append("go 1 -> go1l _ <\n");
        sb.append("go0l + -> swap0 + <\n");
        sb.append("go1l + -> swap1 + <\n");
        sb.append("go0l 0 -> go0l 0 <\n");
        sb.append("go0l 1 -> go0l 1 <\n");
        sb.append("go1l 0 -> go1l 0 <\n");
        sb.append("go1l 1 -> go1l 1 <\n");
        sb.append("go0l 2 -> go0l 2 <\n");
        sb.append("go1l 2 -> go1l 2 <\n");

        sb.append("swap0 0 -> swap00 + >\n");
        sb.append("swap0 _ -> swap00 + >\n");
        sb.append("swap0 1 -> swap01 + >\n");
        sb.append("swap1 0 -> swap10 + >\n");
        sb.append("swap1 _ -> swap10 + >\n");
        sb.append("swap1 1 -> swap11 + >\n");

        sb.append("swap00 + -> add0 0 ^\n");
        sb.append("swap01 + -> add0 1 ^\n");
        sb.append("swap10 + -> add1 0 ^\n");
        sb.append("swap11 + -> add1 1 ^\n");

        sb.append("add0 0 -> next 0 >\n");
        sb.append("add0 1 -> next 1 >\n");
        sb.append("add1 0 -> next 1 >\n");
        sb.append("add1 1 -> next_carry 0 >\n");

        sb.append("next 0 -> next 0 >\n");
        sb.append("next 1 -> next 1 >\n");
        sb.append("next 2 -> after_next 2 >\n");
        sb.append("after_next _ -> finish _ <\n");
        sb.append("after_next 0 -> search_last 0 >\n");
        sb.append("after_next 1 -> search_last 1 >\n");
        sb.append("search_last 0 -> search_last 0 >\n");
        sb.append("search_last 1 -> search_last 1 >\n");
        sb.append("search_last _ -> go _ <\n");
        sb.append("finish 0 -> finish 0 <\n");
        sb.append("finish 1 -> finish 1 <\n");
        sb.append("finish 2 -> finish _ <\n");
        sb.append("finish + -> copy_left + <\n");
        sb.append("copy_left 0 -> copy_right0 0 >\n");
        sb.append("copy_left 1 -> copy_right1 1 >\n");
        sb.append("copy_right0 0 -> left 0 <\n");
        sb.append("copy_right0 1 -> left 0 <\n");
        sb.append("copy_right0 + -> left 0 <\n");
        sb.append("copy_right1 0 -> left 1 <\n");
        sb.append("copy_right1 1 -> left 1 <\n");
        sb.append("copy_right1 + -> left 1 <\n");
        sb.append("left 0 -> copy_left 0 <\n");
        sb.append("left 1 -> copy_left 1 <\n");
        sb.append("left + -> copy_left + <\n");
        sb.append("copy_left _ -> almost_all _ >\n");
        sb.append("almost_all 0 -> all _ >\n");
        sb.append("almost_all 1 -> all _ >\n");
        sb.append("almost_all + -> all _ >\n");
        sb.append("all 0 -> all _ >\n");
        sb.append("all 1 -> ac 1 ^\n");
        sb.append("all _ -> ac 0 ^\n");

        sb.append("next_carry 0 -> next_carry 0 >\n");
        sb.append("next_carry 1 -> next_carry 1 >\n");
        sb.append("next_carry 2 -> after_next_carry 2 >\n");
        sb.append("after_next_carry _ -> finish_carry _ <\n");
        sb.append("after_next_carry 0 -> search_last_carry 0 >\n");
        sb.append("after_next_carry 1 -> search_last_carry 1 >\n");
        sb.append("search_last_carry 0 -> search_last_carry 0 >\n");
        sb.append("search_last_carry 1 -> search_last_carry 1 >\n");
        sb.append("search_last_carry _ -> go_carry _ <\n");
        sb.append("finish_carry 0 -> finish_carry 0 <\n");
        sb.append("finish_carry 1 -> finish_carry 1 <\n");
        sb.append("finish_carry 2 -> finish_carry _ <\n");
        sb.append("finish_carry + -> finish_carry_next + <\n");
        sb.append("finish_carry_next 0 -> finish_carry_prev 1 >\n");
        sb.append("finish_carry_next _ -> finish_carry_prev 1 >\n");
        sb.append("finish_carry_next 1 -> finish_carry_next 0 <\n");
        sb.append("finish_carry_prev 0 -> finish_carry_prev 0 >\n");
        sb.append("finish_carry_prev 1 -> finish_carry_prev 1 >\n");
        sb.append("finish_carry_prev + -> copy_left + <\n");

        sb.append("add0_carry 0 -> next 1 >\n");
        sb.append("add0_carry 1 -> next_carry 0 >\n");
        sb.append("add1_carry 0 -> next_carry 0 >\n");
        sb.append("add1_carry 1 -> next_carry 1 >\n");

        sb.append("go_carry 0 -> go0l_carry _ <\n");
        sb.append("go_carry 1 -> go1l_carry _ <\n");
        sb.append("go0l_carry 0 -> go0l_carry 0 <\n");
        sb.append("go0l_carry 1 -> go0l_carry 1 <\n");
        sb.append("go0l_carry 2 -> go0l_carry 2 <\n");
        sb.append("go1l_carry 0 -> go1l_carry 0 <\n");
        sb.append("go1l_carry 1 -> go1l_carry 1 <\n");
        sb.append("go1l_carry 2 -> go1l_carry 2 <\n");
        sb.append("go0l_carry + -> swap0_carry + <\n");
        sb.append("go1l_carry + -> swap1_carry + <\n");

        sb.append("swap0_carry 0 -> swap00_carry + >\n");
        sb.append("swap0_carry _ -> swap00_carry + >\n");
        sb.append("swap0_carry 1 -> swap01_carry + >\n");
        sb.append("swap1_carry 0 -> swap10_carry + >\n");
        sb.append("swap1_carry _ -> swap10_carry + >\n");
        sb.append("swap1_carry 1 -> swap11_carry + >\n");

        sb.append("swap00_carry + -> add0_carry 0 ^\n");
        sb.append("swap01_carry + -> add0_carry 1 ^\n");
        sb.append("swap10_carry + -> add1_carry 0 ^\n");
        sb.append("swap11_carry + -> add1_carry 1 ^");

        out.println(sb);
    }

    public void run() {
        try {
            out = new PrintWriter(new File("aplusb.out"));

            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] arg) {
        new Aplusb().run();
    }
}
