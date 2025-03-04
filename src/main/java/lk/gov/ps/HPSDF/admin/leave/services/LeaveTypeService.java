package lk.gov.ps.HPSDF.admin.leave.services;

import lk.gov.ps.HPSDF.admin.leave.models.LeaveType;
import lk.gov.ps.HPSDF.admin.leave.repositories.LeaveTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveTypeService {
    @Autowired
    private LeaveTypeRepository leaveTypeRepository;
    public LeaveType saveLeaveType(LeaveType leaveType){
        return leaveTypeRepository.save(leaveType);
    }

    public List<LeaveType> getAllLeaveTypes(){
        return leaveTypeRepository.findAll();
    }
    public LeaveType getLeaveTypeById(int id){
        return leaveTypeRepository.findById(id).orElse(null);
    }

    public LeaveType getLeaveTypeByName(String leaveType){
        return leaveTypeRepository.findByLeaveType(leaveType);
    }

    public List<LeaveType> getByLeaveTypeContaining(String leaveType){
        return leaveTypeRepository.findByLeaveTypeContaining(leaveType);
    }

    public LeaveType updateLeaveType(LeaveType leaveType, int id){
        LeaveType existingLeaveType = leaveTypeRepository.findById(id).orElse(null);
        if(existingLeaveType != null){
            existingLeaveType.setLeaveType(leaveType.getLeaveType());
            return leaveTypeRepository.save(existingLeaveType);
        } else
            return existingLeaveType;
    }

    public boolean deleteLeaveType(int id){
        if(leaveTypeRepository.existsById(id))
        {
            leaveTypeRepository.deleteById(id);
            return true;
        }
        else
            return false;

    }
}
