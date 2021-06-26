package dyds.catalog.alpha.presenter;

import java.util.List;

import dyds.catalog.alpha.model.AccessFailureListener;
import dyds.catalog.alpha.model.DeleteSuccessListener;
import dyds.catalog.alpha.model.InvalidTitleException;
import dyds.catalog.alpha.model.LocalModel;
import dyds.catalog.alpha.model.SaveSuccessListener;
import dyds.catalog.alpha.view.LocalView;

public class SelectLocallyPresenterImpl implements SelectLocallyPresenter {
	
	LocalModel model;
	LocalView view;
	
	public SelectLocallyPresenterImpl(LocalModel model) {
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
		model.addAccessFailureListener(new AccessFailureListener() {
			@Override
			public void notifyFailure() {
				view.throwErrorMessage("Error", "There was an error trying to update the local list of articles");
			}
		});
	}
	
	private void updateViewTitles() {
		List<String> titles = model.getTitles();
		view.updateLocalArray(titles.toArray());
	}

	@Override
	public void selectEntry(Object entry) {
		if (entry != null) {
			try {
				String title = entry.toString();
				String extractText = model.getExtract(title);
				view.setLocalExtractText(extractText);
			}
			catch (InvalidTitleException e) {
				view.throwErrorMessage("Error", e.getMessage());
			}	
		}
		else {
			view.throwInfoMessage("Select result", "There is no article to select");
		}		
	}

	@Override
	public void setView(LocalView view) {
		this.view = view;
		updateViewTitles();
	}

}
