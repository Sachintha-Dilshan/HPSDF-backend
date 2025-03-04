package lk.gov.ps.HPSDF.admin.leave.repositories;


import jakarta.transaction.Transactional;
import lk.gov.ps.HPSDF.admin.leave.models.PastLeaveRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PastLeaveRecordRepository extends JpaRepository<PastLeaveRecord, Integer> {
    @Query(
            value = "select year, casual_leave, vacation_leave from past_leave_records where nic_no = ?1",
            nativeQuery = true
    )
    List<Object[]> findRecordByNicNO(String nic_no);

    @Query(
            value = "select * from past_leave_records where nic_no = ?1 and year = ?2",
            nativeQuery = true
    )
    PastLeaveRecord findRecord(String nic_no, int year);

    @Modifying
    @Query(
            value = "delete from past_leave_records where nic_no = ?1 and year = ?2",
            nativeQuery = true
    )
    @Transactional
    void deleteRecord(String nic_no, int year);

}
