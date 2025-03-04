package lk.gov.ps.HPSDF.archive.services;

import lk.gov.ps.HPSDF.archive.models.ArchiveRack;
import lk.gov.ps.HPSDF.archive.repositories.ArchiveRackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ArchiveRackService {
    @Autowired
    private ArchiveRackRepository archiveRackRepository;



    public ArchiveRack saveRack(ArchiveRack archiveRack){
        return archiveRackRepository.save(archiveRack);
    }

    public List<ArchiveRack> getAllRacks(){
        return archiveRackRepository.findAll();
    }
    public boolean saveArchiveRack(ArchiveRack archiveRack){
        Optional<ArchiveRack> existingArchiveRack=archiveRackRepository.findById(archiveRack.getId());

        if(existingArchiveRack.isPresent()){
            return false ;
        }else{
            archiveRackRepository.save(archiveRack);
            return true;
        }
    }

    public ArchiveRack updateRack(ArchiveRack archiveRack, long id){
        ArchiveRack existingArchiveRack = archiveRackRepository.findById(id).orElse(null);
        if(existingArchiveRack != null)
        {
            existingArchiveRack.setRackNumber(archiveRack.getRackNumber());
            return archiveRackRepository.save(existingArchiveRack);
        }
        else
            return existingArchiveRack;

    }
    public boolean deleteRack(long id){
        if(archiveRackRepository.existsById(id))
        {
            archiveRackRepository.deleteById(id);
            return true;
        }
        else
            return false;

    }
}
