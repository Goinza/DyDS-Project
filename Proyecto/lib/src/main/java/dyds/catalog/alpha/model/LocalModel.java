package dyds.catalog.alpha.model;

import java.util.List;

public interface LocalModel {
		
	public List<String> getTitles();
	
	public void saveEntry(String title, String extract);
	
	public String getExtract(String title) throws InvalidTitleException;
	
	public void deleteEntry(String title) throws InvalidTitleException;		
	
	public void addSaveSuccessListener(SaveSuccessListener listener);
	
	public void addSaveFailureListener(SaveFailureListener listener);
	
	public void addDeleteSuccessListener(DeleteSuccessListener listener);
	
	public void addDeleteFailureListener(DeleteFailureListener listener);
}
