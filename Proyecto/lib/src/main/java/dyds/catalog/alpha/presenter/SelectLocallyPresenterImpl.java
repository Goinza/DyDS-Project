package dyds.catalog.alpha.presenter;

import dyds.catalog.alpha.model.InvalidTitleException;
import dyds.catalog.alpha.model.Model;
import dyds.catalog.alpha.view.MainView;

public class SelectLocallyPresenterImpl implements SelectLocallyPresenter {
	
	Model model;
	MainView view;
	
	public SelectLocallyPresenterImpl(Model model) {
		this.model = model;
	}

	@Override
	public void selectEntry(String title) {
		try {
			String extractText = model.getExtract(title);
			view.setLocalExtractText(extractText);
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
