package lk.gov.ps.HPSDF.archive.services;

import lk.gov.ps.HPSDF.admin.hr.models.Employee;
import lk.gov.ps.HPSDF.admin.hr.repositories.EmployeeRepository;
import lk.gov.ps.HPSDF.archive.models.ArchiveFileTransaction;
import lk.gov.ps.HPSDF.archive.models.ArchiveSection;
import lk.gov.ps.HPSDF.archive.repositories.ArchiveFileTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArchiveFileTransactionService {
    @Autowired
    private ArchiveFileTransactionRepository archiveFileTransactionRepository;

    @Autowired
    private EmployeeRepository employeeRepository;
    public ArchiveFileTransaction saveFileTransaction(ArchiveFileTransaction archiveFileTransaction){
        Employee employee = employeeRepository.findById(archiveFileTransaction.getEmployee().getNicNo()).orElse(null);

        if(employee != null)
            return archiveFileTransactionRepository.save(archiveFileTransaction);
        else
            return  null;

    }

    public ArchiveFileTransaction updateFileTransaction(ArchiveFileTransaction archiveFileTransaction, long id){
        ArchiveFileTransaction existingArchiveFileTransaction = archiveFileTransactionRepository.findById(id).orElse(null);
        if(existingArchiveFileTransaction != null){
            existingArchiveFileTransaction.setCheckedInTimeStamp(archiveFileTransaction.getCheckedInTimeStamp());
            return archiveFileTransactionRepository.save(existingArchiveFileTransaction);
        }
        else
            return existingArchiveFileTransaction;
    }
    public List<ArchiveFileTransaction> getFileTransactionsByEmployee(Employee employee){
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return archiveFileTransactionRepository.findByEmployee(employee, sort);
    }

    public List<ArchiveFileTransaction> getCheckedOutFiles(){
        return archiveFileTransactionRepository.findCheckedOutFiles();
    }
}
