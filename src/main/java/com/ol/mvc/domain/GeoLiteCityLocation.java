package com.ol.mvc.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

/**
 * @author Young Gyu Park (yg.park@otherlevels.com)
 */
@Entity
public class GeoLiteCityLocation {

	// locId,country,region,city,postalCode,latitude,longitude,metroCode,areaCode
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable = false, length = 2)
	private String country;
	@Column(nullable = true, length = 2)
	private String region;
	@Column(nullable = true, length = 250)
	private String city;
	@Column(nullable = false, length = 50)
	private String postalCode;
	@Column(nullable = false, precision=6, scale=10)
	private Float latitude;
	@Column(nullable = false, precision=6, scale=10)
	private Float longitude;
	@Column(nullable = true, length = 32)
	private Integer metroCode;
	@Column(nullable = true, length = 32)
	private Integer areaCode;
	@Transient
	private String timeZone;

	@OneToMany
	private List<GeoLiteCityBlock> geoLiteCityBlk;

	public GeoLiteCityLocation() {
	  
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	public Integer getMetroCode() {
		return metroCode;
	}

	public void setMetroCode(Integer metroCode) {
		this.metroCode = metroCode;
	}

	public Integer getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(Integer areaCode) {
		this.areaCode = areaCode;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public List<GeoLiteCityBlock> getGeoLiteCityBlk() {
		return geoLiteCityBlk;
	}

	public void setGeoLiteCityBlk(List<GeoLiteCityBlock> geoLiteCityBlk) {
		this.geoLiteCityBlk = geoLiteCityBlk;
	}

	@Override
	public String toString() {
		return "GeoLiteCityLocation {" +
				"id=" + id +
				", country='" + country + '\'' + ", region='" + region + '\'' +
				", city='" + city + '\'' + ", postalCode='" + postalCode + '\'' +
				", latitude='" + latitude + '\'' + ", longitude='" + longitude + '\'' +
				", metroCode='" + metroCode + '\'' + ", areaCode='" + areaCode + '\'' +
				'}';
  }

}
