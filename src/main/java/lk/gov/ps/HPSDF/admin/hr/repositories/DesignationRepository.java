package lk.gov.ps.HPSDF.admin.hr.repositories;

import lk.gov.ps.HPSDF.admin.hr.models.Designation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignationRepository extends JpaRepository<Designation, Integer> {
    Designation findByDesignationName(String designationName);
}
