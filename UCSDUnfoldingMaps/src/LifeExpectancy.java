import processing.core.PApplet;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.utils.MapUtils;
import de.fhpotsdam.unfolding.providers.*;
import de.fhpotsdam.unfolding.providers.Google.*;

import java.util.List;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;

import java.util.HashMap;
import java.util.Map;


import de.fhpotsdam.unfolding.marker.Marker;

public class LifeExpectancy extends PApplet{
UnfoldingMap map;
Map<String,Float> LifeExpByCountry;
List<Feature> countries;
List<Marker> countryMarkers;


public void setup() {
	
	size(800,600,OPENGL);
	map = new UnfoldingMap(this,50, 50, 700, 500, new Google.GoogleMapProvider());
	MapUtils.createDefaultEventDispatcher(this, map);
	
	LifeExpByCountry = loadLifeExpectancyFromCSV("LifeExpectancyWorldBankModule3.csv");
	println("Loaded " + LifeExpByCountry.size() + " data entries");
	
	countries = GeoJSONReader.loadData(this, "countries.geo.json");
	countryMarkers = MapUtils.createSimpleMarkers(countries);

	
//adding markers t	
	map.addMarkers(countryMarkers);
	shadeCountries();
};

private void shadeCountries() {
	
	for (Marker marker:countryMarkers) {
		String countryId =marker.getId();
		
		if (LifeExpByCountry.containsKey(countryId)) {
				float lifeExp=LifeExpByCountry.get(countryId);
				int colorLevel= (int)map(lifeExp,40,90,10,255);
				marker.setColor(color(255-colorLevel,100,colorLevel));
			}
		else {
			marker.setColor(color(150,150,150));
		}
	}
	
}

//Helper method to load life expectancy data from file
private Map<String, Float> loadLifeExpectancyFromCSV(String fileName) {
	Map<String, Float> lifeExpMap = new HashMap<String, Float>();

	String[] rows = loadStrings(fileName);
	for (String row : rows) {
		// Reads country name and population density value from CSV row
		// NOTE: Splitting on just a comma is not a great idea here, because
		// the csv file might have commas in their entries, as this one does.  
		// We do a smarter thing in ParseFeed, but for simplicity, 
		// we just use a comma here, and ignore the fact that the first field is split.
		String[] columns = row.split(",");
		if (columns.length == 6 && !columns[5].equals("..")) {
			lifeExpMap.put(columns[4], Float.parseFloat(columns[5]));
		}
	}

	return lifeExpMap;
}

public void draw() {
	map.draw();
}
}
