package dyds.catalog.alpha.model;

import java.util.List;

public class StubInvalidTitleExceptionDatabase implements Database {

	@Override
	public List<String> getTitles() throws DatabaseException {
		return null;
	}

	@Override
	public void saveEntry(String title, String extract) throws DatabaseException {
	}

	@Override
	public String getExtract(String title) throws InvalidTitleException, DatabaseException {
		throw new InvalidTitleException();
	}

	@Override
	public void deleteEntry(String title) throws InvalidTitleException, DatabaseException {
		throw new InvalidTitleException();

	}

}
