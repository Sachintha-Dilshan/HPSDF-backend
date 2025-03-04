package lk.gov.ps.HPSDF.admin.leave.controllers;

import jakarta.validation.Valid;
import lk.gov.ps.HPSDF.admin.leave.models.LeaveTracker;
import lk.gov.ps.HPSDF.admin.leave.services.LeaveTrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth/hr/leave")
public class LeaveTrackerController {

    @Autowired
    private LeaveTrackerService leaveTrackerService;

    @PostMapping("/saveLeaveTrackingDetails")
    public ResponseEntity<LeaveTracker> saveLeaveTrackingDetails(@RequestBody @Valid LeaveTracker leaveTracker){
        LeaveTracker _leaveTracker = leaveTrackerService.saveLeaveTrackingData(leaveTracker);
        try {
            return new ResponseEntity<>(_leaveTracker, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateLeaveTracker/{id}")
    public ResponseEntity<LeaveTracker> updateLeaveTracker(@RequestBody @Valid  LeaveTracker leaveTracker, @PathVariable int id){
        System.out.println(leaveTracker.toString());
        LeaveTracker _leaveTracker = leaveTrackerService.updateLeaveTracker(leaveTracker, id);
        if(_leaveTracker == null)
            return new ResponseEntity<>(_leaveTracker,HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(_leaveTracker, HttpStatus.OK);
    }

    @GetMapping("/getLeaveRequests")
    public ResponseEntity<List<Object[]>> getLeaveRequests(){
        return ResponseEntity.ok(leaveTrackerService.getLeaveRequests());
    }

    @GetMapping("/getLeaveTrackingData/{applicationId}")
    public ResponseEntity<Object[]> getLeaveTrackingData(@PathVariable int applicationId){
        return ResponseEntity.ok(leaveTrackerService.getLeaveTrackingData(applicationId));
    }
}
