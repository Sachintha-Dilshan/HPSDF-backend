package lk.gov.ps.HPSDF.admin.hr.services;

import lk.gov.ps.HPSDF.admin.hr.models.Subject;
import lk.gov.ps.HPSDF.admin.hr.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    public Subject saveSubject(Subject subject){
        return subjectRepository.save(subject);
    }

    public List<Subject> getAllSubjects(){
        return subjectRepository.findAll();
    }
    public Subject getSubjectById(String id){
        return subjectRepository.findById(id).orElse(null);
    }

    public List<Subject> getSubjectBySectionId(int id){
        return subjectRepository.findBySectionId(id);
    }

    public Subject updateSubject(Subject subject, String id){
        Subject existingSubject = subjectRepository.findById(id).orElse(null);
        if(existingSubject != null){
            existingSubject.setSubjectName(subject.getSubjectName());
            existingSubject.setSectionId(subject.getSectionId());
            return subjectRepository.save(existingSubject);
        } else
            return existingSubject;
    }

    public boolean deleteSubject(String id){
        if(subjectRepository.existsById(id))
        {
            subjectRepository.deleteById(id);
            return true;
        }
        else
            return false;

    }
}
