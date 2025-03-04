package lk.gov.ps.HPSDF.admin.hr.controllers;

import jakarta.validation.Valid;
import lk.gov.ps.HPSDF.admin.hr.models.Section;
import lk.gov.ps.HPSDF.admin.hr.models.Subject;
import lk.gov.ps.HPSDF.admin.hr.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth/hr")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;
    @PostMapping("/subject")
    public ResponseEntity<Subject> saveSubject(@RequestBody @Valid Subject subject){
        Subject _subject = subjectService.saveSubject(subject);
        try {
            return new ResponseEntity<>(_subject, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(_subject, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/subject/{id}")
    public ResponseEntity<Subject> updateSubject(@RequestBody @Valid  Subject subject, @PathVariable String id){
        Subject _subject = subjectService.updateSubject(subject, id);
        if(_subject == null)
            return new ResponseEntity<>(_subject,HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(_subject, HttpStatus.OK);
    }

    @GetMapping("/subject")
    public ResponseEntity<List<Subject>> getAllSubjects(){
        return ResponseEntity.ok(subjectService.getAllSubjects());
    }
    @GetMapping("/subjectById/{id}")
    public ResponseEntity<Subject>  getSubjectById(@PathVariable String id){
        Subject subject = subjectService.getSubjectById(id);
        if( subject == null)
            return new ResponseEntity<>(subject,HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(subject,HttpStatus.OK);
    }

    @GetMapping("/subjectBySectionId/{sectionId}")
    public ResponseEntity<List<Subject>> getSubjectBySectionId(@PathVariable int sectionId){
        return ResponseEntity.ok(subjectService.getSubjectBySectionId(sectionId));
    }


    @DeleteMapping("/subject/{id}")
    public String deleteSubject(@PathVariable String id){
        if(subjectService.deleteSubject(id))
            return "Section has been deleted";
        else
            return "Section is not found";
    }
}
