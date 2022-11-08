import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Student;

@Path("/student")
public class StudentRestService {

	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Student getStudentRecord(){
        Student student = new Student();
        student.setFirstName("Elizabeth");
        student.setLastName("Hayden");
        student.setSchool("Little Flower");
        student.setStandard("One");
        student.setRollNumber(1113);
        return student;
    }
	
	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postStudentRecord(Student student){
        String result = "Record entered: "+ student;
        return Response.status(201).entity(result).build();
    }

}
