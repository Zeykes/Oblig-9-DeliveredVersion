public class SortThread extends Thread {
    private String[] old;
    boolean supABC;
    public SortThread(String[] myBunch, boolean supABC) {
        old = myBunch;
        this.supABC = supABC;
    }
    public void run() {
      if (supABC) {
        EgoList[] egl = new EgoList[27];
        for (int i = 0; i < 27; i++) {
          egl[i] = new EgoList();
        }
        for (String s : old) {
          if (s.substring(0, 1).equalsIgnoreCase("A")) {
            //NB: 1
            egl[0].add(s);
          } else if (s.substring(0, 1).equalsIgnoreCase("B")) {
            //NB: 1
            egl[1].add(s);
          } else if (s.substring(0, 1).equalsIgnoreCase("C")) {
            //NB: 1
            egl[2].add(s);
          } else if (s.substring(0, 1).equalsIgnoreCase("D")) {
            //NB: 1
            egl[3].add(s);
          } else if (s.substring(0, 1).equalsIgnoreCase("E")) {
            //NB: 1
            egl[4].add(s);
          } else if (s.substring(0, 1).equalsIgnoreCase("F")) {
            //NB: 1
            egl[5].add(s);
          } else if (s.substring(0, 1).equalsIgnoreCase("G")) {
            //NB: 1
            egl[6].add(s);
          } else if (s.substring(0, 1).equalsIgnoreCase("H")) {
            //NB: 1
            egl[7].add(s);
          } else if (s.substring(0, 1).equalsIgnoreCase("I")) {
            //NB: 1
            egl[8].add(s);
          } else if (s.substring(0, 1).equalsIgnoreCase("J")) {
            //NB: 1
            egl[9].add(s);
          } else if (s.substring(0, 1).equalsIgnoreCase("K")) {
            //NB: 1
            egl[10].add(s);
          } else if (s.substring(0, 1).equalsIgnoreCase("L")) {
            //NB: 1
            egl[11].add(s);
          } else if (s.substring(0, 1).equalsIgnoreCase("M")) {
            //NB: 1
            egl[12].add(s);
          } else if (s.substring(0, 1).equalsIgnoreCase("N")) {
            //NB: 1
            egl[13].add(s);
          } else if (s.substring(0, 1).equalsIgnoreCase("O")) {
            //NB: 1
            egl[14].add(s);
          } else if (s.substring(0, 1).equalsIgnoreCase("P")) {
            //NB: 1
            egl[15].add(s);
          } else if (s.substring(0, 1).equalsIgnoreCase("Q")) {
            //NB: 1
            egl[16].add(s);
          } else if (s.substring(0, 1).equalsIgnoreCase("R")) {
            //NB: 1
            egl[17].add(s);
          } else if (s.substring(0, 1).equalsIgnoreCase("S")) {
            //NB: 1
            egl[18].add(s);
          } else if (s.substring(0, 1).equalsIgnoreCase("T")) {
            //NB: 1
            egl[19].add(s);
          } else if (s.substring(0, 1).equalsIgnoreCase("U")) {
            //NB: 1
            egl[20].add(s);
          } else if (s.substring(0, 1).equalsIgnoreCase("V")) {
            //NB: 1
            egl[21].add(s);
          } else if (s.substring(0, 1).equalsIgnoreCase("W")) {
            //NB: 1
            egl[22].add(s);
          } else if (s.substring(0, 1).equalsIgnoreCase("X")) {
            //NB: 1
            egl[23].add(s);
          } else if (s.substring(0, 1).equalsIgnoreCase("Y")) {
            //NB: 1
            egl[24].add(s);
          } else if (s.substring(0, 1).equalsIgnoreCase("Z")) {
            //NB: 1
            egl[25].add(s);
          } else {
            //NB: 1
            egl[26].add(s);
          }
        }
        String[] newLs = new String[0];
        if (egl[26].toArray() == null) {
          System.out.println("Error (superABC): array i = " + 26 + " er null!");
        } else if (egl[26].toArray().length == 0) {
          //silent
        }
        try {
          newLs = LsTool.append(newLs, egl[26].toArray());
        } catch (NullPointerException npe) {
          System.out.println("Error (superABC): kunne ikke legge sammen arrays!");
        }
        for (int i = 0; i < 26; i++) {
          if (egl[i].toArray() == null) {
            System.out.println("Error (superABC): array i = " + i + " er null!");
            continue;
          } else if (egl[i].toArray().length == 0) {
            //silent
          }
          try {
            newLs = LsTool.append(newLs, egl[i].toArray());
          } catch (NullPointerException npe) {
            System.out.println("Error (superABC): kunne ikke legge sammen arrays!");
          }
        }
        old = newLs;
      }

        while (!LsTool.isSorted(old)) {
            for (int i = 1; i < old.length; i++) {
                if (!LsTool.isSorted(old[i - 1], old[i])) {
                    String s1 = old[i - 1];
                    String s2 = old[i];
                    old[i - 1] = s2;
                    old[i] = s1;
                }
            }
        }
        String[][] meBabies = Monitor.me.feedMonitor(old);
        while (meBabies != null) {
            String[] alpha = meBabies[0];
            String[] beta = meBabies[1];

            String[] ferdig = new String[alpha.length + beta.length];


            for (int atA = 0, atB = 0, atF = 0; atF < ferdig.length; atF++) {
                if (atA == alpha.length) {
                    ferdig[atF] = beta[atB];
                    atB++;
                    continue;
                } else if (atB == beta.length) {
                    ferdig[atF] = alpha[atA];
                    atA++;
                    continue;
                }

                String stringA = alpha[atA];
                String stringB = beta[atB];

                if (LsTool.isSorted(stringA, stringB)) {
                    ferdig[atF] = stringA;
                    atA++;
                } else {
                    ferdig[atF] = stringB;
                    atB++;
                }

            }
            meBabies = Monitor.me.feedMonitor(ferdig);
        }
    }
}
