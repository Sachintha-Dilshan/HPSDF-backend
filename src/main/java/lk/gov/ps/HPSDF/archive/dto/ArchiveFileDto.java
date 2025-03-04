package lk.gov.ps.HPSDF.archive.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Year;

@Data
public class ArchiveFileDto {
    private String fileNumber;
    private String fileName;
    private String archiveSectionId;
    private String archiveSubjectId;
    private String year;
    private long rackId;
    private int boxNumber;
    private int fileIndex;
}
