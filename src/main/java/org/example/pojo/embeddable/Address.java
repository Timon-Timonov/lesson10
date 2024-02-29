package org.example.pojo.embeddable;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString

@Embeddable
public class Address {

    @Column
    private String city;

    @Column
    private String street;
}
