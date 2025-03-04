package lk.gov.ps.HPSDF.admin.hr.controllers;

import jakarta.validation.Valid;
import lk.gov.ps.HPSDF.admin.hr.dto.EmployeeRequest;
import lk.gov.ps.HPSDF.admin.hr.models.Employee;
import lk.gov.ps.HPSDF.admin.hr.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth/hr")
public class EmployeeController {
    @Autowired
    public EmployeeService employeeService;
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getEmployees(){
        return ResponseEntity.ok(employeeService.getEmployees());
    }

    @GetMapping("/employeesCount")
    public ResponseEntity<Long> getCount(){
        return ResponseEntity.ok(employeeService.getCount());
    }

    @GetMapping("/employeeName/{leaveId}")
    public ResponseEntity<String> getEmployeeName(@PathVariable int leaveId){
        return ResponseEntity.ok(employeeService.getEmployeeName(leaveId));
    }

    @GetMapping("/getEmployeeNicNo/{leaveId}")
    public ResponseEntity<String> getEmployeeNicNo(@PathVariable int leaveId){
        return ResponseEntity.ok(employeeService.getEmployeeNicNo(leaveId));
    }


    @GetMapping("/allEmployeesData")
    public ResponseEntity<List<Object[]>> getAllEmployeesData(){
        return ResponseEntity.ok(employeeService.getAllEmployeesData());
    }
    @GetMapping("/allEmployees")
    public ResponseEntity<List<Object[]>> getAllEmployees(){
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/sortEmployeesBySection/{sectionId}")
    public ResponseEntity<List<Object[]>> sortEmployeesBySection(@PathVariable int sectionId){
        return ResponseEntity.ok(employeeService.sortEmployeesBySection(sectionId));
    }

    @GetMapping("/getEmployeeLeavePersonalData/{leaveId}")
    public ResponseEntity<Object[]> getEmployeeLeavePersonalData(@PathVariable int leaveId){
        return ResponseEntity.ok(employeeService.getEmployeeLeavePersonalData(leaveId));
    }
    @GetMapping("/sortEmployeesByNicNo/{nicNo}")
    public ResponseEntity<Object[]> sortEmployeesByNicNo(@PathVariable String nicNo){
        return ResponseEntity.ok(employeeService.sortEmployeesByNicNo(nicNo));
    }

    @GetMapping("/sortEmployeesByLeaveId/{leaveId}")
    public ResponseEntity<Object[]> sortEmployeesByLeaveId(@PathVariable int leaveId){
        return ResponseEntity.ok(employeeService.sortEmployeesByLeaveId(leaveId));
    }
    @GetMapping("/findEmployeeBirthdayToday")
    public ResponseEntity<List<Object[]>> findEmployeeBirthdayToday()
    {
        return ResponseEntity.ok(employeeService.findEmployeeBirthdayToday());
    }
    @GetMapping("/findEmployeeBirthdayTodayCount")
    public ResponseEntity<Integer> findEmployeeBirthdayTodayCount()
    {
        return ResponseEntity.ok(employeeService.findEmployeeBirthdayTodayCount());
    }

    @GetMapping("/employee/{nicNo}")
    public ResponseEntity<Employee> getEmployee(@PathVariable String nicNo){
        Employee employee = employeeService.getEmployee(nicNo);
        if(employee == null)
            return new ResponseEntity<>(employee, HttpStatus.NOT_FOUND);
        else
            return  new ResponseEntity<>(employee,HttpStatus.OK);
    }

    @GetMapping("/employeeAllDataByNic/{nicNo}")
    public ResponseEntity<Object[]> getEmployeeAllData(@PathVariable String nicNo){

        Object[] employee = employeeService.getEmployeeAllDataByNic(nicNo);
        if(employee == null)
            return new ResponseEntity<>(employee, HttpStatus.NOT_FOUND);
        else
            return  new ResponseEntity<>(employee,HttpStatus.OK);
    }

    @PostMapping("/getEmployeeByEmail")
    public ResponseEntity<Object[]> getEmployeeByEmail(@RequestBody Employee employee)
    {
        return ResponseEntity.ok(employeeService.getEmployeeByEmail(employee.getEmail()));
    }
    @PostMapping("/employee")
    public ResponseEntity<Employee> addEmployee(@RequestBody @Valid EmployeeRequest employeeRequest){
        Employee employee = employeeService.addEmployee(
          Employee.build(
                  employeeRequest.getNicNo(),
                  employeeRequest.getNameWithInitials(),
                  employeeRequest.getFullName(),
                  employeeRequest.getAddress(),
                  employeeRequest.getGender(),
                  employeeRequest.getMaritalStatus(),
                  employeeRequest.getDob(),
                  employeeRequest.getMobileNo(),
                  employeeRequest.getEmail(),
                  employeeRequest.getOfficeOfficialAppointmentDate(),
                  employeeRequest.getDesignation(),
                  employeeRequest.getServiceSector(),
                  employeeRequest.getDesignationClass(),
                  employeeRequest.getDesignationGrade(),
                  employeeRequest.getFirstAppointmentDate(),
                  employeeRequest.getDutyAssignedDate(),
                  employeeRequest.isPermanent(),
                  employeeRequest.getDutyPermanentDate(),
                  employeeRequest.getSalaryIncrementDate(),
                  employeeRequest.getSalaryCodePrefix(),
                  employeeRequest.getSalaryCode(),
                  employeeRequest.getWopNo(),
                  employeeRequest.getSection(),
                  employeeRequest.getSubjectNo(),
                  employeeRequest.getSectionAssignedDate(),
                  employeeRequest.getNatureOfAppointment(),
                  employeeRequest.getLeaveId()
          )
        );

        try {
            return new ResponseEntity<>(employee, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/employee")
    public ResponseEntity<Employee> updateEmployee(@RequestBody @Valid EmployeeRequest employeeRequest){
        Employee employee = employeeService.updateEmployee(
                Employee.build(
                        employeeRequest.getNicNo(),
                        employeeRequest.getNameWithInitials(),
                        employeeRequest.getFullName(),
                        employeeRequest.getAddress(),
                        employeeRequest.getGender(),
                        employeeRequest.getMaritalStatus(),
                        employeeRequest.getDob(),
                        employeeRequest.getMobileNo(),
                        employeeRequest.getEmail(),
                        employeeRequest.getOfficeOfficialAppointmentDate(),
                        employeeRequest.getDesignation(),
                        employeeRequest.getServiceSector(),
                        employeeRequest.getDesignationClass(),
                        employeeRequest.getDesignationGrade(),
                        employeeRequest.getFirstAppointmentDate(),
                        employeeRequest.getDutyAssignedDate(),
                        employeeRequest.isPermanent(),
                        employeeRequest.getDutyPermanentDate(),
                        employeeRequest.getSalaryIncrementDate(),
                        employeeRequest.getSalaryCodePrefix(),
                        employeeRequest.getSalaryCode(),
                        employeeRequest.getWopNo(),
                        employeeRequest.getSection(),
                        employeeRequest.getSubjectNo(),
                        employeeRequest.getSectionAssignedDate(),
                        employeeRequest.getNatureOfAppointment(),
                        employeeRequest.getLeaveId()
                )
        );

        if(employee == null)
            return new ResponseEntity<>(employee,HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(employee, HttpStatus.OK);

    }

    @DeleteMapping("/employee/{nicNo}")
    public String deleteEmployee(@PathVariable String nicNo){
        if(employeeService.deleteEmployee(nicNo))
            return "Employee has been deleted";
        else
            return "Employee is not found";
    }

}
