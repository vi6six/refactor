package com.refactor.model;

public class Person {
    private String name;
    private PhoneNumber phoneNumber;

    public Person() {
    }

    public Person(String name, String number) {
        this.name = name;
        this.phoneNumber = new PhoneNumber(number);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return this.phoneNumber.getNumber();
    }

    public void setPhoneNumber(String number) {
        this.phoneNumber.setNumber(number);
    }
}
