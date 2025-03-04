package lk.gov.ps.HPSDF.admin.hr.services;


import lk.gov.ps.HPSDF.admin.hr.models.ServiceSector;
import lk.gov.ps.HPSDF.admin.hr.repositories.ServiceSectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServiceSectorService {
    @Autowired
    private ServiceSectorRepository serviceSectorRepository;

    public ServiceSector saveServiceSector(ServiceSector serviceSector){
        return serviceSectorRepository.save(serviceSector);
    }

    public List<ServiceSector> getAllServiceSectors(){
        return serviceSectorRepository.findAll();
    }
    public ServiceSector getServiceSectorById(int id){
        return serviceSectorRepository.findById(id).orElse(null);
    }

    public ServiceSector getServiceSectorByName(String serviceSectorName){
        return serviceSectorRepository.findByServiceSectorName(serviceSectorName);
    }

    public ServiceSector updateServiceSector(ServiceSector serviceSector, int id){
        ServiceSector existingServiceSector = serviceSectorRepository.findById(id).orElse(null);
        if(existingServiceSector != null){
            existingServiceSector.setServiceSectorName(serviceSector.getServiceSectorName());
            return serviceSectorRepository.save(existingServiceSector);
        } else
            return existingServiceSector;
    }

    public boolean deleteServiceSector(int id){
        if(serviceSectorRepository.existsById(id))
        {
            serviceSectorRepository.deleteById(id);
            return true;
        }
        else
            return false;

    }}
