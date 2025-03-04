package lk.gov.ps.HPSDF.admin.leave.controllers;

import jakarta.validation.Valid;
import lk.gov.ps.HPSDF.admin.leave.models.Leave;
import lk.gov.ps.HPSDF.admin.leave.models.LeaveOfficer;
import lk.gov.ps.HPSDF.admin.leave.services.LeaveOfficersSeriverce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth/hr/leave")
public class LeaveOfficerController {
    @Autowired
    private LeaveOfficersSeriverce leaveOfficersSeriverce;



    @PostMapping("/addLeaveOfficer")
    public ResponseEntity<LeaveOfficer> createLeaveOfficer(@RequestBody @Valid LeaveOfficer leaveOfficer) {
        try {
            LeaveOfficer _leaveOfficer = leaveOfficersSeriverce.saveLeaveOfficer(leaveOfficer);
            return new ResponseEntity<>(_leaveOfficer, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getLeaveOfficerByNic/{nicNo}")
    public ResponseEntity<Object[]> getLeaveOfficerByNic(@PathVariable String nicNo){
        return ResponseEntity.ok(leaveOfficersSeriverce.getLeaveOfficerByNic(nicNo));
    }

    @GetMapping("/getLeaveOfficers/{role}")
    public ResponseEntity<List<Object[]>> getLeaveOfficers(@PathVariable String role){
        return ResponseEntity.ok(leaveOfficersSeriverce.getLeaveOfficers(role));
    }

    @GetMapping("/getLeaveOfficerByLeaveId/{leaveId}")
    public ResponseEntity<Object[]> getLeaveOfficerByLeaveId(@PathVariable String leaveId){
        return ResponseEntity.ok(leaveOfficersSeriverce.getLeaveOfficerByLeaveId(leaveId));
    }

    @DeleteMapping("/removeLeaveOfficer/{nicNo}")
    public String deleteLeaveOfficer(@PathVariable String nicNo){
        if(leaveOfficersSeriverce.deleteLeaveOfficer(nicNo))
            return "Employee has been deleted";
        else
            return "Employee is not found";
    }

}
