package devlight.edu.conference.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import devlight.edu.conference.model.File;
import devlight.edu.conference.repository.FileRepository;
import javassist.NotFoundException;

@Service
public class FileServiceImpl implements FileService {

	@Autowired
	FileRepository fileRepository;

	@Override
	public File addFile(File file) {
		return fileRepository.save(file);
	}

	@Override
	public File editFile(File file) {
		return fileRepository.save(file);
	}

	@Override
	public File getFileById(int id) throws NotFoundException {
		Optional<File> file = fileRepository.findById(id);
		if (file.isPresent())
			return file.get();
		else
			throw new NotFoundException("File is not found");
	}

	@Override
	public void deleteFile(int id) throws NotFoundException {
		if (getFileById(id) != null)
			fileRepository.deleteById(id);
	}

}
