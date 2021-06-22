package dyds.catalog.alpha.presenter;

import dyds.catalog.alpha.view.MainView;

public interface LocalPresenter {
	
	public void selectEntry(Object entry);
	
	public void deleteEntry(Object entry);

	public void setView(MainView view);
	
}
