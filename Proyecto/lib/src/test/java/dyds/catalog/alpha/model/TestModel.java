package dyds.catalog.alpha.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class TestModel {
	
	@Test
	public void testSearchArticle() {
		WikipediaConnection wikiConnection = new StubWikipediaConnection();
		WikipediaArticle article = wikiConnection.searchArticle("Example title");
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
	
	@Test
	public void testGetLastArticle() {
		WikipediaConnection wikiConnection = new StubWikipediaConnection();
		WikipediaArticle article = wikiConnection.getLastSearchedArticle();
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
	
	

}
