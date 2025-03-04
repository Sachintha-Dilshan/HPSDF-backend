package lk.gov.ps.HPSDF.archive.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lk.gov.ps.HPSDF.admin.hr.models.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "archive_file_transaction")
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
public class ArchiveFileTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @NotNull(message = "Employee Id is required")
    private Employee employee;


    @ManyToOne
    @JoinColumn(name = "archive_file_id")
    @NotNull(message = "File Id is required")
    private ArchiveFile archiveFile;

    @Column(name = "checked_out_time_stamp")
    private Date checkedOutTimeStamp;

    @Column(name = "checked_in_time_stamp")
    private Date checkedInTimeStamp;
}
