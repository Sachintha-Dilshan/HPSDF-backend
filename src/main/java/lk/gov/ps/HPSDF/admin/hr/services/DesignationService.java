package lk.gov.ps.HPSDF.admin.hr.services;

import lk.gov.ps.HPSDF.admin.hr.models.Designation;
import lk.gov.ps.HPSDF.admin.hr.repositories.DesignationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DesignationService {
    @Autowired
    private DesignationRepository designationRepository;

    public Designation saveDesignation(Designation designation){
        return designationRepository.save(designation);
    }

    public List<Designation> getAllDesignations(){
        return designationRepository.findAll();
    }
    public Designation getDesignationById(int id){
        return designationRepository.findById(id).orElse(null);
    }

    public Designation getDesignationByName(String designationName){
        return designationRepository.findByDesignationName(designationName);
    }

    public Designation updateDesignation(Designation designation, int id){
        Designation existingDesignation = designationRepository.findById(id).orElse(null);
        if(existingDesignation != null){
            existingDesignation.setDesignationName(designation.getDesignationName());
            return designationRepository.save(existingDesignation);
        } else
            return existingDesignation;
    }

    public boolean deleteDesignation(int id){
        if(designationRepository.existsById(id))
        {
            designationRepository.deleteById(id);
            return true;
        }
        else
            return false;

    }}
