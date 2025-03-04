package lk.gov.ps.HPSDF.admin.leave.repositories;

import lk.gov.ps.HPSDF.admin.leave.models.LeaveOfficer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveOfficersRepository extends JpaRepository<LeaveOfficer, String> {
    @Query(
            value = "select e.nic_no, e.name_with_initials, d.designation_name, e.leave_id from employees e, designations d, leave_officers l where e.designation = d.designation_id and e.nic_no = l.nic_no and l.role = ?1 ",
            nativeQuery = true
    )
    List<Object[]> getOfficers(String role);

    @Query(
            value = "select e.name_with_initials, d.designation_name from employees e, designations d where e.designation = d.designation_id and e.nic_no = ?1",
            nativeQuery = true
    )
    Object[] getEmployeeByNic(String nic_no);

    @Query(
            value = "select e.nic_no, e.name_with_initials, d.designation_name from employees e, designations d where e.designation = d.designation_id and e.leave_id = ?1",
            nativeQuery = true
    )
    Object[] getEmployeeByLeaveId(String leave_id);


}
