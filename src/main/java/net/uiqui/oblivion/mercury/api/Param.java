/*
 * 0blivi0n-cache
 * ==============
 * Mercury Java Client
 * 
 * Copyright (C) 2015 Joaquim Rocha <jrocha@gmailbox.org>
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package net.uiqui.oblivion.mercury.api;

import java.io.Serializable;

/**
 * The Class Param.
 */
public class Param implements Serializable {
	private static final long serialVersionUID = -4243692044456101670L;

	private String name = null;
	private Object value = null;
	
	/**
	 * Instantiates a new param.
	 *
	 * @param name the name
	 * @param value the value
	 */
	public Param(final String name, final Object value) {
		this.name = name;
		this.value = value;
	}
	
	/**
	 * Name.
	 *
	 * @return the string
	 */
	public String name() {
		return name;
	}
	
	/**
	 * Value.
	 *
	 * @return the object
	 */
	public Object value() {
		return value;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Param [name=" + name + ", value=" + value + "]";
	}
}
