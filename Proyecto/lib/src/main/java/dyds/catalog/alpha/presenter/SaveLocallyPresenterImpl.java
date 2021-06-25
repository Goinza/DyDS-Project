package dyds.catalog.alpha.presenter;

import dyds.catalog.alpha.model.Model;
import dyds.catalog.alpha.model.WikipediaArticle;
import dyds.catalog.alpha.model.WikipediaConnection;
import dyds.catalog.alpha.view.OnlineView;

public class SaveLocallyPresenterImpl implements SaveLocallyPresenter {

	private Model model;
	private OnlineView view;
	private WikipediaConnection wikiConnection;
	
	public SaveLocallyPresenterImpl(Model model, WikipediaConnection wikiConnection) {
		this.model = model;
		this.wikiConnection = wikiConnection;
	}	
	
	@Override
	public void saveLastSearchedArticle() {
		WikipediaArticle lastSearchedArticle = wikiConnection.getLastSearchedArticle();
		if(lastSearchedArticle != null){
			String title = lastSearchedArticle.getTitle();
			String extract = lastSearchedArticle.getExtract();
			model.saveEntry(title, extract);
			//ArrayList<String> titleList = model.getTitlesInAscendingOrder();
			//view.updateLocalArray(titleList.toArray());   IMPLEMENT THIS IN A LISTENER?
			view.throwInfoMessage("Save complete", "The article has been saved successfully");
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
