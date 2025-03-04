package lk.gov.ps.HPSDF.admin.hr.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "service_sectors")
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
public class ServiceSector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_sector_id")
    private int serviceSectorId;

    @Column(name = "service_sector_name")
    @NotBlank(message = "Service sector name is required")
    private String serviceSectorName;
}
