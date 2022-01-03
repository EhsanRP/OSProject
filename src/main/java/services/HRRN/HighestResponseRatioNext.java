package services.HRRN;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HighestResponseRatioNext {

    static int n;
    static HRRNProcess[] p = new HRRNProcess[10];
    private static File file = new File("filename.txt");
    private static Scanner inp;

    static {
        try {
            inp = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void runHRRN() {
        n = Integer.parseInt(inp.nextLine());

        int i, j, t, sum_bt = 0;
        char c;
        float avgwt = 0, avgtt = 0;
        Integer[] arrival = new Integer[n];
        Integer[] burst = new Integer[n];


        var burstString = inp.nextLine().split(", ");
        for (i = 0; i < n; i++)
            burst[i] = Integer.parseInt(burstString[i]);


        var arrivalString = inp.nextLine().split(", ");
        for (i = 0; i < n; i++)
            arrival[i] = Integer.parseInt(arrivalString[i]);

        for (i = 0, c = 'A'; i < n; i++, c++) {
            var process = new HRRNProcess();
            process.setName(c);
            process.setAt(arrival[i]);
            process.setBt(burst[i]);
            process.completed = false;

            p[i] = process;
            sum_bt += process.bt;
        }

        sortByArrival();

        t = 0;
        System.out.println();
        System.out.printf("t=" + t + "\t");
        for (t = p[0].at; t < sum_bt; ) {

            float hrr = -9999;

            float temp;

            int loc = 0;
            for (i = 0; i < n; i++) {

                if (p[i].at <= t && !p[i].completed) {

                    temp = (p[i].bt + (t - p[i].at)) / p[i].bt;

                    if (hrr < temp) {

                        hrr = temp;

                        loc = i;
                    }
                }
            }
            System.out.printf(String.valueOf(p[loc].name) + "\t");
            t += p[loc].bt;
            System.out.printf("t=" + t + "\t");

            p[loc].wt = t - p[loc].at - p[loc].bt;

            p[loc].tt = t - p[loc].at;

            avgtt += p[loc].tt;

            p[loc].ntt = ((float) p[loc].tt / p[loc].bt);

            p[loc].completed = true;

            avgwt += p[loc].wt;
        }
        System.out.println("\nAverage waiting time:" + avgwt / n);
        System.out.println("Average Turn Around time:" + avgtt / n);
    }

    static void sortByArrival() {
        HRRNProcess temp = new HRRNProcess();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                temp = p[i];
                p[i] = p[j];
                p[j] = temp;
            }
        }
    }

}
