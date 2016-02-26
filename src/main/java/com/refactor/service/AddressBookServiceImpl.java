package com.refactor.service;

import com.refactor.dao.AddressBookDAO;
import com.refactor.dao.AddressBookDAOImpl;
import com.refactor.model.Person;

import java.util.List;
import java.util.stream.Collectors;


public class AddressBookServiceImpl implements AddressBookService {

    // TODO move to separate holder if many codes added
    public static final String MOBILE_CODE = "070";

    protected AddressBookDAO addressBookDAO = new AddressBookDAOImpl();

    @Override
    public List<String> getAllNamesTrunc(int maxLength) {
        List<Person> persons = addressBookDAO.getAllPersons();
        return persons.stream()
                .filter(person -> (person != null && person.getName() != null))
                .map(person -> truncate(person.getName(), maxLength))
                .collect(Collectors.toList());
    }

    private String truncate(String text, int maxLength) {
        return text.length() > maxLength ? text.substring(0, maxLength) : text;
    }

    @Override
    public List<Person> getAllPersonsWithMobile() {
        List<Person> persons = addressBookDAO.getAllPersons();
        return persons.stream()
                .filter(this::hasMobile)
                .collect(Collectors.toList());
    }

    @Override
    public int getSize() {
        return addressBookDAO.getAllPersons().size();
    }

    @Override
    public String getMobile(String name) {
        Person person = requestPersonByName(name);
        return person == null ? null : person.getPhoneNumber();
    }

    @Override
    public boolean hasMobile(String name) {
        Person person = requestPersonByName(name);
        return person != null && hasMobile(person);
    }

    public boolean hasMobile(Person person) {
        if (person == null || person.getPhoneNumber() == null) {
            return false;
        }
        return person.getPhoneNumber().startsWith(MOBILE_CODE);
    }

    private Person requestPersonByName(String name) {
        return addressBookDAO.getPersonsByName(name).stream()
                .findFirst()
                .orElse(null);
    }

    public void setAddressBookDAO(AddressBookDAO addressBookDAO) {
        this.addressBookDAO = addressBookDAO;
    }
}
