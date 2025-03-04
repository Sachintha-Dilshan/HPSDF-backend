package lk.gov.ps.HPSDF.admin.leave.services;

import lk.gov.ps.HPSDF.admin.leave.models.LeaveOfficer;
import lk.gov.ps.HPSDF.admin.leave.models.LeaveType;
import lk.gov.ps.HPSDF.admin.leave.repositories.LeaveOfficersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveOfficersSeriverce {
    @Autowired
    private LeaveOfficersRepository leaveOfficersRepository;

    public LeaveOfficer saveLeaveOfficer(LeaveOfficer leaveOfficer){
        return leaveOfficersRepository.save(leaveOfficer);
    }

    public List<Object[]> getLeaveOfficers(String role){
        return leaveOfficersRepository.getOfficers(role);
    }
    public Object[] getLeaveOfficerByNic(String nicNo){
        return leaveOfficersRepository.getEmployeeByNic(nicNo);
    }

    public Object[] getLeaveOfficerByLeaveId(String leaveId){
        return leaveOfficersRepository.getEmployeeByLeaveId(leaveId);
    }
    public boolean deleteLeaveOfficer(String id){
        if(leaveOfficersRepository.existsById(id))
        {
            leaveOfficersRepository.deleteById(id);
            return true;
        }
        else
            return false;

    }

}
