package dyds.catalog.alpha.presenter;

import java.util.ArrayList;

import dyds.catalog.alpha.model.InvalidTitleException;
import dyds.catalog.alpha.model.Model;
import dyds.catalog.alpha.view.LocalView;

public class DeleteLocallyPresenterImpl implements DeleteLocallyPresenter {
	
	Model model;
	LocalView view;
	
	public DeleteLocallyPresenterImpl(Model model) {
		this.model = model;
	}

	@Override
	public void deleteEntry(String title) {
		try {
			model.deleteEntry(title);
			ArrayList<String> titleList = model.getTitlesInAscendingOrder();
			view.updateLocalArray(titleList.toArray());
			view.setLocalExtractText("");
			view.throwInfoMessage("Delete complete", "The article has been deleted successfully");
		}
		catch (InvalidTitleException e) {
			view.throwErrorMessage("Error", e.getMessage());
		}

	}

	@Override
	public void setView(LocalView view) {
		this.view = view;
	}

}
