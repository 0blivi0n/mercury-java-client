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
package net.uiqui.oblivion.mercury.error;

public class InvalidResponseException extends Exception {
	private static final long serialVersionUID = -4571612450476911094L;

	public InvalidResponseException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public InvalidResponseException(final String message) {
		super(message);
	}

	public InvalidResponseException(final Throwable cause) {
		super(cause);
	}
}
