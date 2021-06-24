package dyds.catalog.alpha.presenter;

import java.util.ArrayList;

import dyds.catalog.alpha.model.Model;
import dyds.catalog.alpha.model.WikipediaArticle;
import dyds.catalog.alpha.model.WikipediaConnection;
import dyds.catalog.alpha.view.MainView;

public class OnlinePresenterImpl implements OnlinePresenter {
	
	private WikipediaArticle lastSearchedArticle = null;
  	
	private Model database;
  	private MainView view;
  	private WikipediaConnection wikiConnection;
  	
  	public OnlinePresenterImpl(Model database, WikipediaConnection wikiConnection) {
	    this.database = database;
	    this.wikiConnection = wikiConnection;
  	}
  	
  	@Override
  	public void setView(MainView view) {
  		this.view = view;
  	}
	
  	@Override
	public void searchArticle() {
		new Thread(new Runnable() {
	          @Override public void run() {
	              view.setWorkingStatus();          
	              String searchTerm = view.getTitleText();
	              lastSearchedArticle = wikiConnection.searchArticle(searchTerm);
	              if (lastSearchedArticle != null) {
	                  setViewSearchResultText();  
	              }
	              else {
	            	  view.throwInfoMessage("Search result", "No results found");
	              }
	              view.setWatingStatus();
	            }

		}).start();
	}
  	
  	private void setViewSearchResultText() {
  		String title = lastSearchedArticle.getTitle();
  		String extract = lastSearchedArticle.getExtract();
  		String searchResultText;
  		searchResultText = "<h1>" + title + "</h1>";
    	searchResultText += extract.replace("\\n", "\n");
    	searchResultText = MainView.textToHtml(searchResultText, view.getTitleText());
    	view.setExtractText(searchResultText);
  	}
	
	@Override
	public void saveLastSearchedArticle() {		
		if(lastSearchedArticle != null){
			String title = lastSearchedArticle.getTitle();
			String extract = lastSearchedArticle.getExtract();
			database.saveEntry(title, extract);
			ArrayList<String> titleList = database.getTitlesInAscendingOrder();
			view.updateLocalArray(titleList.toArray());  
			view.throwInfoMessage("Save complete", "The article has been saved successfully");
		}
		else {
			view.throwInfoMessage("Save error", "You need to search an article before saving!");
		}
	}
			
}
