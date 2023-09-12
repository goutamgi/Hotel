package com.userlocation.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.userlocation.entity.UserLocation;

@Repository
public interface UserLocationRepo extends JpaRepository<UserLocation, Long>{
	
	 @Query(value = "SELECT u FROM UserLocation u " +
             "WHERE u.excluded = false " +
             "ORDER BY (u.latitude - :latitude)*(u.latitude - :latitude) + (u.longitude - :longitude)*(u.longitude - :longitude)")
     public List<UserLocation> findNearestNonExcludedUsers(double latitude, double longitude);

}
