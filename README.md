# WildFly Book


## Intro

[WildFly](https://www.wildfly.org/) is a [Jakarta EE](https://jakarta.ee/) application server;

This means you can deploy your [Jakarta EE](https://jakarta.ee/) applications on WildFly; for example you can deploy an
application which consists of simple servlet like this one:

```java
package org.jboss.as.quickstarts.helloworld;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/HelloWorld")
public class HelloWorldServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter writer = resp.getWriter();
		writer.println("<html><head><title>helloworld</title></head><body>");
		writer.println("<h1>Hello World!</h1>");
		writer.println("</body></html>");
		writer.close();
	}

}
```


## Bootable Jar

But [WildFly](https://www.wildfly.org/) has evolved and now, you can create stand-alone applications like you do with [Spring Boot](https://spring.io/projects/spring-boot):
it is called "[Bootable Jar](https://docs.wildfly.org/bootablejar/)" and it means that, after building your application with e.g. maven (we used the [jaxrs](https://github.com/wildfly-extras/wildfly-jar-maven-plugin/tree/10.0.0.Final/examples/jaxrs) example ):

```shell
mvn clean install -DskipTests -Denforcer.skip
...
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
```
`
you obtain a jar file that can be started like:

```shell
java -jar target/jaxrs-bootable.jar
...
17:28:36,499 INFO  [org.jboss.as] (Controller Boot Thread) WFLYSRV0025: WildFly Full 29.0.0.Final (WildFly Core 21.1.0.Final) started in 1347ms - Started 159 of 163 services (30 services are lazy, passive or on-demand) - Server configuration file in use: standalone.xml
```

which contains both your [Jakarta EE](https://jakarta.ee/) application and [WildFly](https://www.wildfly.org/) ready to use:

```shell
curl http://127.0.0.1:8080/hello
Hello from WildFly bootable jar!
```

Actually it's even more exciting than that, because you can also "trim" [Jakarta EE](https://jakarta.ee/) and obtain a super light jar which starts really fast;

For example, if you just use `jaxrs`, you can prune everything else (e.g. JPA, EJB, JMS, etc.) and the final `target/myapp-bootable.jar` will be approximately 1/4 the size of the full [WildFly](https://www.wildfly.org/) server; 

In addition to [Jakarta EE](https://jakarta.ee/), [WildFly](https://www.wildfly.org/) also supports [MicroProfile](https://microprofile.io/); this means that, e.g. you can create an application that consumes JTW tokens! 


## Cloud: Kubernetes and OpenShift

[WildFly](https://www.wildfly.org/) has made it super easy to deploy on Kubernetes and OpenShift: more on this in later chapters;


