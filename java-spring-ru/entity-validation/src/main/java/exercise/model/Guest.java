package exercise.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.validation.constraints.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;




@Entity
@Table(name = "guests")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Guest {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    @NotBlank
    private String name;

    @Email
    private String email;

    @Pattern(regexp = "^\\+[\\d]{11,13}$")
    private String phoneNumber;

    @Pattern(regexp = "[\\d]{4}",message = "card number length must be 4 digits")
    private String clubCard;

    @FutureOrPresent(message = "card date not valid")
    private Date cardValidUntil;

    @CreatedDate
    private Date createdAt;
}
