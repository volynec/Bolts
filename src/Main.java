
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Created by user on 18.02.2017.
 */
public class Main {
    public static final String INPUT_FILE = "INPUT.TXT";
    public static final String OUTPUT_FILE = "OUTPUT.TXT";

    public static void main(String[] args) {
        try {
            int totalLoss = parseFileAndCalculate();
            writeFile(Integer.toString(totalLoss));
        } catch (IOException e) {
            System.out.println("Произошла ошибка ввода/выводв! ");
            System.out.println("Подробная отладочная информация: ");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Произошла неизвестная ошибка! ");
            System.out.println("Подробная отладочная информация: ");
            e.printStackTrace();
        }

    }

    private static int parseFileAndCalculate() throws IOException {

        Scanner scanner = new Scanner(Paths.get(INPUT_FILE));
        String line = scanner.nextLine();

        String[] bolts = line.split(" ");
        int numberOfBolts = Integer.parseInt(bolts[0]);
        int percentageLostBolts = Integer.parseInt(bolts[1]);
        int boltCosts = Integer.parseInt(bolts[2]);

        String line2 = scanner.nextLine();
        String[] nuts = line2.split(" ");
        int numberOfNuts = Integer.parseInt(nuts[0]);
        int percentageLostNuts = Integer.parseInt(nuts[1]);
        int nutCosts = Integer.parseInt(nuts[2]);
//        scanner.close();

        return calculate(numberOfBolts, percentageLostBolts, boltCosts, numberOfNuts, percentageLostNuts, nutCosts);

    }

    private static int calculate(int numberOfBolts, int percentageLostBolts, int boltCosts, int numberOfNuts, int percentageLostNuts, int nutCosts) {
        double lostBoltsPart = percentageLostBolts / 100.0;
        double lostNutsPart = percentageLostNuts / 100.0;
        // Потери без пары
        int notCouple = 0;
        // Ущерб от потери болтов
        int lossBolts = (int) (numberOfBolts * lostBoltsPart * boltCosts);
        //Ущерб от потери гаек
        int lossNuts = (int) (numberOfNuts * lostNutsPart * nutCosts);
        // Общие потери гайки и болты
        int loss = lossBolts + lossNuts;
        // Осталось болтов
        int restBolts = (int) (numberOfBolts * (1 - lostBoltsPart));
        // Осталось гаек
        int restNuts = (int) (numberOfNuts * (1 - lostNutsPart));


        if (restBolts > restNuts) {
            // Болтов без гаек
            int bwn = restBolts - restNuts;
            notCouple = bwn * boltCosts;
        } else if (restBolts < restNuts) {
            // Гаек без болтов
            int nwb = restNuts - restBolts;
            notCouple = nwb * nutCosts;
        } else if (restBolts == restNuts) {
            notCouple = 0;
        }
      
        // Общий ущерб
        int totalLoss = loss + notCouple;


        return totalLoss;
    }

    private static void writeFile(String data) throws IOException {
        Files.write(Paths.get(OUTPUT_FILE), data.getBytes());
    }
}



