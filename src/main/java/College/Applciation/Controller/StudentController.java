package College.Applciation.Controller;

import College.Applciation.Exception.ResourceNotFoundException;
import College.Applciation.Model.Student;
import College.Applciation.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("api/Student")
public class StudentController {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @PostMapping("add")
    public ResponseEntity<Student> addStudent(@RequestBody Student student){
        Student savedStudent = studentRepository.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Student not found with id: %d", id)));
        return ResponseEntity.ok(student);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudentById(@PathVariable Long id, @RequestBody Student student) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Student not found with id: %d", id)));

        // Update the existing student object with the new values
        existingStudent.setStudentName(student.getStudentName());
        existingStudent.setCaste(student.getCaste());
        existingStudent.setTotalFee(student.getTotalFee());
        existingStudent.setDueFee(student.getDueFee());
        existingStudent.setYear(student.getYear());
        existingStudent.setMobileNumber(student.getMobileNumber());
        existingStudent.setCourse(student.getCourse());
        existingStudent.setHallTicketNumber(student.getHallTicketNumber());
        existingStudent.setGender(student.getGender());
        existingStudent.setEmail(student.getEmail());

        // Save the updated student object
        Student updatedStudent = studentRepository.save(existingStudent);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        if (!studentRepository.existsById(id)) {
            throw new ResourceNotFoundException(String.format("Student not found with id: %d", id));
        }

        studentRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
