package dyds.catalog.alpha.model;

import java.util.ArrayList;

public interface Database {
		
	public ArrayList<String> getTitlesInAscendingOrder();
	
	public void saveEntry(String title, String extract);
	
	public String getExtract(String title);
	
	public void deleteEntry(String title);
	
}
