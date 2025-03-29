package ru.nsu.peretyatko.model.militaries;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

import static jakarta.persistence.CascadeType.ALL;


@Entity
@Getter
@Setter
@Table(name = "militaries")
public class Military {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "birth_date", nullable = false)
    private Date birthDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rank_id")
    private Rank rank;

    @OneToMany(mappedBy = "military", cascade = ALL)
    private Set<MilitaryProperty> properties;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "militaries_specialties",
            joinColumns = @JoinColumn(name = "military_id"),
            inverseJoinColumns = @JoinColumn(name = "specialty_id"))
    private Set<Specialty> specialties;

}