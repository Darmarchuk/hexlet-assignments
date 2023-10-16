package exercise.model;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    @Lob
    private String description;

    private String path;

    public List<Long> genPath(){
        List<Long> idList;
        if(this.path!=null) {
            idList = Arrays.stream(this.path.split("\\.")).map(Long::valueOf).collect(Collectors.toList());
            return idList;
        }
        else return new ArrayList<>();
    }

}
