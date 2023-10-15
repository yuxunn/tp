package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ListLeadCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

public class ListLeadCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }


    @Test
    public void execute_listIsFiltered_showsEverything() {
        // Filter the model as required by ListClientCommand
        Predicate<Person> predicate = ListLeadCommand.LEAD_TAG_PREDICATE;
        model.updateFilteredPersonList(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(new ListLeadCommand(), model, MESSAGE_SUCCESS, expectedModel);
    }
}

