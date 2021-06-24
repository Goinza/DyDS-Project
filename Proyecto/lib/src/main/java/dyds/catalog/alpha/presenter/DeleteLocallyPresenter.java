package dyds.catalog.alpha.presenter;

import dyds.catalog.alpha.view.MainView;

public interface DeleteLocallyPresenter {

	public void deleteEntry(String title);
	
	public void setView(MainView view);
	
}
