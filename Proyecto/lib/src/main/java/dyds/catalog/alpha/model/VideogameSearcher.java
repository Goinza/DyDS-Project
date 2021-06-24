package dyds.catalog.alpha.model;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class VideogameSearcher implements WikipediaConnection {
	
  	private WikipediaSearchAPI searchAPI;
  	private WikipediaPageAPI pageAPI;
	
	public VideogameSearcher() {
		Retrofit retrofit = new Retrofit.Builder()
		        .baseUrl("https://en.wikipedia.org/w/")
		        .addConverterFactory(ScalarsConverterFactory.create())
		        .build();

	    searchAPI = retrofit.create(WikipediaSearchAPI.class);
	    pageAPI = retrofit.create(WikipediaPageAPI.class);
	}

	@Override
	public WikipediaArticle getArticle(String searchTerm) {
		WikipediaArticle article = null;
		JsonObject searchResult = getSearchResult(searchTerm);
		if (searchResult != null) {
			String title = getSearchTitle(searchResult);
			String extract = getSearchExtract(searchResult);
			article = new WikipediaArticle(title, extract);
		}
		
		return article;
	}
	
	private JsonObject getSearchResult(String searchTerm) {
		Iterator<JsonElement> resultIterator = getResultIterator(searchTerm);
		JsonObject searchResult = null;
		if (resultIterator.hasNext()) {
            searchResult = resultIterator.next().getAsJsonObject();
        }
		
		return searchResult;
	}
	
	private Iterator<JsonElement> getResultIterator(String searchTerm) {
		Iterator<JsonElement> resultIterator = null;
		try {
			Response<String> callResponse = searchAPI.searchForTerm(searchTerm + " articletopic:\"video-games\"").execute();
	        Gson gson = new Gson();
	        JsonObject jobj = gson.fromJson(callResponse.body(), JsonObject.class);
	        JsonObject query = jobj.get("query").getAsJsonObject();
	        JsonArray resultArray = query.get("search").getAsJsonArray();
	        resultIterator = resultArray.iterator();
		} catch (IOException e1) { 
      	  e1.printStackTrace();
        }	
		
		return resultIterator;
	}
	
	private String getSearchTitle(JsonObject searchResult) {
		return searchResult.get("title").getAsString();
	}
	
	private String getSearchExtract(JsonObject searchResult) {
		String searchResultPageId = searchResult.get("pageid").getAsString();
		String extract = null;
		try {
			boolean foundResult = searchResultPageId != null;
			if (foundResult) {
				Response<String> callResponse = pageAPI.getExtractByPageID(searchResultPageId).execute();
				Gson gson = new Gson();
		        JsonObject jobj = gson.fromJson(callResponse.body(), JsonObject.class);
		        JsonObject query = jobj.get("query").getAsJsonObject();
		        JsonObject pages = query.get("pages").getAsJsonObject();
		        Set<Map.Entry<String, JsonElement>> pagesSet = pages.entrySet();
		        Map.Entry<String, JsonElement> first = pagesSet.iterator().next();
		        JsonObject page = first.getValue().getAsJsonObject();
		        JsonElement searchResultExtract = page.get("extract");
		        extract = searchResultExtract.getAsString();
			}			
		} catch (IOException e1) { 			
		  	e1.printStackTrace();
		}	
        
        return extract;
	}

}
