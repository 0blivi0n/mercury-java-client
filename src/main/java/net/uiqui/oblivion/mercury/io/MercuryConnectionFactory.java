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
package net.uiqui.oblivion.mercury.io;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

public class MercuryConnectionFactory extends BasePooledObjectFactory<MercuryConnection> {
	private String server = null;
	private int port = 0;
	
	protected MercuryConnectionFactory() {
		super();
	} 

	public MercuryConnectionFactory(final String server, final int port) {
		this();
		
		this.server = server;
		this.port = port;
	}

	private String server() {
		return server;
	}

	private int port() {
		return port;
	}

	@Override
	public MercuryConnection create() throws Exception {
		return new MercuryConnection(server(), port());
	}

	@Override
	public PooledObject<MercuryConnection> wrap(final MercuryConnection connection) {
		return new DefaultPooledObject<MercuryConnection>(connection);
	}

	@Override
	public boolean validateObject(final PooledObject<MercuryConnection> po) {
		final MercuryConnection connection = po.getObject();
		return connection.isOpen();
	}

	@Override
	public void destroyObject(final PooledObject<MercuryConnection> po) throws Exception {
		final MercuryConnection connection = po.getObject();
		connection.close();
	}
}
