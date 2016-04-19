import java.util.ArrayList;

public class Record {
  static ArrayList<String> rec = new ArrayList<String>();
  public static void add(Object o, String s) {
    rec.add("<" + o + ":" + s + ">");
  }
  public static synchronized void addNullCount(Object o, String s, Object[] n) {
    int c = 0;
    for (Object nn : n) {
      if (nn == null) {
        c++;
      }
    }
    add(o, s + ":NC:[" + c + "/" + n.length + "]");
  }
  public static void forTheRecord() {
    System.out.println();
    for (int i = 0; i < rec.size(); i++) {
      System.out.println(rec.get(i));
    }
  }
}
