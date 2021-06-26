package dyds.catalog.alpha.model;

public class StubNoArticleConnection implements WikipediaConnection {

	@Override
	public Article searchArticle(String searchTerm) {
		return null;
	}

	@Override
	public Article getLastSearchedArticle() {
		return null;
	}

}
