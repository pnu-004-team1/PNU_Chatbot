package libInfo;


public class LibraryCrawler {
	private String url;
	private String category;
	
	
	public LibraryCrawler(String url) {
		this.url = url;
	}

	private void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}
	
	private void setCategory(String category) {
		this.category = category;
	}

	public String getCategory() {
		return category;
	}
	
}
