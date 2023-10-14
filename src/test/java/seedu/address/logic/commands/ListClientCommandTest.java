package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ListClientCommand.CLIENT_TAG;
import static seedu.address.logic.commands.ListClientCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

public class ListClientCommandTest {

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
        Predicate<Person> predicate = person -> person.getTags().toString().equals(CLIENT_TAG);
        model.updateFilteredPersonList(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(new ListClientCommand(), model, MESSAGE_SUCCESS, expectedModel);
    }
}
