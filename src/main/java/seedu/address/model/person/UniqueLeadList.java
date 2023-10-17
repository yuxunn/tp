package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A person is considered unique by comparing using {@code Lead#isSamePerson(Lead)}. As such, adding and updating of
 * persons uses Lead#isSamePerson(Lead) for equality so as to ensure that the person being added or updated is
 * unique in terms of identity in the UniqueLeadList. However, the removal of a person uses Lead#equals(Object) so
 * as to ensure that the person with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Lead#isSamePerson(Person)
 */
public class UniqueLeadList implements Iterable<Lead> {

    private final ObservableList<Lead> internalList = FXCollections.observableArrayList();
    private final ObservableList<Lead> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(Person toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSamePerson);
    }

    /**
     * Adds a person to the list.
     * The person must not already exist in the list.
     */
    public void add(Lead toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the person {@code target} in the list with {@code editedLead}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedLead} must not be the same as another existing person in the list.
     */
    public void setLead(Lead target, Lead editedLead) {
        requireAllNonNull(target, editedLead);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!target.isSamePerson(editedLead) && contains(editedLead)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedLead);
    }

    /**
     * Removes the equivalent person from the list.
     * The person must exist in the list.
     */
    public void remove(Lead toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
    }

    public void setLeads(UniqueLeadList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setLeads(List<Lead> leads) {
        requireAllNonNull(leads);
        if (!leadsAreUnique(leads)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(leads);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Lead> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Lead> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueLeadList)) {
            return false;
        }

        UniqueLeadList otherUniqueLeadList = (UniqueLeadList) other;
        return internalList.equals(otherUniqueLeadList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    /**
     * Returns true if {@code persons} contains only unique persons.
     */
    private boolean leadsAreUnique(List<Lead> leads) {
        for (int i = 0; i < leads.size() - 1; i++) {
            for (int j = i + 1; j < leads.size(); j++) {
                if (leads.get(i).isSamePerson(leads.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
