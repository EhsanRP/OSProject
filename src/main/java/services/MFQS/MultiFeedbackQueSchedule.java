package services.MFQS;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MultiFeedbackQueSchedule {

    static int n;
    private static File file = new File("filename.txt");
    private static Scanner inp;

    static MFQSProcess[] Q1, Q2, Q3 = new MFQSProcess[10];

    static {
        try {
            inp = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static void runMFQS() {
        int i, j, k = 0, r = 0, time = 0, tq1, tq2, flag = 0;
        char c;

        n = Integer.parseInt(inp.nextLine());

        Integer[] arrival = new Integer[n];
        Integer[] burst = new Integer[n];

        var burstString = inp.nextLine().split(", ");
        for (i = 0; i < n; i++) {
            burst[i] = Integer.parseInt(burstString[i]);
        }

        var arrivalString = inp.nextLine().split(", ");
        for (i = 0; i < n; i++)
            arrival[i] = Integer.parseInt(arrivalString[i]);

        tq1 = Integer.parseInt(inp.nextLine());
        tq2 = Integer.parseInt(inp.nextLine());

        for (i = 0, c = 'A'; i < n; i++, c++) {

            MFQSProcess process = new MFQSProcess();
            process.name = c;
            process.setAT(arrival[i]);
            process.setBT(burst[i]);
            Q1[i].RT = Q1[i].BT;/*save burst time in remaining time for each process*/

        }
        sortByArrival();
        time = Q1[0].AT;

        for (i = 0; i < n; i++) {

            if (Q1[i].RT <= tq1) {

                System.out.printf("t=" +time + "\t");

                time += Q1[i].RT;/*from arrival time of first process to completion of this process*/
                Q1[i].RT = 0;
                Q1[i].WT = time - Q1[i].AT - Q1[i].BT;/*amount of time process has been waiting in the first queue*/
                Q1[i].TAT = time - Q1[i].AT;/*amount of time to execute the process*/

                System.out.printf("process "+Q1[i].name + "\t");

            } else/*process moves to queue 2 with qt=8*/ {

                System.out.printf("t=" +time + "\t");

                Q2[k].WT = time;
                time += tq1;
                Q1[i].RT -= tq1;
                Q2[k].BT = Q1[i].RT;
                Q2[k].RT = Q2[k].BT;
                Q2[k].name = Q1[i].name;
                k = k + 1;
                flag = 1;

                System.out.printf("process "+Q1[i].name + "\t");

            }
        }
        for (i = 0; i < k; i++) {
            if (Q2[i].RT <= tq2) {
                System.out.printf("t=" +time + "\t");

                time += Q2[i].RT;/*from arrival time of first process +BT of this process*/
                Q2[i].RT = 0;
                Q2[i].WT = time - tq1 - Q2[i].BT;/*amount of time process has been waiting in the ready queue*/
                Q2[i].TAT = time - Q2[i].AT;/*amount of time to execute the process*/

                System.out.printf("process "+Q1[i].name + "\t");


            } else/*process moves to queue 3 with FCFS*/ {

                System.out.printf("t=" +time + "\t");

                Q3[r].AT = time;
                time += tq2;
                Q2[i].RT -= tq2;
                Q3[r].BT = Q2[i].RT;
                Q3[r].RT = Q3[r].BT;
                Q3[r].name = Q2[i].name;
                r = r + 1;
                flag = 2;

                System.out.printf("process "+Q1[i].name + "\t");

            }
        }

        for (i = 0; i < r; i++) {
            if (i == 0)
                Q3[i].CT = Q3[i].BT + time - tq1 - tq2;
            else
                Q3[i].CT = Q3[i - 1].CT + Q3[i].BT;

        }

        for (i = 0; i < r; i++) {
            Q3[i].TAT = Q3[i].CT;
            Q3[i].WT = Q3[i].TAT - Q3[i].BT;

        }
    }


    static void sortByArrival() {
        MFQSProcess temp;
        int i, j;
        for (i = 0; i < n; i++) {
            for (j = i + 1; j < n; j++) {
                if (Q1[i].AT > Q1[j].AT) {
                    temp = Q1[i];
                    Q1[i] = Q1[j];
                    Q1[j] = temp;
                }
            }
        }
    }
}
