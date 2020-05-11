import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String group;
        ArrayList<StringBuffer> fine = new ArrayList<>();
        ArrayList<Item> items = new ArrayList<>();
        ArrayList<Item> last = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        String eq = in.nextLine();
        if (eq.length() == 0 || eq.trim().length() == 0) {
            System.out.println("WRONG FORMAT!");
            System.exit(0);
        }
        StringBuffer handle = new StringBuffer(eq);
        int i;
        handle = new StringBuffer(Polys.handle(handle).toString());
        if (Polys.checkSpace(handle) == 0) {
            System.out.println("WRONG FORMAT!");
            System.exit(0);
        }
        handle = new StringBuffer(Polys.deleteSpace(handle).toString());
        Pattern poly = Pattern.compile("([-+]{0,2}\\d+\\*x\\^[-+]?\\d+[-+])|" +
                "([-+]{0,2}\\d+\\*x[-+])|" +
                "([-+]{0,2}x\\^[-+]?\\d+[-+])|" +
                "([-+]{0,2}x[-+])|" +
                "([-+]{0,2}\\d+[-+])");
        Matcher po = poly.matcher(handle);
        int num = 0;
        while (po.find()) {
            group = po.group();
            fine.add(new StringBuffer(group));
            num += group.length();
        }
        if (num != handle.length()) {
            System.out.println("WRONG FORMAT!");
            System.exit(0);
        } else {
            char ch = 'a';
            for (i = 0; i < fine.size(); i++) {
                if (i != 0) {
                    fine.get(i).insert(0, ch);
                }
                ch = fine.get(i).charAt(fine.get(i).length() - 1);
                fine.get(i).deleteCharAt(fine.get(i).length() - 1);
                items.add(new Item(fine.get(i).toString()));
            }
        }
        ArrayList<Item> merge = Polys.sort(items);
        for (Item k : merge) {
            last.add(k.derivation(k));
        }
        ArrayList<Item> l = Polys.sort(last);
        for (Item k : l) {
            int re = k.print(k);
            if (re == 0 && l.size() == 1) {
                System.out.println(0);
            }
        }
    }
}
