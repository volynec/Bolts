
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Created by user on 18.02.2017.
 */
public class Main {
    static double notCouple;

    public static void main(String[] args) throws IOException {
        String fileName = "INPUT.TXT";
        readFiles(fileName);
    }

    private static void readFiles(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        Scanner scanner = new Scanner(path);
        String line = scanner.nextLine();

        String[] bolts = line.split(" ");
        int k1 = Integer.parseInt(bolts[0]);
        int l1 = Integer.parseInt(bolts[1]);
        int m1 = Integer.parseInt(bolts[2]);

        String line2 = scanner.nextLine();
        String[] nuts = line2.split(" ");
        int k2 = Integer.parseInt(nuts[0]);
        int l2 = Integer.parseInt(nuts[1]);
        int m2 = Integer.parseInt(nuts[2]);

        double l3 = (double)l1/100 ;
        double l4 = (double)l2/100  ;

        double lossBolts = k1 * l3 * m1;
        System.out.println("ущерб от потери болтов " + lossBolts);
        double lossNuts = k2 * l4 * m2;
        System.out.println("ущерб от потери гае " + lossNuts);
        double loss = lossBolts + lossNuts;
        System.out.println("общие потери гайки и болты " + loss);
        double restBolts = k1 * (1 - l3);
        System.out.println("осталось болтов " + restBolts);
        double restNuts = k2 * (1 - l4);
        System.out.println("осталось гаек " + restNuts);

        if (restBolts > restNuts) {
            double bwn = restBolts - restNuts;
            System.out.println("болтов без гаек " + bwn);
            notCouple = bwn * m1;
            System.out.println("потери без пары " +notCouple);
        } else if (restBolts < restNuts) {
            double nwb = restNuts - restBolts;
            System.out.println("гаек без болтов " + nwb);
            notCouple = nwb * m2;
            System.out.println("потери без пары " +notCouple);
        }else if (restBolts == restNuts){
            notCouple = 0;
            System.out.println("потери без пары " +notCouple);
        }
        double totalLoss = loss +notCouple;
        System.out.println("общий ущерб "+ totalLoss);
        String srtTotalLoss = String.valueOf(totalLoss);

        writeFiles(srtTotalLoss);

    }
    private static void writeFiles(String data) {
        try {
            Files.write(Paths.get("OUTPUT.TXT"), data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



