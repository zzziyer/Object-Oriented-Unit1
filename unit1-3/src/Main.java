import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String eq = "";
        if (in.hasNextLine()) {
            eq = in.nextLine();
        } else {
            Polys.wrong();
        }
        StringBuffer handle = new StringBuffer(eq);
        if (Polys.checkNoneOrBlank(eq) == 0
                || Polys.checkSpace(handle) == 0 ||
                eq.matches(".*[^sincox()^*+\\-\\d \t].*")
                || Polys.brcket(eq)[0] < 0) {
            Polys.wrong();
        }
        handle = new StringBuffer(Polys.deleteSpace(handle).toString());
        if (!Polys.checkTribleAdd(handle)) {
            Polys.wrong();
        }
        Base last = Build.buildtree(handle.toString());
        System.out.println(last.div(last));
    }
}
