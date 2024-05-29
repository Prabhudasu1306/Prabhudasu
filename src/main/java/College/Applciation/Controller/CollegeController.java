package College.Applciation.Controller;

import College.Applciation.Exception.ResourceNotFoundException;
import College.Applciation.Model.College;
import College.Applciation.Repository.CollegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/college")
@RestController
@CrossOrigin("*")
public class CollegeController {
    private CollegeRepository collegeRepository;
    @Autowired
    private CollegeController(CollegeRepository collegeRepository){
        this.collegeRepository = collegeRepository;
    }
   @PostMapping("/add")
   public ResponseEntity<College> addCollege(@RequestBody College college){
        College addCollege = collegeRepository.save(college);
        return ResponseEntity.status(HttpStatus.CREATED).body(addCollege);
}
@GetMapping("/getAll")
public List<College> getAll(){
        return collegeRepository.findAll();
}
@GetMapping("/{id}")
public ResponseEntity<College> getById(@PathVariable("id") Integer id){
    Optional<College> optionalCollege = collegeRepository.findById(id);
    College college = optionalCollege.orElseThrow(() -> new ResourceNotFoundException(String.format("Item not found with id: %d", id)));
    return ResponseEntity.ok(college);
}

}
