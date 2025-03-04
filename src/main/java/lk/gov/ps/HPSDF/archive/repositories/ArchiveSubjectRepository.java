package lk.gov.ps.HPSDF.archive.repositories;

import lk.gov.ps.HPSDF.archive.models.ArchiveSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArchiveSubjectRepository extends JpaRepository<ArchiveSubject,Integer> {

    List<ArchiveSubject> findByArchiveSectionId(String id);
}
