package lk.gov.ps.HPSDF.archive.repositories;

import jakarta.annotation.Nullable;
import lk.gov.ps.HPSDF.archive.models.ArchiveFile;
import lk.gov.ps.HPSDF.admin.hr.models.Employee;
import lk.gov.ps.HPSDF.archive.models.ArchiveSection;
import lk.gov.ps.HPSDF.archive.models.ArchiveSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArchiveFileRepository extends JpaRepository<ArchiveFile,Long> {
    int countByArchiveSection(ArchiveSection section);
//    Optional<ArchiveFile> findByFileNumber(String FileNumber);

//    Optional<ArchiveFile> findByFileName(String fileName);
//
//    ArchiveFile findById(long id);

//    @Query("SELECT af FROM ArchiveFile af WHERE af.archiveSection.id = :sectionId ORDER BY af.id DESC")
//    List<ArchiveFile> findTop5ByOrderByIdDesc(String sectionId,org.springframework.data.domain.Pageable pageable);
//
//    @Query("SELECT f FROM ArchiveFile f WHERE f.id = :fileId AND f.archiveSection.id = :sectionId")
//    Optional<ArchiveFile> findByIdAndArchiveSection_Id(Long fileId, String sectionId);
//
    @Query("SELECT COUNT(ag) FROM ArchiveFile ag WHERE ag.archiveSection = ?1")
    int getFileCountsBySection(String sectionId);
//
//    @Query("SELECT COUNT(ag) FROM ArchiveFile ag WHERE ag.isCheckedOut =true ")
//    int getFileCountsCheckedOut();
//
@Query("SELECT af FROM ArchiveFile af WHERE " +
        "(:fileNumber IS NULL OR af.fileNumber = :fileNumber) " +
        "AND (:fileName IS NULL OR af.fileName = :fileName) " +
        "AND (:year IS NULL OR af.year = :year) " +
        "AND (:archiveSection IS NULL OR af.archiveSection = :archiveSection) " +
        "AND (:archiveSubject IS NULL OR af.archiveSubject = :archiveSubject)")
List<ArchiveFile> findByAttributes(
        @Param("fileNumber") String fileNumber,
        @Param("fileName") String fileName,
        @Param("year") String year,
        @Param("archiveSection") ArchiveSection archiveSection,
        @Param("archiveSubject") ArchiveSubject archiveSubject,
        Pageable pageable
);




}
