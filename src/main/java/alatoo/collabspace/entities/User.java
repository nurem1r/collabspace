package alatoo.collabspace.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    private String university;
    private String course;
    private int points = 0;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_skills",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private Set<Skill> skills = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "leader", fetch = FetchType.LAZY)
    private Set<Project> ownedProjects = new HashSet<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "members", fetch = FetchType.LAZY)
    private Set<Project> joinedProjects = new HashSet<>();
}
