package dyds.catalog.alpha.model;

public class Article {
	
	private String title;
	private String extract;
	
	public Article(String title, String extract) {
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
