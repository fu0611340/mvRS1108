

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.apache.tomcat.dbcp.pool2.impl.AbandonedConfig;

@ApplicationPath("/api")
public class FirsrRestApp extends Application{
	
	@Override
	public Set<Class<?>> getClasses(){
		return new HashSet(Arrays.asList(CustomerRestService.class, StudentRestService.class, CoffeeRestService.class));
	}

}
