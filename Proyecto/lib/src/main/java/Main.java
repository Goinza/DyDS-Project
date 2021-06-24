import dyds.catalog.alpha.model.DatabaseImpl;
import dyds.catalog.alpha.model.VideogameSearcher;
import dyds.catalog.alpha.model.WikipediaConnection;
import dyds.catalog.alpha.model.Database;
import dyds.catalog.alpha.presenter.LocalPresenter;
import dyds.catalog.alpha.presenter.LocalPresenterImpl;
import dyds.catalog.alpha.presenter.OnlinePresenter;
import dyds.catalog.alpha.presenter.OnlinePresenterImpl;
import dyds.catalog.alpha.view.MainView;

public class Main {

  	public static void main(String[] args) {
  		Database db = new DatabaseImpl();
  		WikipediaConnection wc = new VideogameSearcher();
  		Object [] currentArticles = db.getTitlesInAscendingOrder().stream().sorted().toArray();
  		
  		OnlinePresenter op = new OnlinePresenterImpl(db, wc);
  		LocalPresenter lp = new LocalPresenterImpl(db);
  		MainView mw = new MainView(op, lp, currentArticles);
  		op.setView(mw);
  		lp.setView(mw);
  	}
	
}
