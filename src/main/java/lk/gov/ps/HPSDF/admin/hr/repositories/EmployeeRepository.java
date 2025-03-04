package lk.gov.ps.HPSDF.admin.hr.repositories;

import lk.gov.ps.HPSDF.admin.hr.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    long count();
    @Query(
            value = "SELECT e.nic_no, e.name_with_initials, e.full_name, e.address, e.gender, e.marital_status, e.dob, e.mobile_no, e.email, e.office_official_appointment_date, d.designation_name, ss.service_sector_name, e.designation_class, e.designation_grade, e.first_appointment_date, e.duty_assigned_date, e.permanent, e.duty_permanent_date, e.salary_increment_date, e.salary_code_prefix, e.salary_code, e.wop_no, s.section_name, sb.subject_name, e.section_assigned_date, e.nature_of_appointment, e.leave_id FROM employees e LEFT JOIN sections s ON e.section = s.section_id LEFT JOIN designations d ON e.designation = d.designation_id LEFT JOIN service_sectors ss ON e.service_sector = ss.service_sector_id LEFT JOIN subjects sb ON e.subject_no = sb.subject_id WHERE e.nic_no = ?1",
            nativeQuery = true
    )

    Object[] findEmployeesByNic(String nic);

    @Query(
            value = "select e.nic_no, e.name_with_initials, e.full_name, e.address, e.gender, e.marital_status, e.dob,mobile_no, e.email, e.office_official_appointment_date, d.designation_name, ss.service_sector_name, e.designation_class, e.designation_grade, e.first_appointment_date, e.duty_assigned_date, e.permanent, e.duty_permanent_date, e.salary_increment_date, e.salary_code, e.wop_no, s.section_name, sb.subject_name, e.section_assigned_date, e.nature_of_appointment, e.leave_id  from employees e,sections s,designations d, service_sectors ss, subjects sb where e.section = s.section_id and e.designation = d.designation_id and e.service_sector = ss.service_sector_id and e.subject_no = sb.subject_id",
            nativeQuery = true
    )
    List<Object[]> findAllEmployeesData();


    @Query(
            value = "select e.nic_no,e.name_with_initials,e.mobile_no,d.designation_name from employees e, designations d  where e.designation = d.designation_id  order by e.designation",
            nativeQuery = true
    )
    List<Object[]> findAllEmployees();
    @Query(
            value = "select e.nic_no,e.name_with_initials,e.mobile_no,d.designation_name from employees e, designations d  where e.designation = d.designation_id and e.section = ?1",
            nativeQuery = true
    )
    List<Object[]> sortEmployeesBySection(int sectionId);

    @Query(
            value = "select e.nic_no,e.name_with_initials,e.mobile_no,d.designation_name from employees e, designations d  where e.designation = d.designation_id and e.nic_no = ?1 ",
            nativeQuery = true
    )
    Object[] sortEmployeesByNicNo(String nic_no);

    @Query(
            value = "select e.nic_no,e.name_with_initials,e.mobile_no,d.designation_name from employees e, designations d  where e.designation = d.designation_id and e.leave_id = ?1 ",
            nativeQuery = true
    )
    Object[] sortEmployeesByLeaveId(int leave_id);

    @Query(
            value = "select e.name_with_initials from employees e where leave_id = ?1",
            nativeQuery = true
    )
    String findEmployeeName(int leave_id);



    @Query(
            value = "select e.nic_no from employees e where leave_id = ?1",
            nativeQuery = true
    )
    String findEmployeeNicNo(int leave_id);

    @Query(
            value = "select e.name_with_initials, d.designation_name ,e.first_appointment_date  from employees e, designations d where e.designation = d.designation_id and leave_id = ?1",
            nativeQuery = true
    )
    Object[] findEmployeeLeavePersonalData(int leave_id);

    @Query(
            value = "select e.nic_no,e.name_with_initials,e.mobile_no,d.designation_name from employees e, designations d  where e.designation = d.designation_id and e.dob = curdate()",
            nativeQuery = true
    )
    List<Object[]> findEmployeeBirthdayToday();

    @Query(
            value = "select count(e.nic_no) from employees e where e.dob = curdate()",
            nativeQuery = true
    )
    int findEmployeeBirthdayTodayCount();

    @Query(
            value = "select e.nic_no,e.name_with_initials,e.mobile_no,d.designation_name from employees e, designations d  where e.designation = d.designation_id and email = ?1",
            nativeQuery = true
    )
    Object[] getEmployeeByEmail(String email);
}
