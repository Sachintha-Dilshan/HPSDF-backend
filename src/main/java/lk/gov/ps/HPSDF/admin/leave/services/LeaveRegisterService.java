package lk.gov.ps.HPSDF.admin.leave.services;

import lk.gov.ps.HPSDF.admin.leave.models.LeaveRegister;
import lk.gov.ps.HPSDF.admin.leave.repositories.LeaveRegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveRegisterService {
    @Autowired
    private LeaveRegisterRepository leaveRegisterRepository;

    public List<Object[]> getAllLeaveRegisters()
    {
        return leaveRegisterRepository.getAllLeaveRequests();
    }
    public LeaveRegister getLeaveRegister(String nicNo)
    {
        return leaveRegisterRepository.findByNicNo(nicNo);
    }

    public LeaveRegister saveLeaveRegister(LeaveRegister leaveRegister)
    {
        LeaveRegister existingLeaveRegister = leaveRegisterRepository.findByNicNo(leaveRegister.getNicNo());

        if(existingLeaveRegister == null)
            return leaveRegisterRepository.save(leaveRegister);
        else
        {
            if(leaveRegister.getCasualLeave() != 0.0)
                existingLeaveRegister.setCasualLeave(existingLeaveRegister.getCasualLeave() + leaveRegister.getCasualLeave());

            if(leaveRegister.getVacationLeave() != 0.0)
                existingLeaveRegister.setVacationLeave(existingLeaveRegister.getVacationLeave() + leaveRegister.getVacationLeave());

            if(leaveRegister.getExpiredVacationLeave() != 0.0)
                existingLeaveRegister.setExpiredVacationLeave(existingLeaveRegister.getExpiredVacationLeave() + leaveRegister.getExpiredVacationLeave());

            if(leaveRegister.getCommutedHalfPayLeave() != 0.0)
                existingLeaveRegister.setCommutedHalfPayLeave(existingLeaveRegister.getCommutedHalfPayLeave() + leaveRegister.getCommutedHalfPayLeave());

            if(leaveRegister.getHalfPayLeave() != 0.0)
                existingLeaveRegister.setHalfPayLeave(existingLeaveRegister.getHalfPayLeave() + leaveRegister.getHalfPayLeave());

            if(leaveRegister.getNoPayLeave() != 0.0)
                existingLeaveRegister.setNoPayLeave(existingLeaveRegister.getNoPayLeave() + leaveRegister.getNoPayLeave());

            if(leaveRegister.getDutyLeave() != 0.0)
                existingLeaveRegister.setDutyLeave(existingLeaveRegister.getDutyLeave() + leaveRegister.getDutyLeave());

            return leaveRegisterRepository.save(existingLeaveRegister);
        }
    }
}
