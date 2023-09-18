package com.raimis.Blogas.entities;

import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;

import java.util.ArrayList;

import java.util.Collection;
import java.util.List;
import java.util.Set;


@Entity
@NoArgsConstructor
@Data
@Table(name = "usr")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @OneToMany(mappedBy = "createdBy")
    private List<Comment> comments = new ArrayList<>();

    private boolean active;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setCreatedBy(this);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + "hidden" + '\'' +
                ", comments=" + comments.stream().map(Comment::getId).toList() +
                ", active=" + active +
                ", roles=" + roles +
                '}';
    }
}
