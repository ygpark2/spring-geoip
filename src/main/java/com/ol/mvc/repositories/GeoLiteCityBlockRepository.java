package com.ol.mvc.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ol.mvc.domain.GeoLiteCityBlock;


/**
 * @author Young Gyu Park <yg.park@otherlevels.com>
 */
@Repository
public interface GeoLiteCityBlockRepository extends PagingAndSortingRepository<GeoLiteCityBlock, Long> {

	@Query("SELECT g FROM GeoLiteCityBlock g WHERE g.startIpNum <= :ip AND g.endIpNum >= :ip")
	public GeoLiteCityBlock findOneByIP(@Param("ip") Long ip);

	/*
	public GeoLiteCityLocation findByIP(@Param("ip") String ip) {
		
		GeoLiteCityLocation glcl = new GeoLiteCityLocation();
		return glcl;
	}
	*/

}
