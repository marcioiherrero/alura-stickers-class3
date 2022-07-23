
public enum APIsUrl {
	
    URL_IMDB("https://api.mocki.io/v2/549a5d8b/Top250Movies"), 
    URL_NASA("https://api.mocki.io/v2/549a5d8b/NASA-APOD");
	
	private String url;
	
	APIsUrl(String url) {
		this.url = url;
	}
	
	public String url() {
		return url;
	}
	
}