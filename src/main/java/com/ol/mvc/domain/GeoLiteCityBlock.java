package com.ol.mvc.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * @author Young Gyu Park (yg.park@otherlevels.com)
 */
@Entity
public class GeoLiteCityBlock {

	// startIpNum,endIpNum,locId
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable = false, length = 10)
	private Long startIpNum;
	@Column(nullable = false, length = 10)
	private Long endIpNum;

	@ManyToOne(fetch = FetchType.EAGER)
	private GeoLiteCityLocation geoLiteCityLoc;

	public GeoLiteCityBlock() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStartIpNum() {
		return startIpNum;
	}

	public void setStartIpNum(Long startIpNum) {
		this.startIpNum = startIpNum;
	}

	public Long getEndIpNum() {
		return endIpNum;
	}

	public void setEndIpNum(Long endIpNum) {
		this.endIpNum = endIpNum;
	}

	public GeoLiteCityLocation getGeoLiteCityLoc() {
		return geoLiteCityLoc;
	}

	public void setGeoLiteCityLoc(GeoLiteCityLocation geoLiteCityLoc) {
		this.geoLiteCityLoc = geoLiteCityLoc;
	}

	@Override
	public String toString() {
		return "GeoLiteCityBlock {" +
				"id=" + id +
				", startIpNum='" + startIpNum + '\'' +
				", endIpNum='" + endIpNum + '\'' +
				'}';
	}

}
