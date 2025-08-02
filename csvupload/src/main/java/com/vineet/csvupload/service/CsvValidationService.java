package com.vineet.csvupload.service;

import com.vineet.csvupload.model.CsvError;
import org.apache.commons.csv.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

@Service
public class CsvValidationService {

    public List<CsvError> validateCsv(MultipartFile file) {
        List<CsvError> errors = new ArrayList<>();

        try (
            Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            CSVParser parser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader)
        ) {
            int rowNum = 1;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            for (CSVRecord record : parser) {
                rowNum++;
                String name = record.get("Name").trim();
                String dob = record.get("DateOfBirth").trim();

                if (name.isEmpty()) {
                    errors.add(new CsvError(rowNum, "Name is missing"));
                }

                if (dob.isEmpty()) {
                    errors.add(new CsvError(rowNum, "DateOfBirth is missing"));
                } else {
                    try {
                        LocalDate.parse(dob, formatter);
                    } catch (DateTimeParseException e) {
                        errors.add(new CsvError(rowNum, "DateOfBirth format should be yyyy-MM-dd"));
                    }
                }
            }
        } catch (IOException e) {
            errors.add(new CsvError(0, "Failed to read file: " + e.getMessage()));
        }

        return errors;
    }
}

