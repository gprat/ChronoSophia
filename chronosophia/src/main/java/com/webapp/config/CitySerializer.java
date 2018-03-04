package com.webapp.config;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.webapp.site.entities.City;

public class CitySerializer extends StdSerializer<City> {
	
	public CitySerializer(){
		this(null);
	}
	
	public CitySerializer(Class<City> c){
		super(c);
	}
	
	@Override
	public void serialize(City city, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
		String countryName = "";
		if(city.getCountry()!=null)	countryName=city.getCountry().getName();	
		jgen.writeStartObject();
		jgen.writeStringField("idCity", String.valueOf(city.getIdCity()));
		jgen.writeStringField("name", city.getName());
		jgen.writeNumberField("latitude",city.getLatitude());
		jgen.writeNumberField("longitude",city.getLongitude());
		jgen.writeStringField("countryName",countryName);
		jgen.writeStringField("sCity", city.toString());
		jgen.writeEndObject();
	}
}
