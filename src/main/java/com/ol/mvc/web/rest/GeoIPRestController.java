package com.ol.mvc.web.rest;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.util.TimeZone;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.maxmind.geoip.timeZone;
import com.ol.mvc.domain.GeoLiteCityBlock;
import com.ol.mvc.domain.GeoLiteCityLocation;
import com.ol.mvc.repositories.GeoLiteCityBlockRepository;
import com.ol.mvc.web.controller.IndexController;
import com.ol.util.rest.IPConversion;

@Controller
@RequestMapping("/rest/geoip")
public class GeoIPRestController {

	@Autowired
	private GeoLiteCityBlockRepository repository;

	private DecimalFormat tzFormatter = new DecimalFormat("#00.###");
	
	private static final Logger logger = LoggerFactory.getLogger(GeoIPRestController.class);
	
	@RequestMapping(value = "/id/{id}", method = GET,
			headers = "Accept=application/xml, application/json", 
	  		produces = { "application/json", "application/xml" })
	@ResponseBody
	public GeoLiteCityBlock showOneById(@PathVariable("id") Long id) {
		GeoLiteCityBlock glcb = repository.findOne(id);
		System.out.println("glcb : " + glcb.toString());
		System.out.println("glcb city : " + glcb.getGeoLiteCityLoc().toString());
		System.out.println("id : " + id);
		System.out.println("id : show geo lite city block");
		return glcb;
	}

	@RequestMapping(value = "/ip/{ip}", method = GET,
			headers = "Accept=application/xml, application/json", 
	  		produces = { "application/json", "application/xml" })
	@ResponseBody
	public GeoLiteCityBlock showGeoLiteCityBlockByIP(@PathVariable("ip") String ip) {
		long startTime = System.currentTimeMillis();
		logger.debug("ip address : " + ip);
		try {
			InetAddress addr = InetAddress.getByName(ip);
			if (addr.isSiteLocalAddress()) {
				logger.info("=======> private ip address! <==========");
				return IPConversion.getInstance().getNullGeoIP("XX");
			} else if(addr.isLoopbackAddress()) {
				return IPConversion.getInstance().getNullGeoIP("XX");
			} else {
				logger.info("=========> public ip address <==========");
				GeoLiteCityBlock glcb = repository.findOneByIP( IPConversion.getInstance().getLongIP(addr) );
				if (glcb.equals(null)) {
					return IPConversion.getInstance().getNullGeoIP("XX");					
				} else {
					String timeZoneNm = timeZone.timeZoneByCountryAndRegion(glcb.getGeoLiteCityLoc().getCountry(), glcb.getGeoLiteCityLoc().getRegion());
					TimeZone locTz = TimeZone.getTimeZone(timeZoneNm);

					String tzHour = tzFormatter.format( locTz.getRawOffset() / (60*60*1000) );
					String tzMinute = tzFormatter.format( Math.abs(locTz.getRawOffset() / (60*1000)) % 60 );
					glcb.getGeoLiteCityLoc().setTimeZone("GMT " + tzHour + ":" + tzMinute);
					long elapsedTime = System.currentTimeMillis() - startTime;
					logger.info("elapsed time : " + elapsedTime);
					return glcb;					
				}

			}
		} catch (UnknownHostException e) {
			return IPConversion.getInstance().getNullGeoIP("UnknownHost");
		} catch (Exception e) {
			return IPConversion.getInstance().getNullGeoIP("UnknownError");
		}

	}

	/* -----------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/geoip", method = GET,
			headers = "Accept=application/xml, application/json", 
	  		produces = { "application/json", "application/xml" })
	@ResponseBody
	public Iterable<GeoLiteCityBlock> showGeoLiteCityBlock(Model model) {
		return repository.findAll();
	}

	@RequestMapping(method = POST)
	@ResponseStatus(CREATED)
	public void createUser(@RequestBody GeoLiteCityBlock geoLiteCityBlock, HttpServletResponse response) {
		repository.save(geoLiteCityBlock);
		response.setHeader("Location", String.format("/rest/Users/%s", geoLiteCityBlock.getId()));
	}

	@RequestMapping(value = "/{id}", method = PUT)
	@ResponseStatus(OK)
	public void updateGeoLiteCityBlock(@RequestBody GeoLiteCityBlock geoLiteCityBlock) {
		repository.save(geoLiteCityBlock);
	}

	@RequestMapping(value = "/{id}", method = DELETE)
	@ResponseStatus(OK)
	public void deleteGeoLiteCityBlock(@PathVariable Long id) {
		repository.delete(id);
	}
	 ----------------------------------------------------------------------------------------------------- */
}