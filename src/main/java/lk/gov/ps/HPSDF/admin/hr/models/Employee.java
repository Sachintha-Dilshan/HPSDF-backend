package lk.gov.ps.HPSDF.admin.hr.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
public class Employee {
    @Id
    @Column(name = "nic_no")
    private String nicNo;

    @Column(name = "name_with_initials")
    private String nameWithInitials;

    @Column(name = "full_name")
    private String fullName;

    @Column(name ="address")
    private String address;

    @Column(name = "gender")
    private char gender;

    @Column(name = "marital_status")
    private String maritalStatus;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "dob",columnDefinition = "date")
    private Date dob;

    @Column(name = "mobile_no")
    private long mobileNo;

    @Column(name = "email")
    private String email;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "office_official_appointment_date", columnDefinition = "date")
    private Date officeOfficialAppointmentDate;

    @Column(name = "designation")
    private int designation;

    @Column(name = "service_sector")
    private int serviceSector;

    @Column(name = "designation_class")
    private int designationClass;

    @Column(name = "designation_grade")
    private int designationGrade;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "first_appointment_date",columnDefinition = "date")
    private Date firstAppointmentDate;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "duty_assigned_date",columnDefinition = "date")
    private Date dutyAssignedDate;

    @Column(name = "permanent")
    private boolean permanent;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "duty_permanent_date",columnDefinition = "date")
    private Date dutyPermanentDate;


    @Column(name = "salary_increment_date")
    private String salaryIncrementDate;

    @Column(name = "salary_code_prefix")
    private String salaryCodePrefix;

    @Column(name = "salary_code")
    private String salaryCode;

    @Column(name = "wop_no")
    private String wopNo;

    @Column(name = "section")
    private int section;

    @Column(name = "subject_no")
    private String subjectNo;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "section_assigned_date",columnDefinition = "date")
    private Date sectionAssignedDate;

    @Column(name = "nature_of_appointment")
    private String natureOfAppointment;

    @Column(name = "leave_id")
    private int leaveId;
}
