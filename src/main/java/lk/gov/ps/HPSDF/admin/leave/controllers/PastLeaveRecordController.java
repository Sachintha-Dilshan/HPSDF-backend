package lk.gov.ps.HPSDF.admin.leave.controllers;

import jakarta.validation.Valid;
import lk.gov.ps.HPSDF.admin.leave.dto.PastLeaveRecordDto;
import lk.gov.ps.HPSDF.admin.leave.models.PastLeaveRecord;
import lk.gov.ps.HPSDF.admin.leave.services.PastLeaveRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth/hr/leave")
public class PastLeaveRecordController {
    @Autowired
    private PastLeaveRecordService pastLeaveRecordService;
    @PostMapping("/addPastRecord")
    public ResponseEntity<PastLeaveRecord> save(@RequestBody @Valid PastLeaveRecord pastLeaveRecord){
        PastLeaveRecord _pastLeaveRecord = pastLeaveRecordService.add(pastLeaveRecord);
        try {
            return new ResponseEntity<>(_pastLeaveRecord, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(_pastLeaveRecord, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updatePastRecord")
    public ResponseEntity<PastLeaveRecord> update(@RequestBody @Valid  PastLeaveRecord pastLeaveRecord){
        PastLeaveRecord _pastLeaveRecord = pastLeaveRecordService.update(pastLeaveRecord);
        if(_pastLeaveRecord == null)
            return new ResponseEntity<>(_pastLeaveRecord,HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(_pastLeaveRecord, HttpStatus.OK);
    }

    @GetMapping("/getPastRecordByNicNo/{nicNo}")
    public ResponseEntity<List<Object[]>>  getByNicNo(@PathVariable String nicNo){
        return ResponseEntity.ok(pastLeaveRecordService.getByNicNo(nicNo));

    }
    @DeleteMapping("/deletePastRecord/{nicNo}/{year}")
    public String delete(@PathVariable String nicNo, @PathVariable int year){
        if(pastLeaveRecordService.delete(nicNo, year))
            return "Past leave record has been deleted";
        else
            return "Past leave record is not found";
    }
}
