package lk.gov.ps.HPSDF.admin.leave.repositories;

import lk.gov.ps.HPSDF.admin.leave.models.AttendanceSheet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceSheetRepository extends JpaRepository<AttendanceSheet, Integer> {
}
