package PP_3_1_2_Boot_Security.services;

import PP_3_1_2_Boot_Security.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    Optional<Role> findByName(String name);
    List<Role> getAllRoles ();
}
