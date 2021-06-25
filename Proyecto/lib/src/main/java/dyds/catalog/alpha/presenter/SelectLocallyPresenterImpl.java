package dyds.catalog.alpha.presenter;

import java.util.ArrayList;

import dyds.catalog.alpha.model.DeleteSuccessListener;
import dyds.catalog.alpha.model.InvalidTitleException;
import dyds.catalog.alpha.model.Model;
import dyds.catalog.alpha.model.SaveSuccessListener;
import dyds.catalog.alpha.view.LocalView;

public class SelectLocallyPresenterImpl implements SelectLocallyPresenter {
	
	Model model;
	LocalView view;
	
	public SelectLocallyPresenterImpl(Model model) {
		this.model = model;
		initializeListeners();
	}
	
	private void initializeListeners() {
		model.addSaveSuccessListener(new SaveSuccessListener() {
			@Override
			public void notifySuccess() {
				updateViewTitles();
			}			
		});
		model.addDeleteSuccessListener(new DeleteSuccessListener() {
			@Override
			public void notifySuccess() {
				updateViewTitles();
			}
		});
	}
	
	private void updateViewTitles() {
		ArrayList<String> titles = model.getTitlesInAscendingOrder();
		view.updateLocalArray(titles.toArray());
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
	public void setView(LocalView view) {
		this.view = view;
		updateViewTitles();
	}

}
