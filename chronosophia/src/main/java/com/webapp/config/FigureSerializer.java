package com.webapp.config;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.webapp.site.entities.Figure;

public class FigureSerializer extends StdSerializer<Figure> {

	public FigureSerializer(){
		this(null);
	}
	
	public FigureSerializer(Class<Figure> f){
		super(f);
	}
	
	 @Override
	 public void serialize(Figure figure, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
		 String sBirthYear = "", sDeathYear = ""; 
		 jgen.writeStartObject();
		 jgen.writeStringField("idFigure", String.valueOf(figure.getIdFigure()));
		 jgen.writeStringField("firstName", figure.getFirstName());
		 jgen.writeStringField("lastName", figure.getLastName());
		 if(figure.getBirthDate()!=null) sBirthYear = String.valueOf(figure.getBirthDate().getYear());
		 if(figure.getDeathDate()!=null) sDeathYear = String.valueOf(figure.getDeathDate().getYear());
		 jgen.writeStringField("figureDates","("+sBirthYear+"-"+sDeathYear+")");
		 jgen.writeEndObject();
	 }
	
}
