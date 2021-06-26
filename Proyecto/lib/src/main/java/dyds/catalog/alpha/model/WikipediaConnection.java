package dyds.catalog.alpha.model;

import java.io.IOException;

public interface WikipediaConnection {
		
	public Article searchArticle(String searchTerm) throws IOException;
	
	public Article getLastSearchedArticle();

}
