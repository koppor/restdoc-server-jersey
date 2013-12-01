package org.restdoc.jersey.server;

/**
 * restdoc-server-jersey
 * 
 * Based on org.restdoc.cxf.RestDocFeature by thoeger, restdoc.org This is a minimal stripped-down version tested with Winery
 * 
 * Copyright (C) 2013 Oliver Kopp
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */

import javax.ws.rs.core.Response;

import org.restdoc.api.GlobalHeader;
import org.restdoc.api.RestDoc;
import org.restdoc.server.impl.RestDocGenerator;
import org.slf4j.LoggerFactory;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;

public abstract class RestDocFeature implements ContainerResponseFilter {
	
	private static final String HTTP_METHOD = "OPTIONS";
	
	private final RestDocGenerator restDoc;
	
	
	public RestDocFeature() {
		this.restDoc = new RestDocGenerator();
		this.restDoc.init(this.getClasses(), new GlobalHeader(), this.getBaseURL());
	}
	
	protected abstract Class<?>[] getClasses();
	
	protected String getBaseURL() {
		// the empty string is enough for most cases
		return "";
	}
	
	@Override
	public final ContainerResponse filter(ContainerRequest request, ContainerResponse response) {
		if (request.getMethod().equals(RestDocFeature.HTTP_METHOD)) {
			final String decodedPath = request.getPath(true);
			// we have to prepend a "/" as the path does not contain a "/", but RestDoc expects one
			final String path = "/" + decodedPath;
			LoggerFactory.getLogger(this.getClass()).debug(String.format("RestDoc request on path: %s", path));
			// find RestDoc and return it
			Response res = Response.ok(this.restDoc.getRestDocStringForPath(path), RestDoc.RESTDOC_MEDIATYPE).build();
			response.setResponse(res);
		} else {
			// response object is kept as is
		}
		return response;
	}
}
