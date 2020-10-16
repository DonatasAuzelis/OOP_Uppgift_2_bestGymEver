import org.junit.Test;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import static org.junit.Assert.*;

/**
 * Created by Donatas Auzelis
 * Date: 2020-10-09
 * Time: 15:00
 * Project: OOP_Uppgift_2
 * Copyright: MIT
 */
public class GymTest {

    Gym test = new Gym();

    @Test
    public void getFileAndGenerateListTest() {
        List<String> customerList = test.getFileAndGenerateList("customersTest.txt");
        assertTrue(customerList.size() == 6);
        assertTrue(customerList.get(0).equals("7603021234, Alhambra Aromes"));
        assertFalse(customerList.get(1).equals("3023-07-01"));
    }

    @Test
    public void isOnTheListTest() {
        test.getFileAndGenerateList("customersTest.txt");
        String input1 = "Alhambra";
        String input2 = "8104021234";
        String input3 = "Donatas97";
        assertTrue(test.isOnTheList(input1));
        assertTrue(test.isOnTheList(input2));
        assertFalse(test.isOnTheList(input3));
    }

    @Test
    public void isValidMemberTest() {
        String date = "2015-09-03";
        String otherDate = "2020-10-01";
        String dateNow = "2020-10-15";
        LocalDate date1 = LocalDate.parse(date);
        LocalDate date2 = LocalDate.parse(otherDate);
        LocalDate date3 = LocalDate.parse(dateNow);

        ChronoUnit.DAYS.between(date1, date3);
        ChronoUnit.DAYS.between(date2, date3);

        assertFalse(test.isValidMember(date));
        assertTrue(test.isValidMember(otherDate));
    }

}
