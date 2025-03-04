package lk.gov.ps.HPSDF.admin.leave.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LeaveTypeRequest {
    @NotBlank(message = "Leave type name is required !")
    private String leaveType;
}
