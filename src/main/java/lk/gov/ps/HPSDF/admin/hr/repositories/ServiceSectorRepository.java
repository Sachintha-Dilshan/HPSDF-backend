package lk.gov.ps.HPSDF.admin.hr.repositories;

import lk.gov.ps.HPSDF.admin.hr.models.ServiceSector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceSectorRepository extends JpaRepository<ServiceSector, Integer> {
    ServiceSector findByServiceSectorName(String serviceSectorName);
}
