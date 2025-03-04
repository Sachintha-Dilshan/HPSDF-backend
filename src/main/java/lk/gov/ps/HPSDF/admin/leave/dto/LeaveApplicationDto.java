package lk.gov.ps.HPSDF.admin.leave.dto;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
public class LeaveApplicationDto {
    @NotNull(message = "Employee leave ID is required.")
    private int employeeLeaveId;

    @NotNull(message = "Acting officer leave ID is required.")
    private int actingOfficerLeaveId;

    @NotNull(message = "Supervisor Nic No is required.")
    private String supervisorNicNo;

    @NotBlank(message = "Head of the department nic no is required.")
    private String hodNicNo;

    @NotNull(message = "Leave type is required.")
    private int leaveType;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Leave commencing date is required.")
    private Date leaveCommencingDate;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Date of resuming duties is required.")
    private Date dateOfResumingDuties;

    @NotNull(message = "Leave period is required.")
    private int leavePeriod;

    @NotBlank(message = "Reason for leave is required.")
    private String reason;

    @NotNull(message = "Status is required")
    private int status;
}
