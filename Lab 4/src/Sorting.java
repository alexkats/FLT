/**
 * Created by Alex on 17.12.15.
 */

import java.io.*;
import java.util.*;

public class Sorting {
    PrintWriter out;

    public void solve() throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("3\n");
        sb.append("S 0 _ _ -> s 0 ^ _ ^ _ ^\n"); 
        sb.append("S 1 _ _ -> s 1 ^ _ ^ _ ^\n");

        sb.append("s 0 _ _ -> s 0 > 0 > _ ^\n");
        sb.append("s 1 _ _ -> s 1 > 1 > _ ^\n");
        sb.append("s | _ _ -> next_number | > _ ^ _ ^\n"); 

        sb.append("next_number 0 _ _ -> next_number 0 > _ ^ 0 >\n");
        sb.append("next_number 1 _ _ -> next_number 1 > _ ^ 1 >\n");
        sb.append("next_number | _ _ -> go_left | ^ _ < _ <\n"); 
        sb.append("next_number _ _ _ -> go_left _ ^ _ < _ <\n");

        sb.append("go_left _ 0 0 -> go_left _ ^ 0 < 0 <\n");
        sb.append("go_left _ 0 1 -> go_left _ ^ 0 < 1 <\n");
        sb.append("go_left _ 1 0 -> go_left _ ^ 1 < 0 <\n");
        sb.append("go_left _ 1 1 -> go_left _ ^ 1 < 1 <\n");

        sb.append("go_left | 0 0 -> go_left | ^ 0 < 0 <\n");
        sb.append("go_left | 0 1 -> go_left | ^ 0 < 1 <\n");
        sb.append("go_left | 1 0 -> go_left | ^ 1 < 0 <\n");
        sb.append("go_left | 1 1 -> go_left | ^ 1 < 1 <\n");

        sb.append("go_left _ 0 _ -> go_left _ ^ 0 < _ <\n");
        sb.append("go_left _ 1 _ -> go_left _ ^ 1 < _ <\n");
        sb.append("go_left | 0 _ -> go_left | ^ 0 < _ <\n");
        sb.append("go_left | 1 _ -> go_left | ^ 1 < _ <\n");

        sb.append("go_left _ _ 0 -> to_start_compare _ ^ _ > 0 >\n"); 
        sb.append("go_left _ _ 1 -> to_start_compare _ ^ _ > 1 >\n");
        sb.append("go_left _ _ _ -> to_start_compare _ ^ _ > _ >\n");

        sb.append("go_left | _ 0 -> to_start_compare | ^ _ > 0 >\n");
        sb.append("go_left | _ 1 -> to_start_compare | ^ _ > 1 >\n");
        sb.append("go_left | _ _ -> to_start_compare | ^ _ > _ >\n");

        sb.append("to_start_compare _ 0 0 -> to_start_compare _ ^ 0 < 0 <\n");
        sb.append("to_start_compare _ 0 1 -> to_start_compare _ ^ 0 < 1 <\n");
        sb.append("to_start_compare _ 1 0 -> to_start_compare _ ^ 1 < 0 <\n");
        sb.append("to_start_compare _ 1 1 -> to_start_compare _ ^ 1 < 1 <\n");

        sb.append("to_start_compare | 0 0 -> to_start_compare | ^ 0 < 0 <\n");
        sb.append("to_start_compare | 0 1 -> to_start_compare | ^ 0 < 1 <\n");
        sb.append("to_start_compare | 1 0 -> to_start_compare | ^ 1 < 0 <\n");
        sb.append("to_start_compare | 1 1 -> to_start_compare | ^ 1 < 1 <\n");

        sb.append("to_start_compare _ _ 0 -> to_start_compare _ ^ _ < 0 <\n");
        sb.append("to_start_compare _ _ 1 -> to_start_compare _ ^ _ < 1 <\n");

        sb.append("to_start_compare | _ 0 -> to_start_compare | ^ _ < 0 <\n");
        sb.append("to_start_compare | _ 1 -> to_start_compare | ^ _ < 1 <\n");

        sb.append("to_start_compare _ 0 _ -> start_compare _ ^ 0 ^ _ ^\n"); 
        sb.append("to_start_compare _ 1 _ -> start_compare _ ^ 1 ^ _ ^\n");

        sb.append("to_start_compare | 0 _ -> start_compare | ^ 0 ^ _ ^\n");
        sb.append("to_start_compare | 1 _ -> start_compare | ^ 1 ^ _ ^\n");

        sb.append("to_start_compare _ _ _ -> start_compare _ ^ _ > _ >\n");
        sb.append("to_start_compare | _ _ -> start_compare | ^ _ > _ >\n");

