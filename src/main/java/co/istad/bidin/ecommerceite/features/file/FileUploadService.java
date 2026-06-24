package co.istad.bidin.ecommerceite.features.file;

import co.istad.bidin.ecommerceite.features.file.dto.FileUploadResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileUploadService {

    FileUploadResponse upload (MultipartFile file);

    List<FileUploadResponse> uploadMulti (MultipartFile[] files);

    void deleteByName(String name);
}
