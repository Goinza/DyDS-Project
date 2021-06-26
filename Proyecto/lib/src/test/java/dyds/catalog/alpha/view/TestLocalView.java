package dyds.catalog.alpha.view;

import dyds.catalog.alpha.presenter.DeletePresenter;
import dyds.catalog.alpha.presenter.SelectPresenter;

public class TestLocalView extends LocalView {

	public TestLocalView(MainWindow window, SelectPresenter selectPresenter, DeletePresenter deletePresenter) {
		super(window, selectPresenter, deletePresenter);
	}
	
	public void selectArticle(int index) {
		titlesComboBox.setSelectedIndex(index);
	}
	
	public String getExtractText() {
		return extractTextPane.getText();
	}
	
	public int getTitlesCount() {
		return titlesComboBox.getItemCount();
	}

	public void deleteFirstEntry() {
		deleteButton.doClick();
	}
}
