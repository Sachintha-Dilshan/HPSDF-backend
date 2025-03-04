package lk.gov.ps.HPSDF.admin.hr.controllers;

import jakarta.validation.Valid;
import lk.gov.ps.HPSDF.admin.hr.models.Section;
import lk.gov.ps.HPSDF.admin.hr.services.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth/hr")
public class SectionController {
    @Autowired
    private SectionService sectionService;
    @PostMapping("/section")
    public ResponseEntity<Section> saveSection(@RequestBody @Valid Section section){
        Section _section = sectionService.saveSection(section);
        try {
            return new ResponseEntity<>(_section, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(_section, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/section/{id}")
    public ResponseEntity<Section> updateSection(@RequestBody @Valid  Section section, @PathVariable int id){
        Section _section = sectionService.updateSection(section, id);
        if(_section == null)
            return new ResponseEntity<>(_section,HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(_section, HttpStatus.OK);
    }

    @GetMapping("/section")
    public ResponseEntity<List<Section>> getAllSections(){
        return ResponseEntity.ok(sectionService.getAllSections());
    }
    @GetMapping("/sectionById/{id}")
    public ResponseEntity<Section>  getSectionById(@PathVariable int id){
        Section section = sectionService.getSectionById(id);
        if( section == null)
            return new ResponseEntity<>(section,HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(section,HttpStatus.OK);
    }

    @GetMapping("/sectionByName/{sectionName}")
    public ResponseEntity<Section> getSectionByName(@PathVariable String sectionName){
        Section section = sectionService.getSectionByName(sectionName);
        if( section == null)
            return new ResponseEntity<>(section,HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(section,HttpStatus.OK);
    }

    @DeleteMapping("/section/{id}")
    public String deleteSection(@PathVariable int id){
        if(sectionService.deleteSection(id))
            return "Section has been deleted";
        else
            return "Section is not found";
    }
}
