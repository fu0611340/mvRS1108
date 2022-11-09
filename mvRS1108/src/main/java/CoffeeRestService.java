import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.CoffeeDao;
import model.Coffee;

@Path("/coffee")
public class CoffeeRestService {

	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String postCoffee(Coffee cf) throws SQLException {
		String result = "Record entered: "+ cf.toString();
        System.out.println(result);
        int r = new CoffeeDao().InsertCoffee(cf);
        if(r > 0)
        	result = "Insert Succeed";
        else
        	result = "Insert Failed";
        return result;
	}
	
	@POST
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateCoffee(Coffee cf) throws Exception {
		String result = "Record entered: "+ cf.toString();
        System.out.println(result);
        int r = new CoffeeDao().updateCoffeeSales(cf);
        if(r > 0) {
        	return "Update Succeed";
        }
        else {
        	return "Update Failed";
        }
	}
	
	@POST
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteCoffee(Coffee cf) {
		String result = null;
		System.out.println("Record Entered: "+cf.toString());
		int r = new CoffeeDao().deleteCoffee(cf);
		if(r > 0)
			result = "Delete Succeed";
		else
			result = "Delete Failed";
		return result;
	}
}
