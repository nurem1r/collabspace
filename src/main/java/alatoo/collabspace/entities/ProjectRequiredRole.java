package alatoo.collabspace.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "project_required_roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectRequiredRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "project_id")
    private Project project;

    @Column(name = "role_name")
    private String roleName;

    private Integer slots;
}