        sb.append("start_compare _ 0 1 -> good _ ^ 0 ^ 1 ^\n"); 
        sb.append("start_compare | 0 1 -> good | ^ 0 ^ 1 ^\n");
        sb.append("start_compare _ _ 1 -> good _ ^ _ ^ 1 ^\n");
        sb.append("start_compare | _ 1 -> good | ^ _ ^ 1 ^\n");

        sb.append("start_compare _ 1 0 -> bad _ ^ 1 > 0 >\n"); 
        sb.append("start_compare | 1 0 -> bad | ^ 1 > 0 >\n");
        sb.append("start_compare _ 1 _ -> bad _ ^ 1 > _ >\n");
        sb.append("start_compare | 1 _ -> bad | ^ 1 > _ >\n");

        sb.append("start_compare _ 0 0 -> start_compare _ ^ 0 > 0 >\n");
        sb.append("start_compare _ 1 1 -> start_compare _ ^ 1 > 1 >\n");
        sb.append("start_compare | 0 0 -> start_compare | ^ 0 > 0 >\n");
        sb.append("start_compare | 1 1 -> start_compare | ^ 1 > 1 >\n");

        sb.append("start_compare _ _ 0 -> start_compare _ ^ _ > 0 >\n");
        sb.append("start_compare | _ 0 -> start_compare | ^ _ > 0 >\n");
        sb.append("start_compare _ 0 _ -> start_compare _ ^ 0 > _ >\n");
        sb.append("start_compare | 0 _ -> start_compare | ^ 0 > _ >\n");

        sb.append("start_compare _ _ _ -> all_good _ ^ _ < _ <\n"); 
        sb.append("start_compare | _ _ -> all_good | ^ _ < _ <\n");

        sb.append("good _ 0 0 -> good _ ^ 0 > 0 >\n");
        sb.append("good _ 0 1 -> good _ ^ 0 > 1 >\n");
        sb.append("good _ 1 0 -> good _ ^ 1 > 0 >\n");
        sb.append("good _ 1 1 -> good _ ^ 1 > 1 >\n");

        sb.append("good | 0 0 -> good | ^ 0 > 0 >\n");
        sb.append("good | 0 1 -> good | ^ 0 > 1 >\n");
        sb.append("good | 1 0 -> good | ^ 1 > 0 >\n");
        sb.append("good | 1 1 -> good | ^ 1 > 1 >\n");

        sb.append("good _ _ 0 -> good _ ^ _ > 0 >\n");
        sb.append("good _ _ 1 -> good _ ^ _ > 1 >\n");
        sb.append("good _ 0 _ -> good _ ^ 0 > _ >\n");
        sb.append("good _ 1 _ -> good _ ^ 1 > _ >\n");

        sb.append("good | _ 0 -> good | ^ _ > 0 >\n");
        sb.append("good | _ 1 -> good | ^ _ > 1 >\n");
        sb.append("good | 0 _ -> good | ^ 0 > _ >\n");
        sb.append("good | 1 _ -> good | ^ 1 > _ >\n");

        sb.append("good _ _ _ -> all_good _ ^ _ < _ <\n");
        sb.append("good | _ _ -> all_good | ^ _ < _ <\n");

        sb.append("all_good _ 0 0 -> all_good _ ^ _ < _ <\n");
        sb.append("all_good _ 0 1 -> all_good _ ^ _ < _ <\n");
        sb.append("all_good _ 1 0 -> all_good _ ^ _ < _ <\n");
        sb.append("all_good _ 1 1 -> all_good _ ^ _ < _ <\n");

        sb.append("all_good | 0 0 -> all_good | ^ _ < _ <\n");
        sb.append("all_good | 0 1 -> all_good | ^ _ < _ <\n");
        sb.append("all_good | 1 0 -> all_good | ^ _ < _ <\n");
        sb.append("all_good | 1 1 -> all_good | ^ _ < _ <\n");

        sb.append("all_good _ _ 0 -> all_good _ ^ _ < _ <\n");
        sb.append("all_good _ _ 1 -> all_good _ ^ _ < _ <\n");
        sb.append("all_good _ 0 _ -> all_good _ ^ _ < _ <\n");
        sb.append("all_good _ 1 _ -> all_good _ ^ _ < _ <\n");

        sb.append("all_good | _ 0 -> all_good | ^ _ < _ <\n");
        sb.append("all_good | _ 1 -> all_good | ^ _ < _ <\n");
        sb.append("all_good | 0 _ -> all_good | ^ _ < _ <\n");
        sb.append("all_good | 1 _ -> all_good | ^ _ < _ <\n");

