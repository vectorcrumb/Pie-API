package com.pie.lib;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

import com.esotericsoftware.yamlbeans.YamlReader;
import com.esotericsoftware.yamlbeans.YamlException;
import com.pie.lib.util.SimpleConstants;

public class SimpleParser {
	
	private static SimpleParser parser;
	
	private YamlReader reader;
	private Map<String, Object> robotData;
	
	public static SimpleParser getInstance() {
		if (parser == null) {
			parser = new SimpleParser();
		}
		return parser;
	}
	
	private SimpleParser() {
		// Constructor
		try {
			this.reader = new YamlReader(new FileReader(SimpleConstants.robotFile));
			this.robotData = (Map<String, Object>) reader.read();
		} catch (FileNotFoundException | YamlException | ClassCastException e) {
			e.printStackTrace();
		}
		
	}
	
	public Map<String, Object> getData() {
		return this.robotData;
	}

}
