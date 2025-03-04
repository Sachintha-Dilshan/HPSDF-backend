package lk.gov.ps.HPSDF.admin.leave.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "past_leave_records")
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
@IdClass(PastLeaveRecordId.class)
public class PastLeaveRecord {
    @Id
    @Column(name = "nic_no")
    @NotNull
    private String nicNo;

    @Id
    @Column(name = "year")
    @NotNull(message = "Year is required")
    private int year;

    @Column(name = "casual_leave")
    @NotNull
    private double casualLeave;

    @Column(name = "vacation_leave")
    @NotNull
    private double vacationLeave;

}
