import javax.swing.*;
import java.io.*;
import java.nio.file.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * Created by Donatas Auzelis
 * Date: 2020-10-09
 * Time: 15:00
 * Project: OOP_Uppgift_2
 * Copyright: MIT
 */

public class Gym extends Member {

    boolean isMember;
    String memberInfo;
    String gymPath = "customers.txt";
    String listPTPath = "listForPT.txt";
    List<String> gymMembers = new ArrayList();

    public Gym() {}

    public List<String> getFileAndGenerateList(String gymPath) {
        try (BufferedReader reader = new BufferedReader(
                new FileReader(gymPath));) {
            while ((memberInfo = reader.readLine()) != null) {
                gymMembers.add(memberInfo);
            }
        }catch (FileNotFoundException e) {
            System.out.println("Filen kunde inte hittas");
        }
        catch (Exception e) {
            System.out.println("Något gick fel");
            e.printStackTrace();
            System.exit(0);
        }
        return gymMembers;
    }

    public boolean isOnTheList(String input) {
        for (String s : gymMembers) {

            if (s.contains(input))
                return isMember = true;
        }
        return isMember = false;
    }

    public boolean isValidMember(String date) {
        LocalDate date1 = LocalDate.parse(date);
        LocalDate date2 = LocalDate.now();
        int diff = (int) ChronoUnit.DAYS.between(date1, date2);

        return diff < 365;
    }

    public void readFileAddValidMemberToNewFile(Scanner sc, FileWriter w, String input) {
        if (isMember) {
            String firstLine;
            String secondLine;

            if (sc.hasNext()) {
                firstLine = sc.nextLine();

                if (sc.hasNext()) {
                    secondLine = sc.nextLine();
                    String date = getDate(secondLine);

                    if (isValidMember(date) && firstLine.contains(input)) {
                        JOptionPane.showMessageDialog(null, firstLine + ":\när en nuvarande medlem!");
                        LocalDateTime now = LocalDateTime.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        String formatDateTime = now.format(formatter);

                        try {
                            w.write(firstLine + "\n");
                            w.write(formatDateTime + "\n");
                        } catch (IOException e) {
                            System.out.println("Error");
                            e.printStackTrace();
                        }

                    }
                    if (firstLine.contains(input) && !isValidMember(date)) {
                        JOptionPane.showMessageDialog(null, firstLine + ":\när INTE en nuvarande medlem!");
                    }
                }
            }

        }
    }

    public void mainProgram() {
        Path inFilePath = Paths.get(gymPath);

        String input = JOptionPane.showInputDialog(null,
                "Ange namn eller personnummer: ", "Best Gym Ever!", JOptionPane.INFORMATION_MESSAGE);
        if (input == null)
            System.exit(0);
        input = input.trim();

        getFileAndGenerateList(gymPath);

        if (isOnTheList(input)) {
            try (FileWriter w = new FileWriter(listPTPath, true);
                 Scanner fileScanner = new Scanner(inFilePath)) {

                while (fileScanner.hasNext()) {
                    readFileAddValidMemberToNewFile(fileScanner, w, input);
                }
            } catch (IOException e) {
                System.out.println("Error");
                e.printStackTrace();
            }
        } else {
            askToDoOver();
        }

    }

    public void askToDoOver() {
        int input = JOptionPane.showConfirmDialog(null, "Kunde inte hitta, vill du försöka igen?");
        if (input != 1) mainProgram();
    }


}
