package lk.gov.ps.HPSDF.archive.repositories;

import lk.gov.ps.HPSDF.archive.models.ArchiveSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchiveSectionRepository extends JpaRepository<ArchiveSection,String> {
}
