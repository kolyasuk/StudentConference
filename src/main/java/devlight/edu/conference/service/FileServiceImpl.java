package devlight.edu.conference.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import devlight.edu.conference.model.File;
import devlight.edu.conference.repository.FileRepository;

@Service
public class FileServiceImpl implements FileService {

	@Autowired
	FileRepository fileRepository;

	@Override
	public void addFile(File file) {
		fileRepository.save(file);
	}

	@Override
	public File getFile(int id) {
		return fileRepository.getOne(id);
	}

	@Override
	public void editFile(File file) {
		fileRepository.save(file);
	}

	@Override
	public void deleteFile(int id) {
		fileRepository.deleteById(id);
	}

}
