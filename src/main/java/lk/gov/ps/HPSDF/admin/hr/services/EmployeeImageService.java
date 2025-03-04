package lk.gov.ps.HPSDF.admin.hr.services;

import lk.gov.ps.HPSDF.admin.hr.models.EmployeeImage;
import lk.gov.ps.HPSDF.admin.hr.repositories.EmployeeImageRepository;
import lk.gov.ps.HPSDF.admin.hr.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Service
public class EmployeeImageService {
    @Autowired
    private EmployeeImageRepository employeeImageRepository;

    public EmployeeImage uploadImage(String nicNo, MultipartFile file) throws IOException {
        if (file == null) {
            throw new IllegalArgumentException("File cannot be null");
        }
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        EmployeeImage image  = EmployeeImage.build(nicNo, fileName, file.getBytes());
        return employeeImageRepository.save(image);
    }

    public EmployeeImage getImage(String nicNo) {
        return employeeImageRepository.findById(nicNo).orElse(null);
    }

    public EmployeeImage updateImage(String nicNo, MultipartFile file) throws IOException {
        if (file == null) {
            throw new IllegalArgumentException("File cannot be null");
        }
        EmployeeImage existingImage = employeeImageRepository.findById(nicNo).orElse(null);
        if (existingImage == null) {
            throw new IllegalArgumentException("No image found for nicNo: " + nicNo);
        }
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        existingImage.setImageName(fileName);
        existingImage.setImage(file.getBytes());
        return employeeImageRepository.save(existingImage);
    }

    public boolean deleteImage(String nicNo) {
        if (employeeImageRepository.existsById(nicNo)) {
            employeeImageRepository.deleteById(nicNo);
            return true;
        } else {
            return false;
        }
    }
}
