package PP_3_1_2_Boot_Security.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role implements GrantedAuthority {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @ManyToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private Set<User> users = new HashSet<>();


    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public Role(String name, Set <User> users) {
        this.name = name;
        this.users=users;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
    public static Role getRoleUser(){
        return new Role("ROLE_USER");
    }
    public static Role getRoleAdmin(){
        return new Role("ROLE_ADMIN");
    }



    @Override
    public String getAuthority() {
        return this.getName();
    }
}
