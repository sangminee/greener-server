package com.example.SwDeveloperServer.domain.ex.s3Upload;

import com.example.SwDeveloperServer.utils.response.ResponseService;
import com.example.SwDeveloperServer.utils.response.SuccessStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class UploadController {
    private final S3UploadService s3UploadService;
    private final ResponseService responseService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("images") MultipartFile multipartFile) throws IOException {
        String file = s3UploadService.upload(multipartFile);
        return responseService.successResult(file, SuccessStatus.SUCCESS);
    }

}
