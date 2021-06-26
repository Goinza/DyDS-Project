package dyds.catalog.alpha.model;

import java.util.List;

public class StubSuccessListeners implements LocalModel {
	
	private SaveSuccessListener saveSuccess;
	private DeleteSuccessListener deleteSuccess;

	@Override
	public List<String> getTitles() {
		return null;
	}

	@Override
	public void saveEntry(String title, String extract) {
		saveSuccess.notifySuccess();
	}

	@Override
	public String getExtract(String title) throws InvalidTitleException {
		return null;
	}

	@Override
	public void deleteEntry(String title) throws InvalidTitleException {
		deleteSuccess.notifySuccess();
	}

	@Override
	public void addSaveSuccessListener(SaveSuccessListener listener) {
		saveSuccess = listener;
	}

	@Override
	public void addSaveFailureListener(SaveFailureListener listener) {
	}

	@Override
	public void addDeleteSuccessListener(DeleteSuccessListener listener) {
		deleteSuccess = listener;
	}

	@Override
	public void addDeleteFailureListener(DeleteFailureListener listener) {
	}

	@Override
	public void addAccessFailureListener(AccessFailureListener listener) {
	}

}
