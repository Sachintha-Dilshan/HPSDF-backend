package lk.gov.ps.HPSDF.archive.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "archive_section")
public class ArchiveSection {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "section_name")
    private String sectionName;

    @Column(name="section_color")
    private String sectionColor;

    @Column(name="section_icon")
    private String sectionIcon;


}
