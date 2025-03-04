package lk.gov.ps.HPSDF.system_security.repositories;

import lk.gov.ps.HPSDF.system_security.models.ERole;
import lk.gov.ps.HPSDF.system_security.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
