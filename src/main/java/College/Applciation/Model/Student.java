package College.Applciation.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name ="Student_Details")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;
    private String studentName;
    private String caste;
    private double totalFee;
    private double dueFee;
    private int year;
    private int mobileNumber;
    private String course;
    private String hallTicketNumber;
    private String gender;
    private String email;

}
