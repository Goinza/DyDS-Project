package dyds.catalog.alpha.model;

import java.util.List;

public interface Database {
	
	public List<String> getTitles() throws DatabaseException;
	
	public void saveEntry(String title, String extract) throws DatabaseException;
	
	public String getExtract(String title) throws InvalidTitleException, DatabaseException;
	
	public void deleteEntry(String title) throws InvalidTitleException, DatabaseException;

}
