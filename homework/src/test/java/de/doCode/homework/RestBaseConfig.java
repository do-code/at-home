package de.doCode.homework;

import org.apache.commons.lang3.StringUtils;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunAsClient
@RunWith(Arquillian.class)
public abstract class RestBaseConfig {

	private static final Logger baseLogger = LoggerFactory.getLogger(RestBaseConfig.class);
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected static final String PERSON_SERVICE = "PersonService";

	@Deployment(testable = false)
	public static WebArchive deploy() {
		final boolean RECURSIVE = true;
		WebArchive result = ShrinkWrap.create(WebArchive.class, "test.war")
				.addPackages(RECURSIVE, JaxRsActivator.class.getPackage()).addClass(UnitTestHashMapDatabase.class)
				.addAsResource("log4j.properties")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsManifestResource("test-persistence.xml", "persistence.xml")
				.addAsResource("test-persistence.xml", "META-INF/persistence.xml")
				.addClass(StringUtils.class);

		baseLogger.info("ShrinkWrap create this: " + result.toString(true));
		return result;
	}

}
