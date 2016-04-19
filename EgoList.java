public class EgoList {
  class Node {
    String contains = "-";
    Node(String s) {
      contains = s;
    }
    Node nxt;
    public void countNodes(int i) throws Th1 {
      try {
        i++;
        if (nxt == null) {
          Th1 t = new Th1();
          t.ego = i;
          throw t;
        }
        nxt.countNodes(i);
      } catch (Exception e) {

      }
    }
    public void addAll(String[] ret, int at) throws Th2 {
      try {
        at++;
        ret[at - 1] = contains;
        if (nxt == null) {
          Th2 t = new Th2();
          t.ego = ret;
          throw t;
        } else {
        }
        nxt.addAll(ret, at);
      } catch (Exception e) {
        System.out.println("\nERRD");
        e.printStackTrace();
        System.exit(0);
      }
    }
  }
  class Th1 extends Throwable {
    int ego;
  }
  class Th2 extends Throwable {
    String[] ego;
  }
  Node frst = null;
  public void add(String s) {
    if (frst == null) {
      frst = new Node(s);
    } else {
      Node scnd = frst;
      frst = new Node(s);
      frst.nxt = scnd;
    }
  }
  public int length() {
    if (frst == null) {
      return 0;
    }
    try {
      frst.countNodes(0);
      return -1;
    } catch (Th1 t) {
      return t.ego;
    }
  }
  public String[] toArray() {
    if (frst == null) {
      return new String[0];
    }
    String[] ret = new String[length()];
    try {
      frst.addAll(ret, 0);
      return null;
    } catch (Th2 t) {
      return t.ego;
    }
  }
}
