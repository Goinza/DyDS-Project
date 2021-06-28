package dyds.catalog.alpha.presenter;

import dyds.catalog.alpha.model.Article;
import dyds.catalog.alpha.model.ServiceException;
import dyds.catalog.alpha.model.ServiceModel;
import dyds.catalog.alpha.view.OnlineView;

public class SearchPresenterImpl implements SearchPresenter {

	private OnlineView view;
	private ServiceModel model;
	
	public SearchPresenterImpl(ServiceModel model) {
		this.model = model;
	}
	 
	@Override
	public void searchArticle() {
		new Thread(new Runnable() {
	          @Override public void run() {
	              view.setWorkingStatus();  
	              try {
		              String searchTerm = view.getTitleText();
		              if (!searchTerm.isBlank()) {
			              Article article = model.searchArticle(searchTerm);
			              if (article != null) {
			                  setViewSearchResultText(article);  
			              }
			              else {
			            	  view.throwInfoMessage("Search result", "No results found");
			              }
		              }
		              else {
		            	  view.throwInfoMessage("Search result", "You need to write a title before you search");
		              } 
	              }
	              catch (ServiceException e) {
	            	  view.throwErrorMessage("Search error", e.getMessage());
	              }
	              finally {
	            	  view.setWatingStatus(); 
	              }

	              
	            }

		}).start();
	}
	
	private void setViewSearchResultText(Article article) {
  		String title = article.getTitle();
  		String extract = article.getExtract();
  		String searchResultText;
  		searchResultText = "<h1>" + title + "</h1>";
    	searchResultText += extract.replace("\\n", "\n");
    	view.setExtractText(searchResultText);
  	}

	@Override
	public void setView(OnlineView view) {
		this.view = view;
	}

}
