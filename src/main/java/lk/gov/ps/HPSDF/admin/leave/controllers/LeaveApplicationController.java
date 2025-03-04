package lk.gov.ps.HPSDF.admin.leave.controllers;

import jakarta.validation.Valid;
import lk.gov.ps.HPSDF.admin.leave.dto.LeaveApplicationDto;
import lk.gov.ps.HPSDF.admin.leave.models.LeaveApplication;
import lk.gov.ps.HPSDF.admin.leave.services.LeaveApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth/hr/leave")
public class LeaveApplicationController {

    @Autowired
    private LeaveApplicationService leaveApplicationService;
    @PostMapping("/saveLeaveApplication")
    public ResponseEntity<LeaveApplication> saveLeaveApplication(@RequestBody @Valid LeaveApplicationDto leaveApplication){
        LeaveApplication _leaveApplication = leaveApplicationService.saveLeaveApplication(leaveApplication);
        try {
            return new ResponseEntity<>(_leaveApplication, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getLeaveChit/{applicationId}")
    public ResponseEntity<Object[]> getLeaveChit(@PathVariable int applicationId){
        return ResponseEntity.ok(leaveApplicationService.getLeaveChit(applicationId));
    }

    @GetMapping("/getApprovedLeaveApplications/{nicNo}")
    public ResponseEntity<List<Object[]>> getApprovedLeaveApplications(@PathVariable String nicNo){
        return ResponseEntity.ok(leaveApplicationService.getApprovedLeaveApplications(nicNo));
    }

    @GetMapping("/getOnLeaveTodayApplications")
    public ResponseEntity<List<Object[]>> getOnLeaveTodayApplications(){
        return ResponseEntity.ok(leaveApplicationService.getOnLeaveTodayApplications());
    }

    @GetMapping("/getOnLeaveTodayApplicationsCount")
    public ResponseEntity<Integer> getOnLeaveTodayApplicationsCount(){
        return ResponseEntity.ok(leaveApplicationService.getOnLeaveTodayApplicationsCount());
    }

    @GetMapping("/getLeaveRequests/{nicNo}")
    public ResponseEntity<List<Object[]>> getLeaveRequests(@PathVariable String nicNo){
        return ResponseEntity.ok(leaveApplicationService.getLeaveRequests(nicNo));
    }

    @GetMapping("/getLeaveRequestsCount/{nicNo}")
    public ResponseEntity<Integer> getLeaveRequestsCount(@PathVariable String nicNo){
        return ResponseEntity.ok(leaveApplicationService.getLeaveRequestsCount(nicNo));
    }

    @PutMapping("/updateApplicationStatus/{applicationId}")
    public ResponseEntity<LeaveApplication> updateApplicationStatus(@RequestBody LeaveApplication leaveApplication, @PathVariable int applicationId){
        LeaveApplication _leaveApplication = leaveApplicationService.updateApplicationStatus(leaveApplication, applicationId);
        if(_leaveApplication == null)
            return new ResponseEntity<>(_leaveApplication,HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(_leaveApplication, HttpStatus.OK);

    }
}
