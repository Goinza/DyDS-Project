package dyds.catalog.alpha.presenter;

import dyds.catalog.alpha.view.MainView;

public interface OnlinePresenter {

	public void searchArticle();
	
	public void saveLastSearchedArticle();
	
	public void setView(MainView view);
	
}
