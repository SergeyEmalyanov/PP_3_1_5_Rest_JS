package PP_3_1_4_Bootstrap.services;

import PP_3_1_4_Bootstrap.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    Optional<Role> findByName(String name);
    List<Role> getAllRoles ();
}
