package dyds.catalog.alpha.model;

public class StubWikipediaConnection implements WikipediaConnection {

	@Override
	public WikipediaArticle searchArticle(String searchTerm) {
		WikipediaArticle article = new WikipediaArticle("Example title", "Example description");
		return article;
	}

	@Override
	public WikipediaArticle getLastSearchedArticle() {
		WikipediaArticle article = new WikipediaArticle("Last searched title", "Last searched description");
		return article;
	}

}
