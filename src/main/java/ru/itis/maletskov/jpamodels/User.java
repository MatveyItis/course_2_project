package ru.itis.maletskov.jpamodels;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "client", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username")
})
@ToString(exclude = {"uploadedSongs", "subscribers", "subscriptions", "addedSongs"})
@EqualsAndHashCode(exclude = {"uploadedSongs", "subscribers", "subscriptions", "addedSongs"})
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "about_me")
    private String aboutMe;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "author", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Set<Song> uploadedSongs = new HashSet<>();

    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(name = "client_added_songs",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id")
    )
    private Set<Song> addedSongs = new HashSet<>();

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "main_img", referencedColumnName = "id")
    private Img img;

    @ManyToMany
    @JoinTable(
            name = "user_subscriptions",
            joinColumns = {@JoinColumn(name = "channel_id")},
            inverseJoinColumns = {@JoinColumn(name = "subscriber_id")}
    )
    private Set<User> subscribers = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "user_subscriptions",
            joinColumns = {@JoinColumn(name = "subscriber_id")},
            inverseJoinColumns = {@JoinColumn(name = "channel_id")}
    )
    private Set<User> subscriptions = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

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
        return true;
    }

    public boolean isAdmin() {
        return roles.contains(Role.ADMIN);
    }
}
