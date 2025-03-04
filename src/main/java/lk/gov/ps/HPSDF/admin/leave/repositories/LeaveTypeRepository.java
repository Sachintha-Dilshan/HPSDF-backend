package lk.gov.ps.HPSDF.admin.leave.repositories;

import lk.gov.ps.HPSDF.admin.leave.models.LeaveType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveTypeRepository extends JpaRepository<LeaveType, Integer> {
    LeaveType findByLeaveType(String leaveType);
    List<LeaveType> findByLeaveTypeContaining(String leaveType);
}
