package dyds.catalog.alpha.presenter;

import java.util.ArrayList;

import dyds.catalog.alpha.model.Database;
import dyds.catalog.alpha.model.WikipediaArticle;
import dyds.catalog.alpha.model.WikipediaConnection;
import dyds.catalog.alpha.view.MainView;

public class OnlinePresenterImpl implements OnlinePresenter {
	
	private String searchResultTitle = null;
	private String searchResultExtract = null;
  	
	private Database database;
  	private MainView view;
  	private WikipediaConnection wikiConnection;
  	
  	public OnlinePresenterImpl(Database database, WikipediaConnection wikiConnection) {
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
	                  setViewTitleText();  
	              }
	              else {
	            	  //Throw error message in the view
	              }
	              view.setWatingStatus();	                           
	            }

		}).start();
	}
  	
  	private void setViewTitleText() {
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
			String replacedTitle = searchResultTitle.replace("'", "`");
			database.saveEntry(replacedTitle, searchResultExtract);
			ArrayList<String> titleList = database.getTitlesInAscendingOrder();
			view.updateLocalArray(titleList.toArray());  
		}
	}
			
}
