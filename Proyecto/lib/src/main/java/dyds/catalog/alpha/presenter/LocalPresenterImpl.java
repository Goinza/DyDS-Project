package dyds.catalog.alpha.presenter;

import java.util.ArrayList;

import dyds.catalog.alpha.model.Database;
import dyds.catalog.alpha.view.MainView;

public class LocalPresenterImpl implements LocalPresenter {

	Database database;
	MainView view;
	
	public LocalPresenterImpl(Database database) {
		this.database = database;
	}
	
	@Override
	public void selectEntry(Object entry) {
		String title = entry.toString();
		String extractText = database.getExtract(title);
		view.setLocalExtractText(extractText);
		
	}

	@Override
	public void deleteEntry(Object entry) {		
		String title = entry.toString();
		database.deleteEntry(title);
		ArrayList<String> titleList = database.getTitlesInAscendingOrder();
		view.updateLocalArray(titleList.toArray());
		view.setLocalExtractText("");
		
	}

	@Override
	public void setView(MainView view) {
		this.view = view;
	}
	
}
