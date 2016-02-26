package com.refactor.service;

import com.refactor.model.Person;

import java.util.List;

public interface AddressBookService {

    /**
     * All names in the book truncated to the given length.
     */
    List<String> getAllNamesTrunc(int maxLength);

    /**
     * All people who have mobile phone numbers.
     */
    List<Person> getAllPersonsWithMobile();

    /**
     * Number of persons
     */
    int getSize();

    /**
     * Given user's mobile phone number, or null if he doesn't have one.
     */
    String getMobile(String name);

    /**
     * check if user have mobile number
     */
    boolean hasMobile(String name);
}
