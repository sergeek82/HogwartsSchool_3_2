package ru.hogwarts.school.model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
public class Avatar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String filePath;
    private long fileSize;
    private String mediaType;
    //    @Lob  //If this annotation marked Type is required
//    @Type(type = "org.hibernate.type.BinaryType")//Postgres is not working without this certain type
    private byte[] data;
    @OneToOne
    private Student student;
}
