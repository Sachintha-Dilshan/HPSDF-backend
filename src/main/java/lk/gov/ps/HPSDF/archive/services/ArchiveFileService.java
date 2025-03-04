package lk.gov.ps.HPSDF.archive.services;

import lk.gov.ps.HPSDF.archive.models.ArchiveFile;
import lk.gov.ps.HPSDF.archive.models.ArchiveSection;
import lk.gov.ps.HPSDF.archive.repositories.ArchiveFileRepository;
import lk.gov.ps.HPSDF.admin.hr.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArchiveFileService {
    @Autowired
    private ArchiveFileRepository archiveFileRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ModelMapper modelMapper;


    public ArchiveFile saveArchiveFile(ArchiveFile archiveFile){
        return archiveFileRepository.save(archiveFile);
    }

    public ArchiveFile updateFile(ArchiveFile archiveFile, long id){
        ArchiveFile existingFile = archiveFileRepository.findById(id).orElse(null);
        if(existingFile != null){
            if(archiveFile.getFileNumber() != null)
                existingFile.setFileNumber(archiveFile.getFileNumber());
            if(archiveFile.getFileName() != null)
                existingFile.setFileName(archiveFile.getFileName());
            if(archiveFile.getArchiveSection() != null)
                existingFile.setArchiveSection(archiveFile.getArchiveSection());
            if(archiveFile.getArchiveSubject() != null)
                existingFile.setArchiveSubject(archiveFile.getArchiveSubject());
            if(archiveFile.getYear() != null)
                existingFile.setYear(archiveFile.getYear());
            if(archiveFile.getRack() != null)
                existingFile.setRack(archiveFile.getRack());
            if(archiveFile.getBoxNumber() != 0)
                existingFile.setBoxNumber(archiveFile.getBoxNumber());
            if(archiveFile.getFileIndex() != 0)
                existingFile.setFileIndex(archiveFile.getFileIndex());
            existingFile.setCheckedOut(archiveFile.isCheckedOut());
            return archiveFileRepository.save(existingFile);
        } else
            return existingFile;
    }
    public List<ArchiveFile> getFiles(){
        // Create a sort object to specify descending order by ID (or any other field)
        Sort sort = Sort.by(Sort.Direction.DESC, "id"); // Replace "id" with your desired field

        // Create a pageable object with sorting and limit to 10 records
        Pageable pageable = PageRequest.of(0, 10, sort);

        // Fetch the top 10 records in descending order from the repository
        return archiveFileRepository.findAll(pageable).getContent();
    }

    public List<ArchiveFile> searchFiles(ArchiveFile archiveFile) {
        Sort sort = Sort.by(Sort.Order.asc("id"));
        Pageable pageable = PageRequest.of(0, 100, sort);

        return archiveFileRepository.findByAttributes(
                archiveFile.getFileNumber(),
                archiveFile.getFileName(),
                archiveFile.getYear(),
                archiveFile.getArchiveSection(),
                archiveFile.getArchiveSubject(),
                pageable
        );
    }

    public int getArchiveFileCountBySection(ArchiveSection section)
    {
        return archiveFileRepository.countByArchiveSection(section);
    }

    public ArchiveFile getFileById(long id){
        return archiveFileRepository.findById(id).orElse(null);
    }

    public boolean deleteFile(long id){
        if(archiveFileRepository.existsById(id))
        {
            archiveFileRepository.deleteById(id);
            return true;
        }
        else
            return false;

    }
//    public List<ArchiveFile> getRecentFiles(String sectionId){
//        Pageable pageable=  PageRequest.of(0,5, Sort.by("id").descending());
//        return archiveFileRepository.findTop5ByOrderByIdDesc(sectionId,pageable);
//
//    }
//    public List<ArchiveGetCheckedOutFileDTO> getCheckedOutFiles(String fileNumber, String fileName,String year, String sectionName, String subjectName, String employeeNIC){
//        List<ArchiveFile> files= archiveFileRepository
//                .findByDynamicParameters(
//                        true, fileNumber, fileName,year,sectionName,subjectName, employeeNIC);
//        List<ArchiveGetCheckedOutFileDTO> newfiles=files.stream()
//                .map(this::convertToDTO)
//                .collect(Collectors.toList());
//        for (ArchiveGetCheckedOutFileDTO file : newfiles) {
//            System.out.println(file); // Assuming you've overridden toString() in ArchiveGetCheckedOutFileDTO
//        }
//        return newfiles;
//    }
//    private ArchiveGetCheckedOutFileDTO convertToDTO(ArchiveFile file){
//        return modelMapper.map(file,ArchiveGetCheckedOutFileDTO.class);
//    }

//    public int getFileCountsCheckedOut(){
//        return archiveFileRepository.getFileCountsCheckedOut();
//    }

//
//    public void deleteArchiveFile(String sectionId,Long archiveFileId) {
//        Optional<ArchiveFile> file= archiveFileRepository.findByIdAndArchiveSection_Id(archiveFileId,sectionId);
//        if(file.isPresent()){
//            archiveFileRepository.deleteById(archiveFileId);
//        }else{
//            throw new EntityNotFoundException("File with the id "+archiveFileId+" is not available in this section");
//        }
//
//    }
//
//    @Transactional
//    public void updateArchiveFile(String sectionId,Long archiveFileId,ArchiveSaveFileDTO fileDTO) {
//
//        ArchiveFile existingFile=archiveFileRepository.findByIdAndArchiveSection_Id(archiveFileId,sectionId)
//                .orElse(null);
//        if(existingFile==null){
//            throw new EntityNotFoundException("File with the id "+archiveFileId+ " is not available in this section");
//        }else{
//            Optional<ArchiveFile> archiveFile1=archiveFileRepository.findByFileNumber(fileDTO.getFileNumber());
//            Optional<ArchiveFile> archiveFile2=archiveFileRepository.findByFileName(fileDTO.getFileName());
//            if(archiveFile1.isPresent() && archiveFile1.isPresent() && existingFile.getId()!=archiveFile1.get().getId()){
//                throw new DataIntegrityViolationException("Already exist a file with FileNumber "+fileDTO.getFileNumber());
//            }
//            if(archiveFile2.isPresent() && archiveFile2.isPresent() && existingFile.getId()!=archiveFile2.get().getId()){
//                throw new DataIntegrityViolationException("Already exist a file with FileName "+fileDTO.getFileName());
//            }
//            // Copy non-null properties from updatedFile to existingFile
//            ArchiveFile archiveFile=modelMapper.map(fileDTO,ArchiveFile.class);
//            System.out.println(archiveFile);
//            BeanUtils.copyProperties(archiveFile, existingFile, "id");
//            // Save the updated file
//            archiveFileRepository.save(existingFile);
//        }
//    }
//
//    public void checkInArchiveFile(Long fileId) {
//        ArchiveFile existingFile=archiveFileRepository.findById(fileId)
//                .orElse(null);
//        if(existingFile!=null){
//            existingFile.setDateTime(null);
//            existingFile.setCheckedOut(false);
//            archiveFileRepository.save(existingFile);
//
//        }
//    }
//
//
//    public void checkOutArchiveFile(Long fileId, String employeeId) {
//        ArchiveFile existingFile=archiveFileRepository.findById(fileId)
//                .orElse(null);
//        if(existingFile==null){
//            throw new EntityNotFoundException("File with file id " +fileId +" is not found");
//        }else{
//           Optional<Employee> employee =employeeRepository.findById(employeeId);
//            System.out.println("hoooooooooooooooooo");
//            if(!employee.isPresent()){
//                throw new EntityNotFoundException("Employee with id "+employeeId+" is not found.");
//            }else{
//                existingFile.setDateTime(LocalDateTime.now());
//                existingFile.setCheckedOut(true);
//                archiveFileRepository.save(existingFile);
//
//            }
//
//        }
//    }
}
