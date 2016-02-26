package com.refactor.service;

import com.refactor.dao.AddressBookDAOImpl;
import com.refactor.model.Person;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AddressBookServiceImplTest {

    public static final String LONG_NAME = "Alexander";
    public static final String SHORT_NAME = "Li";
    public static final String SOME_NAME = "Somename";
    public static final String MOB_NUM = "0701231212";
    public static final String NOT_MOBILE = "9998887766";

    private static AddressBookServiceImpl addressBookService;

    @Test
    public void testGetAllNamesTrunc() throws Exception {
        List<String> allNamesTrunc = addressBookService.getAllNamesTrunc(4);

        assertEquals("Fail! Name is not truncated.", "Alex", allNamesTrunc.get(0));
        assertEquals("Fail! Short name is changed.", "Li", allNamesTrunc.get(1));
    }

    @Test
    public void testHasMobile() throws Exception {
        assertTrue(addressBookService.hasMobile(new Person(LONG_NAME, MOB_NUM)));
        assertFalse(addressBookService.hasMobile(new Person(LONG_NAME, NOT_MOBILE)));
        assertFalse(addressBookService.hasMobile(new Person(null, null)));
    }

    @Test
    public void testGetSize() throws Exception {
        assertEquals(4, addressBookService.getSize());
    }

    @Test
    public void testGetMobile() throws Exception {
        assertEquals(MOB_NUM, addressBookService.getMobile(LONG_NAME));
        assertEquals(null, addressBookService.getMobile("Non existing user name"));
    }

    @Test
    public void testGetAllPersonsWithMobile() throws Exception {
        List<Person> allPersonsWithMobile = addressBookService.getAllPersonsWithMobile();
        assertEquals(2, allPersonsWithMobile.size());
    }


    @BeforeClass
    public static void init() throws Exception {
        addressBookService = new AddressBookServiceImpl();

        AddressBookDAOImpl daoMock = mock(AddressBookDAOImpl.class);
        when(daoMock.getAllPersons())
                .thenReturn(asList(
                        new Person(LONG_NAME, MOB_NUM),
                        new Person(null, null), // check for NPE's
                        new Person(SHORT_NAME, "070"),
                        new Person(SOME_NAME, NOT_MOBILE)));

        when(daoMock.getPersonsByName(LONG_NAME))
                .thenReturn(asList(new Person(LONG_NAME, MOB_NUM)));

        addressBookService.setAddressBookDAO(daoMock);
    }
}