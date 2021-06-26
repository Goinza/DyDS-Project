package dyds.catalog.alpha.presenter;

import java.util.List;

import dyds.catalog.alpha.model.AccessFailureListener;
import dyds.catalog.alpha.model.DeleteFailureListener;
import dyds.catalog.alpha.model.DeleteSuccessListener;
import dyds.catalog.alpha.model.InvalidTitleException;
import dyds.catalog.alpha.model.LocalModel;
import dyds.catalog.alpha.view.LocalView;

public class DeleteLocallyPresenterImpl implements DeleteLocallyPresenter {
	
	LocalModel model;
	LocalView view;
	
	public DeleteLocallyPresenterImpl(LocalModel model) {
		this.model = model;
		initializeListeners();
	}
	
	public void initializeListeners() {
		model.addDeleteSuccessListener(new DeleteSuccessListener() {
			@Override
			public void notifySuccess() {
				view.throwInfoMessage("Delete complete", "The article has been deleted successfully");
			}
			
		});		
		model.addDeleteFailureListener(new DeleteFailureListener() {
			@Override
			public void notifyFailure() {
				view.throwErrorMessage("Delete failure", "There was an error trying to delete the article");
			}			
		});
		model.addAccessFailureListener(new AccessFailureListener() {
			@Override
			public void notifyFailure() {
				view.throwErrorMessage("Delete failure", "There was an error trying to delete the article");
			}
		});
	}

	@Override
	public void deleteEntry(Object entry) {
		if (entry != null) {
			try {
				String title = entry.toString();
				model.deleteEntry(title);
				List<String> titleList = model.getTitles();
				view.updateLocalArray(titleList.toArray());
				view.setLocalExtractText("");
			}
			catch (InvalidTitleException e) {
				view.throwErrorMessage("Error", e.getMessage());
			}
		}
		else {
			view.throwInfoMessage("Delete result", "You need to select an article beforing deleting");	
		}		
	}

	@Override
	public void setView(LocalView view) {
		this.view = view;
	}

}
