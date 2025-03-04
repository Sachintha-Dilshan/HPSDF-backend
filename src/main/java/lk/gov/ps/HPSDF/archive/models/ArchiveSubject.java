package lk.gov.ps.HPSDF.archive.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "archive_subject")
public class ArchiveSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "subject_name")
    @NotBlank(message = "Subject name is required")
    private String subjectName;

    @ManyToOne
    @JoinColumn(name = "archive_section_id")
    @NotNull(message = "Archive section is required")
    private ArchiveSection archiveSection;

}
