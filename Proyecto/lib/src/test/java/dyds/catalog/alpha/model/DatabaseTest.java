package dyds.catalog.alpha.model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class DatabaseTest {
	
	@Test
	public void testAddEntry() {
		Database database = new DatabaseImpl();
		ArrayList<String> titles;		
		titles = database.getTitlesInAscendingOrder();
		int oldSize = titles.size();		
		database.saveEntry("Game Title", "Game description");
		titles = database.getTitlesInAscendingOrder();		
		int newSize = titles.size();
		int expectedSize = oldSize + 1;
		assertEquals(expectedSize, newSize);
	}
	
	@Test
	public void testGetExtract() {
		Database database = new DatabaseImpl();
		String title = "Game Title";
		String expectedExtract = "Game description";
		database.saveEntry(title, expectedExtract);
		String testExtract = database.getExtract(title);
		assertEquals(expectedExtract, testExtract);
	}
	
	@Test
	public void failGetExtract() {
		Database database = new DatabaseImpl();
		String title = "Game that does not exist in the database";
		database.getExtract(title);
	}
	
	
}
