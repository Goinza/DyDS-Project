package dyds.catalog.alpha.model;

import java.io.IOException;

public class ServiceModelImpl implements ServiceModel {
	
	private WikipediaConnection wikiConnection;
	
	public ServiceModelImpl(WikipediaConnection wikiConnection) {
		this.wikiConnection = wikiConnection;
	}

	@Override
	public Article searchArticle(String searchTerm) throws ServiceException {
		Article article = null;
		try {
			article = wikiConnection.searchArticle(searchTerm);
		}
		catch (IOException e) {
			throw new ServiceException(e.getMessage());
		}
		return article;
	}

	@Override
	public Article getLastSearchedArticle() {
		return wikiConnection.getLastSearchedArticle();
	}

}
