package main;

import dyds.catalog.alpha.model.SQLDatabase;
import dyds.catalog.alpha.model.ServiceModel;
import dyds.catalog.alpha.model.ServiceModelImpl;
import dyds.catalog.alpha.model.VideogameSearcher;
import dyds.catalog.alpha.model.WikipediaConnection;
import dyds.catalog.alpha.model.Database;
import dyds.catalog.alpha.model.LocalModel;
import dyds.catalog.alpha.model.LocalModelImpl;
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
  		Database database = new SQLDatabase();
  		LocalModel localModel = new LocalModelImpl(database);
  		
  		WikipediaConnection wikiConnection = new VideogameSearcher();
  		ServiceModel serviceModel = new ServiceModelImpl(wikiConnection);
  		
  		SearchPresenter search = new SearchPresenterImpl(serviceModel);
  		SaveLocallyPresenter save = new SaveLocallyPresenterImpl(localModel, serviceModel);
  		SelectLocallyPresenter select = new SelectLocallyPresenterImpl(localModel);
  		DeleteLocallyPresenter delete = new DeleteLocallyPresenterImpl(localModel);  		
  		
  		MainWindow window = new MainWindow();
  		
  		OnlineView online = new OnlineView(window, search, save);
  		search.setView(online);
  		save.setView(online);
  		
  		LocalView local = new LocalView(window, select, delete);
  		select.setView(local);
  		delete.setView(local);
  	}
	
}
