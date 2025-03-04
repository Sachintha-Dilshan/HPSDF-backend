package lk.gov.ps.HPSDF.admin.hr.services;

import lk.gov.ps.HPSDF.admin.hr.models.Employee;
import lk.gov.ps.HPSDF.admin.hr.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getEmployees(){
        return employeeRepository.findAll();
    }

    public long getCount(){
        return employeeRepository.count();
    }

    public String getEmployeeName(int leaveId){
        return employeeRepository.findEmployeeName(leaveId);
    }

    public String getEmployeeNicNo(int leaveId){
        return employeeRepository.findEmployeeNicNo(leaveId);
    }

    public List<Object[]> getAllEmployeesData(){
        return employeeRepository.findAllEmployeesData();
    }

    public List<Object[]> getAllEmployees(){
        return employeeRepository.findAllEmployees();
    }

    public List<Object[]> findEmployeeBirthdayToday(){
        return employeeRepository.findEmployeeBirthdayToday();
    }

    public int findEmployeeBirthdayTodayCount(){
        return employeeRepository.findEmployeeBirthdayTodayCount();
    }
    public List<Object[]> sortEmployeesBySection(int sectionId){
        return employeeRepository.sortEmployeesBySection(sectionId);
    }

    public Object[] getEmployeeLeavePersonalData(int  leaveId){
        return employeeRepository.findEmployeeLeavePersonalData(leaveId);
    }

    public Object[] getEmployeeByEmail(String email)
    {
        return employeeRepository.getEmployeeByEmail(email);
    }


    public Object[] sortEmployeesByNicNo(String  nicNo){
        return employeeRepository.sortEmployeesByNicNo(nicNo);
    }

    public Object[] sortEmployeesByLeaveId(int  leaveId){
        return employeeRepository.sortEmployeesByLeaveId(leaveId);
    }

    public Employee getEmployee(String nicNo){
        return employeeRepository.findById(nicNo).orElse(null);
    }

    public Object[] getEmployeeAllDataByNic(String nicNo){
        return employeeRepository.findEmployeesByNic(nicNo);
    }

    public Employee addEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Employee employee){
        Employee existingEmployee = employeeRepository.findById(employee.getNicNo()).orElse(null);
        if(existingEmployee != null){
            existingEmployee.setNameWithInitials(employee.getNameWithInitials());
            existingEmployee.setFullName(employee.getFullName());
            existingEmployee.setAddress(employee.getAddress());
            existingEmployee.setGender(employee.getGender());
            existingEmployee.setMaritalStatus(employee.getMaritalStatus());
            existingEmployee.setDob(employee.getDob());
            existingEmployee.setMobileNo(employee.getMobileNo());
            existingEmployee.setEmail(employee.getEmail());
            existingEmployee.setOfficeOfficialAppointmentDate(employee.getOfficeOfficialAppointmentDate());
            existingEmployee.setDesignation(employee.getDesignation());
            existingEmployee.setServiceSector(employee.getServiceSector());
            existingEmployee.setDesignationClass(employee.getDesignationClass());
            existingEmployee.setDesignationGrade(employee.getDesignationGrade());
            existingEmployee.setFirstAppointmentDate(employee.getFirstAppointmentDate());
            existingEmployee.setDutyAssignedDate(employee.getDutyAssignedDate());
            existingEmployee.setPermanent(employee.isPermanent());
            existingEmployee.setNatureOfAppointment(employee.getNatureOfAppointment());
            existingEmployee.setDutyPermanentDate(employee.getDutyPermanentDate());
            existingEmployee.setSalaryIncrementDate(employee.getSalaryIncrementDate());
            existingEmployee.setSalaryCodePrefix(employee.getSalaryCodePrefix());
            existingEmployee.setSalaryCode(employee.getSalaryCode());
            existingEmployee.setWopNo(employee.getWopNo());
            existingEmployee.setSection(employee.getSection());
            existingEmployee.setSubjectNo(employee.getSubjectNo());
            existingEmployee.setSectionAssignedDate(employee.getSectionAssignedDate());
            existingEmployee.setLeaveId(employee.getLeaveId());
            return employeeRepository.save(existingEmployee);
        }
        else
            return existingEmployee;

    }

    public boolean deleteEmployee(String nicNo){
        if(employeeRepository.existsById(nicNo)){
            employeeRepository.deleteById(nicNo);
            return true;
        }else{
            return false;
        }

    }

}
