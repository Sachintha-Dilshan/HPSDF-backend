package lk.gov.ps.HPSDF.admin.leave.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
@Table(name = "leave_officers")
public class LeaveOfficer {

    @Id
    @Column(name = "nic_no")
    @NotBlank(message = "NIC No is required")
    private String nicNo;

    @NotBlank(message = "Role is required")
    @Column(name = "role")
    private String role;
}
