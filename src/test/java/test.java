import java.net.InetAddress;
import java.net.UnknownHostException;

import com.ol.util.rest.IPConversion;

import java.text.DecimalFormat;
import java.util.*;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		InetAddress addr = null;
		try {
			addr = InetAddress.getByName("192.168.0.12");
		}
		catch (UnknownHostException e) {
			System.out.println("unknown host exception!!!!");
		}

		Date date = new Date();
		String TimeZoneIds[] = TimeZone.getAvailableIDs();
		for(int i = 0; i < TimeZoneIds.length; i++) {
			TimeZone tz = TimeZone.getTimeZone(TimeZoneIds[i]);
			String tzName = tz.getDisplayName(tz.inDaylightTime(date), TimeZone.LONG);
			System.out.print(TimeZoneIds[i] + ": ");
			// Get the number of hours from GMT
			int rawOffset = tz.getRawOffset();
			int hour = rawOffset / (60*60*1000);
			int minute = Math.abs(rawOffset / (60*1000)) % 60;
			System.out.println(tzName + " " + hour + ":" + minute);
		}

		TimeZone tz = TimeZone.getTimeZone("America/Argentina/Buenos_Aires");
		DecimalFormat formatter = new DecimalFormat("#00.###");
		System.out.println( "GMT" + formatter.format(tz.getRawOffset() / 1000 / 60 / 60) + ":" + formatter.format(Math.abs(tz.getRawOffset() / (60*1000)) % 60)) ;
		System.out.println(tz.getDisplayName());
		// System.out.println(IPConversion.getInstance().getLongIP("192.168.0.123"));
	}

}
