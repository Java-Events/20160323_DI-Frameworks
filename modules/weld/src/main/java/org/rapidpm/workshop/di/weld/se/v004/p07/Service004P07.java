package org.rapidpm.workshop.di.weld.se.v004.p07;

import org.rapidpm.workshop.di.weld.se.v004.Service;

public class Service004P07 implements Service {

	@Override
	public String doWork(String data) {
		return this.getClass().getName() + " - " + data;
	}

}
