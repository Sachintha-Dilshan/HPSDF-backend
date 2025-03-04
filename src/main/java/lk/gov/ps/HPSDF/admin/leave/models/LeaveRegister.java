package lk.gov.ps.HPSDF.admin.leave.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
@Table(name = "leave_register")
public class LeaveRegister {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "casual_leave")
    private double casualLeave;

    @Column(name = "vacation_leave")
    private double vacationLeave;

    @Column(name = "expired_vacation_leave")
    private double expiredVacationLeave;

    @Column(name = "commuted_half_pay_leave")
    private double commutedHalfPayLeave;

    @Column(name = "half_pay_leave")
    private double halfPayLeave;

    @Column(name = "no_pay_leave")
    private double noPayLeave;

    @Column(name = "duty_leave")
    private double dutyLeave;

    @Column(name = "nic_no")
    private String nicNo;

}
