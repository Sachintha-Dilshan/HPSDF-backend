package lk.gov.ps.HPSDF.admin.leave.repositories;

import lk.gov.ps.HPSDF.admin.leave.models.LeaveTracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LeaveTrackerRepository extends JpaRepository<LeaveTracker, Integer> {
    @Query(
            value = "select e.nic_no, a.application_id, e.name_with_initials, t.leave_type, l.acting_officer_approval_status, l.supervisor_approval_status, l.hod_approval_status  from employees e, leave_application a,leave_tracker l, leave_types t  where e.nic_no = a.employee_nic_no and a.application_id = l.leave_application_id and a.leave_type=t.id order by a.application_id desc",
            nativeQuery = true
    )
    List<Object[]> getLeaveRequests();

    @Query(
            value = "select emp.name_with_initials, lt.application_submitted_time_stamp, act.name_with_initials, lt.acting_officer_approval_status, lt.acting_officer_approval_time_stamp, sup.name_with_initials , lt.supervisor_approval_status, lt.supervisor_approval_time_stamp, hod.name_with_initials, lt.hod_approval_status, lt.hod_approval_time_stamp from leave_application la, leave_tracker lt, employees emp, employees act, employees sup, employees hod where la.application_id = lt.leave_application_id and emp.nic_no = la.employee_nic_no and act.nic_no = la.acting_officer_nic_no and sup.nic_no = la.supervisor_nic_no and hod.nic_no = la.hod_nic_no and la.application_id = ?1",
            nativeQuery = true
    )
    Object[] getLeaveTrackingData(int applicationId);
}
