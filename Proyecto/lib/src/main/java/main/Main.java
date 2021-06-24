package main;

import dyds.catalog.alpha.model.SQLDatabase;
import dyds.catalog.alpha.model.VideogameSearcher;
import dyds.catalog.alpha.model.WikipediaConnection;
import dyds.catalog.alpha.model.Model;
import dyds.catalog.alpha.presenter.DeleteLocallyPresenter;
import dyds.catalog.alpha.presenter.DeleteLocallyPresenterImpl;
import dyds.catalog.alpha.presenter.OnlinePresenter;
import dyds.catalog.alpha.presenter.OnlinePresenterImpl;
import dyds.catalog.alpha.presenter.SaveLocallyPresenter;
import dyds.catalog.alpha.presenter.SaveLocallyPresenterImpl;
import dyds.catalog.alpha.presenter.SearchPresenter;
import dyds.catalog.alpha.presenter.SearchPresenterImpl;
import dyds.catalog.alpha.presenter.SelectLocallyPresenter;
import dyds.catalog.alpha.presenter.SelectLocallyPresenterImpl;
import dyds.catalog.alpha.view.MainView;

public class Main {

  	public static void main(String[] args) {
  		Model m = new SQLDatabase();
  		WikipediaConnection wc = new VideogameSearcher();
  		Object [] currentArticles = m.getTitlesInAscendingOrder().toArray();
  		
  		SearchPresenter search = new SearchPresenterImpl(wc);
  		SaveLocallyPresenter save = new SaveLocallyPresenterImpl(m, wc);
  		SelectLocallyPresenter select = new SelectLocallyPresenterImpl(m);
  		DeleteLocallyPresenter delete = new DeleteLocallyPresenterImpl(m);
  		
  		MainView mw = new MainView(search, save, select, delete, currentArticles);  		
  		search.setView(mw);
  		save.setView(mw);
  		select.setView(mw);
  		delete.setView(mw);
  	}
	
}
