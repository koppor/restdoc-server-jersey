# RestDoc Jersey Server

This project enables [RestDoc] to be rendered by [restdoc-java-server, JAX-RS 1.1 branch] together with [Jersey 1.x]

## Usage

In your `web` xml, add following `init-param`:

```
<init-param>
    <param-name>com.sun.jersey.spi.container.ContainerResponseFilters</param-name>
    <param-value>org.example.RestDocFilter</param-value>
</init-param>
```

The `org.example.RestDocFilter` looks as follows:

```
package org.example.RestDocFilter;

import org.example.MainResource;

import com.allweathercomputing.restdoc.server.jersey.RestDocFeature;

public class RestDocFilter extends RestDocFeature {

	@Override
	protected Class<?>[] getClasses() {
		Class<?>[] res = {MainResource.class};
		return res;
	}

}
```

Now, `OPTIONS` requests to your REST resources result in a RestDoc HTML response.

## License
Licensed under [Apache License, Version 2.0]

  [RestDoc]: http://www.restdoc.org/
  [restdoc-java-server, JAX-RS 1.1 branch]: https://github.com/hoegertn/restdoc-java-server/tree/jaxrs11
  [Jersey 1.x]: https://jersey.java.net/
  [Apache License, Version 2.0]: http://www.apache.org/licenses/LICENSE-2.0.html
