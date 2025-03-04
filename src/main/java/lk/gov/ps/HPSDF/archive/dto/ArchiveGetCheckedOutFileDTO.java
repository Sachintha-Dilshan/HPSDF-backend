package lk.gov.ps.HPSDF.archive.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ArchiveGetCheckedOutFileDTO {
    private Long id;
    private String fileNumber;
    private String fileName;
    private String sectionName;
    private String subjectName;
    private String employeeFullName;
    private String employeeNicNo;
    private LocalDateTime dateTime;

    public ArchiveGetCheckedOutFileDTO() {
    }

    @Override
    public String toString() {
        return "ArchiveGetCheckedOutFileDTO{" +
                "id=" + id +
                ", fileNumber='" + fileNumber + '\'' +
                ", fullName='" + employeeFullName + '\'' +
                ", employeeId='" + employeeNicNo + '\'' +
                '}';
    }
}
