package com.example.radio_active_mushroom.models;

import com.example.radio_active_mushroom.enums.ProjectPermissionsEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "project_entity")
public class ProjectEntity {
    @Id
    @Column(name = "name", nullable = false)
    private String name;

    @Id
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "owner_username")
    private UserEntity owner;

    @Column(name = "friendly_name")
    private String friendly_name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime created_at = LocalDateTime.now();

    @Column(name = "last_update", nullable = false)
    private LocalDateTime last_update;

    @Enumerated(EnumType.STRING)
    @Column(name = "can_see", nullable = false, length = 12)
    private ProjectPermissionsEnum can_see = ProjectPermissionsEnum.ONLY_OWNER;

    @Enumerated(EnumType.STRING)
    @Column(name = "can_edit", nullable = false, length = 12)
    private ProjectPermissionsEnum can_edit = ProjectPermissionsEnum.ONLY_OWNER;

    @Enumerated(EnumType.STRING)
    @Column(name = "can_download", nullable = false, length = 12)
    private ProjectPermissionsEnum can_download = ProjectPermissionsEnum.ONLY_OWNER;

    @Column(name = "members_can_add_others", nullable = false)
    private Boolean members_can_add_others = false;

    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(
            name = "project_members",
            joinColumns = {},
            inverseJoinColumns = {@JoinColumn(name = "member_username", referencedColumnName = "username")}
    )
    @JoinColumn(name = "project_name", referencedColumnName = "name")
    @JoinColumn(name = "project_owner_username", referencedColumnName = "owner_username")
    private Set<UserEntity> members = new LinkedHashSet<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MembershipRequestEntity> membership_requests = new LinkedHashSet<>();

}