package com.userlocation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLocationDto {
	
	private Long id;
	private String name;
	private double latitude;
	private double longitude;
	private boolean excluded;

}
