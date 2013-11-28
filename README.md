# RestDoc Jersey Server

This project enables [RestDoc] to be rendered by [restdoc-java-server] together with [Jersey 1.x]

  [RestDoc]: http://www.restdoc.org/
  [restdoc-java-server]: https://github.com/koppor/restdoc-java-server
  [Jersey 1.x]: https://jersey.java.net/

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