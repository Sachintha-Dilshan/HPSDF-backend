package lk.gov.ps.HPSDF.archive.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArchiveSectionDTO {
    private String id;
    private String sectionName;
    private String sectionColor;
    private String sectionIcon;
    private int count;
    public ArchiveSectionDTO() {
    }
}
