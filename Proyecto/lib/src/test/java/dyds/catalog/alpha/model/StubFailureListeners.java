package dyds.catalog.alpha.model;

import java.util.List;

public class StubFailureListeners implements LocalModel {

	private SaveFailureListener saveFailureListener;
	private DeleteFailureListener deleteFailureListener;
	private AccessFailureListener accessFailureListener;
	
	@Override
	public List<String> getTitles() {
		accessFailureListener.notifyFailure();
		return null;
	}

	@Override
	public void saveEntry(String title, String extract) {
		saveFailureListener.notifyFailure();
	}

	@Override
	public String getExtract(String title) throws InvalidTitleException {
		accessFailureListener.notifyFailure();
		return null;
	}

	@Override
	public void deleteEntry(String title) throws InvalidTitleException {
		deleteFailureListener.notifyFailure();
	}

	@Override
	public void addSaveSuccessListener(SaveSuccessListener listener) {
	}

	@Override
	public void addSaveFailureListener(SaveFailureListener listener) {
		saveFailureListener = listener;
	}

	@Override
	public void addDeleteSuccessListener(DeleteSuccessListener listener) {
	}

	@Override
	public void addDeleteFailureListener(DeleteFailureListener listener) {
		deleteFailureListener = listener;
	}

	@Override
	public void addAccessFailureListener(AccessFailureListener listener) {
		accessFailureListener = listener;
	}

}
