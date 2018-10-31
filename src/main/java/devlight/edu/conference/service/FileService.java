package devlight.edu.conference.service;

import devlight.edu.conference.model.File;
import javassist.NotFoundException;

public interface FileService {

	File addFile(byte[] data);

	File getFileById(int id) throws NotFoundException;

	File editFile(File file);

	void deleteFile(int id) throws NotFoundException;

}
