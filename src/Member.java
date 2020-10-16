import java.time.LocalDate;

/**
 * Created by Donatas Auzelis
 * Date: 2020-10-12
 * Time: 11:36
 * Project: OOP_Uppgift_2
 * Copyright: MIT
 */
public class Member{

    private String socialNumber;
    private String firstName;
    private String lastName;
    private LocalDate date;

    public Member(String socialNumber, String firstName, String lastName, LocalDate date) {
        this.socialNumber = socialNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.date = date;
    }

    public Member() {
    }

    public String getDate(String date) {
        return date;
    }

}
