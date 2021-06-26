package dyds.catalog.alpha.model;

import java.util.LinkedList;
import java.util.List;

public class StubOneEntryDatabase implements Database {
	
	private String title;
	private String extract;

	@Override
	public List<String> getTitles() throws DatabaseException {
		List<String> list = new LinkedList<String>();
		list.add(title);
		return list;
	}

	@Override
	public void saveEntry(String title, String extract) throws DatabaseException {
		this.title = title;
		this.extract = extract;
	}

	@Override
	public String getExtract(String title) throws InvalidTitleException, DatabaseException {
		if (this.title == null) {
			throw new InvalidTitleException();
		}
		return extract;
	}

	@Override
	public void deleteEntry(String title) throws InvalidTitleException, DatabaseException {
		this.title = null;
		extract = null;
	}

}
