package main;

import dyds.catalog.alpha.model.SQLDatabase;
import dyds.catalog.alpha.model.VideogameSearcher;
import dyds.catalog.alpha.model.WikipediaConnection;

import dyds.catalog.alpha.model.Model;
import dyds.catalog.alpha.presenter.DeleteLocallyPresenter;
import dyds.catalog.alpha.presenter.DeleteLocallyPresenterImpl;
import dyds.catalog.alpha.presenter.SaveLocallyPresenter;
import dyds.catalog.alpha.presenter.SaveLocallyPresenterImpl;
import dyds.catalog.alpha.presenter.SearchPresenter;
import dyds.catalog.alpha.presenter.SearchPresenterImpl;
import dyds.catalog.alpha.presenter.SelectLocallyPresenter;
import dyds.catalog.alpha.presenter.SelectLocallyPresenterImpl;
import dyds.catalog.alpha.view.LocalView;
import dyds.catalog.alpha.view.MainWindow;
import dyds.catalog.alpha.view.OnlineView;

public class Main {

  	public static void main(String[] args) {
  		Model m = new SQLDatabase();
  		WikipediaConnection wc = new VideogameSearcher();
  		Object [] currentArticles = m.getTitlesInAscendingOrder().toArray();
  		
  		SearchPresenter search = new SearchPresenterImpl(wc);
  		SaveLocallyPresenter save = new SaveLocallyPresenterImpl(m, wc);
  		SelectLocallyPresenter select = new SelectLocallyPresenterImpl(m);
  		DeleteLocallyPresenter delete = new DeleteLocallyPresenterImpl(m);  		
  		
  		MainWindow window = new MainWindow();
  		
  		OnlineView online = new OnlineView(window, search, save);
  		search.setView(online);
  		save.setView(online);
  		
  		LocalView local = new LocalView(window, select, delete, currentArticles);
  		select.setView(local);
  		delete.setView(local);
  	}
	
}
