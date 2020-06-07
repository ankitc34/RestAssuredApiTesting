package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.GoogleApi;
import pojo.Location;
import pojo.PlaceId;

public class TestData {
	
	public GoogleApi addPlacePayload(String name, String language, String address) {
		GoogleApi google = new GoogleApi();

		Location lc = new Location();
		lc.setLat(-38.383494);
		lc.setLng(33.427362);
		google.setLocation(lc);

		google.setAccuracy(50);
		google.setName(name);
		google.setPhone_number("(+91) 983 893 3937");
		google.setAddress(address);
		google.setWebsite("http://google.com");
		google.setLanguage(language);

		List<String> ls = new ArrayList<String>();
		ls.add("shoe park");
		ls.add("shop");
		google.setTypes(ls);
		
		return google;

	}
	
	public PlaceId addPlaceId(String place_id) {
		PlaceId place = new PlaceId();
		place.setPlace_id(place_id);
		
		return place;
	}

}
