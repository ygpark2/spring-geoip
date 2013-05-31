package com.ol.util.rest;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.ol.mvc.domain.GeoLiteCityBlock;
import com.ol.mvc.domain.GeoLiteCityLocation;

public class IPConversion {

	private static IPConversion ipConvert;

	public static IPConversion getInstance() {
		if ( IPConversion.ipConvert == null ) {
			ipConvert = new IPConversion();
		}
		return ipConvert;
	}

    public long getLongIP(InetAddress addr) {
		return bytesToLong(addr.getAddress());
    }

    public GeoLiteCityBlock getNullGeoIP(String countryCode) {

    	GeoLiteCityLocation geoLiteCityLoc = new GeoLiteCityLocation();
		geoLiteCityLoc.setId(new Long(0));
		geoLiteCityLoc.setAreaCode(0);
		geoLiteCityLoc.setCity("null");
		geoLiteCityLoc.setCountry(countryCode);
		geoLiteCityLoc.setLatitude(new Float(0.0));
		geoLiteCityLoc.setLongitude(new Float(0.0));
		geoLiteCityLoc.setMetroCode(0);
		geoLiteCityLoc.setPostalCode("000");
		geoLiteCityLoc.setRegion("null");
		geoLiteCityLoc.setTimeZone("null");

		GeoLiteCityBlock glcb = new GeoLiteCityBlock();
		glcb.setId(new Long(0));
		glcb.setEndIpNum(new Long(0));
		glcb.setStartIpNum(new Long(0));
		glcb.setGeoLiteCityLoc(geoLiteCityLoc);

		return glcb;
    }

    /**
     * Returns the long version of an IP address given an InetAddress object.
     *
     * @param address the InetAddress.
     * @return the long form of the IP address.
     */
    private static long bytesToLong(byte [] address) {
        long ipnum = 0;
        for (int i = 0; i < 4; ++i) {
            long y = address[i];
            if (y < 0) {
                y+= 256;
            }
            ipnum += y << ((3-i)*8);
        }
        System.out.println("ipnum : " + ipnum);
        return ipnum;
    }
}
