package dyds.catalog.alpha.presenter;

import dyds.catalog.alpha.model.WikipediaArticle;
import dyds.catalog.alpha.model.WikipediaConnection;
import dyds.catalog.alpha.view.MainView;

public class SearchPresenterImpl implements SearchPresenter {

	private MainView view;
	private WikipediaConnection wikiConnection;
	
	public SearchPresenterImpl(WikipediaConnection wikiConnection) {
		this.wikiConnection = wikiConnection;
	}
	 
	@Override
	public void searchArticle() {
		new Thread(new Runnable() {
	          @Override public void run() {
	              view.setWorkingStatus();          
	              String searchTerm = view.getTitleText();
	              WikipediaArticle article = wikiConnection.searchArticle(searchTerm);
	              if (article != null) {
	                  setViewSearchResultText(article);  
	              }
	              else {
	            	  view.throwInfoMessage("Search result", "No results found");
	              }
	              view.setWatingStatus();
	            }

		}).start();
	}
	
	private void setViewSearchResultText(WikipediaArticle article) {
  		String title = article.getTitle();
  		String extract = article.getExtract();
  		String searchResultText;
  		searchResultText = "<h1>" + title + "</h1>";
    	searchResultText += extract.replace("\\n", "\n");
    	searchResultText = MainView.textToHtml(searchResultText, view.getTitleText());
    	view.setExtractText(searchResultText);
  	}

	@Override
	public void setView(MainView view) {
		this.view = view;
	}

}
