package lk.gov.ps.HPSDF.admin.hr.controllers;

import jakarta.validation.Valid;
import lk.gov.ps.HPSDF.admin.hr.models.ServiceSector;
import lk.gov.ps.HPSDF.admin.hr.services.ServiceSectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth/hr")
public class ServiceSectorController {
    @Autowired
    private ServiceSectorService serviceSectorService;
    @PostMapping("/serviceSector")
    public ResponseEntity<ServiceSector> saveServiceSector(@RequestBody @Valid ServiceSector serviceSector){
        ServiceSector _serviceSector = serviceSectorService.saveServiceSector(serviceSector);
        try {
            return new ResponseEntity<>(_serviceSector, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(_serviceSector, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/serviceSector/{id}")
    public ResponseEntity<ServiceSector> updateServiceSector(@RequestBody @Valid  ServiceSector serviceSector, @PathVariable int id){
        ServiceSector _serviceSector = serviceSectorService.updateServiceSector(serviceSector, id);
        if(_serviceSector == null)
            return new ResponseEntity<>(_serviceSector,HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(_serviceSector, HttpStatus.OK);
    }

    @GetMapping("/serviceSector")
    public ResponseEntity<List<ServiceSector>> getAllServiceSectors(){
        return ResponseEntity.ok(serviceSectorService.getAllServiceSectors());
    }
    @GetMapping("/serviceSectorById/{id}")
    public ResponseEntity<ServiceSector>  getServiceSectorById(@PathVariable int id){
        ServiceSector serviceSector = serviceSectorService.getServiceSectorById(id);
        if( serviceSector == null)
            return new ResponseEntity<>(serviceSector,HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(serviceSector,HttpStatus.OK);
    }

    @GetMapping("/serviceSectorByName/{serviceSectorName}")
    public ResponseEntity<ServiceSector> getServiceSectorByName(@PathVariable String serviceSectorName){
        ServiceSector serviceSector = serviceSectorService.getServiceSectorByName(serviceSectorName);
        if( serviceSector == null)
            return new ResponseEntity<>(serviceSector,HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(serviceSector,HttpStatus.OK);
    }

    @DeleteMapping("/serviceSector/{id}")
    public String deleteServiceSector(@PathVariable int id){
        if(serviceSectorService.deleteServiceSector(id))
            return "Service sector has been deleted";
        else
            return "Service sector is not found";
    }
}
