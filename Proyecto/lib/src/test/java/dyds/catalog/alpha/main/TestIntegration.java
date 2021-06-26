package dyds.catalog.alpha.main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import dyds.catalog.alpha.model.Database;
import dyds.catalog.alpha.model.LocalModel;
import dyds.catalog.alpha.model.LocalModelImpl;
import dyds.catalog.alpha.model.SQLDatabase;
import dyds.catalog.alpha.model.ServiceModel;
import dyds.catalog.alpha.model.ServiceModelImpl;
import dyds.catalog.alpha.model.StubNoArticleConnection;
import dyds.catalog.alpha.model.StubWikipediaConnection;
import dyds.catalog.alpha.model.WikipediaConnection;
import dyds.catalog.alpha.presenter.DeletePresenter;
import dyds.catalog.alpha.presenter.DeletePresenterImpl;
import dyds.catalog.alpha.presenter.SavePresenter;
import dyds.catalog.alpha.presenter.SavePresenterImpl;
import dyds.catalog.alpha.presenter.SearchPresenter;
import dyds.catalog.alpha.presenter.SearchPresenterImpl;
import dyds.catalog.alpha.presenter.SelectPresenter;
import dyds.catalog.alpha.presenter.SelectPresenterImpl;
import dyds.catalog.alpha.view.MainWindow;
import dyds.catalog.alpha.view.TestLocalView;
import dyds.catalog.alpha.view.TestOnlineView;

public class TestIntegration {
	
	private TestOnlineView onlineView;
	private TestLocalView localView;
	
	
	@Before
	public void setup() {
  		Database database = new SQLDatabase();
  		LocalModel localModel = new LocalModelImpl(database);
  		
  		WikipediaConnection wikiConnection = new StubWikipediaConnection();
  		ServiceModel serviceModel = new ServiceModelImpl(wikiConnection);
  		
  		SearchPresenter search = new SearchPresenterImpl(serviceModel);
  		SavePresenter save = new SavePresenterImpl(localModel, serviceModel);
  		SelectPresenter select = new SelectPresenterImpl(localModel);
  		DeletePresenter delete = new DeletePresenterImpl(localModel);  		
  		
  		MainWindow window = new MainWindow();
  		
  		onlineView = new TestOnlineView(window, search, save);
  		search.setView(onlineView);
  		save.setView(onlineView);
  		
  		localView = new TestLocalView(window, select, delete);
  		select.setView(localView);
  		delete.setView(localView);
	}
	
	@Test
	public void testSearchArticle() {			
		String searchTerm = "Example title";
		String expectedExtract = "<html>\n"
				+ "  <head>\n"
				+ "    \n"
				+ "  </head>\n"
				+ "  <body>\n"
				+ "    <h1>\n"
				+ "      Example title\n"
				+ "    </h1>\n"
				+ "    Example description\n"
				+ "  </body>\n"
				+ "</html>\n";
		onlineView.searchArticle(searchTerm);				
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			fail();
		}
		String actualExtract = onlineView.getExtractText();
		assertEquals(expectedExtract, actualExtract);
	}
	
	@Test
	public void testSaveLastSearchedArticle() {
		String expectedExtract = "<html>\n"
				+ "  <head>\n"
				+ "    \n"
				+ "  </head>\n"
				+ "  <body>\n"
				+ "    Last searched description\n"
				+ "  </body>\n"
				+ "</html>\n";
		onlineView.saveLastSearchedArticle();
		localView.selectArticle(0);
		String actualExtract = localView.getExtractText();
		assertEquals(expectedExtract, actualExtract);
	}
	
	@Test
	public void testDeleteEntry() {
		onlineView.saveLastSearchedArticle();
		int titlesCount = localView.getTitlesCount();
		if (titlesCount > 0) {
			localView.deleteFirstEntry();	
		}			
		else {
			fail("There is nothing to delete");
		}
		int expectedCount = titlesCount - 1;
		int actualCount = localView.getTitlesCount();
		assertEquals(expectedCount, actualCount);
	}
	
	@Test
	public void testDeleteAllEntries() {
		int titlesCount = localView.getTitlesCount();
		for (int i=0; i<titlesCount; i++) {
			localView.deleteFirstEntry();
		}
		int newTitlesCount = localView.getTitlesCount();
		assertEquals(0, newTitlesCount);
	}
	
	
	@Test
	public void testSearchArticleFailure() {
		setupNoArticleStub();
		String searchTerm = "Title that does not exists";
		String expectedExtract = "<html>\r\n"
				+ "  <head>\r\n"
				+ "\r\n"
				+ "  </head>\r\n"
				+ "  <body>\r\n"
				+ "    <p style=\"margin-top: 0\">\r\n"
				+ "      \r\n"
				+ "    </p>\r\n"
				+ "  </body>\r\n"
				+ "</html>\r\n";
		onlineView.searchArticle(searchTerm);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			fail();
		}
		String actualExtract = onlineView.getExtractText();
		assertEquals(expectedExtract, actualExtract);
	}
	
	private void setupNoArticleStub() {
  		Database database = new SQLDatabase();
  		LocalModel localModel = new LocalModelImpl(database);
  		
  		WikipediaConnection wikiConnection = new StubNoArticleConnection();
  		ServiceModel serviceModel = new ServiceModelImpl(wikiConnection);
  		
  		SearchPresenter search = new SearchPresenterImpl(serviceModel);
  		SavePresenter save = new SavePresenterImpl(localModel, serviceModel);
  		SelectPresenter select = new SelectPresenterImpl(localModel);
  		DeletePresenter delete = new DeletePresenterImpl(localModel);  		
  		
  		MainWindow window = new MainWindow();
  		
  		onlineView = new TestOnlineView(window, search, save);
  		search.setView(onlineView);
  		save.setView(onlineView);
  		
  		localView = new TestLocalView(window, select, delete);
  		select.setView(localView);
  		delete.setView(localView);
	}

}
