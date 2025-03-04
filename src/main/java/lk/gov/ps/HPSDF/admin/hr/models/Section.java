package lk.gov.ps.HPSDF.admin.hr.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sections")
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "section_id")
    private int sectionId;

    @Column(name = "section_name")
    @NotBlank(message = "Section name is required")
    private String sectionName;
}
