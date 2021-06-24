package dyds.catalog.alpha.presenter;

import java.util.ArrayList;

import dyds.catalog.alpha.model.InvalidTitleException;
import dyds.catalog.alpha.model.Model;
import dyds.catalog.alpha.view.MainView;

public class LocalPresenterImpl implements LocalPresenter {

	Model database;
	MainView view;
	
	public LocalPresenterImpl(Model database) {
		this.database = database;
	}
	
	@Override
	public void selectEntry(Object entry) {
		try {
			String title = entry.toString();
			String extractText = database.getExtract(title);
			view.setLocalExtractText(extractText);
		}
		catch (InvalidTitleException e) {
			view.throwErrorMessage("Error", e.getMessage());
		}

		
	}

	@Override
	public void deleteEntry(Object entry) {	
		try {
			String title = entry.toString();
			database.deleteEntry(title);
			ArrayList<String> titleList = database.getTitlesInAscendingOrder();
			view.updateLocalArray(titleList.toArray());
			view.setLocalExtractText("");
		}
		catch (InvalidTitleException e) {
			view.throwErrorMessage("Error", e.getMessage());
		}	
	}

	@Override
	public void setView(MainView view) {
		this.view = view;
	}
	
}
