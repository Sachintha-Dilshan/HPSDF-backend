package lk.gov.ps.HPSDF.admin.hr.controllers;

import lk.gov.ps.HPSDF.admin.hr.models.EmployeeImage;
import lk.gov.ps.HPSDF.admin.hr.services.EmployeeImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth/hr")
public class EmployeeImageController {
    @Autowired
    private EmployeeImageService employeeImageService;

    @PostMapping("/uploadImage/{nicNo}")
    public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile file, @PathVariable String nicNo) {
        String message = "";
        try {
            if (file == null || file.isEmpty()) {
                message = "Please select a file to upload.";
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
            }

            EmployeeImage image = employeeImageService.uploadImage(nicNo, file);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.ok().body(message);
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }


    @GetMapping("/getImage/{nicNo}")
    public ResponseEntity<?> getImage(@PathVariable String nicNo) {
        EmployeeImage image = employeeImageService.getImage(nicNo);
        if (image == null) {
            String message = "Image not found for NIC No: " + nicNo;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getImageName() + "\"")
                .body(image.getImage());
    }

    @PutMapping("/updateImage/{nicNo}")
    public ResponseEntity<String> updateImage(@RequestParam("image") MultipartFile file, @PathVariable String nicNo) {
        String message = "";
        try {
            if (file == null || file.isEmpty()) {
                message = "Please select a file to update.";
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
            }

            EmployeeImage updatedImage = employeeImageService.updateImage(nicNo, file);

            message = "Updated the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.ok().body(message);
        } catch (Exception e) {
            message = "Could not update the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }

    @DeleteMapping("/deleteImage/{nicNo}")
    public ResponseEntity<String> deleteImage(@PathVariable String nicNo) {
        String message = "";
        try {
            boolean isDeleted = employeeImageService.deleteImage(nicNo);
            if (isDeleted) {
                message = "Deleted image for NIC No: " + nicNo;
                return ResponseEntity.ok().body(message);
            } else {
                message = "Image not found for NIC No: " + nicNo;
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
            }
        } catch (Exception e) {
            message = "Could not delete image for NIC No: " + nicNo;
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }
}
