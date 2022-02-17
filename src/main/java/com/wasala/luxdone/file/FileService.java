package com.wasala.luxdone.file;

import com.wasala.luxdone.file.exceptions.FileNotFoundException;
import com.wasala.luxdone.file.exceptions.FileTypeNotSupportedException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileService {

    private final FileRepository repository;

    public FileService(FileRepository repository) {
        this.repository = repository;
    }

    public List<File> findAllFiles() {
        return repository.findAll();
    }

    public File findFileById(String id) throws FileNotFoundException {
        return repository.findById(id).orElseThrow(FileNotFoundException::new);
    }

    public File createFile(MultipartFile multipartFile) throws IOException, FileTypeNotSupportedException {
        File file = new File(FileType.fromString(multipartFile.getContentType()),
                multipartFile.getOriginalFilename(),
                multipartFile.getSize(),
                multipartFile.getBytes());

        return repository.save(file);
    }

    public File createFile(File file) {
        return repository.save(file);
    }

    public File updateFile(String id, MultipartFile multipartFile) throws FileTypeNotSupportedException, IOException, FileNotFoundException {
        File file = repository.findById(id).orElseThrow(FileNotFoundException::new);

        if (file.getType() != FileType.fromString(multipartFile.getContentType())) {
            throw new FileTypeNotSupportedException("Attempted to update file with different content-type than it was uploaded");
        }

        file.setOriginalName(multipartFile.getOriginalFilename());
        file.setSize(multipartFile.getSize());
        file.setData(multipartFile.getBytes());

        return repository.save(file);
    }

    public File updateFile(File file) {
        return repository.save(file);
    }

    public void deleteFileById(String id) {
        repository.deleteById(id);
    }
}
