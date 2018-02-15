package com.ppbf.helpers;

import static com.ppbf.helpers.File.readFromFile;
import static com.ppbf.helpers.Menu.printMenu;

import com.ppbf.sandbox.Sandbox;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Setup {

    private static Scanner in = new Scanner(System.in);

    public static void startSandbox() throws FileNotFoundException {
        System.out.println("Welcome to the Switch Programme Exercise @Blip");

        boolean quit = false;

        do {
            printMenu();

            int menuItem = in.nextInt();

            switch (menuItem) {
                case 1:
                    // readFromFile returns a List with each entry representing a line of the file.
                    List<String> lines = readFromFile("resources/eventsWithDuplicates.csv");

                    System.out.print("Choose marketId:");
                    long marketId = in.nextLong();
                   
                    Sandbox.ex1(lines, marketId);

                    break;
                case 2:
                    // readFromFile returns a List with each entry representing a line of the file.
                    lines = readFromFile("resources/eventsWithDuplicates.csv");

                    Sandbox.ex2(lines);

                    break;
                case 3:
                    // readFromFile returns a List with each entry representing a line of the file.
                    lines = readFromFile("resources/eventsWithoutDuplicates.csv");

                    BigDecimal TOTAL_MONEY = new BigDecimal("30.1");

                    List<Long> removedMarkets = new ArrayList<>();

                    Map<Long, BigDecimal> bets = new HashMap<>();

                    do {
                        System.out.println("\nTotal Money: " + TOTAL_MONEY);
                        

                        System.out.println("\nEvents Available:\n"+lines+"\n");

                        System.out.print("Choose marketId:");
                        marketId = in.nextLong();
                        System.out.print("Choose stake:");
                        BigDecimal stake = in.nextBigDecimal();
                        if (TOTAL_MONEY.compareTo(stake) < 0)
                        {
                        	System.out.println("\n** Insuficient funds!! **");
                        	
                        }else
                        {
                         	
                            TOTAL_MONEY= Sandbox.validateAndUpdateTotalMoney(lines, TOTAL_MONEY, marketId,
                        			stake);
 
                            bets =  Sandbox.addMarketAndStateToMap(bets,marketId, stake);
                            System.out.println("Expected Profit: "+ Sandbox.sum(Sandbox.ex3_2(lines, bets)));
                            
                        }

                    } while (TOTAL_MONEY.compareTo(BigDecimal.ZERO) > 0);
                    break;
                case 0:
                    quit = true;
                    break;
                default:
                    System.out.println("ERROR: Invalid choice.");
            }
        } while (!quit);

        System.out.println("Bye-bye!");
    }
}
