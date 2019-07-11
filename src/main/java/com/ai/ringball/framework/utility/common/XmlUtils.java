package com.ai.ringball.framework.utility.common;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class XmlUtils {

	static public String writeObject2Xml(Object o) throws JsonProcessingException {
		XmlMapper xml = new XmlMapper();
		return xml.writeValueAsString(o);
	}

	static public <T> T writeXml2Object(String string, Class<T> t) throws JsonParseException, JsonMappingException, IOException {
		XmlMapper xml = new XmlMapper();
		xml.enableDefaultTyping(DefaultTyping.JAVA_LANG_OBJECT);
		T object = xml.readValue(string, t);
		return object;
	}
}
