package dyds.catalog.alpha.presenter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import dyds.catalog.alpha.fulllogic.WikipediaPageAPI;
import dyds.catalog.alpha.fulllogic.WikipediaSearchAPI;
import dyds.catalog.alpha.model.Database;
import dyds.catalog.alpha.view.MainView;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class OnlinePresenterImpl implements OnlinePresenter {
	
	private String searchResultTitle = null;
	private String searchResultText = null;
  	
	private Database database;
  	private MainView view;
  	private WikipediaSearchAPI searchAPI;
  	private WikipediaPageAPI pageAPI;
  	
  	public OnlinePresenterImpl(Database database) {
  		Retrofit retrofit = new Retrofit.Builder()
		        .baseUrl("https://en.wikipedia.org/w/")
		        .addConverterFactory(ScalarsConverterFactory.create())
		        .build();

	    searchAPI = retrofit.create(WikipediaSearchAPI.class);
	    pageAPI = retrofit.create(WikipediaPageAPI.class);
	    this.database = database;
  	}
  	
  	@Override
  	public void setView(MainView view) {
  		this.view = view;
  	}
	
  	@Override
	public void searchArticle() {
		new Thread(new Runnable() {
	          @Override public void run() {
	              view.setWorkingStatus();                     
                  searchResultText = getSearchResultText();
                  view.setExtractText(searchResultText);
	              view.setWatingStatus();	                           
	            }

		}).start();
	}
	
	private String getSearchResultText() {
		JsonElement searchResultExtract = getSearchResultExtract();
		String searchResultText;
		if (searchResultExtract == null) {
			searchResultText = "No Results";
        } else {	            
        	searchResultText = "<h1>" + searchResultTitle + "</h1>";
        	searchResultText += searchResultExtract.getAsString().replace("\\n", "\n");
        	searchResultText = MainView.textToHtml(searchResultText, view.getTitleText());
        }
		
		return searchResultText;
	}
	
	private JsonElement getSearchResultExtract() {
		Iterator<JsonElement> resultIterator = getResultIterator();
		searchResultTitle = null;
        JsonElement searchResultExtract = null;
		if (resultIterator.hasNext()) {
            JsonObject searchResult = resultIterator.next().getAsJsonObject();
            searchResultTitle = searchResult.get("title").getAsString();
            String searchResultPageId = searchResult.get("pageid").getAsString();
            searchResultExtract = getExtractFromSearchResult(searchResultPageId);
        }
		
		return searchResultExtract;
	}
	
	private Iterator<JsonElement> getResultIterator() {
		Iterator<JsonElement> resultIterator = null;
		try {
			Response<String> callResponse = searchAPI.searchForTerm(view.getTitleText() + " articletopic:\"video-games\"").execute();
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
	
	private JsonElement getExtractFromSearchResult(String searchResultPageId) {
		JsonElement searchResultExtract = null;
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
		        searchResultExtract = page.get("extract");
			}			
		} catch (IOException e1) { 			
		  	e1.printStackTrace();
		}	
        
        return searchResultExtract;
	}
	
	@Override
	public void saveLastSearchedArticle() {		
		boolean validText = searchResultText != "" && !searchResultText.equals("No Results");
		if(validText){
			String replacedTitle = searchResultTitle.replace("'", "`");
			database.saveEntry(replacedTitle, searchResultText);
			ArrayList<String> titleList = database.getTitlesInAscendingOrder();
			view.updateLocalArray(titleList.toArray());  
		}
	}
			
}
