package lk.gov.ps.HPSDF.archive.controllers;

import jakarta.validation.Valid;
import lk.gov.ps.HPSDF.archive.models.ArchiveRack;
import lk.gov.ps.HPSDF.archive.services.ArchiveRackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth/ar")
public class ArchiveRackController {
    @Autowired
    private ArchiveRackService archiveRackService;




    @PostMapping("/saveRack")
    public ResponseEntity<ArchiveRack> saveRack(@RequestBody @Valid ArchiveRack archiveRack){
        ArchiveRack _archiveRack = archiveRackService.saveRack(archiveRack);
        try {
            return new ResponseEntity<>(_archiveRack, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(_archiveRack, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateRack/{id}")
    public ResponseEntity<ArchiveRack> updateRack(@RequestBody ArchiveRack archiveRack, @PathVariable long id){
        ArchiveRack _archiveRack = archiveRackService.updateRack(archiveRack, id);
        if(_archiveRack == null)
            return new ResponseEntity<>(_archiveRack,HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(_archiveRack, HttpStatus.OK);
    }

    @GetMapping("/getAllRacks")
    public List<ArchiveRack> getAllRacks(){
        return archiveRackService.getAllRacks();
    }

    @DeleteMapping("/deleteRack/{id}")
    public String deleteRack(@PathVariable long id){
        if(archiveRackService.deleteRack(id))
            return "Rack has been deleted";
        else
            return "Rack is not found";
    }

}
