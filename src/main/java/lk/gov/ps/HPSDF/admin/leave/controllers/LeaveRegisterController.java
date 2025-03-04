package lk.gov.ps.HPSDF.admin.leave.controllers;

import jakarta.validation.Valid;
import lk.gov.ps.HPSDF.admin.leave.models.LeaveRegister;
import lk.gov.ps.HPSDF.admin.leave.services.LeaveRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth/hr/leave")
public class LeaveRegisterController {
    @Autowired
    private LeaveRegisterService leaveRegisterService;

    @PostMapping("/saveLeaveRegister")
    public ResponseEntity<LeaveRegister> saveLeaveRegister(@RequestBody @Valid LeaveRegister leaveRegister){
        LeaveRegister _leaveRegister = leaveRegisterService.saveLeaveRegister(leaveRegister);
        try {
            return new ResponseEntity<>(_leaveRegister, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(_leaveRegister, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllLeaveRegisters")
    public ResponseEntity<List<Object[]>> getAllLeaveRegisters(){
        return ResponseEntity.ok(leaveRegisterService.getAllLeaveRegisters());
    }
    @GetMapping("/leaveRegisterByNic/{nicNo}")
    public ResponseEntity<LeaveRegister>  getLeaveRegisterByNic(@PathVariable String nicNo){
        LeaveRegister leaveRegister = leaveRegisterService.getLeaveRegister(nicNo);
        if( leaveRegister == null)
            return new ResponseEntity<>(leaveRegister,HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(leaveRegister,HttpStatus.OK);
    }
}
