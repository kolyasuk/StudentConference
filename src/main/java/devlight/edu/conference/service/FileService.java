package devlight.edu.conference.service;

import devlight.edu.conference.model.File;

public interface FileService {

	void addFile(File file);

	File getFile(int id);

	void editFile(File file);

	void deleteFile(int id);

}
