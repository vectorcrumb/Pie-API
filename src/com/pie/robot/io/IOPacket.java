package com.pie.robot.io;

import java.util.Map;

import com.pie.ingredients.PiePort;

public class IOPacket {
	
	/**
	 * IOPacket is a simple data structure which has a name and a map which maps ports to information (their values to write usually)
	 */
	
	public String name;
	public Map<PiePort, Object> data;
	
}
