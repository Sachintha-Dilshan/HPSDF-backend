package lk.gov.ps.HPSDF.admin.leave.controllers;

import lk.gov.ps.HPSDF.admin.leave.models.Leave;
import lk.gov.ps.HPSDF.admin.leave.repositories.LeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth/hr/leave")

public class LeaveController {
    @Autowired
    LeaveRepository leaveRepository;

    @GetMapping("/leave")
    public ResponseEntity<List<Leave>> getAllLeaves(@RequestParam(required = false) String leaveName) {
        try {
            List<Leave> leaves = new ArrayList<Leave>();

            if (leaveName == null)
                leaveRepository.findAll().forEach(leaves::add);
            else
                leaveRepository.findByLeaveNameContaining(leaveName).forEach(leaves::add);

            if (leaves.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(leaves, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/leave/{id}")
    public ResponseEntity<Leave> getLeaveById(@PathVariable("id") long id) {
        Optional<Leave> leaveData = leaveRepository.findById(id);

        if (leaveData.isPresent()) {
            return new ResponseEntity<>(leaveData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/leave")
    public ResponseEntity<Leave> createLeave(@RequestBody Leave leave) {
        try {
            Leave _leave = leaveRepository
                    .save(new Leave(leave.getLeaveName()));
            return new ResponseEntity<>(_leave, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/leave/{id}")
    public ResponseEntity<Leave> updateLeave(@PathVariable("id") long id, @RequestBody Leave leave) {
        Optional<Leave> leaveData = leaveRepository.findById(id);

        if (leaveData.isPresent()) {
            Leave _leave = leaveData.get();
            _leave.setLeaveName(leave.getLeaveName());
            return new ResponseEntity<>(leaveRepository.save(_leave), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/leave/{id}")
    public ResponseEntity<HttpStatus> deleteLeave(@PathVariable("id") long id) {
        try {
            leaveRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/leave")
    public ResponseEntity<HttpStatus> deleteAllLeaves() {
        try {
            leaveRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
