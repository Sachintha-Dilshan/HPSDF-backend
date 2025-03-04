package lk.gov.ps.HPSDF.admin.hr.controllers;

import jakarta.validation.Valid;
import lk.gov.ps.HPSDF.admin.hr.models.Designation;
import lk.gov.ps.HPSDF.admin.hr.services.DesignationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth/hr")
public class DesignationController {
    @Autowired
    private DesignationService designationService;
    @PostMapping("/designation")
    public ResponseEntity<Designation> saveDesignation(@RequestBody @Valid Designation designation){
        Designation _designation = designationService.saveDesignation(designation);
        try {
            return new ResponseEntity<>(_designation, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(_designation, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/designation/{id}")
    public ResponseEntity<Designation> updateDesignation(@RequestBody @Valid  Designation designation, @PathVariable int id){
        Designation _designation = designationService.updateDesignation(designation, id);
        if(_designation == null)
            return new ResponseEntity<>(_designation,HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(_designation, HttpStatus.OK);
    }

    @GetMapping("/designation")
    public ResponseEntity<List<Designation>> getAllDesignations(){
        return ResponseEntity.ok(designationService.getAllDesignations());
    }
    @GetMapping("/designationById/{id}")
    public ResponseEntity<Designation>  getDesignationById(@PathVariable int id){
        Designation designation = designationService.getDesignationById(id);
        if( designation == null)
            return new ResponseEntity<>(designation,HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(designation,HttpStatus.OK);
    }

    @GetMapping("/designationByName/{designationName}")
    public ResponseEntity<Designation> getDesignationByName(@PathVariable String designationName){
        Designation designation = designationService.getDesignationByName(designationName);
        if( designation == null)
            return new ResponseEntity<>(designation,HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(designation,HttpStatus.OK);
    }

    @DeleteMapping("/designation/{id}")
    public String deleteDesignation(@PathVariable int id){
        if(designationService.deleteDesignation(id))
            return "Designation has been deleted";
        else
            return "Designation is not found";
    }
}
