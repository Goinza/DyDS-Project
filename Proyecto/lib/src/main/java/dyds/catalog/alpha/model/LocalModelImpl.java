package dyds.catalog.alpha.model;

import java.util.LinkedList;
import java.util.List;

public class LocalModelImpl implements LocalModel {
	
	private Database database;
	private List<SaveSuccessListener> saveSuccessListeners;
	private List<SaveFailureListener> saveFailureListeners;
	private List<DeleteSuccessListener> deleteSuccessListeners;
	private List<DeleteFailureListener> deleteFailureListeners;
	private List<AccessFailureListener> accessFailureListeners;
	
	public LocalModelImpl(Database database) {
		this.database = database;
		saveSuccessListeners = new LinkedList<SaveSuccessListener>();
		saveFailureListeners = new LinkedList<SaveFailureListener>();
		deleteSuccessListeners = new LinkedList<DeleteSuccessListener>();
		deleteFailureListeners = new LinkedList<DeleteFailureListener>();
		accessFailureListeners = new LinkedList<AccessFailureListener>();
	}

	@Override
	public List<String> getTitles() {
		List<String> titles = null;
		try {
			titles = database.getTitles();			
		}
		catch (DatabaseException e) {
			notifyAccessFailure();
		}
		return titles;
	}

	@Override
	public void saveEntry(String title, String extract) {
		try {
			database.saveEntry(title, extract);
			notifySaveSuccess();
		}
		catch (DatabaseException e) {
			notifySaveFailure();
		}
	}

	@Override
	public String getExtract(String title) throws InvalidTitleException {
		String extract = null;
		try {
			extract = database.getExtract(title);
		}
		catch (DatabaseException e) {
			notifyAccessFailure();
		}		
		return extract; 
	}

	@Override
	public void deleteEntry(String title) throws InvalidTitleException {
		try {
			database.deleteEntry(title);
			notifyDeleteSuccess();
		}
		catch (DatabaseException e) {
			notifyDeleteFailure();
		}

	}

	@Override
	public void addSaveSuccessListener(SaveSuccessListener listener) {
		saveSuccessListeners.add(listener);
	}

	@Override
	public void addSaveFailureListener(SaveFailureListener listener) {
		saveFailureListeners.add(listener);
	}

	@Override
	public void addDeleteSuccessListener(DeleteSuccessListener listener) {
		deleteSuccessListeners.add(listener);
	}

	@Override
	public void addDeleteFailureListener(DeleteFailureListener listener) {
		deleteFailureListeners.add(listener);
	}
	
	@Override
	public void addAccessFailureListener(AccessFailureListener listener) {
		accessFailureListeners.add(listener);
	}
	
	private void notifySaveSuccess() {
		for (SaveSuccessListener listener : saveSuccessListeners) {
    		listener.notifySuccess();
    	}
	}
	
	private void notifySaveFailure() {
		for (SaveFailureListener listener : saveFailureListeners) {
    		listener.notifyFailure();
    	}
	}
	
	private void notifyDeleteSuccess() {
		for (DeleteSuccessListener listener : deleteSuccessListeners) {
    		listener.notifySuccess();
    	}
	}
	
	private void notifyDeleteFailure() {
		for (DeleteFailureListener listener : deleteFailureListeners) {
    		listener.notifyFailure();
    	}
	}
	
	private void notifyAccessFailure() {
		for (AccessFailureListener listener : accessFailureListeners) {
    		listener.notifyFailure();
    	}
	}

}
