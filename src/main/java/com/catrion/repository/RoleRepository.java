/**
 * 
 */
package com.catrion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.catrion.entity.Role;

/**
 * 
 */
@Repository
public interface RoleRepository extends JpaRepository<Role,String>{

}
