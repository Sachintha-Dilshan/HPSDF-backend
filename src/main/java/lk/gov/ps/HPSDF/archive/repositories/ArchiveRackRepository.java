package lk.gov.ps.HPSDF.archive.repositories;

import lk.gov.ps.HPSDF.archive.models.ArchiveRack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchiveRackRepository extends JpaRepository<ArchiveRack,Long> {
}
