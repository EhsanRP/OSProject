package services.HRRN;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class HighestResponseRatioNext {

    private static File file = new File("filename.txt");
    private static Scanner inp;

    static {
        try {
            inp = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static int n;
    static HRRNProcess[] p = new HRRNProcess[10];

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

        t=0;
        System.out.println();
        System.out.printf("t=" +t + "\t");
        for (t = p[0].at; t < sum_bt; ) {

            // Set lower limit to response ratio
            float hrr = -9999;

            // Response Ratio Variable
            float temp;

            // Variable to store next process selected
            int loc = 0;
            for (i = 0; i < n; i++) {

                // Checking if process has arrived and is Incomplete
                if (p[i].at <= t && !p[i].completed) {

                    // Calculating Response Ratio
                    temp = (p[i].bt + (t - p[i].at)) / p[i].bt;

                    // Checking for Highest Response Ratio
                    if (hrr < temp) {

                        // Storing Response Ratio
                        hrr = temp;

                        // Storing Location
                        loc = i;
                    }
                }
            }
            // Updating time value
            System.out.printf(String.valueOf(p[loc].name) + "\t");
            t += p[loc].bt;
            System.out.printf("t=" +t  + "\t");

            // Calculation of waiting time
            p[loc].wt = t - p[loc].at - p[loc].bt;

            // Calculation of Turn Around Time
            p[loc].tt = t - p[loc].at;

            // Sum Turn Around Time for average
            avgtt += p[loc].tt;

            // Calculation of Normalized Turn Around Time
            p[loc].ntt = ((float) p[loc].tt / p[loc].bt);

            // Updating Completion Status
            p[loc].completed = true;

            // Sum Waiting Time for average
            avgwt += p[loc].wt;
//            System.out.print("\n " + p[loc].name + "\t\t" + p[loc].at + "\t\t");
//            System.out.print(p[loc].bt+"\t\t" + p[loc].wt +"\t\t");
//            System.out.print(p[loc].tt +"\t\t" + p[loc].ntt);
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
