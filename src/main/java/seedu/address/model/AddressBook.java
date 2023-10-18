package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Client;
import seedu.address.model.person.Lead;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniqueClientList;
import seedu.address.model.person.UniqueLeadList;
import seedu.address.model.person.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final UniqueClientList clients;
    private final UniqueLeadList leads;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        clients = new UniqueClientList();
        leads = new UniqueLeadList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    public void setClients(List<Client> clients) {
        this.clients.setClients(clients);
    }

    public void setLeads(List<Lead> leads) {
        this.leads.setLeads(leads);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setClients(newData.getClientList());
        setLeads(newData.getLeadList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person person) {
        persons.add(person);
    }

    /**
     * Adds a client to the address book.
     * The client must not already exist in the address book.
     */
    public void addClient(Client client) {
        clients.add(client);
        persons.add(client);
    }

    /**
     * Adds a lead to the address book.
     * The lead must not already exist in the address book.
     */
    public void addLead(Lead lead) {
        leads.add(lead);
        persons.add(lead);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);
        persons.setPerson(target, editedPerson);
    }

    public void setClient(Client target, Client editedPerson) {
        requireNonNull(editedPerson);
        clients.setClient(target, editedPerson);
    }

    public void setLead(Lead target, Lead editedPerson) {
        requireNonNull(editedPerson);
        leads.setLead(target, editedPerson);
    }

    /**
     * Removes {@code personKey} from this {@code AddressBook}.
     * {@code personKey} must exist in the address book.
     */
    public void removePerson(Person personKey) {
        persons.remove(personKey);
    }

    /**
     * Removes {@code clientKey} from this {@code AddressBook}.
     * {@code clientKey} must exist in the address book.
     */
    public void removeClient(Client clientKey) {
        clients.remove(clientKey);
        persons.remove(clientKey);
    }

    /**
     * Removes {@code leadKey} from this {@code AddressBook}.
     * {@code leadKey} must exist in the address book.
     */
    public void removeLead(Lead leadKey) {
        leads.remove(leadKey);
        persons.remove(leadKey);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("persons", persons)
                .toString();
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Client> getClientList() {
        return clients.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Lead> getLeadList() {
        return leads.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddressBook)) {
            return false;
        }

        AddressBook otherAddressBook = (AddressBook) other;
        return clients.equals(otherAddressBook.clients) && leads.equals(otherAddressBook.leads);
    }

    @Override
    public int hashCode() {
        return clients.hashCode() + leads.hashCode();
    }
}
