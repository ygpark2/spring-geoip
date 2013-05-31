package com.ol.mvc.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ol.mvc.domain.GeoLiteCityLocation;

/**
 * @author Young Gyu Park <yg.park@otherlevels.com>
 */
@Repository
public interface GeoLiteCityLocationRepository extends CrudRepository<GeoLiteCityLocation, Long> {
	
}
