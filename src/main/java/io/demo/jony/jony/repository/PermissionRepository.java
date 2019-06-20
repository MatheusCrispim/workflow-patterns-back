package io.demo.jony.jony.repository;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;

import io.demo.jony.jony.core.repository.CrudBaseRepository;
import io.demo.jony.jony.model.Permission;

/**
 * CRUD Repository for entity: Permission.
 * 
 * @author Virtus
 */
public interface PermissionRepository extends CrudBaseRepository<Permission, Integer> {

	/**
	 * Gets all permissions for the specified role.
	 * 
	 * @param idRole Role ID.
	 * @return Role's permissions.
	 */
	@Cacheable("db-role-permissions")
	@Query(value = "select p.name from role_permission rp inner join permission p on p.id = rp.idpermission where rp.idrole = ?1", nativeQuery = true)
	List<String> findPermissionByRole(Integer idRole);
}
