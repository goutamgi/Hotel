package com.userlocation.serviceImpl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.userlocation.dto.UserLocationDto;
import com.userlocation.entity.UserLocation;
import com.userlocation.exception.UserLocationNotFoundException;
import com.userlocation.repo.UserLocationRepo;
import com.userlocation.service.UserLocationService;

@Service
public class UserLocationServiceImpl implements UserLocationService {
	
	@Autowired
	private UserLocationRepo userLocationRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	

	@Override
	public UserLocationDto create(UserLocationDto userLocationDto) {
		UserLocation userloc = modelMapper.map(userLocationDto, UserLocation.class);
		UserLocation saveUser = userLocationRepo.save(userloc);
		return modelMapper.map(saveUser, UserLocationDto.class);
	}

	@Override
	public UserLocationDto update(UserLocationDto userLocationDto, Long id) {
		UserLocation user = userLocationRepo.findById(id).orElseThrow(()->new UserLocationNotFoundException("UserNotFound"));
		user.setLatitude(userLocationDto.getLatitude());
		user.setLongitude(userLocationDto.getLatitude());
		user.setName(userLocationDto.getName());
		UserLocation updateUser = userLocationRepo.save(user);
		return modelMapper.map(updateUser, UserLocationDto.class);
	}

	@Override
	public List<UserLocationDto> getAll() {
		List<UserLocation> getAllUser = userLocationRepo.findAll();
		List<UserLocationDto> collect = getAllUser.stream().map(user->modelMapper.map(user, UserLocationDto.class)).collect(Collectors.toList());
		return collect;
	}
	
//	 @Override
//	    public UserLocationDto getUserLocation(int N) {
//	        List<UserLocation> allUsers = userLocationRepo.findAll();
//
//	        List<UserLocation> nonExcludedUsers = allUsers.stream()
//	                .filter(user -> !user.isExcluded())
//	                .collect(Collectors.toList());
//
//	        nonExcludedUsers.sort(Comparator.comparingDouble(user -> calculateDistance(user.getLatitude(), user.getLongitude())));
//
//	        List<UserLocation> nearestUsers = nonExcludedUsers.stream()
//	                .limit(N)
//	                .collect(Collectors.toList());
//
//	        List<UserLocationDto> nearestUserDtos = nearestUsers.stream()
//	                .map(user -> modelMapper.map(user, UserLocationDto.class))
//	                .collect(Collectors.toList());
//
//	        return modelMapper.map(nearestUserDtos, UserLocationDto.class);
//	    }
//	 private double calculateDistance(double latitude, double longitude) {
//	        double distance = Math.sqrt(latitude * latitude + longitude * longitude);
//	        return distance;
//	    }
	
	
	@Override
    public List<UserLocationDto> getUserLocation(int N) {
        double targetLatitude = 0; // Your target latitude
        double targetLongitude = 0; // Your target longitude
        
        List<UserLocation> nearestUsers = userLocationRepo.findNearestNonExcludedUsers(targetLatitude, targetLongitude);
        
        List<UserLocationDto> nearestUserDtos = nearestUsers.stream()
                .map(user -> modelMapper.map(user, UserLocationDto.class))
                .collect(Collectors.toList());

        return nearestUserDtos;
    }

	
	
	
}
