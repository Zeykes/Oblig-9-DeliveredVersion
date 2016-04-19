import java.util.*;
import java.io.*;

public class Monitor {
    static int traader;
    static int doedeTraader;
    static File input;
    static FileOutputStream output;
    static int frstLine;

    static String[] ls;
    static String[][] lsts;

    static String[] available;

    static Monitor me;

    public static void main(String[] args) {
      me = new Monitor();
      me.gogoPowerRangers(args);
    }
    public void gogoPowerRangers(String[] args) {
        frstLine = 0;
        Scanner bookworm = null;
        try {
            traader = Integer.parseInt(args[0]);
            input = new File(args[1]);
            output = new FileOutputStream(args[2], false);
            if (!input.exists()) {
                throw new FileNotFoundException(args[1]);
            }
            bookworm = new Scanner(input);
            frstLine = Integer.parseInt(bookworm.nextLine());
        } catch (Exception e) {
            System.out.println("Fant ikke fil eller kunne ikke parseInt!");
            e.printStackTrace();
            System.exit(0);
        }
        //her er filene funnet og programmet kan begynne
        ls = new String[frstLine];
        for (int i = 0; bookworm.hasNextLine(); i++) {
            ls[i] = bookworm.nextLine();
        }
        for (String s : ls) {
            if (s == null) {
                throw new NullPointerException("Problem med input fil!");
            }
        }
        //ABC SORTING
        boolean superABC = false;
        if (args.length > 3) {
          if (args[3].equals("S:ABC")) {
            superABC = true;
          }
        }
        //ABC SORTING END
        final long startTime = System.currentTimeMillis();

        //n = antOrd/antTraader ord(± 1)
        lsts = new String[traader][0];
        int per = frstLine / traader;
        int mod = frstLine % traader;
        for (int atLS = 0, atTR = 0; atLS < ls.length && atTR < traader; atTR++) {
            String[] insrt;
            if (mod > 0) {
              mod--;
              insrt = new String[per + 1];
            } else {
              insrt = new String[per];
            }
            for (int atINS = 0; atINS < insrt.length && atLS < ls.length; atINS++, atLS++) {
              insrt[atINS] = ls[atLS];
            }
            lsts[atTR] = insrt;
        }






        //her er vi klar for aa lage traader og begynne sorteringen
        available = null;
        doedeTraader = 0;
        for (int i = 0; i < traader; i++) {
            new SortThread(lsts[i], superABC).start();
        }
        synchronized (me) {
          try {
            me.wait();
          } catch (Exception e) {
            //silence
          }
        }


        final long endTime = System.currentTimeMillis();
        System.out.println("\nTid brukt totalt: " + ((endTime-startTime)/1000.0));

        //sjekker at alt er i orden
        System.out.println("Riktig sortert: " + LsTool.isSorted(available));
        System.out.print("Ingen null-peker til slutt: ");
        if (available[available.length - 1] == null) {
          System.out.println("false");
        } else {
          System.out.println("true");
        }
        System.out.println("Riktig lengde: " + (available.length == frstLine));
        System.out.println("Antall ord sortert: " + frstLine);



        try {
          PrintWriter pw = new PrintWriter(output);
          pw.println(frstLine);
          for (String s : available) {
            pw.println(s);
          }
          pw.close();
        } catch (Exception e) {
          System.out.println("Error i skriving til fil!");
        }
    }
    public synchronized String[][] feedMonitor(String[] a) {
        if (available == null) {
            available = a;
            if (available.length == frstLine) {
              me.notify();
            }
            return null;
        }
        String[][] ret = new String[2][0];
        ret[0] = available;
        ret[1] = a;
        available = null;
        return ret;
    }
}
