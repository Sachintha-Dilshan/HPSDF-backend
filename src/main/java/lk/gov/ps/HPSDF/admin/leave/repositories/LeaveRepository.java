package lk.gov.ps.HPSDF.admin.leave.repositories;

import lk.gov.ps.HPSDF.admin.leave.models.Leave;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRepository extends JpaRepository<Leave, Long> {
    List<Leave> findByLeaveNameContaining(String leaveName);
}
