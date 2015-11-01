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

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import net.uiqui.oblivion.mercury.util.Converter;
import net.uiqui.oblivion.mercury.util.MercuryConstants;

import com.ericsson.otp.erlang.OtpErlangAtom;
import com.ericsson.otp.erlang.OtpErlangList;
import com.ericsson.otp.erlang.OtpErlangObject;
import com.ericsson.otp.erlang.OtpErlangTuple;
import com.ericsson.otp.erlang.OtpExternal;
import com.ericsson.otp.erlang.OtpOutputStream;

/**
 * The Class MercuryRequest.
 */
public class MercuryRequest extends OtpErlangTuple {
	private static final long serialVersionUID = 850764874192765477L;

	private MercuryRequest(final OtpErlangObject[] elems) {
		super(elems);
	}
	
	/**
	 * Write.
	 *
	 * @param os the os
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void write(final OutputStream os) throws IOException {
		final OtpOutputStream stream = new OtpOutputStream();
		stream.write(OtpExternal.versionTag);
		stream.write_any(this);
		stream.writeTo(os);
		stream.close();
	}
	
	/**
	 * Builder.
	 *
	 * @return the builder
	 */
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * The Class Builder.
	 */
	public static class Builder {
		private final OtpErlangObject[] elems = new OtpErlangObject[5];
		
		private Builder() {
			elems[0] = new OtpErlangAtom(MercuryConstants.REQUEST);
			elems[3] = new OtpErlangList();
			elems[4] = new OtpErlangAtom(MercuryConstants.EMPTY);
		}
		
		/**
		 * Operation.
		 *
		 * @param opName the op name
		 * @return the builder
		 */
		public Builder operation(final String opName) {
			elems[1] = Converter.encode(opName);
			return this;
		}
		
		/**
		 * Resource.
		 *
		 * @param resource the resource
		 * @return the builder
		 */
		public Builder resource(final String[] resource) {			
			elems[2] = Converter.encode(resource);
			return this;
		}	
		
		/**
		 * Resource.
		 *
		 * @param resource the resource
		 * @return the builder
		 */
		public Builder resource(final List<String> resource) {			
			elems[2] = Converter.encode(resource);
			return this;
		}		
		
		/**
		 * Params.
		 *
		 * @param params the params
		 * @return the builder
		 */
		public Builder params(final Param[] params) {
			elems[3] = Converter.encode(params);
			return this;
		}
		
		/**
		 * Params.
		 *
		 * @param params the params
		 * @return the builder
		 */
		public Builder params(final List<Param> params) {
			elems[3] = Converter.encode(params);
			return this;
		}		
		
		/**
		 * Payload.
		 *
		 * @param payload the payload
		 * @return the builder
		 */
		public Builder payload(final Object payload) {
			elems[4] = Converter.encode(payload);
			return this;
		}
		
		/**
		 * Builds the.
		 *
		 * @return the mercury request
		 */
		public MercuryRequest build() {
			return new MercuryRequest(elems);
		}
	}
}
