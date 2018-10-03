package edu.otus.hw14.entity.mongo;

import edu.otus.hw14.entity.jpa.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Nik Bespalov on 03/10/2018.
 *
 * @author Nik Bespalov.
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class PersonMongo {
    @Id
    private String id;
    private String name;
    private int age;
    private Address address;

    @Override
    public String toString() {
        return "PersonMongo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address=" + address +
                '}';
    }
}
