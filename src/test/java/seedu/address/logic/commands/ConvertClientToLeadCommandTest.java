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

public class ConvertClientToLeadCommandTest {
    private Model model = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validClientIndex_success() {
        Client clientToConvert = TypicalPersons.getTypicalClients().get(0); // assuming you have a list of clients
        ConvertClientToLeadCommand command = new ConvertClientToLeadCommand(clientToConvert.getIndex());

        String expectedMessage = String.format(ConvertClientToLeadCommand.MESSAGE_CONVERT_SUCCESS, clientToConvert);
        expectedModel.deletePerson(clientToConvert);
        expectedModel.addPerson(new Lead(clientToConvert)); // Assuming a constructor for Lead is available

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidClientIndex_failure() {
        int outOfBoundIndex = model.getFilteredPersonList().size() + 1;
        ConvertClientToLeadCommand command = new ConvertClientToLeadCommand(Index.fromOneBased(outOfBoundIndex));

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    // Add more test cases for edge cases, if necessary
}

