package lk.gov.ps.HPSDF.archive.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lk.gov.ps.HPSDF.admin.hr.models.Employee;
import lombok.*;
import org.aspectj.bridge.IMessage;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "archive_file")
public class ArchiveFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "file_number")
    private String fileNumber;

    @Column(name = "file_name")
    @NotBlank(message = "File name is required")
    private String fileName;

    @ManyToOne
    @JoinColumn(name = "section_id")
    @NotNull(message = "Section is required")
    private ArchiveSection archiveSection;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    @NotNull(message = "Subject is required")
    private ArchiveSubject archiveSubject;

    @Column(name = "year")
    @NotBlank(message = "Year is required")
    private String year;

    @ManyToOne
    @JoinColumn(name = "rack_id")
    @NotNull(message = "Rack number is required")
    private ArchiveRack rack;

    @Column(name = "box_number")
    @NotNull(message = "Box number is required")
    private int boxNumber;

    @NotNull(message = "File index is required")
    @Column(name ="file_index")
    private int fileIndex;

    @Column(name="is_checked_out")
    private boolean isCheckedOut;
}
