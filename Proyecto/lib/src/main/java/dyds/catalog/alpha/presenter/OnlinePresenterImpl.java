package dyds.catalog.alpha.presenter;

import java.util.ArrayList;

import dyds.catalog.alpha.model.Model;
import dyds.catalog.alpha.model.WikipediaArticle;
import dyds.catalog.alpha.model.WikipediaConnection;
import dyds.catalog.alpha.view.MainView;

public class OnlinePresenterImpl implements OnlinePresenter {
	
	private String searchResultTitle = null;
	private String searchResultExtract = null;
  	
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
	              WikipediaArticle article = wikiConnection.getArticle(searchTerm);
	              if (article != null) {
		              searchResultTitle = article.getTitle();
	                  searchResultExtract = article.getExtract();
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
  		String searchResultText;
  		searchResultText = "<h1>" + searchResultTitle + "</h1>";
    	searchResultText += searchResultExtract.replace("\\n", "\n");
    	searchResultText = MainView.textToHtml(searchResultText, view.getTitleText());
    	view.setExtractText(searchResultText);
  	}
	
	@Override
	public void saveLastSearchedArticle() {		
		boolean validText = searchResultExtract != "" && !searchResultExtract.equals("No Results");
		if(validText){
			database.saveEntry(searchResultTitle, searchResultExtract);
			ArrayList<String> titleList = database.getTitlesInAscendingOrder();
			view.updateLocalArray(titleList.toArray());  
		}
	}
			
}
