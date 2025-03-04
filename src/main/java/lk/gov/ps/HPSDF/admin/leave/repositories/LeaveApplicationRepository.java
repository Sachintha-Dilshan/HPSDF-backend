package lk.gov.ps.HPSDF.admin.leave.repositories;

import lk.gov.ps.HPSDF.admin.leave.models.LeaveApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication, Integer> {
    @Query(
            value = "select t.leave_type, l.leave_period, l.leave_commencing_date, l.date_of_resuming_duties, e.name_with_initials , l.reason from employees e, leave_application l, leave_types t where e.nic_no = l.employee_nic_no and l.leave_type = t.id and l.application_id = ?1 ",
            nativeQuery = true
    )
    Object[] getLeaveChit(int applicationId);

    @Query(
            value = "select  l.application_id, l.leave_commencing_date, l.date_of_resuming_duties, l.leave_period, t.leave_type,l.reason from leave_application l, leave_types t where l.leave_type = t.id and l.status = 1 and l.employee_nic_no = ?1 ",
            nativeQuery = true
    )
    List<Object[]> getApprovedLeaveApplications(String nicNo);

    @Query(
            value = "select  l.application_id, e.name_with_initials, l.leave_commencing_date, l.date_of_resuming_duties, l.leave_period, t.leave_type,l.reason from employees e, leave_application l, leave_types t where e.nic_no = l.employee_nic_no and l.leave_type = t.id and l.status = 1 and curdate() >= l.leave_commencing_date and curdate() <= l.date_of_resuming_duties order by l.application_id",
            nativeQuery = true
    )
    List<Object[]> getOnLeaveTodayApplications();

    @Query(
            value = "select  count(l.application_id) as count from employees e, leave_application l, leave_types t where e.nic_no = l.employee_nic_no and l.leave_type = t.id and l.status = 1 and curdate() >= l.leave_commencing_date and curdate() <= l.date_of_resuming_duties order by l.application_id desc",
            nativeQuery = true
    )
    int getOnLeaveTodayApplicationsCount();

    @Query(
            value = "select e.nic_no, a.application_id, e.name_with_initials, t.leave_type, l.acting_officer_approval_status, l.supervisor_approval_status, l.hod_approval_status  from employees e, leave_application a,leave_tracker l, leave_types t  where e.nic_no = a.employee_nic_no and a.application_id = l.leave_application_id and a.leave_type=t.id and (a.acting_officer_nic_no = ?1 or a.supervisor_nic_no = ?1 or a.hod_nic_no = ?1) order by a.application_id desc",
            nativeQuery = true
    )
    List<Object[]> getLeaveRequests(String nicNo);

    @Query(
            value = "SELECT COUNT(l.application_id) AS count FROM employees e,      leave_application l,      leave_types t,      leave_tracker lt WHERE e.nic_no = l.employee_nic_no   AND l.leave_type = t.id   AND l.application_id = lt.leave_application_id   AND ((l.acting_officer_nic_no = ?1 AND lt.acting_officer_approval_status = 0)     OR (l.supervisor_nic_no = ?1 AND lt.supervisor_approval_status = 0)     OR (l.hod_nic_no = ?1 AND lt.hod_approval_status = 0));",
            nativeQuery = true
    )
    int getLeaveRequestsCount(String nicNo);
}
