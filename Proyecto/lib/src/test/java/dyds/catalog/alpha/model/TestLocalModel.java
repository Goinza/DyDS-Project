package dyds.catalog.alpha.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

public class TestLocalModel {
	
	boolean triggeredListener;

	@Test
	public void testGetTitles() {
		Database database = new StubReadOnlyDatabase();
		LocalModel localModel = new LocalModelImpl(database);
		List<String> list = localModel.getTitles();
		String expectedTitle = "Example Title";
		assertEquals(expectedTitle, list.get(0));
	}
	
	@Test
	public void testGetExtract() {
		try {
			Database database = new StubReadOnlyDatabase();
			LocalModel localModel = new LocalModelImpl(database);
			String expectedExtract = "Article Description";
			String actualExtract = localModel.getExtract("Article Title");
			assertEquals(expectedExtract, actualExtract);
		}
		catch (InvalidTitleException e) {
			fail();
		}
	}
	
	@Test
	public void testSaveEntry() {
		try {
			Database database = new StubOneEntryDatabase();
			LocalModel localModel = new LocalModelImpl(database);
			String expectedTitle = "New title";
			String expectedExtract = "New description";
			localModel.saveEntry(expectedTitle, expectedExtract);
			List<String> list = localModel.getTitles();
			String actualTitle = list.get(0);
			String actualExtract = localModel.getExtract(actualTitle);
			assertEquals(expectedTitle, actualTitle);
			assertEquals(expectedExtract, actualExtract);
		}
		catch (InvalidTitleException e) {
			fail();
		}
	}
	
	@Test
	public void testDeleteEntry() {
		try {
			Database database = new StubOneEntryDatabase();
			LocalModel localModel = new LocalModelImpl(database);
			String expectedTitle = "Deleted title";
			String expectedExtract = "Deleted description";
			localModel.saveEntry(expectedTitle, expectedExtract);
			localModel.deleteEntry(expectedTitle);
			localModel.getExtract(expectedTitle);
			fail();
		}
		catch (InvalidTitleException e) {			
		}
	}
	
	@Test
	public void testInvalidExceptionGetExtract() {
		try {
			Database database = new StubInvalidTitleExceptionDatabase();
			LocalModel localModel = new LocalModelImpl(database);
			localModel.getExtract(null);
			fail();
		}
		catch (InvalidTitleException e) {
		}
	}
	
	@Test
	public void testInvalidExceptionDeleteEntry() {
		try {
			Database database = new StubInvalidTitleExceptionDatabase();
			LocalModel localModel = new LocalModelImpl(database);
			localModel.deleteEntry(null);
			fail();
		}
		catch (InvalidTitleException e) {
		}
	}
	
	@Test
	public void testSaveSuccessListener() {
		triggeredListener = false;
		LocalModel localModel = new StubSuccessListeners();
		localModel.addSaveSuccessListener(new SaveSuccessListener() {
			@Override
			public void notifySuccess() {
				triggeredListener = true;				
			}			
		});
		localModel.saveEntry("Title", "Description");
		assertTrue(triggeredListener);
	}
	
	@Test 
	public void testSaveFailureListener() {
		triggeredListener = false;
		LocalModel localModel = new StubFailureListeners();
		localModel.addSaveFailureListener(new SaveFailureListener() {
			@Override
			public void notifyFailure() {
				triggeredListener = true;				
			}			
		});
		localModel.saveEntry("Title", "Description");
		assertTrue(triggeredListener);
	}
	
	@Test
	public void testDeleteSuccessListener() {
		try {
			triggeredListener = false;
			LocalModel localModel = new StubSuccessListeners();
			localModel.addDeleteSuccessListener(new DeleteSuccessListener() {
				@Override
				public void notifySuccess() {
					triggeredListener = true;				
				}			
			});
			localModel.deleteEntry("Title");
			assertTrue(triggeredListener);
		}
		catch (InvalidTitleException e) {
			fail();
		}
	}
	
	@Test
	public void testDeleteFailureListener() {
		try {
			triggeredListener = false;
			LocalModel localModel = new StubFailureListeners();
			localModel.addDeleteFailureListener(new DeleteFailureListener() {
				@Override
				public void notifyFailure() {
					triggeredListener = true;				
				}			
			});
			localModel.deleteEntry("Title");
			assertTrue(triggeredListener);
		}
		catch (InvalidTitleException e) {
			fail();
		}
	}
	
	@Test
	public void testAccessFailureListener() {
		triggeredListener = false;
		LocalModel localModel = new StubFailureListeners();
		localModel.addAccessFailureListener(new AccessFailureListener() {
			@Override
			public void notifyFailure() {
				triggeredListener = true;				
			}			
		});
		localModel.getTitles();
		assertTrue(triggeredListener);
	}
	
}
