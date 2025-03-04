package lk.gov.ps.HPSDF.admin.hr.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.support.incrementer.AbstractColumnMaxValueIncrementer;

@Entity
@Table(name = "subjects")
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
public class Subject {
    @Id
    @Column(name = "subject_id")
    @NotBlank(message = "Subject No is required")
    private String subjectId;

    @Column(name = "section_id")
    @NotNull(message = "Section No is required")
    private int sectionId;

    @Column(name = "subject_name")
    @NotBlank(message = "Subject name is required")
    private String subjectName;
}
