package lk.gov.ps.HPSDF.admin.leave.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
@Table(name = "leave_application")
public class LeaveApplication {

    @Id
    @Column(name = "application_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int applicationId;

    @Column(name = "employee_nic_no")
    @NotBlank(message = "Employee Nic No is required.")
    private String employeeNicNo;

    @Column(name = "acting_officer_nic_no")
    @NotBlank(message = "Acting officer Nic No is required.")
    private String actingOfficerNicNo;

    @Column(name = "supervisor_nic_no")
    @NotBlank(message = "Supervisor Nic No is required.")
    private String supervisorNicNo;

    @Column(name = "hod_nic_no")
    @NotBlank(message = "Head of the department nic no is required.")
    private String hodNicNo;

    @Column(name = "leave_type")
    @NotNull(message = "Leave type is required.")
    private int leaveType;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "leave_commencing_date",columnDefinition = "date")
    @NotNull(message = "Leave commencing date is required.")
    private Date leaveCommencingDate;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_of_resuming_duties",columnDefinition = "date")
    @NotNull(message = "Date of resuming duties is required.")
    private Date dateOfResumingDuties;

    @Column(name = "leave_period")
    @NotNull(message = "Leave period is required.")
    private double leavePeriod;

    @Column(name = "reason")
    @NotBlank(message = "Reason for leave is required.")
    private String reason;

    @Column(name = "status")
    @NotNull(message = "Status is required")
    private int status;
}
