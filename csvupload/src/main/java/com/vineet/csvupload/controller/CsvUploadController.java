package com.vineet.csvupload.controller;

import com.vineet.csvupload.model.CsvError;
import com.vineet.csvupload.service.CsvValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RestController
@RequestMapping("/api/csv")
public class CsvUploadController {

    @Autowired
    private CsvValidationService validationService;

    @PostMapping("/upload")
    public ResponseEntity<Map<String, Object>> uploadCsv(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of(
                "status", "error",
                "errors", List.of(Map.of("row", 0, "message", "File is empty"))
            ));
        }

        List<CsvError> errors = validationService.validateCsv(file);

        if (errors.isEmpty()) {
            return ResponseEntity.ok(Map.of("status", "success", "errors", Collections.emptyList()));
        } else {
            return ResponseEntity.badRequest().body(Map.of("status", "error", "errors", errors));
        }
    }
}
