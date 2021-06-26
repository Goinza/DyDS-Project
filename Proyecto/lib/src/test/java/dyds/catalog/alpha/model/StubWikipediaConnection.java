package dyds.catalog.alpha.model;

public class StubWikipediaConnection implements WikipediaConnection {

	@Override
	public Article searchArticle(String searchTerm) {
		Article article = new Article("Example title", "Example description");
		return article;
	}

	@Override
	public Article getLastSearchedArticle() {
		Article article = new Article("Last searched title", "Last searched description");
		return article;
	}

}
