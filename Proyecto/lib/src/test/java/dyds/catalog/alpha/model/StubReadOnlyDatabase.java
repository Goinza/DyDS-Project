package dyds.catalog.alpha.model;

import java.util.LinkedList;
import java.util.List;

public class StubReadOnlyDatabase implements Database {
	
	LinkedList<String> titles;
	
	public StubReadOnlyDatabase() {
		titles = new LinkedList<String>();
		titles.add("Example Title");
		titles.add("Another Title");
		titles.add("Last Title");
	}

	@Override
	public List<String> getTitles() throws DatabaseException {
		return titles;
	}

	@Override
	public void saveEntry(String title, String extract) throws DatabaseException {
	}

	@Override
	public String getExtract(String title) throws InvalidTitleException, DatabaseException {
		return "Article Description";
	}

	@Override
	public void deleteEntry(String title) throws InvalidTitleException, DatabaseException {
	}

}
