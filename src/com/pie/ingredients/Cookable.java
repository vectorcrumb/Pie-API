package com.pie.ingredients;

import com.pie.robot.io.IOPacket;

public interface Cookable {
	/**
	 * Cookable is the basic interface for all Ingredients to update with IOPackets and disable for stopping.
	 * @return
	 */
	public IOPacket update();
	public IOPacket disable();
}
