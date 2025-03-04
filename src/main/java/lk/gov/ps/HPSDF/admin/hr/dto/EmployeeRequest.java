package lk.gov.ps.HPSDF.admin.hr.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Date;

@Data
public class EmployeeRequest {
    @Id
    @NotNull(message = "NIC No is required")
    private String nicNo;

    @NotBlank(message = "Name with initials is required")
    private String nameWithInitials;

    @NotBlank(message = "Full name is required")
    private String fullName;

    @NotBlank(message = "Address is required")
    private String address;

    @NotNull(message = "Gender is required")
    private char gender;

    @NotBlank(message = "Marital status is required")
    private String maritalStatus;

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date must be in the past")
    private Date dob;

    @NotNull(message = "Mobile no is required")
    private long mobileNo;

    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotNull(message = "Office official appointment date is required")
    @Past(message = "Date must be in the past")
    private Date officeOfficialAppointmentDate;

    @NotNull(message = "Designation is required")
    private int designation;

    @NotNull(message = "Service sector is required")
    private int serviceSector;

    @NotNull(message = "Designation class is required")
    private int designationClass;

    @NotNull(message = "Designation grade is required")
    private int designationGrade;

    @NotNull(message = "First appointment date is required")
    @Past(message = "Date must be in the past")
    private Date firstAppointmentDate;

    @NotNull(message = "Duty assigned date is required")
    @Past(message = "Date must be in the past")
    private Date dutyAssignedDate;

    @NotNull(message = "Permanent details is required")
    private boolean permanent;

    @NotBlank(message = "Nature of appointment is required")
    private String natureOfAppointment;

    @NotNull(message = "Duty permanent date is required")
    @Past(message = "Date must be in the past")
    private Date dutyPermanentDate;

    @NotBlank(message = "Salary increment date is required")
    private String salaryIncrementDate;

    @NotBlank(message = "Salary code is required")
    private String salaryCodePrefix;

    @NotBlank(message = "Salary code is required")
    private String salaryCode;

    @NotBlank(message = "WOP no is required")
    private String wopNo;

    private int section;

    private String subjectNo;

    private Date sectionAssignedDate;

    @NotNull(message = "Leave id is required")
    private int leaveId;
}
