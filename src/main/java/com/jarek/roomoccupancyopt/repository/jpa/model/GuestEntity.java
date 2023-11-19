package com.jarek.roomoccupancyopt.repository.jpa.model;

import com.jarek.roomoccupancyopt.model.Guest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Entity
@Table(name = "guest")
public class GuestEntity {

    @Id
    @Column(name ="id", columnDefinition = "VARCHAR(36)")
    private String id;

    @Column(name ="budget", nullable = false)
    private int budget;

    public Guest toGuest() {
        return  new Guest(id, budget);
    }
}
