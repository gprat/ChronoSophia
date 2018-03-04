package com.webapp.config;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.webapp.site.entities.Chronology;
import com.webapp.site.entities.Event;

public class ChronologySerializer  extends StdSerializer<Chronology> {

	public ChronologySerializer(){
		this(null);
	}
	
	public ChronologySerializer(Class<Chronology> c){
		super(c);
	}
	
	@Override
	public void serialize(Chronology chronology,JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
		jgen.writeStartObject();
		jgen.writeStringField("id","chrono"+String.valueOf(chronology.getIdChronology()));
		jgen.writeStringField("title", chronology.getName());
		if(chronology.getEvents()!=null&&!chronology.getEvents().isEmpty()){
			jgen.writeStringField("focus_date",chronology.getEvents().get(0).getDate().toSerializer());
		}
		else{
			jgen.writeStringField("focus_date", "2000-01-01 12:00:00");
		}
		jgen.writeStringField("initial_zoom","20");
		jgen.writeStringField("timezone", "+01:00");
		jgen.writeNumberField("image_lane_height", 50);
		jgen.writeFieldName("events");
		jgen.writeStartArray();
		int i = 1;
		for(Event e : chronology.getEvents()){
			jgen.writeStartObject();
			jgen.writeStringField("id","chrono"+String.valueOf(chronology.getIdChronology())+"_"+String.valueOf(i));	
			i++;
			jgen.writeStringField("title", e.getName());
			jgen.writeStringField("description", e.getDescription());
			jgen.writeStringField("startdate", e.getDate().toSerializer());
			jgen.writeStringField("enddate", e.getDate().toSerializer());
			jgen.writeStringField("date_display", "da");
			jgen.writeStringField("link", "http://en.wikipedia.org/wiki/JavaScript");
			jgen.writeNumberField("importance", 50);
			jgen.writeStringField("icon","square_blue.png");
			jgen.writeNumberField("high_treshold",50);
			jgen.writeStringField("modal_type", "full");
			jgen.writeEndObject();
		}
		jgen.writeEndArray();
		jgen.writeEndObject();
	}
			
}
