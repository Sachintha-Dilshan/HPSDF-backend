package lk.gov.ps.HPSDF.admin.hr.services;

import lk.gov.ps.HPSDF.admin.hr.models.Section;
import lk.gov.ps.HPSDF.admin.hr.repositories.SectionRepository;
import lk.gov.ps.HPSDF.admin.leave.models.LeaveType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionService {
    @Autowired
    private SectionRepository sectionRepository;

    public Section saveSection(Section section){
        return sectionRepository.save(section);
    }

    public List<Section> getAllSections(){
        return sectionRepository.findAll();
    }
    public Section getSectionById(int id){
        return sectionRepository.findById(id).orElse(null);
    }

    public Section getSectionByName(String sectionName){
        return sectionRepository.findBySectionName(sectionName);
    }

    public Section updateSection(Section section, int id){
        Section existingSection = sectionRepository.findById(id).orElse(null);
        if(existingSection != null){
            existingSection.setSectionName(section.getSectionName());
            return sectionRepository.save(existingSection);
        } else
            return existingSection;
    }

    public boolean deleteSection(int id){
        if(sectionRepository.existsById(id))
        {
            sectionRepository.deleteById(id);
            return true;
        }
        else
            return false;

    }
}
