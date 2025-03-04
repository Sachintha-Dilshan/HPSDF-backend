package lk.gov.ps.HPSDF.archive.controllers;

import jakarta.validation.Valid;
import lk.gov.ps.HPSDF.archive.models.ArchiveSection;
import lk.gov.ps.HPSDF.archive.models.ArchiveSubject;
import lk.gov.ps.HPSDF.archive.services.ArchiveSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth/ar")
public class ArchiveSubjectController {
    @Autowired
    private ArchiveSubjectService archiveSubjectService;

    @PostMapping("/saveArchiveSubject")
    public ResponseEntity<ArchiveSubject> saveArchiveSubject(@RequestBody @Valid ArchiveSubject subject){
        ArchiveSubject _subject = archiveSubjectService.saveArchiveSubject(subject);
        try {
            return new ResponseEntity<>(_subject, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(_subject, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/updateArchiveSubject/{id}")
    public ResponseEntity<ArchiveSubject> updateArchiveSubject(@RequestBody ArchiveSubject archiveSubject, @PathVariable int id){
        ArchiveSubject _archiveSubject = archiveSubjectService.updateArchiveSubject(archiveSubject, id);
        if(_archiveSubject == null)
            return new ResponseEntity<>(_archiveSubject,HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(_archiveSubject, HttpStatus.OK);
    }

    @GetMapping("/getAllArchiveSubjects")
    public ResponseEntity<List<ArchiveSubject>> getAllArchiveSubjects(){
        return ResponseEntity.ok(archiveSubjectService.getAllArchiveSubjects());
    }

    @GetMapping("/subjectsBySectionId/{sectionId}")
    public ResponseEntity<?> getSubjectsBySectionId(@PathVariable String sectionId){
        try{
            List<ArchiveSubject> subjects=archiveSubjectService.getSubjectsBySectionId(sectionId);
            return ResponseEntity.ok(subjects);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error occured whe  retreiving data to the controller");
        }

    }

    @DeleteMapping("/deleteArchiveSubject/{id}")
    public String deleteArchiveSubject(@PathVariable int id){
        if(archiveSubjectService.deleteArchiveSubject(id))
            return "Subject has been deleted";
        else
            return "Subject is not found";
    }



}
