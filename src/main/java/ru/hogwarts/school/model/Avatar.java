package ru.hogwarts.school.model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Avatar {
    @Id
    @EqualsAndHashCode.Include
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
