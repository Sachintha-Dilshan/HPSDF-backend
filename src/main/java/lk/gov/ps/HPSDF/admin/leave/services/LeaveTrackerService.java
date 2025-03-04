package lk.gov.ps.HPSDF.admin.leave.services;

import lk.gov.ps.HPSDF.admin.hr.models.Designation;
import lk.gov.ps.HPSDF.admin.leave.models.LeaveTracker;
import lk.gov.ps.HPSDF.admin.leave.repositories.LeaveTrackerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveTrackerService {
    @Autowired
    private LeaveTrackerRepository leaveTrackerRepository;

    public LeaveTracker saveLeaveTrackingData(LeaveTracker leaveTracker)
    {
        return leaveTrackerRepository.save(leaveTracker);
    }

    public LeaveTracker updateLeaveTracker(LeaveTracker leaveTracker,int id){
        System.out.println("********************************************Application Id :" + leaveTracker.getLeaveApplicationId());
        LeaveTracker existingLeaveTracker = leaveTrackerRepository.findById(id).orElse(null);
        if(existingLeaveTracker != null){
            if(leaveTracker.getActingOfficerApprovalTimeStamp() != null)
            {
                existingLeaveTracker.setActingOfficerApprovalStatus(leaveTracker.getActingOfficerApprovalStatus());
                existingLeaveTracker.setActingOfficerApprovalTimeStamp(leaveTracker.getActingOfficerApprovalTimeStamp());
            }

            if(leaveTracker.getSupervisorApprovalTimeStamp() != null)
            {
                existingLeaveTracker.setSupervisorApprovalStatus(leaveTracker.getSupervisorApprovalStatus());
                existingLeaveTracker.setSupervisorApprovalTimeStamp(leaveTracker.getSupervisorApprovalTimeStamp());
            }

            if(leaveTracker.getHodApprovalTimeStamp() != null)
            {
                existingLeaveTracker.setHodApprovalStatus(leaveTracker.getHodApprovalStatus());
                existingLeaveTracker.setHodApprovalTimeStamp(leaveTracker.getHodApprovalTimeStamp());
            }

            return leaveTrackerRepository.save(existingLeaveTracker);
        } else
            return existingLeaveTracker;
    }

    public List<Object[]> getLeaveRequests(){
        return leaveTrackerRepository.getLeaveRequests();
    }

    public Object[] getLeaveTrackingData(int applicationId){
        return leaveTrackerRepository.getLeaveTrackingData(applicationId);
    }

}
