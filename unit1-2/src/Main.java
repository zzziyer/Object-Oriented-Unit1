import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String group;


        ArrayList<Item> last = new ArrayList<>(); //最终输出
        Scanner in = new Scanner(System.in);
        String eq = "";
        if (in.hasNextLine()) {
            eq = in.nextLine();
        } else {
            System.out.println("WRONG FORMAT!");
            System.exit(0);
        }
        StringBuffer handle = new StringBuffer(eq);
        if (Polys.checkNoneOrBlank(eq) == 0
                || Polys.checkSpace(handle) == 0) {  //TODO 或者全是空格
            System.out.println("WRONG FORMAT!");
            System.exit(0);
        }
        int i;
        handle = new StringBuffer(Polys.deleteSpace(handle).toString());
        ArrayList<String> fine; //保存切片字符
        ArrayList<Item> items; //保存生成项
        fine = Polys.splitByPlusSign(handle);
        items = Polys.createItem(fine);
        if (items.size() == 0) {
            System.out.println("WRONG FORMAT!");
            System.exit(0);
        }
        ArrayList<Item> merge = Polys.sort(items);
        for (Item k : merge) {
            last.addAll(k.derivation(k));
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
