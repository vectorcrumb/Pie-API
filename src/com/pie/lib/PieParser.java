package com.pie.lib;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.esotericsoftware.yamlbeans.YamlReader;
import com.pie.ingredients.PiePort;
import com.pie.ingredients.PortCatalog.*;
import com.pie.robot.controllers.DSControllerFactory.DSControllerCatalog;
import com.pie.robot.controllers.PWMMotorFactory.MotorCatalog;
import com.esotericsoftware.yamlbeans.YamlException;

public class PieParser {
	
	private static PieParser parser;
	
	private YamlReader reader;
	private Map robotData;
	
	public static PieParser getInstance(String filePath) {
		if (parser == null) {
			parser = new PieParser(filePath);
		}
		return parser;
	}
	
	private PieParser(String filePath) {
		// Constructor
		try {
			this.reader = new YamlReader(new FileReader(filePath));
			this.robotData = (Map) reader.read();
		} catch (FileNotFoundException | YamlException | ClassCastException e) {
			e.printStackTrace();
		}
	}
	
	public HashMap<PiePort, MotorCatalog> getPinDefinitions() {
		Map<String, String> tempPinDefs = (Map<String, String>) ((Map) this.robotData.get("robot")).get("pindefs");
		HashMap<PiePort, MotorCatalog> pinDefs = new HashMap<PiePort, MotorCatalog>();
		
		tempPinDefs.entrySet().forEach((entry) -> {
			try {
				PiePort port = PWMPort.valueOf(entry.getKey());
				MotorCatalog motor = MotorCatalog.valueOf(entry.getValue().toUpperCase());
				pinDefs.put(port, motor);
			} catch (IllegalArgumentException err) {
				err.printStackTrace();
			}
		});
		return pinDefs;
	}
	
	public HashMap<PiePort, DSControllerCatalog> getControlDefinitions() {
		Map<String, String> tempControlDefs = (Map<String, String>) ((Map) this.robotData.get("robot")).get("controls");
		HashMap<PiePort, DSControllerCatalog> controlDefs = new HashMap<PiePort, DSControllerCatalog>();
		
		tempControlDefs.entrySet().forEach((entry) -> {
			try {
				PiePort port = USBPort.valueOf(entry.getKey());
				DSControllerCatalog controller = DSControllerCatalog.valueOf(entry.getValue().toUpperCase());
				controlDefs.put(port, controller);
			} catch (IllegalArgumentException err) {
				err.printStackTrace();
			}
		});
		return controlDefs;
	}
	
	public HashMap<String, Object> getSubsystemDefinitions() {
		HashMap<String, Object> subsystems = (HashMap<String, Object>) this.robotData.get("subsystems");
		return subsystems;
	}

}
