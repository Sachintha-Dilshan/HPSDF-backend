package lk.gov.ps.HPSDF.admin.leave.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "leaves")
public class Leave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "leave_name")
    private String leaveName;

    public Leave(String leaveName){
        this.leaveName = leaveName;
    }
}
