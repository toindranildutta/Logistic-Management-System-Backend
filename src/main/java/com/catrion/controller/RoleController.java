/**
 * 
 */
package com.catrion.controller;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.catrion.entity.Role;
import com.catrion.service.RoleService;

/**
 * 
 */
@RestController
@RequestMapping("/api/role")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@RequestMapping(produces=MediaType.APPLICATION_JSON
			,method=RequestMethod.POST
			,value="createRole")
	public ResponseEntity<Role> createNewRole(@RequestBody Role role) {
		Role savedRole = roleService.createNewRole(role);
		return new ResponseEntity<Role>(savedRole,HttpStatus.CREATED);
	}
	
}
