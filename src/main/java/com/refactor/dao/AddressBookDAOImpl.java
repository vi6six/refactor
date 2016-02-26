package com.refactor.dao;

import com.refactor.model.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AddressBookDAOImpl implements AddressBookDAO {

    public static final String SELECT_PERSON_BY_NAME_QUERY = "select name , phoneNumber from AddressEntry where name = ?";
    public static final String INSERT_PERSON_QUERY = "insert into AddressEntry values (?, ?, ?)";
    public static final String SELECT_ALL_PERSON = "select * from AddressEntry";

    /**
     * Writes Person into DB.
     */
    @Override
    public void addPerson(Person person) {

        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_PERSON_QUERY)) {

            // TODO column names should be used.
            statement.setLong(1, System.currentTimeMillis());
            statement.setString(2, person.getName());
            statement.setString(3, person.getPhoneNumber());
            statement.executeUpdate();
        } catch (SQLException e) {
            // TODO log or throw up
            e.printStackTrace();
        } // resources are closed.
    }

    /**
     * Looks up for Persons in DB with given name.
     * Returns List of Persons found with matching name or null if nobody found.
     *
     * TODO: getPersonsByName and getAllPersons got code duplications. Refactor if logic don't became diff very fast.
     */
    @Override
    public List<Person> getPersonsByName(String searchedName) {
        List<Person> persons = new ArrayList<>();
        ResultSet rs = null;

        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_PERSON_BY_NAME_QUERY)) {

            statement.setString(1, searchedName);
            rs = statement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String phoneNumber = rs.getString("phoneNumber");
                persons.add(new Person(name, phoneNumber));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return persons;
    }

    /**
     * Returns a List of all Persons from DB.
     */
    @Override
    public List<Person> getAllPersons() {
        List<Person> persons = new ArrayList<>();

        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_PERSON);
             ResultSet rs = statement.executeQuery()) {

             while (rs.next()) {
                 String name = rs.getString("name");
                 String phoneNumber = rs.getString("phoneNumber");
                 persons.add(new Person(name, phoneNumber));
             }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return persons;
    }
}
