package org.example.pojo;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.pojo.embeddable.Address;
import org.hibernate.annotations.CreationTimestamp;

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
@DiscriminatorValue("H")
public class HomeTask extends Task {

    public static final int PRECISSION_OF_TIMESTAMP = 1000;
    private static final long serialVersionUID = 1L;

    @Column(name = "start_date")
    @CreationTimestamp
    private Timestamp startDate;

    @Column(name = "end_date")
    private Timestamp endDate;

    @Embedded
    private Address address;

    public void setStartDate(Timestamp startDate) {

        int nan = startDate.getNanos();
        startDate.setNanos(nan - nan % PRECISSION_OF_TIMESTAMP);
        this.startDate = startDate;
    }

    public void setEndDate(Timestamp endDate) {

        int nan = endDate.getNanos();
        endDate.setNanos(nan - nan % PRECISSION_OF_TIMESTAMP);
        this.endDate = endDate;
    }
}
