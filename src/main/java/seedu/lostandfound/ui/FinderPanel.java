package seedu.lostandfound.ui;

import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.lostandfound.commons.core.LogsCenter;
import seedu.lostandfound.commons.events.ui.ArticlePanelSelectionChangedEvent;
import seedu.lostandfound.model.article.Article;

/**
 * The Finder Panel of the App.
 */
public class FinderPanel extends UiPart<Region> {

    private static final String FXML = "FinderPanel.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    @FXML
    private Label finder;

    @FXML
    private Label phone;

    @FXML
    private Label email;

    public FinderPanel() {
        super(FXML);
        registerAsAnEventHandler(this);
    }

    private void loadFinder(Article article) {
        Platform.runLater(() -> {
            finder.setText(article.getFinder().fullName);
            phone.setText("Phone: " + article.getPhone().value);
            email.setText("Email: " + article.getEmail().value);
        });
    }

    @Subscribe
    private void handleArticlePanelSelectionChangedEvent(ArticlePanelSelectionChangedEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        loadFinder(event.getNewSelection());
    }
}
