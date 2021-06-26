package dyds.catalog.alpha.model;

public interface ServiceModel {

	public Article searchArticle(String searchTerm) throws ServiceException;
	
	public Article getLastSearchedArticle();
	
}
