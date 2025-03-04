package lk.gov.ps.HPSDF.archive.services;

import lk.gov.ps.HPSDF.archive.dto.ArchiveSectionDTO;
import lk.gov.ps.HPSDF.archive.models.ArchiveSection;
import lk.gov.ps.HPSDF.archive.models.ArchiveSubject;
import lk.gov.ps.HPSDF.archive.repositories.ArchiveFileRepository;
import lk.gov.ps.HPSDF.archive.repositories.ArchiveSectionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ArchiveSectionService {
    @Autowired
    private ArchiveSectionRepository archiveSectionRepository;
    @Autowired
    private ArchiveFileRepository archiveFileRepository;
    @Autowired
    private ModelMapper modelMapper;



    public ArchiveSection saveArchiveSection(ArchiveSection section){
        return archiveSectionRepository.save(section);
    }

    public List<ArchiveSection> getAllArchiveSections(){
        return archiveSectionRepository.findAll();
    }

    public ArchiveSection getArchiveSectionById(String id){
        return archiveSectionRepository.findById(id).orElse(null);
    }


    public ArchiveSectionDTO getSectionById(String id){
        try{
            ArchiveSection section=archiveSectionRepository.findById(id).orElse(null);
            return modelMapper.map(section,ArchiveSectionDTO.class);
        }catch(Exception e){
            throw new RuntimeException("Error occurred while retrieving archive sections", e);
        }

    }

    public boolean deleteArchiveSection(String archiveSectionId) {
        boolean doesExists= archiveSectionRepository.existsById(archiveSectionId);
        if(!doesExists){
            return false;
        }else{
            archiveSectionRepository.deleteById(archiveSectionId);
            return true;
        }
    }
}
