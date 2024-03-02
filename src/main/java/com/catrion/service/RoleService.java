/**
 * 
 */
package com.catrion.service;


import org.springframework.stereotype.Service;

import com.catrion.entity.Role;

/**
 * 
 */
@Service
public interface RoleService {
	public Role createNewRole(Role role);
}
