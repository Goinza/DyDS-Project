package dyds.catalog.alpha.model;

public class WikipediaArticle {
	
	private String title;
	private String extract;
	
	public WikipediaArticle(String title, String extract) {
		this.title = title;
		this.extract = extract;
	}
	public String getTitle() {
		return title;
	}
	public String getExtract() {
		return extract;
	}

}
