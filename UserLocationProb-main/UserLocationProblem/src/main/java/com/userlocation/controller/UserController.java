package com.userlocation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userlocation.dto.UserLocationDto;
import com.userlocation.service.UserLocationService;
import com.userlocation.serviceImpl.UserLocationServiceImpl;

@RestController
@RequestMapping("/base_url")
public class UserController {
	
	@Autowired
	private UserLocationService userLocationService;
	
//	@Autowired
//	private UserLocationServiceImpl userLocationServiceImpl;

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/create_data")
	public ResponseEntity<UserLocationDto> create(@RequestBody UserLocationDto userLocationDto) {
		UserLocationDto createUser = userLocationService.create(userLocationDto);
		return new ResponseEntity<>(createUser,HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/update_data/{id}")
	public ResponseEntity<UserLocationDto>update(@RequestBody UserLocationDto userLocationDto, @PathVariable Long id){
			UserLocationDto updateUser = userLocationService.update(userLocationDto, id);
			return ResponseEntity.ok(updateUser);
	}
	
	@PreAuthorize("hasRole('READER')")
	@GetMapping("/all")
	public ResponseEntity<List<UserLocationDto>>getAll(){
		List<UserLocationDto> allUser = userLocationService.getAll();
		return ResponseEntity.ok(allUser);
	}
	
	@PreAuthorize("hasRole('READER')")
	@GetMapping("/get_users/{N}")
	public ResponseEntity<List<UserLocationDto>>getUsers(@PathVariable int N){
		List<UserLocationDto> nearestUser = userLocationService.getUserLocation(N);
		return ResponseEntity.ok(nearestUser);
	}
	
}
