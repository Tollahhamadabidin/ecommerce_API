package co.istad.bidin.ecommerceite.features.file;

import co.istad.bidin.ecommerceite.features.file.dto.FileUploadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/files")
public class FileController {
    private final FileUploadService fileUploadService;

//    @ResponseStatus(HttpStatus.CREATED)
//    @PostMapping
//    public FileUploadResponse upload(@RequestPart MultipartFile file) {
//        return fileUploadService.upload(file);
//    }
//
//
//    @ResponseStatus(HttpStatus.CREATED)
//    @PostMapping("/multiple")
//    public List<FileUploadResponse> uploadMultiple(@RequestPart List<MultipartFile> files) {
//        return fileUploadService.uploadMultiple(files);
//    }

//
//    @ResponseStatus(HttpStatus.CREATED)
//    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public FileUploadResponse upload(@RequestPart MultipartFile file) {
//        return fileUploadService.upload(file);
//    }



//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @DeleteMapping("/{name}")
//    public void deleteByName(@PathVariable String name){
//        fileUploadService.deleteByName(name);
//    }
}
