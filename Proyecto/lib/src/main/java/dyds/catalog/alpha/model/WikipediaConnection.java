package dyds.catalog.alpha.model;

public interface WikipediaConnection {
		
	public WikipediaArticle searchArticle(String searchTerm);
	
	public WikipediaArticle getLastSearchedArticle();

}
