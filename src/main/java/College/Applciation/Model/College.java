package College.Applciation.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "College")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class College {
    @Id
    private int id;
    private String college_Name;
    private String address;
    private int phoneNumber;
    private String description;
}

