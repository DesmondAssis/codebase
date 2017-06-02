package com.desmond.codebase.jdk8.lambda;

import com.desmond.codebase.print.Print;

import java.time.LocalDate;
import java.util.Random;

/**
 * Created by Li.Xiaochuan on 16/11/23.
 */
public class Person {
    public enum Sex {
        MALE, FEMALE;
    }

    public Person() {
    }

    public Person(String name, Sex gender, LocalDate birthday, String emailAddress) {
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.emailAddress = emailAddress;
    }

    public static Person getRandonPerson() {
        int i = new Random().nextInt(12) + 1;
        return new Person("name " + i, Sex.MALE, LocalDate.now(), "address" + i);
    }

    private String name;
    private Sex gender;
    private LocalDate birthday;
    private String emailAddress;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Sex getGender() {
        return gender;
    }

    public void setGender(Sex gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", birthday=" + birthday +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }

    public void printPerson() {
        System.out.println(this);
    }
}
