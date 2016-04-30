import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Factorial {
    PrintWriter out;

    public void solve() throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("start: s\n");
        sb.append("accept: ac\n");
        sb.append("reject: rj\n");
        sb.append("blank: _\n");

        sb.append("s 0 -> s 0 >\n"); 
        sb.append("s 1 -> s 1 >\n");
        sb.append("s _ -> ss 2 >\n"); 

        sb.append("ss _ -> sss 1 >\n"); 
        sb.append("sss _ -> back_to_ss 0 <\n"); 

        sb.append("back_to_ss 1 -> start 1 <\n"); 
        sb.append("start 2 -> maybe_end 2 <\n"); 

        sb.append("maybe_end 0 -> maybe_end 0 <\n");
        sb.append("maybe_end 1 -> real_start 1 ^\n"); 
        sb.append("maybe_end _ -> end _ >\n"); 

        sb.append("real_start 0 -> real_start 0 >\n");
        sb.append("real_start 1 -> real_start 1 >\n");
        sb.append("real_start 2 -> pow0 2 <\n"); 

        sb.append("pow0 0 -> pow1 0 <\n"); 
        sb.append("pow0 1 -> mul_pow0 1 ^\n"); 
        sb.append("mul_pow0 1 -> to_start_pow0 1 ^\n"); 

        sb.append("to_start_pow0 0 -> to_start_pow0 0 >\n");
        sb.append("to_start_pow0 1 -> to_start_pow0 1 >\n");
        sb.append("to_start_pow0 2 -> no_mul 9 >\n"); 

        sb.append("no_mul 0 -> no_mul_have0 0 >\n"); 
        sb.append("no_mul 1 -> no_mul_have1 1 >\n"); 
        sb.append("no_mul _ -> to_start_mul _ <\n"); 

        sb.append("no_mul_have0 0 -> no_mul 0 >\n");
        sb.append("no_mul_have0 1 -> no_mul 1 >\n");
        sb.append("no_mul_have1 0 -> no_mul 1 >\n");
        sb.append("no_mul_have1 1 -> need_mul 0 >\n"); 

        sb.append("need_mul 0 -> need_mul_have0 0 >\n"); 
        sb.append("need_mul 1 -> need_mul_have1 1 >\n"); 
        sb.append("need_mul _ -> need_mul_0 0 >\n"); 

        sb.append("need_mul_have0 0 -> no_mul 1 >\n");
        sb.append("need_mul_have0 1 -> need_mul 0 >\n");
        sb.append("need_mul_have1 0 -> need_mul 0 >\n");
        sb.append("need_mul_have1 1 -> need_mul 1 >\n");

        sb.append("need_mul_0 _ -> need_mul_01 1 <\n"); 
        sb.append("need_mul_01 0 -> to_start_mul 0 <\n");

        sb.append("to_start_mul 0 -> to_start_mul 0 <\n");
        sb.append("to_start_mul 1 -> to_start_mul 1 <\n");
        sb.append("to_start_mul 5 -> next_mul_pow4 2 <\n"); 
        sb.append("to_start_mul 6 -> next_mul_pow3 2 <\n"); 
        sb.append("to_start_mul 7 -> next_mul_pow2 2 <\n"); 
        sb.append("to_start_mul 8 -> next_mul_pow1 2 <\n"); 
        sb.append("to_start_mul 9 -> next_mul_pow0 2 <\n"); 

        sb.append("next_mul_pow0 0 -> pow1 0 <\n");
        sb.append("next_mul_pow0 1 -> pow1 1 <\n");

        sb.append("next_mul_pow1 0 -> next_mul_pow1_1 0 <\n"); 
        sb.append("next_mul_pow1 1 -> next_mul_pow1_1 1 <\n");
        sb.append("next_mul_pow1_1 0 -> pow2 0 <\n"); 
        sb.append("next_mul_pow1_1 1 -> pow2 1 <\n");

        sb.append("next_mul_pow2 0 -> next_mul_pow2_1 0 <\n"); 
        sb.append("next_mul_pow2 1 -> next_mul_pow2_1 1 <\n");
        sb.append("next_mul_pow2_1 0 -> next_mul_pow2_2 0 <\n"); 
        sb.append("next_mul_pow2_1 1 -> next_mul_pow2_2 1 <\n");
        sb.append("next_mul_pow2_2 0 -> pow3 0 <\n"); 
        sb.append("next_mul_pow2_2 1 -> pow3 1 <\n");

        sb.append("next_mul_pow3 0 -> next_mul_pow3_1 0 <\n"); 
        sb.append("next_mul_pow3 1 -> next_mul_pow3_1 1 <\n");
        sb.append("next_mul_pow3_1 0 -> next_mul_pow3_2 0 <\n"); 
        sb.append("next_mul_pow3_1 1 -> next_mul_pow3_2 1 <\n");
        sb.append("next_mul_pow3_2 0 -> next_mul_pow3_3 0 <\n"); 
        sb.append("next_mul_pow3_2 1 -> next_mul_pow3_3 1 <\n");
        sb.append("next_mul_pow3_3 0 -> pow4 0 <\n"); 
        sb.append("next_mul_pow3_3 1 -> pow4 1 <\n");

        sb.append("next_mul_pow4 0 -> next_mul_pow4_1 0 <\n"); 
        sb.append("next_mul_pow4 1 -> next_mul_pow4_1 1 <\n");
        sb.append("next_mul_pow4_1 0 -> next_mul_pow4_2 0 <\n"); 
        sb.append("next_mul_pow4_1 1 -> next_mul_pow4_2 1 <\n");
        sb.append("next_mul_pow4_2 0 -> next_mul_pow4_3 0 <\n"); 
        sb.append("next_mul_pow4_2 1 -> next_mul_pow4_3 1 <\n");
        sb.append("next_mul_pow4_3 0 -> next_mul_pow4_4 0 <\n"); 
        sb.append("next_mul_pow4_3 1 -> next_mul_pow4_4 1 <\n");
        sb.append("next_mul_pow4_4 0 -> pow5 0 <\n"); 
        sb.append("next_mul_pow4_4 1 -> pow5 1 <\n");

        sb.append("pow1 0 -> to_start_copy_pow1 0 ^\n"); 
        sb.append("pow1 1 -> to_start_copy_pow1 1 ^\n");
        sb.append("pow1 _ -> to_start_sub _ >\n"); 

        sb.append("pow2 0 -> to_start_copy_pow2 0 ^\n"); 
        sb.append("pow2 1 -> to_start_copy_pow2 1 ^\n");
        sb.append("pow2 _ -> to_start_sub _ >\n");

        sb.append("pow3 0 -> to_start_copy_pow3 0 ^\n"); 
        sb.append("pow3 1 -> to_start_copy_pow3 1 ^\n");
        sb.append("pow3 _ -> to_start_sub _ >\n");

        sb.append("pow4 0 -> to_start_copy_pow4 0 ^\n"); 
        sb.append("pow4 1 -> to_start_copy_pow4 1 ^\n");
        sb.append("pow4 _ -> to_start_sub _ >\n");

        sb.append("pow5 _ -> to_start_sub _ >\n");

        sb.append("to_start_sub 0 -> to_start_sub 0 >\n");
        sb.append("to_start_sub 1 -> to_start_sub 1 >\n");
        sb.append("to_start_sub 2 -> sub 2 <\n"); 

        sb.append("sub 0 -> sub 1 <\n");
        sb.append("sub 1 -> maybe_last 0 <\n"); 

        sb.append("maybe_last 0 -> no_last 0 >\n"); 
        sb.append("maybe_last 1 -> no_last 1 >\n");
        sb.append("maybe_last _ -> last _ >\n"); 
        sb.append("no_last 0 -> to_start_next 0 >\n"); 
        sb.append("last 0 -> to_start_next _ >\n");

        sb.append("to_start_next 0 -> to_start_next 0 >\n");
        sb.append("to_start_next 1 -> to_start_next 1 >\n");
        sb.append("to_start_next 2 -> finish 2 >\n"); 

        sb.append("finish 0 -> go_right 0 >\n"); 
        sb.append("finish 1 -> go_right 1 >\n");
        sb.append("finish _ -> from_begin _ <\n"); 

        sb.append("go_right 0 -> go_left0 0 <\n"); 
        sb.append("go_right 1 -> go_left1 0 <\n"); 
        sb.append("go_left0 0 -> do_nothing 0 >\n"); 
        sb.append("go_left0 1 -> do_nothing 0 >\n");
        sb.append("go_left1 0 -> do_nothing 1 >\n");
        sb.append("go_left1 1 -> do_nothing 1 >\n");
        sb.append("do_nothing 0 -> finish 0 >\n");

        sb.append("from_begin 0 -> from_begin 0 <\n");
        sb.append("from_begin 1 -> from_begin 1 <\n");
        sb.append("from_begin 2 -> start 2 ^\n");

        sb.append("to_start_copy_pow1 0 -> to_start_copy_pow1 0 >\n");
        sb.append("to_start_copy_pow1 1 -> to_start_copy_pow1 1 >\n");
        sb.append("to_start_copy_pow1 2 -> copy 8 >\n"); 

        sb.append("to_start_copy_pow2 0 -> to_start_copy_pow2 0 >\n");
        sb.append("to_start_copy_pow2 1 -> to_start_copy_pow2 1 >\n");
        sb.append("to_start_copy_pow2 2 -> copy 7 >\n");

        sb.append("to_start_copy_pow3 0 -> to_start_copy_pow3 0 >\n");
        sb.append("to_start_copy_pow3 1 -> to_start_copy_pow3 1 >\n");
        sb.append("to_start_copy_pow3 2 -> copy 6 >\n");

        sb.append("to_start_copy_pow4 0 -> to_start_copy_pow4 0 >\n");
        sb.append("to_start_copy_pow4 1 -> to_start_copy_pow4 1 >\n");
        sb.append("to_start_copy_pow4 2 -> copy 5 >\n");

        sb.append("copy 0 -> copy0 0 >\n"); 
        sb.append("copy 1 -> copy1 0 >\n"); 
        sb.append("copy0 0 -> copy00 0 >\n"); 
        sb.append("copy0 1 -> copy00 1 >\n");
        sb.append("copy0 _ -> copy00 0 >\n");
        sb.append("copy1 0 -> copy11 0 >\n"); 
        sb.append("copy1 1 -> copy11 1 >\n");
        sb.append("copy1 _ -> copy11 0 >\n");

        sb.append("copy00 0 -> copy0 0 >\n");
        sb.append("copy00 1 -> copy1 0 >\n");
        sb.append("copy00 _ -> write_copied 0 >\n"); 
        sb.append("copy11 0 -> copy0 1 >\n");
        sb.append("copy11 1 -> copy1 1 >\n");
        sb.append("copy11 _ -> write_copied 1 >\n");
        sb.append("write_copied _ -> copied 0 ^\n"); 

        sb.append("copied 0 -> copied 0 <\n");
        sb.append("copied 1 -> copied 1 <\n");
        sb.append("copied 5 -> next_copy_pow4 2 <\n"); 
        sb.append("copied 6 -> next_copy_pow3 2 <\n"); 
        sb.append("copied 7 -> next_copy_pow2 2 <\n"); 
        sb.append("copied 8 -> next_copy_pow1 2 <\n"); 

        sb.append("next_copy_pow1 0 -> pow1_next 0 <\n"); 
        sb.append("next_copy_pow1 1 -> pow1_next 1 <\n");

        sb.append("next_copy_pow2 0 -> next_copy_pow2_1 0 <\n"); 
        sb.append("next_copy_pow2 1 -> next_copy_pow2_1 1 <\n");
        sb.append("next_copy_pow2_1 0 -> pow2_next 0 <\n"); 
        sb.append("next_copy_pow2_1 1 -> pow2_next 1 <\n");

        sb.append("next_copy_pow3 0 -> next_copy_pow3_1 0 <\n"); 
        sb.append("next_copy_pow3 1 -> next_copy_pow3_1 1 <\n");
        sb.append("next_copy_pow3_1 0 -> next_copy_pow3_2 0 <\n"); 
        sb.append("next_copy_pow3_1 1 -> next_copy_pow3_2 1 <\n");
        sb.append("next_copy_pow3_2 0 -> pow3_next 0 <\n"); 
        sb.append("next_copy_pow3_2 1 -> pow3_next 1 <\n");

        sb.append("next_copy_pow4 0 -> next_copy_pow4_1 0 <\n"); 
        sb.append("next_copy_pow4 1 -> next_copy_pow4_1 1 <\n");
        sb.append("next_copy_pow4_1 0 -> next_copy_pow4_2 0 <\n"); 
        sb.append("next_copy_pow4_1 1 -> next_copy_pow4_2 1 <\n");
        sb.append("next_copy_pow4_2 0 -> next_copy_pow4_3 0 <\n"); 
        sb.append("next_copy_pow4_2 1 -> next_copy_pow4_3 1 <\n");
        sb.append("next_copy_pow4_3 0 -> pow4_next 0 <\n"); 
        sb.append("next_copy_pow4_3 1 -> pow4_next 1 <\n");

        sb.append("pow1_next 0 -> pow2 0 <\n");
        sb.append("pow1_next 1 -> mul_pow1 1 ^\n"); 

        sb.append("pow2_next 0 -> pow3 0 <\n");
        sb.append("pow2_next 1 -> mul_pow2 1 ^\n"); 

        sb.append("pow3_next 0 -> pow4 0 <\n");
        sb.append("pow3_next 1 -> mul_pow3 1 ^\n"); 

        sb.append("pow4_next 0 -> pow5 0 <\n");
        sb.append("pow4_next 1 -> mul_pow4 1 ^\n"); 

        sb.append("mul_pow1 1 -> to_start_pow1 1 ^\n"); 
        sb.append("mul_pow2 1 -> to_start_pow2 1 ^\n"); 
        sb.append("mul_pow3 1 -> to_start_pow3 1 ^\n"); 
        sb.append("mul_pow4 1 -> to_start_pow4 1 ^\n"); 

        sb.append("to_start_pow1 0 -> to_start_pow1 0 >\n");
        sb.append("to_start_pow1 1 -> to_start_pow1 1 >\n");
        sb.append("to_start_pow1 2 -> no_mul 8 >\n");

        sb.append("to_start_pow2 0 -> to_start_pow2 0 >\n");
        sb.append("to_start_pow2 1 -> to_start_pow2 1 >\n");
        sb.append("to_start_pow2 2 -> no_mul 7 >\n");

        sb.append("to_start_pow3 0 -> to_start_pow3 0 >\n");
        sb.append("to_start_pow3 1 -> to_start_pow3 1 >\n");
        sb.append("to_start_pow3 2 -> no_mul 6 >\n");

        sb.append("to_start_pow4 0 -> to_start_pow4 0 >\n");
        sb.append("to_start_pow4 1 -> to_start_pow4 1 >\n");
        sb.append("to_start_pow4 2 -> no_mul 5 >\n");

        sb.append("end 0 -> end _ >\n");
        sb.append("end 1 -> end _ >\n");
        sb.append("end 2 -> all _ >\n"); 

        sb.append("all 0 -> pipe 0 >\n"); 
        sb.append("all 1 -> pipe 1 >\n");
        sb.append("all _ -> to_left _ <\n"); 

        sb.append("to_left 0 -> to_left0 _ <\n"); 
        sb.append("to_left 1 -> to_left1 _ <\n"); 
        sb.append("to_left _ -> to_first _ <\n"); 
        sb.append("to_left | -> to_left _ <\n");

        sb.append("to_left0 0 -> to_left0 0 <\n");
        sb.append("to_left0 1 -> to_left0 1 <\n");
        sb.append("to_left0 _ -> to_second0 _ <\n"); 
        sb.append("to_left0 | -> to_left0 | <\n");

        sb.append("to_left1 0 -> to_left1 0 <\n");
        sb.append("to_left1 1 -> to_left1 1 <\n");
        sb.append("to_left1 _ -> to_second1 _ <\n"); 
        sb.append("to_left1 | -> to_left1 | <\n");

        sb.append("to_second0 0 -> to_second0 0 <\n");
        sb.append("to_second0 1 -> to_second1 0 <\n");
        sb.append("to_second0 _ -> to_right 0 ^\n"); 

        sb.append("to_second1 0 -> to_second0 1 <\n");
        sb.append("to_second1 1 -> to_second1 1 <\n");
        sb.append("to_second1 _ -> to_right 1 ^\n");

        sb.append("to_right 0 -> to_right 0 >\n");
        sb.append("to_right 1 -> to_right 1 >\n");
        sb.append("to_right _ -> check_next _ >\n"); 

        sb.append("check_next 0 -> check_next 0 >\n");
        sb.append("check_next 1 -> check_next 1 >\n");
        sb.append("check_next | -> check_next | >\n");
        sb.append("check_next _ -> to_left _ <\n");

        sb.append("to_first 0 -> to_first 0 <\n");
        sb.append("to_first 1 -> to_first 1 <\n");
        sb.append("to_first _ -> ac _ >\n");

        sb.append("pipe 0 -> all | >\n");
        sb.append("pipe 1 -> all | >");

        out.println(sb);
    }

    public void run() {
        try {
            out = new PrintWriter(new File("factorial.out"));

            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] arg) {
        new Factorial().run();
    }
}
