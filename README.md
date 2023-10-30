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
it is called "[Bootable Jar](https://docs.wildfly.org/bootablejar/)" and it means that, after building your application with e.g. maven, you obtain a jar file that can be started like:

```shell
java -jar target/myapp-bootable.jar
```

which contains both your [Jakarta EE](https://jakarta.ee/) application and [WildFly](https://www.wildfly.org/);

Actually it's even more exciting than that, because you can also "trim" [Jakarta EE](https://jakarta.ee/) and obtain a super light jar which starts really fast;

For example, if you just use REST and JPA, you can prune everything else (e.g. EJB layer, JMS layer, etc.) and the final `target/myapp-bootable.jar` will be less than a half the size of the full [WildFly](https://www.wildfly.org/) server; 


# Cloud: Kubernetes and OpenShift

[WildFly](https://www.wildfly.org/) has made it super easy to deploy on Kubernetes and OpenShift: more on this in later chapters;


