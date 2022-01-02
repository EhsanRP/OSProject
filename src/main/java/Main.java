import services.HRRN.HighestResponseRatioNext;
import services.RR.RoundRobinService;
import services.SRTF.ShortestRemainingTimeFirst;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter 1 to have round robin algorithm");
        int coice = scanner.nextInt();

        if (coice ==1)
            RoundRobinService.runRoundRobin();
        if (coice ==2)
            ShortestRemainingTimeFirst.runSRTF();
        if (coice==3)
            HighestResponseRatioNext.runHRRN();
    }


}
