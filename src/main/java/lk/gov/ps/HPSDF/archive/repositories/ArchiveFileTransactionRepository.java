package lk.gov.ps.HPSDF.archive.repositories;

import lk.gov.ps.HPSDF.admin.hr.models.Employee;
import lk.gov.ps.HPSDF.archive.models.ArchiveFileTransaction;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArchiveFileTransactionRepository extends JpaRepository<ArchiveFileTransaction, Long> {
    List<ArchiveFileTransaction> findByEmployee(Employee employee, Sort sort);

    @Query(
            value = "SELECT * FROM archive_file_transaction a WHERE a.checked_in_time_stamp IS NULL",
            nativeQuery = true
    )
    List<ArchiveFileTransaction> findCheckedOutFiles();

}
