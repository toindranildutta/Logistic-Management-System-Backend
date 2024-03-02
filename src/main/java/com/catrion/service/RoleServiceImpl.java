package com.catrion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catrion.entity.Role;
import com.catrion.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	/**
	 * Create a new role
	 * @param role
	 * @return role
	 */
	@Override
	public Role createNewRole(Role role) {
		// TODO Auto-generated method stub
		return roleRepository.save(role);
	}

}
