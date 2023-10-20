package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

/**
 * Panel containing the list of clients.
 */
public class ViewClientListPanel extends UiPart<Region> {
    private static final String FXML = "ClientListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ViewClientListPanel.class);

    @FXML
    private ListView<Person> clientListView;

    /**
     * Creates a {@code ClientListPanel} with the given {@code ObservableList}.
     */
    public ViewClientListPanel(ObservableList<Person> clientList) {
        super(FXML);
        clientListView.setItems(clientList);
        clientListView.setCellFactory(listView -> new viewClientListCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class viewClientListCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonCard(person, getIndex() + 1).getRoot());
            }
        }
    }
}
