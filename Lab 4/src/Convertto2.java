/**
 * Created by Alex on 18.12.15.
 */

import java.io.*;
import java.util.*;

public class Convertto2 {
    PrintWriter out;

    public void solve() throws IOException {
        StringBuilder sb = new StringBuilder();

        sb.append("start: s\n");
        sb.append("accept: ac\n");
        sb.append("reject: rj\n");
        sb.append("blank: _\n");

        sb.append("s 0 -> s 0 >\n");
        sb.append("s _ -> ac _ <\n");
        sb.append("s 1 -> ss 1 >\n");
        sb.append("s 2 -> ss 2 >\n");

        sb.append("ss 0 -> ss 0 <\n");
        sb.append("ss 1 -> ss 1 <\n");
        sb.append("ss 2 -> ss 2 <\n");
        sb.append("ss _ -> maybe_end _ >\n");

        sb.append("maybe_end _ -> end _ <\n");
        sb.append("end _ -> to_begin _ <\n");
        sb.append("maybe_end 0 -> do 0 ^\n");
        sb.append("maybe_end 1 -> do 1 ^\n");
        sb.append("maybe_end 2 -> do 2 ^\n");

        sb.append("to_begin 0 -> to_begin 0 <\n");
        sb.append("to_begin 1 -> to_begin 1 <\n");
        sb.append("to_begin 2 -> to_begin 2 <\n");
        sb.append("to_begin _ -> do _ >\n");

        sb.append("do 0 -> do 0 >\n");
        sb.append("do 1 -> have1 1 >\n");
        sb.append("do 2 -> have0 2 >\n");

        sb.append("do _ -> all _ <\n");
        sb.append("all 0 -> all _ <\n");
        sb.append("all 1 -> all _ <\n");
        sb.append("all 2 -> all _ <\n");
        sb.append("all _ -> finish _ <\n");

        sb.append("finish 0 -> finish 0 <\n");
        sb.append("finish 1 -> finish 1 <\n");
        sb.append("finish 2 -> finish 2 <\n");
        sb.append("finish _ -> ac _ >\n");

        sb.append("have0 0 -> have0 0 >\n");
        sb.append("have0 1 -> have1 1 >\n");
        sb.append("have0 2 -> have0 2 >\n");
        sb.append("have0 _ -> go0 _ <\n");

        sb.append("go0 0 -> go0 0 <\n");
        sb.append("go0 1 -> go0 1 <\n");
        sb.append("go0 2 -> go0 2 <\n");
        sb.append("go0 _ -> write0 _ <\n");

        sb.append("write0 0 -> write0 0 <\n");
        sb.append("write0 1 -> write0 1 <\n");
        sb.append("write0 _ -> go 0 >\n");

        sb.append("go 0 -> go 0 >\n");
        sb.append("go 1 -> go 1 >\n");
        sb.append("go _ -> div _ >\n");

        sb.append("div 0 -> div 0 >\n");;
        sb.append("div 1 -> div_carry 0 >\n");
        sb.append("div 2 -> div 1 >\n");
        sb.append("div _ -> ss _ <\n");

        sb.append("div_carry 0 -> div_carry 1 >\n");
        sb.append("div_carry 1 -> div 2 >\n");
        sb.append("div_carry 2 -> div_carry 2 >\n");
        sb.append("div_carry _ -> ss _ <\n");

        sb.append("have1 0 -> have1 0 >\n");
        sb.append("have1 1 -> have0 1 >\n");
        sb.append("have1 2 -> have1 2 >\n");
        sb.append("have1 _ -> go1 _ <\n");

        sb.append("go1 0 -> go1 0 <\n");
        sb.append("go1 1 -> go1 1 <\n");
        sb.append("go1 2 -> go1 2 <\n");
        sb.append("go1 _ -> write1 _ <\n");

        sb.append("write1 0 -> write1 0 <\n");
        sb.append("write1 1 -> write1 1 <\n");
        sb.append("write1 _ -> go 1 >");

        out.println(sb);
    }

    public void run() {
        try {
            out = new PrintWriter(new File("convertto2.out"));

            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] arg) {
        new Convertto2().run();
    }
}
