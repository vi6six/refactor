package com.refactor.dao;


import com.refactor.model.Person;

import java.util.List;

public interface AddressBookDAO {

    /**
     * Insert person
     */
    void addPerson(Person person);

    /**
     * Select persons by name.
     *
     * @return Empty list if not found.
     */
    List<Person> getPersonsByName(String name);

    /**
     * Select all persons by name
     * TODO ambiguity in results should lead to select by id.
     *
     * @return Empty list if not found.
     */
    List<Person> getAllPersons();

    // TODO add other CRUD operations
}