        sb.append("all_good _ _ _ -> back_to_number _ < _ ^ _ ^\n"); 
        sb.append("all_good | _ _ -> back_to_number | < _ ^ _ ^\n");

        sb.append("back_to_number 0 _ _ -> back_to_number 0 < _ ^ _ ^\n");
        sb.append("back_to_number 1 _ _ -> back_to_number 1 < _ ^ _ ^\n");

        sb.append("back_to_number _ _ _ -> s _ > _ ^ _ ^\n");
        sb.append("back_to_number | _ _ -> s | > _ ^ _ ^\n");

        sb.append("bad _ 0 0 -> bad _ ^ 0 > 0 >\n");
        sb.append("bad _ 0 1 -> bad _ ^ 0 > 1 >\n");
        sb.append("bad _ 1 0 -> bad _ ^ 1 > 0 >\n");
        sb.append("bad _ 1 1 -> bad _ ^ 1 > 1 >\n");

        sb.append("bad | 0 0 -> bad | ^ 0 > 0 >\n");
        sb.append("bad | 0 1 -> bad | ^ 0 > 1 >\n");
        sb.append("bad | 1 0 -> bad | ^ 1 > 0 >\n");
        sb.append("bad | 1 1 -> bad | ^ 1 > 1 >\n");

        sb.append("bad _ _ 0 -> bad _ ^ _ > 0 >\n");
        sb.append("bad _ _ 1 -> bad _ ^ _ > 1 >\n");
        sb.append("bad _ 0 _ -> bad _ ^ 0 > _ >\n");
        sb.append("bad _ 1 _ -> bad _ ^ 1 > _ >\n");

        sb.append("bad | _ 0 -> bad | ^ _ > 0 >\n");
        sb.append("bad | _ 1 -> bad | ^ _ > 1 >\n");
        sb.append("bad | 0 _ -> bad | ^ 0 > _ >\n");
        sb.append("bad | 1 _ -> bad | ^ 1 > _ >\n");

        sb.append("bad _ _ _ -> write_first _ < _ < _ <\n"); 
        sb.append("bad | _ _ -> write_first | < _ < _ <\n");

        sb.append("write_first 0 0 0 -> write_first 0 < _ < 0 ^\n");
        sb.append("write_first 0 0 1 -> write_first 0 < _ < 1 ^\n");
        sb.append("write_first 1 0 0 -> write_first 0 < _ < 0 ^\n");
        sb.append("write_first 1 0 1 -> write_first 0 < _ < 1 ^\n");
        sb.append("write_first _ 0 0 -> write_first 0 < _ < 0 ^\n");
        sb.append("write_first _ 0 1 -> write_first 0 < _ < 1 ^\n");
        sb.append("write_first | 0 0 -> write_first 0 < _ < 0 ^\n");
        sb.append("write_first | 0 1 -> write_first 0 < _ < 1 ^\n");
        sb.append("write_first 0 0 _ -> write_first 0 < _ < _ ^\n");
        sb.append("write_first 1 0 _ -> write_first 0 < _ < _ ^\n");
        sb.append("write_first _ 0 _ -> write_first 0 < _ < _ ^\n");
        sb.append("write_first | 0 _ -> write_first 0 < _ < _ ^\n");

        sb.append("write_first 0 1 0 -> write_first 1 < _ < 0 ^\n");
        sb.append("write_first 0 1 1 -> write_first 1 < _ < 1 ^\n");
        sb.append("write_first 1 1 0 -> write_first 1 < _ < 0 ^\n");
        sb.append("write_first 1 1 1 -> write_first 1 < _ < 1 ^\n");
        sb.append("write_first _ 1 0 -> write_first 1 < _ < 0 ^\n");
        sb.append("write_first _ 1 1 -> write_first 1 < _ < 1 ^\n");
        sb.append("write_first | 1 0 -> write_first 1 < _ < 0 ^\n");
        sb.append("write_first | 1 1 -> write_first 1 < _ < 1 ^\n");
        sb.append("write_first 0 1 _ -> write_first 1 < _ < _ ^\n");
        sb.append("write_first 1 1 _ -> write_first 1 < _ < _ ^\n");
        sb.append("write_first _ 1 _ -> write_first 1 < _ < _ ^\n");
        sb.append("write_first | 1 _ -> write_first 1 < _ < _ ^\n");

