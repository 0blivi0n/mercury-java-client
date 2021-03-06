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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSON implements Serializable {
	private static final long serialVersionUID = -8739986265954655733L;
	
	private final List<Field> fields = new ArrayList<Field>();
	private transient Map<String, Object> fieldMap = null;

	public JSON(final Field...fields) {
		for (Field field : fields) {
			this.fields.add(field);
		}
	}

	public JSON field(final String name, final Object value) {
		fields.add(new Field(name, value));
		return this;
	}

	public List<Field> fields()  {
		return fields;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T value(final String name) {
		if (fieldMap == null) {
			convertFields();
		}
		
		return (T) fieldMap.get(name);
	}	

	private synchronized void convertFields() {
		if (fieldMap != null) {
			return;
		}
		
		fieldMap = toMap();
	}

	public Map<String, Object> toMap() {
		if (fieldMap != null) {
			return fieldMap;
		}
		
		final Map<String, Object> map = new HashMap<String, Object>();
		
		for (Field field : fields) {
			map.put(field.name(), field.value());
		}
		
		return map;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		
		builder.append("{");
		
		boolean first = true;
		
		for (Field field : fields) {
			if (first) {
				first = false;
			} else {
				builder.append(", ");
			}
			
			builder.append(field);
		}
		
		builder.append("}");
		
		return builder.toString();
	}	

	public static JSON fromMap(final Map<String, Object> fieldMap) {
		final JSON json = new JSON();
		
		for (Map.Entry<String, Object> entry : fieldMap.entrySet()) {
			json.field(entry.getKey(), entry.getValue());
		}
		
		return json;
	}

	public static class Field implements Serializable {
		private static final long serialVersionUID = -1349404110784531443L;

		private String name = null;
		private Object value = null;
		
		public Field(final String name, final Object value) {
			this.name = name;
			this.value = value;
		}

		public String name() {
			return name;
		}

		public Object value() {
			return value;
		}

		@Override
		public String toString() {
			return "'" + name() + "' : " + value();
		}
	}
}
