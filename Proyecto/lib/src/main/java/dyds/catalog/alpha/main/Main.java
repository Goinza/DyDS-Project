package dyds.catalog.alpha.main;

import dyds.catalog.alpha.model.SQLDatabase;
import dyds.catalog.alpha.model.ServiceModel;
import dyds.catalog.alpha.model.ServiceModelImpl;
import dyds.catalog.alpha.model.VideogameSearcher;
import dyds.catalog.alpha.model.WikipediaConnection;
import dyds.catalog.alpha.model.Database;
import dyds.catalog.alpha.model.LocalModel;
import dyds.catalog.alpha.model.LocalModelImpl;
import dyds.catalog.alpha.presenter.DeletePresenter;
import dyds.catalog.alpha.presenter.DeletePresenterImpl;
import dyds.catalog.alpha.presenter.SavePresenter;
import dyds.catalog.alpha.presenter.SavePresenterImpl;
import dyds.catalog.alpha.presenter.SearchPresenter;
import dyds.catalog.alpha.presenter.SearchPresenterImpl;
import dyds.catalog.alpha.presenter.SelectPresenter;
import dyds.catalog.alpha.presenter.SelectPresenterImpl;
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
  		SavePresenter save = new SavePresenterImpl(localModel, serviceModel);
  		SelectPresenter select = new SelectPresenterImpl(localModel);
  		DeletePresenter delete = new DeletePresenterImpl(localModel);  		
  		
  		MainWindow window = new MainWindow();
  		
  		OnlineView online = new OnlineView(window, search, save);
  		search.setView(online);
  		save.setView(online);
  		
  		LocalView local = new LocalView(window, select, delete);
  		select.setView(local);
  		delete.setView(local);
  	}
	
}
