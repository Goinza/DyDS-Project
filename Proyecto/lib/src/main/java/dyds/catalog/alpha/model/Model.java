package dyds.catalog.alpha.model;

import java.util.ArrayList;

public interface Model {
		
	public ArrayList<String> getTitlesInAscendingOrder();
	
	public void saveEntry(String title, String extract);
	
	public String getExtract(String title) throws InvalidTitleException;
	
	public void deleteEntry(String title) throws InvalidTitleException;
		
}
