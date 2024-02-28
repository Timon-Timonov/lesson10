package org.example.pojo;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.pojo.embeddable.Address;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity()
//@Table(name = "home_task")
@DiscriminatorValue("H")
public class HomeTask extends Task {

    private static final long serialVersionUID = 1L;

    @Column(name = "start_date")
    private Timestamp startDate;

    @Column(name = "end_date")
    private Timestamp endDate;

    @Embedded
    private Address address;
}
