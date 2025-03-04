package lk.gov.ps.HPSDF.admin.leave.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
@Table(name = "attendance_sheet")
public class AttendanceSheet {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nic_no")
    @NotBlank(message = "Nic no is required")
    private String nicNo;

    @Column(name = "date")
    @NotNull(message = "Date is required")
    private Date date;

    @Column(name = "status")
    @NotNull(message = "Status is required")
    private int status;

    @Column(name = "time_in")
    private Time timeIn;

    @Column(name = "time_out")
    private Time timeOut;

    @Column(name = "remark")
    private String remark;
}
