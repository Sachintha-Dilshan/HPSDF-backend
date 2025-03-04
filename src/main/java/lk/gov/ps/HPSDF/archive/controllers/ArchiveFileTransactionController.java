package lk.gov.ps.HPSDF.archive.controllers;

import jakarta.validation.Valid;
import lk.gov.ps.HPSDF.admin.hr.models.Employee;
import lk.gov.ps.HPSDF.archive.models.ArchiveFileTransaction;
import lk.gov.ps.HPSDF.archive.models.ArchiveSection;
import lk.gov.ps.HPSDF.archive.services.ArchiveFileTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth/ar")
public class ArchiveFileTransactionController {
    @Autowired
    private ArchiveFileTransactionService archiveFileTransactionService;

    @PostMapping("/saveFileTransaction")
    public ResponseEntity<ArchiveFileTransaction> saveFileTransaction(@RequestBody @Valid ArchiveFileTransaction archiveFileTransaction){
        ArchiveFileTransaction _archiveFileTransaction = archiveFileTransactionService.saveFileTransaction(archiveFileTransaction);
        if(_archiveFileTransaction != null)
            return new ResponseEntity<>(_archiveFileTransaction, HttpStatus.OK);
        else
            return new ResponseEntity<>(_archiveFileTransaction,HttpStatus.NOT_FOUND);
    }

    @PutMapping("/updateFileTransaction/{id}")
    public ResponseEntity<ArchiveFileTransaction> updateFileTransaction(@RequestBody ArchiveFileTransaction archiveFileTransaction, @PathVariable long id){
        ArchiveFileTransaction _archiveFileTransaction = archiveFileTransactionService.updateFileTransaction(archiveFileTransaction, id);
        if(_archiveFileTransaction == null)
            return new ResponseEntity<>(_archiveFileTransaction,HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(_archiveFileTransaction, HttpStatus.OK);
    }

    @GetMapping("/getFileTransactionsByEmployee/{nic}")
    public ResponseEntity<List<ArchiveFileTransaction>> getFileTransactionsByEmployee(@PathVariable String nic)
    {
        Employee employee = new Employee();
        employee.setNicNo(nic);
        return ResponseEntity.ok(archiveFileTransactionService.getFileTransactionsByEmployee(employee));
    }

    @GetMapping("/getCheckedOutFiles")
    public ResponseEntity<List<ArchiveFileTransaction>> getCheckedOutFiles()
    {
        return ResponseEntity.ok(archiveFileTransactionService.getCheckedOutFiles());
    }
}
