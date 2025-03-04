package lk.gov.ps.HPSDF.archive.services;

import lk.gov.ps.HPSDF.archive.models.ArchiveSubject;
import lk.gov.ps.HPSDF.archive.repositories.ArchiveSubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Component
public class ArchiveSubjectService {
    @Autowired
    private ArchiveSubjectRepository archiveSubjectRepository;

    public ArchiveSubject saveArchiveSubject(ArchiveSubject subject){
        return archiveSubjectRepository.save(subject);
    }

    public ArchiveSubject updateArchiveSubject(ArchiveSubject archiveSubject, int id){
        ArchiveSubject existingArchiveSubject = archiveSubjectRepository.findById(id).orElse(null);
        if(existingArchiveSubject != null)
        {
            existingArchiveSubject.setSubjectName(archiveSubject.getSubjectName());
            existingArchiveSubject.setArchiveSection(archiveSubject.getArchiveSection());
            return archiveSubjectRepository.save(existingArchiveSubject);
        }
        else
            return existingArchiveSubject;

    }
    public List<ArchiveSubject> getAllArchiveSubjects(){
        try{
            Sort sort = Sort.by(Sort.Direction.DESC, "id"); // Sort by ID in descending order
            return archiveSubjectRepository.findAll(sort);
        } catch(Exception e){
            throw new RuntimeException("Error occurred while fetching subjects into services.", e);
        }
    }
    public List<ArchiveSubject> getSubjectsBySectionId(String sectionId){
        try{
            return archiveSubjectRepository.findByArchiveSectionId(sectionId);
        }catch(Exception e){
            throw new RuntimeException("Error occured while fetching subjects into services.",e);
        }

    }

    public boolean deleteArchiveSubject(int id){
        if(archiveSubjectRepository.existsById(id))
        {
            archiveSubjectRepository.deleteById(id);
            return true;
        }
        else
            return false;

    }

}
