package module1;

import java.util.*;
import java.util.ArrayList;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.marker.Marker;
//import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.Microsoft;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;
import processing.core.*;
import de.fhpotsdam.unfolding.geo.Location;

import parsing.ParseFeed;

public class EarthCityMap extends PApplet {
private UnfoldingMap map;

public void setup() {
	size(950,600,OPENGL);
	
	// Assume online
	map = new UnfoldingMap(this, 200, 50, 700, 500, new Google.GoogleMapProvider());
//	map = new UnfoldingMap(this, 200, 50, 700, 500, new OpenStreetMap.OpenStreetMapProvider() );
//    map = new UnfoldingMap(this, 200, 50, 650, 600, new MBTilesMapProvider(mbTilesString));

	
	
	
	map.zoomToLevel(2);
	MapUtils.createDefaultEventDispatcher(this,map);
	
	Location valLoc =new Location (-38.14f,-73.03f);
	SimplePointMarker val =new SimplePointMarker(valLoc);
	map.addMarker(val);
	
	PointFeature valEq= new PointFeature(valLoc);
	valEq.addProperty("title", "Valdivia Chile");
	valEq.addProperty("magnitude","9.5");
	valEq.addProperty("date","May 22 1960");
	valEq.addProperty("year","1960");
	    
	    PointFeature alaskaEq = new PointFeature(new Location(61.02f,-147.65f));
	    alaskaEq.addProperty("title", "1964 Great Alaska Earthquake");
	    alaskaEq.addProperty("magnitude", "9.2");
	    alaskaEq.addProperty("date", "March 28, 1964"); 
	    alaskaEq.addProperty("year", 1964);

	    PointFeature sumatraEq = new PointFeature(new Location(3.30f,95.78f));
	    sumatraEq.addProperty("title", "Off the West Coast of Northern Sumatra");
	    sumatraEq.addProperty("magnitude", "9.1");
	    sumatraEq.addProperty("date", "February 26, 2004");
	    sumatraEq.addProperty("year", 2004);

	    
	    PointFeature japanEq = new PointFeature(new Location(38.322f,142.369f));
	    japanEq.addProperty("title", "Near the East Coast of Honshu, Japan");
	    japanEq.addProperty("magnitude", "9.0");
	    japanEq.addProperty("date", "March 11, 2011");
	    japanEq.addProperty("year", 2011);

	
	    
	    PointFeature kamchatkaEq = new PointFeature(new Location(52.76f,160.06f));
	    kamchatkaEq.addProperty("title", "Kamchatka");
	    kamchatkaEq.addProperty("magnitude", "9.0");
	    kamchatkaEq.addProperty("date", "November 4, 1952");
	    kamchatkaEq.addProperty("year", 1952);
	
	
	Marker valMk =new SimplePointMarker(valLoc,valEq.getProperties());
	map.addMarker(valMk);

	List<PointFeature> bigEqs =new ArrayList<PointFeature>();
	bigEqs.add(valEq);
	bigEqs.add(alaskaEq);
	bigEqs.add(valEq);
	bigEqs.add(valEq);
	bigEqs.add(sumatraEq);
	bigEqs.add(japanEq);
	bigEqs.add(kamchatkaEq);
	
	List<Marker> markers = new ArrayList<Marker>();
	for (PointFeature eq : bigEqs) {
		markers.add(new SimplePointMarker(eq.getLocation(),eq.getProperties()));
	}
	map.addMarkers(markers);
	
	int yellow =color(255,255,0);
	int grey=color(150,150,150);
	int red = color(255,0,0);
	
	
	
	for (Marker mk :markers) {
		if ( (Integer.parseInt(mk.getProperty("year").toString())) > 2000) {
			mk.setColor(yellow);
		}
		else {
			mk.setColor(grey);
		}
	}
	
/***Example of finding by title 	
			for (Marker mk :markers) {
				String a= (String) mk.getProperty("title");
				if ( a.contains("Japan") ) {
					mk.setColor(yellow);
				}
				else {
					mk.setColor(red);
				}
			}
	*/
	}
	

	public void draw() {
		background(230);
		map.draw();
		addKey();
	}
	private void addKey() {
		// TODO Auto-generated method stub
		
	}

}
