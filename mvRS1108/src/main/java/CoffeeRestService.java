import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.Coffee;

@Path("/coffee")
public class CoffeeRestService {

	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String postCoffee(Coffee cf) {
		String result = "Record entered: "+ cf.toString();
        System.out.println(result);
		return "OK";
	}
}
