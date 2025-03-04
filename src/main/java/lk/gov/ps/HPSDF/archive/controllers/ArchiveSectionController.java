package lk.gov.ps.HPSDF.archive.controllers;

import jakarta.validation.Valid;
import lk.gov.ps.HPSDF.archive.dto.ArchiveSectionDTO;
import lk.gov.ps.HPSDF.archive.models.ArchiveSection;
import lk.gov.ps.HPSDF.archive.services.ArchiveSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth/ar")
public class ArchiveSectionController {
    @Autowired
    private ArchiveSectionService archiveSectionService;

    @PostMapping("/saveArchiveSection")
    public ResponseEntity<ArchiveSection> saveArchiveSection(@RequestBody @Valid ArchiveSection section){
        ArchiveSection _section = archiveSectionService.saveArchiveSection(section);
        try {
            return new ResponseEntity<>(_section, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(_section, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllArchiveSections")
    public ResponseEntity<List<ArchiveSection>> getAllArchiveSections(){
        return ResponseEntity.ok(archiveSectionService.getAllArchiveSections());
    }

    @GetMapping("/getArchiveSectionById/{id}")
    public ResponseEntity<ArchiveSection>  getArchiveSectionById(@PathVariable String id){
        ArchiveSection section = archiveSectionService.getArchiveSectionById(id);
        if( section == null)
            return new ResponseEntity<>(section,HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(section,HttpStatus.OK);
    }


    @GetMapping("/sectionById/{sectionId}")
    public ResponseEntity<?> getSection(@PathVariable String sectionId) {

        try{
            ArchiveSectionDTO section = archiveSectionService.getSectionById(sectionId);
            return ResponseEntity.ok(section);
        }
        catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("NO ..Section with this id");
        }

    }



    @DeleteMapping(path = "{sectionId}")
    public boolean deleteArchiveSection(@PathVariable("sectionId") String sectionId) {
        return archiveSectionService.deleteArchiveSection(sectionId);
    }

}