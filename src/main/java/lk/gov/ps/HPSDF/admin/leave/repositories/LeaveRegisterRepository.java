package lk.gov.ps.HPSDF.admin.leave.repositories;

import lk.gov.ps.HPSDF.admin.leave.models.LeaveRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRegisterRepository extends JpaRepository<LeaveRegister, Integer> {
    LeaveRegister findByNicNo(String nicNo);

    @Query(
            value = "select e.leave_id, e.name_with_initials, r.casual_leave, r.vacation_leave, r.expired_vacation_leave, r.commuted_half_pay_leave, r.half_pay_leave, no_pay_leave, duty_leave from employees e, leave_register r where e.nic_no = r.nic_no",
            nativeQuery = true
    )
    List<Object[]> getAllLeaveRequests();
}
