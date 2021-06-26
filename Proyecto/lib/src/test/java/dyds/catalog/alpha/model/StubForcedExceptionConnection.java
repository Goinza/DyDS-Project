package dyds.catalog.alpha.model;

import java.io.IOException;

public class StubForcedExceptionConnection implements WikipediaConnection {

	@Override
	public Article searchArticle(String searchTerm) throws IOException {
		throw new IOException();
	}

	@Override
	public Article getLastSearchedArticle() {
		return null;
	}

}
