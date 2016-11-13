package de.doCode.homework;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/PersonService")
public class JaxRsActivator extends Application {
	/* only to activate without web.xml */
}