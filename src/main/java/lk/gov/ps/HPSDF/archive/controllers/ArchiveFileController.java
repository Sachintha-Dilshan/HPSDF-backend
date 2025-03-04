package lk.gov.ps.HPSDF.archive.controllers;

import jakarta.validation.Valid;
import lk.gov.ps.HPSDF.archive.models.ArchiveFile;
import lk.gov.ps.HPSDF.archive.models.ArchiveSection;
import lk.gov.ps.HPSDF.archive.models.ArchiveSubject;
import lk.gov.ps.HPSDF.archive.services.ArchiveFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth/ar")
public class ArchiveFileController {
    @Autowired
    private ArchiveFileService archiveFileService;

    @PostMapping("/saveFile")
    public ResponseEntity<ArchiveFile> saveArchiveFile(@RequestBody @Valid ArchiveFile archiveFile){

        ArchiveFile _archiveFile = archiveFileService.saveArchiveFile(archiveFile);
        try {
            return new ResponseEntity<>(_archiveFile, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(_archiveFile, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateFile/{id}")
    public ResponseEntity<ArchiveFile> updateFile(@RequestBody  ArchiveFile archiveFile, @PathVariable long id){
        ArchiveFile _archiveFile = archiveFileService.updateFile(archiveFile, id);
        if(_archiveFile == null)
            return new ResponseEntity<>(_archiveFile,HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(_archiveFile, HttpStatus.OK);
    }

    @GetMapping("/getFiles")
    public ResponseEntity<List<ArchiveFile>> getFiles(){
        return ResponseEntity.ok(archiveFileService.getFiles());
    }

    @PostMapping("/getFileCountBySection")
    public ResponseEntity<Integer> getFileCountBySection(@RequestBody ArchiveSection section)
    {
        return ResponseEntity.ok(archiveFileService.getArchiveFileCountBySection(section));
    }
    @GetMapping("/getFileById/{fileId}")
    public ResponseEntity<ArchiveFile> getFileById(@PathVariable long fileId){
            ArchiveFile archiveFile=archiveFileService.getFileById(fileId);
        if(archiveFile == null)
            return new ResponseEntity<>(archiveFile,HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(archiveFile, HttpStatus.OK);
    }

    @PostMapping("/searchFiles")
    public ResponseEntity<List<ArchiveFile>> searchFiles(
            @RequestParam(required = false) String fileNumber,
            @RequestParam(required = false) String fileName,
            @RequestParam(required = false) String year,
            @RequestParam(required = false) String archiveSectionId,
            @RequestParam(required = false) Integer archiveSubjectId
    ) {
        System.out.println(fileNumber + fileName + year + archiveSectionId + archiveSubjectId);
        ArchiveFile archiveFile = new ArchiveFile();
        archiveFile.setFileNumber(fileNumber);
        archiveFile.setFileName(fileName);
        archiveFile.setYear(year);
        if (archiveSectionId != null) {
            ArchiveSection archiveSection = new ArchiveSection();
            archiveSection.setId(archiveSectionId);
            archiveFile.setArchiveSection(archiveSection);
            System.out.println(archiveSection);
        }
        if (archiveSubjectId != null) {
            ArchiveSubject archiveSubject = new ArchiveSubject();
            archiveSubject.setId(archiveSubjectId);
            archiveFile.setArchiveSubject(archiveSubject);
            System.out.println(archiveSubject);
        }
        //System.out.println(archiveFile);

        return ResponseEntity.ok(archiveFileService.searchFiles(archiveFile));
    }


    @DeleteMapping("/deleteFile/{id}")
    public ResponseEntity<String> deleteFile(@PathVariable long id){
        if(archiveFileService.deleteFile(id))
            return new ResponseEntity<>("File No : " + id + " has been deleted", HttpStatus.OK);
        else
            return new ResponseEntity<>("File No : " + id + " is not found",HttpStatus.NOT_FOUND);
    }
//
//    @GetMapping("/recentFiles/{sectionId}")
//    public ResponseEntity<List<ArchiveFile>> getRecentFiles(@PathVariable String sectionId){
//        return ResponseEntity.ok(archiveFileService.getRecentFiles(sectionId));
//    }
//    @GetMapping("/checkedOutFiles")
//    public ResponseEntity<List<ArchiveGetCheckedOutFileDTO>> getCheckedOutFiles(
//            @RequestParam(required = false) String fileNumber,
//            @RequestParam(required = false) String fileName,
//            @RequestParam(required = false) String year,
//            @RequestParam(required = false) String sectionName,
//            @RequestParam(required = false) String subjectName,
//            @RequestParam(required = false) String employeeNIC
//    )
//    {
//        System.out.println("hit the controller.");
//        return ResponseEntity.ok(archiveFileService
//                .getCheckedOutFiles(fileNumber,fileName,year,sectionName,subjectName,employeeNIC));
//
//    }
//
//    @GetMapping("/checkedOutFilesCount")
//    public ResponseEntity<Integer> getFileCountsCheckedOut(){
//        Integer count=archiveFileService.getFileCountsCheckedOut();
//        return ResponseEntity.ok(count);
//    }



//    @DeleteMapping(path="file/{sectionId}/{fileId}")
//    public ResponseEntity<String> deleteArchiveFile(@PathVariable("sectionId") String sectionId,
//                                                    @PathVariable("fileId") Long fileId)
//    {
//
//        try{
//            archiveFileService.deleteArchiveFile(sectionId,fileId);
//            return ResponseEntity.ok("file Deleted successfully.");
//        }catch(Exception ex) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
//        }
//
//    }

//    @PutMapping("checkInFile/{fileId}")
//    public ResponseEntity<String> checkInArchiveFile(@PathVariable("fileId") Long fileId){
//        archiveFileService.checkInArchiveFile(fileId);
//        return ResponseEntity.ok("File has successfully Checked In");
//    }
//    @PutMapping("checkOutFile/{fileId}/{employeeId}")
//    public ResponseEntity<String> checkOutArchiveFile(@PathVariable("fileId") Long fileId,
//                                                        @PathVariable("employeeId") String employeeId)
//    {
//        try {
//            archiveFileService.checkOutArchiveFile(fileId, employeeId);
//            return ResponseEntity.ok("File with file id " + fileId + "is CheckedOut.");
//        }catch (EntityNotFoundException e){
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//        }catch (Exception e){
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
//        }
//
//
//    }
}
