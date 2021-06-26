package dyds.catalog.alpha.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class TestServiceModel {
	
	@Test
	public void testSearchArticle() {
		WikipediaConnection wikiConnection = new StubWikipediaConnection();
		ServiceModel serviceModel = new ServiceModelImpl(wikiConnection);
		try {
			Article article = serviceModel.searchArticle("Example title");
			String expectedTitle = "Example title";
			String expectedDescription = "Example description";
			if (article!=null) {
				assertEquals(expectedTitle, article.getTitle());
				assertEquals(expectedDescription, article.getExtract());
			}
			else {
				fail("Article from StubWikipediaConnection can't be null");
			}
		}
		catch (ServiceException e) {
			fail();
		}

	}	
	
	@Test
	public void testNullSearchArticle() {
		try {
			WikipediaConnection wikiConnection = new StubNoArticleConnection();
			ServiceModel serviceModel = new ServiceModelImpl(wikiConnection);
			Article article = serviceModel.searchArticle("Title that does not exists");
			if (article != null) {
				fail("The article should be null");
			}
		}
		catch (ServiceException e) {
			fail();
		}
	}
	
	@Test
	public void testExceptionSearchArticle() {
		try {
			WikipediaConnection wikiConnection = new StubForcedExceptionConnection();
			ServiceModel serviceModel = new ServiceModelImpl(wikiConnection);
			Article article = serviceModel.searchArticle("Title");
			fail("Stub should throw ServiceException");
		}
		catch (ServiceException e) {
			
		}
	}
	
	@Test
	public void testGetLastArticle() {
		WikipediaConnection wikiConnection = new StubWikipediaConnection();
		ServiceModel serviceModel = new ServiceModelImpl(wikiConnection);
		Article article = serviceModel.getLastSearchedArticle();
		String expectedTitle = "Last searched title";
		String expectedDescription = "Last searched description";
		if (article!=null) {
			assertEquals(expectedTitle, article.getTitle());
			assertEquals(expectedDescription, article.getExtract());
		}
		else {
			fail("Article from StubWikipediaConnection can't be null");
		}
	}
	
	@Test
	public void testNullLastArticle() {
		WikipediaConnection wikiConnection = new StubNoArticleConnection();
		ServiceModel serviceModel = new ServiceModelImpl(wikiConnection);
		Article article = serviceModel.getLastSearchedArticle();
		if (article != null) {
			fail("The article should be null");
		}
	}

}
