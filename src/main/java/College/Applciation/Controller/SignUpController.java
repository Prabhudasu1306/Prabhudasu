package College.Applciation.Controller;



import College.Applciation.Exception.ResourceNotFoundException;
import College.Applciation.Model.SignUp;
import College.Applciation.Repository.SignUpRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/signup")
public class SignUpController {

    private final SignUpRepository signUpRepository;

    @Autowired
    public SignUpController(SignUpRepository signUpRepository) {
        this.signUpRepository = signUpRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<SignUp> addSignUp(@RequestBody SignUp signUp) {
        SignUp createdSignup = signUpRepository.save(signUp);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSignup);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<SignUp>> getAllSignUp() {
        List<SignUp> signUps = signUpRepository.findAll();
        return ResponseEntity.ok(signUps);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SignUp> getSignUpById(@PathVariable("id") Long id) {
        SignUp signUp = signUpRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Item not found with id: %d", id)));
        return ResponseEntity.ok(signUp);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SignUp> updateSignUp(@PathVariable Long id, @RequestBody SignUp signUp) {
        SignUp existingSignUp = signUpRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Item not found with id: %d", id)));

        existingSignUp.setFirstName(signUp.getFirstName());
        existingSignUp.setLastName(signUp.getLastName());
        existingSignUp.setEmail(signUp.getEmail());
        existingSignUp.setPassword(signUp.getPassword());

        SignUp updatedSignUp = signUpRepository.save(existingSignUp);
        return ResponseEntity.ok(updatedSignUp);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSignUp(@PathVariable Long id) {
        SignUp signUp = signUpRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Item not found with id: %d", id)));

        signUpRepository.delete(signUp);
        return ResponseEntity.noContent().build();
    }
}
