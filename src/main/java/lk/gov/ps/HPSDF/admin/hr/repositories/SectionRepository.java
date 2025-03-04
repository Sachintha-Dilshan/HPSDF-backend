package lk.gov.ps.HPSDF.admin.hr.repositories;

import lk.gov.ps.HPSDF.admin.hr.models.Section;
import lk.gov.ps.HPSDF.admin.leave.models.LeaveType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionRepository extends JpaRepository<Section, Integer> {
    Section findBySectionName(String sectionName);
}
