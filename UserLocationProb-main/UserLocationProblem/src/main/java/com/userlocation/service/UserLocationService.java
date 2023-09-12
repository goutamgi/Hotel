package com.userlocation.service;

import java.util.List;

import com.userlocation.dto.UserLocationDto;

public interface UserLocationService {
	
	public UserLocationDto create(UserLocationDto userLocationDto);
	public UserLocationDto update(UserLocationDto userLocationDto, Long id);
	public List<UserLocationDto>getAll();
//	public UserLocationDto getUserLocation(int N);
	List<UserLocationDto> getUserLocation(int N);
	

}
