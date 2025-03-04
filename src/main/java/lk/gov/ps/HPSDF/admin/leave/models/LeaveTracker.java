package lk.gov.ps.HPSDF.admin.leave.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
@Table(name = "leave_tracker")
public class LeaveTracker {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "application_submitted_time_stamp")
    private Date applicationSubmittedTimeStamp;

    @Column(name = "acting_officer_approval_status")
    private int actingOfficerApprovalStatus;

    @Column(name = "acting_officer_approval_time_stamp")
    private Date actingOfficerApprovalTimeStamp;

    @Column(name = "supervisor_approval_status")
    private int supervisorApprovalStatus;

    @Column(name = "supervisor_approval_time_stamp")
    private Date supervisorApprovalTimeStamp;

    @Column(name = "hod_approval_status")
    private int hodApprovalStatus;

    @Column(name = "hod_approval_time_stamp")
    private Date hodApprovalTimeStamp;

    @Column(name = "leave_application_Id")
    private int leaveApplicationId;
}
