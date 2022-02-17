package com.wasala.luxdone.file;

import com.wasala.luxdone.file.exceptions.FileNotFoundException;
import com.wasala.luxdone.file.exceptions.FileTypeNotSupportedException;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/files")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping
    public ResponseEntity<List<FileInfo>> getAllFilesInfo() {
        List<FileInfo> result = fileService.findAllFiles().stream()
                .map(FileInfo::mapFileToFileInfo)
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FileInfo> getFileInfo(@PathVariable String id) throws FileNotFoundException {
        return ResponseEntity.ok(FileInfo.mapFileToFileInfo(fileService.findFileById(id)));
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String id) throws FileNotFoundException {
        File file = fileService.findFileById(id);
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.set(HttpHeaders.CONTENT_TYPE, getContentTypeHeader(file));
        httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.attachment().filename(file.getOriginalName()).build().toString());

        return ResponseEntity.ok().headers(httpHeaders).body(file.getData());
    }

    @PostMapping
    public ResponseEntity<FileInfo> uploadFile(@RequestParam("file") MultipartFile multipartFile) throws IOException, FileTypeNotSupportedException {
        File savedFile = fileService.createFile(multipartFile);
        FileInfo fileInfo = FileInfo.mapFileToFileInfo(savedFile);
        return ResponseEntity.ok(fileInfo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FileInfo> updateFile(@PathVariable String id, @RequestParam("file") MultipartFile multipartFile) throws FileTypeNotSupportedException, IOException, FileNotFoundException {
        File updatedFile = fileService.updateFile(id, multipartFile);
        FileInfo fileInfo = FileInfo.mapFileToFileInfo(updatedFile);
        return ResponseEntity.ok(fileInfo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFileById(@PathVariable String id) {
        fileService.deleteFileById(id);
        return ResponseEntity.ok("File deleted successfully");
    }

    private String getContentTypeHeader(File file) {
        switch (file.getType().toString()) {
            case "application/pdf":
                return MediaType.APPLICATION_PDF_VALUE;
            case "image/jpeg":
                return MediaType.IMAGE_JPEG_VALUE;
            case "text/plain":
                return MediaType.TEXT_PLAIN_VALUE;
        }
        return MediaType.APPLICATION_OCTET_STREAM_VALUE;
    }
}