        sb.append("write_first 0 _ 0 -> write_second | < _ ^ 0 ^\n");
        sb.append("write_first 0 _ 1 -> write_second | < _ ^ 1 ^\n");
        sb.append("write_first 1 _ 0 -> write_second | < _ ^ 0 ^\n");
        sb.append("write_first 1 _ 1 -> write_second | < _ ^ 1 ^\n");
        sb.append("write_first _ _ 0 -> write_second | < _ ^ 0 ^\n");
        sb.append("write_first _ _ 1 -> write_second | < _ ^ 1 ^\n");
        sb.append("write_first | _ 0 -> write_second | < _ ^ 0 ^\n");
        sb.append("write_first | _ 1 -> write_second | < _ ^ 1 ^\n");
        sb.append("write_first 0 _ _ -> write_second | < _ ^ _ ^\n");
        sb.append("write_first 1 _ _ -> write_second | < _ ^ _ ^\n");
        sb.append("write_first _ _ _ -> write_second | < _ ^ _ ^\n");
        sb.append("write_first | _ _ -> write_second | < _ ^ _ ^\n");

        sb.append("write_second 0 0 0 -> write_second 0 < 0 ^ _ <\n");
        sb.append("write_second 0 1 0 -> write_second 0 < 1 ^ _ <\n");
        sb.append("write_second 1 0 0 -> write_second 0 < 0 ^ _ <\n");
        sb.append("write_second 1 1 0 -> write_second 0 < 1 ^ _ <\n");
        sb.append("write_second _ 0 0 -> write_second 0 < 0 ^ _ <\n");
        sb.append("write_second _ 1 0 -> write_second 0 < 1 ^ _ <\n");
        sb.append("write_second | 0 0 -> write_second 0 < 0 ^ _ <\n");
        sb.append("write_second | 1 0 -> write_second 0 < 1 ^ _ <\n");
        sb.append("write_second 0 _ 0 -> write_second 0 < _ ^ _ <\n");
        sb.append("write_second 1 _ 0 -> write_second 0 < _ ^ _ <\n");
        sb.append("write_second _ _ 0 -> write_second 0 < _ ^ _ <\n");
        sb.append("write_second | _ 0 -> write_second 0 < _ ^ _ <\n");

        sb.append("write_second 0 0 1 -> write_second 1 < 0 ^ _ <\n");
        sb.append("write_second 0 1 1 -> write_second 1 < 1 ^ _ <\n");
        sb.append("write_second 1 0 1 -> write_second 1 < 0 ^ _ <\n");
        sb.append("write_second 1 1 1 -> write_second 1 < 1 ^ _ <\n");
        sb.append("write_second _ 0 1 -> write_second 1 < 0 ^ _ <\n");
        sb.append("write_second _ 1 1 -> write_second 1 < 1 ^ _ <\n");
        sb.append("write_second | 0 1 -> write_second 1 < 0 ^ _ <\n");
        sb.append("write_second | 1 1 -> write_second 1 < 1 ^ _ <\n");
        sb.append("write_second 0 _ 1 -> write_second 1 < _ ^ _ <\n");
        sb.append("write_second 1 _ 1 -> write_second 1 < _ ^ _ <\n");
        sb.append("write_second _ _ 1 -> write_second 1 < _ ^ _ <\n");
        sb.append("write_second | _ 1 -> write_second 1 < _ ^ _ <\n");

        sb.append("write_second 0 0 _ -> to_very_left 0 ^ 0 ^ _ ^\n"); 
        sb.append("write_second 0 1 _ -> to_very_left 0 ^ 1 ^ _ ^\n");
        sb.append("write_second 1 0 _ -> to_very_left 1 ^ 0 ^ _ ^\n");
        sb.append("write_second 1 1 _ -> to_very_left 1 ^ 1 ^ _ ^\n");
        sb.append("write_second _ 0 _ -> to_very_left _ ^ 0 ^ _ ^\n");
        sb.append("write_second _ 1 _ -> to_very_left _ ^ 1 ^ _ ^\n");
        sb.append("write_second | 0 _ -> to_very_left | ^ 0 ^ _ ^\n");
        sb.append("write_second | 1 _ -> to_very_left | ^ 1 ^ _ ^\n");
        sb.append("write_second 0 _ _ -> to_very_left 0 ^ _ ^ _ ^\n");
        sb.append("write_second 1 _ _ -> to_very_left 1 ^ _ ^ _ ^\n");
        sb.append("write_second _ _ _ -> to_very_left _ ^ _ ^ _ ^\n");
        sb.append("write_second | _ _ -> to_very_left | ^ _ ^ _ ^\n");

