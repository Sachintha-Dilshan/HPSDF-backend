package lk.gov.ps.HPSDF.admin.leave.controllers;

import jakarta.validation.Valid;
import lk.gov.ps.HPSDF.admin.leave.dto.LeaveTypeRequest;
import lk.gov.ps.HPSDF.admin.leave.models.LeaveType;
import lk.gov.ps.HPSDF.admin.leave.services.LeaveTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth/hr")
public class LeaveTypeController {
    @Autowired
    private LeaveTypeService leaveTypeService;
    @PostMapping("/leaveType")
    public ResponseEntity<LeaveType>  saveLeaveType(@RequestBody @Valid  LeaveTypeRequest leaveTypeRequest){
        LeaveType leaveType = leaveTypeService.saveLeaveType(LeaveType.build(0,leaveTypeRequest.getLeaveType()));
        try {
            return new ResponseEntity<>(leaveType, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/leaveType/{id}")
    public ResponseEntity<LeaveType> updateLeaveType(@RequestBody @Valid  LeaveTypeRequest leaveTypeRequest, @PathVariable int id){
        LeaveType leaveType = leaveTypeService.updateLeaveType(LeaveType.build(0,leaveTypeRequest.getLeaveType()), id);
        if(leaveType == null)
            return new ResponseEntity<>(leaveType,HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(leaveType, HttpStatus.OK);
    }

    @GetMapping("/leaveType")
    public ResponseEntity<List<LeaveType>> getAllLeaveTypes(){
        return ResponseEntity.ok(leaveTypeService.getAllLeaveTypes());
    }
    @GetMapping("/leaveTypeById/{id}")
    public ResponseEntity<LeaveType>  getLeaveTypeById(@PathVariable int id){
        LeaveType leaveType = leaveTypeService.getLeaveTypeById(id);
        if( leaveType == null)
            return new ResponseEntity<>(leaveType,HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(leaveType,HttpStatus.OK);
    }

    @GetMapping("/leaveTypeByName/{leaveType}")
    public ResponseEntity<LeaveType> getLeaveTypeByName(@PathVariable String leaveType){
        LeaveType _leaveType = leaveTypeService.getLeaveTypeByName(leaveType);
        if( _leaveType == null)
            return new ResponseEntity<>(_leaveType,HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(_leaveType,HttpStatus.OK);
    }
    @GetMapping("leaveTypeContaining/{leaveType}")
    public ResponseEntity<List<LeaveType>> getByLeaveTypeContaining(@PathVariable String leaveType){
        return ResponseEntity.ok(leaveTypeService.getByLeaveTypeContaining(leaveType));
    }
    @DeleteMapping("/leaveType/{id}")
    public String deleteLeaveType(@PathVariable int id){
        if(leaveTypeService.deleteLeaveType(id))
            return "Leave type has been deleted";
        else
            return "Leave type is not found";
    }
}
