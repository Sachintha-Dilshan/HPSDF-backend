package lk.gov.ps.HPSDF.archive.dto;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class ArchiveGetSearchFileDTO {
    private Long id;
    private String fileNumber;
    private String fileName;
    private String year;
    private String sectionName;
    private String subjectName;
    private String rackNumber;
    private int boxNumber;
    private int fileIndex;
    private boolean isCheckedOut;


    public ArchiveGetSearchFileDTO() {
    }
}
