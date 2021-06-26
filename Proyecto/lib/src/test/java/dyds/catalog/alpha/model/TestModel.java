package dyds.catalog.alpha.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class TestModel {
	
	@Test
	public void testSearchArticle() {
		WikipediaConnection wikiConnection = new StubWikipediaConnection();
		try {
			Article article = wikiConnection.searchArticle("Example title");
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
		WikipediaConnection wikiConnection = new StubNoArticle();
		Article article = wikiConnection.searchArticle("Title that does not exists");
		if (article != null) {
			fail("The article should be null");
		}
	}
	
	@Test
	public void testGetLastArticle() {
		WikipediaConnection wikiConnection = new StubWikipediaConnection();
		Article article = wikiConnection.getLastSearchedArticle();
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
		WikipediaConnection wikiConnection = new StubNoArticle();
		Article article = wikiConnection.getLastSearchedArticle();
		if (article != null) {
			fail("The article should be null");
		}
	}

}
