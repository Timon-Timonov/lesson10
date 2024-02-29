package org.example.pojo;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "task")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;
}
