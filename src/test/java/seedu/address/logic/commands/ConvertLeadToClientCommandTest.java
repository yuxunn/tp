import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Client;
import seedu.address.model.person.Lead;
import seedu.address.testutil.TypicalPersons;

public class ConvertLeadToClientCommandTest {
    private Model model = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validLeadIndex_success() {
        Lead leadToConvert = TypicalPersons.getTypicalLeads().get(0); // assuming you have a list of leads
        ConvertLeadToClientCommand command = new ConvertLeadToClientCommand(leadToConvert.getIndex());

        String expectedMessage = String.format(ConvertLeadToClientCommand.MESSAGE_CONVERT_SUCCESS, leadToConvert);
        expectedModel.deletePerson(leadToConvert);
        expectedModel.addPerson(new Client(leadToConvert)); // Assuming a constructor for Client is available

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidLeadIndex_failure() {
        int outOfBoundIndex = model.getFilteredPersonList().size() + 1;
        ConvertLeadToClientCommand command = new ConvertLeadToClientCommand(Index.fromOneBased(outOfBoundIndex));

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    // Add more test cases for edge cases, if necessary
}