        sb.append("to_very_left 0 0 0 -> to_very_left 0 < 0 ^ 0 ^\n");
        sb.append("to_very_left 0 0 1 -> to_very_left 0 < 0 ^ 1 ^\n");
        sb.append("to_very_left 0 1 0 -> to_very_left 0 < 1 ^ 0 ^\n");
        sb.append("to_very_left 0 1 1 -> to_very_left 0 < 1 ^ 1 ^\n");
        sb.append("to_very_left 1 0 0 -> to_very_left 1 < 0 ^ 0 ^\n");
        sb.append("to_very_left 1 0 1 -> to_very_left 1 < 0 ^ 1 ^\n");
        sb.append("to_very_left 1 1 0 -> to_very_left 1 < 1 ^ 0 ^\n");
        sb.append("to_very_left 1 1 1 -> to_very_left 1 < 1 ^ 1 ^\n");

        sb.append("to_very_left 0 0 _ -> to_very_left 0 < 0 ^ _ ^\n");
        sb.append("to_very_left 1 0 _ -> to_very_left 1 < 0 ^ _ ^\n");
        sb.append("to_very_left 0 1 _ -> to_very_left 0 < 1 ^ _ ^\n");
        sb.append("to_very_left 1 1 _ -> to_very_left 1 < 1 ^ _ ^\n");
        sb.append("to_very_left 0 _ 0 -> to_very_left 0 < _ ^ 0 ^\n");
        sb.append("to_very_left 0 _ 1 -> to_very_left 0 < _ ^ 1 ^\n");
        sb.append("to_very_left 1 _ 0 -> to_very_left 1 < _ ^ 0 ^\n");
        sb.append("to_very_left 1 _ 1 -> to_very_left 1 < _ ^ 1 ^\n");
        sb.append("to_very_left 0 _ _ -> to_very_left 0 < _ ^ _ ^\n");
        sb.append("to_very_left 1 _ _ -> to_very_left 1 < _ ^ _ ^\n");

        sb.append("to_very_left _ _ _ -> s _ > _ ^ _ ^\n");
        sb.append("to_very_left | _ _ -> to_very_left | < _ ^ _ ^\n");

        sb.append("to_very_start _ 0 0 -> s _ > 0 ^ 0 ^\n");
        sb.append("to_very_start _ 0 1 -> s _ > 0 ^ 1 ^\n");
        sb.append("to_very_start _ 1 0 -> s _ > 1 ^ 0 ^\n");
        sb.append("to_very_start _ 1 1 -> s _ > 1 ^ 1 ^\n");
        sb.append("to_very_start _ 0 _ -> s _ > 0 ^ _ ^\n");
        sb.append("to_very_start _ 1 _ -> s _ > 1 ^ _ ^\n");
        sb.append("to_very_start _ _ 0 -> s _ > _ ^ 0 ^\n");
        sb.append("to_very_start _ _ 1 -> s _ > _ ^ 1 ^\n");
        sb.append("to_very_start _ _ _ -> s _ > _ ^ _ ^\n");

        sb.append("to_very_start | 0 0 -> s | > 0 ^ 0 ^\n");
        sb.append("to_very_start | 0 1 -> s | > 0 ^ 1 ^\n");
        sb.append("to_very_start | 1 0 -> s | > 1 ^ 0 ^\n");
        sb.append("to_very_start | 1 1 -> s | > 1 ^ 1 ^\n");
        sb.append("to_very_start | 0 _ -> s | > 0 ^ _ ^\n");
        sb.append("to_very_start | 1 _ -> s | > 1 ^ _ ^\n");
        sb.append("to_very_start | _ 0 -> s | > _ ^ 0 ^\n");
        sb.append("to_very_start | _ 1 -> s | > _ ^ 1 ^\n");
        sb.append("to_very_start | _ _ -> s | > _ ^ _ ^\n");

        sb.append("s _ _ _ -> end _ ^ _ < _ ^\n"); 
        sb.append("end _ 0 _ -> end _ ^ _ < _ ^\n");
        sb.append("end _ 1 _ -> end _ ^ _ < _ ^\n");
        sb.append("end _ _ _ -> to_ac _ < _ ^ _ ^\n"); 
        sb.append("to_ac 0 _ _ -> to_ac 0 < _ ^ _ ^\n");
        sb.append("to_ac 1 _ _ -> to_ac 1 < _ ^ _ ^\n");
        sb.append("to_ac | _ _ -> to_ac | < _ ^ _ ^\n");
        sb.append("to_ac _ _ _ -> AC _ > _ ^ _ ^");

        out.println(sb);
    }

    public void run() {
        try {
            out = new PrintWriter(new File("sorting.out"));

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
        new Sorting().run();
    }
}
