package lk.gov.ps.HPSDF.archive.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Year;

@Getter
@Setter
public class ArchiveSaveFileDTO {

    private String fileNumber;
    private String fileName;
    private String archiveSectionId;
    private String archiveSubjectId;
    private Year year;
    private Long rackId;
    private int boxNumber;
    private int fileIndex;

    public ArchiveSaveFileDTO() {
    }

    @Override
    public String toString() {
        return "ArchiveSaveFileDTO{" +
                "fileNumber='" + fileNumber + '\'' +
                ", fileName='" + fileName + '\'' +
                ", archiveSectionId=" + archiveSectionId +
                ", archiveSubjectId=" + archiveSubjectId +
                ", year=" + year +
                ", rackId=" + rackId +
                ", boxNumber=" + boxNumber +
                ", fileIndex=" + fileIndex +
                '}';
    }
}
