package lk.gov.ps.HPSDF.admin.hr.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "designations")
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
public class Designation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "designation_id")
    private int designationId;

    @Column(name = "designation_name")
    @NotBlank(message = "Designation name is required")
    private String designationName;
}
