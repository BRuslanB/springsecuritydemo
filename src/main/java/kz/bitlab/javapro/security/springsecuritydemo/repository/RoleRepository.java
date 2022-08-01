package kz.bitlab.javapro.security.springsecuritydemo.repository;

import kz.bitlab.javapro.security.springsecuritydemo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(String role);
}
