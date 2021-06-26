package dyds.catalog.alpha.presenter;

import dyds.catalog.alpha.view.LocalView;

public interface DeleteLocallyPresenter {

	public void deleteEntry(Object entry);
	
	public void setView(LocalView view);
	
}
