package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GeoIPServiceTests {
    
    
    @Test
    public void testMyIp(){
        String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("46.138.15.94");
        Assert.assertEquals(ipLocation, "<GeoIP><Country>RU</Country><State>48</State></GeoIP>");

    }

    @Test
    public void testInvalidIp(){
        String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("46.138.15.xxx");
        Assert.assertEquals(ipLocation, "<GeoIP><Country>RU</Country><State>48</State></GeoIP>");

    }


}
