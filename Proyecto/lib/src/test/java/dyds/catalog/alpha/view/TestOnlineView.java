package dyds.catalog.alpha.view;

import dyds.catalog.alpha.presenter.SavePresenter;
import dyds.catalog.alpha.presenter.SearchPresenter;

public class TestOnlineView extends OnlineView {

	public TestOnlineView(MainWindow window, SearchPresenter searchPresenter, SavePresenter savePresenter) {
		super(window, searchPresenter, savePresenter);
	}
	
	public void searchArticle(String searchTerm) {
		titleTextField.setText(searchTerm);
		searchButton.doClick();
	}
	
	public String getExtractText() {
		return extractTextPane.getText();
	}
	
	public void saveLastSearchedArticle() {		
		saveLocallyButton.doClick();
	}

}
