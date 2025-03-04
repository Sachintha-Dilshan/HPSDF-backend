package lk.gov.ps.HPSDF.admin.hr.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employee_images")
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
public class EmployeeImage {
    @Id
    @Column(name = "nic_no")
    private String nicNo;

    @Column(name = "image_name")
    private String imageName;

    @Lob
    @Column(name = "image", columnDefinition = "MEDIUMBLOB")
    private byte[] image;
}
