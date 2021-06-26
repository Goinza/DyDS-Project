package dyds.catalog.alpha.presenter;

import dyds.catalog.alpha.model.LocalModel;
import dyds.catalog.alpha.model.SaveFailureListener;
import dyds.catalog.alpha.model.SaveSuccessListener;
import dyds.catalog.alpha.model.ServiceModel;
import dyds.catalog.alpha.model.Article;
import dyds.catalog.alpha.view.OnlineView;

public class SavePresenterImpl implements SavePresenter {

	private LocalModel localModel;
	private OnlineView view;
	private ServiceModel serviceModel;
	
	public SavePresenterImpl(LocalModel localModel, ServiceModel serviceModel) {
		this.localModel = localModel;
		this.serviceModel = serviceModel;
		initializeListeners();
	}	
	
	private void initializeListeners() {
		localModel.addSaveSuccessListener(new SaveSuccessListener() {
			@Override
			public void notifySuccess() {
				view.throwInfoMessage("Save complete", "The article has been saved successfully");				
			}						
		});
		localModel.addSaveFailureListener(new SaveFailureListener() {
			@Override
			public void notifyFailure() {
				view.throwInfoMessage("Save error", "There was an erorr trying to save the article");		
			}			
		});
	}
	
	@Override
	public void saveLastSearchedArticle() {
		Article lastSearchedArticle = serviceModel.getLastSearchedArticle();
		if(lastSearchedArticle != null){
			String title = lastSearchedArticle.getTitle();
			String extract = lastSearchedArticle.getExtract();
			localModel.saveEntry(title, extract);
		}
		else {
			view.throwInfoMessage("Save error", "You need to search an article before saving!");
		}
	}

	@Override
	public void setView(OnlineView view) {
		this.view = view;
	}

}
