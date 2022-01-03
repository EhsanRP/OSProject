import services.HRRN.HighestResponseRatioNext;
import services.MFQS.MultiFeedbackQueSchedule;
import services.RR.RoundRobinService;
import services.SRTF.ShortestRemainingTimeFirst;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter 1 to have round robin algorithm");
        System.out.println("Please enter 2 to have shortest remaining time first algorithm");
        System.out.println("Please enter 3 to have highest response ratio next algorithm");
        System.out.println("Please enter 4 to have multi feedback queue schedule algorithm");
        int coice = scanner.nextInt();

        switch (coice) {
            case 1:
                RoundRobinService.runRoundRobin();
                break;
            case 2:
                ShortestRemainingTimeFirst.runSRTF();
                break;
            case 3:
                HighestResponseRatioNext.runHRRN();
                break;
            case 4:
                MultiFeedbackQueSchedule.runMFQS();
                break;

        }
    }


}
