package rest;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import dao.DaoFactory;
import dao.PackageDao;

import model.Package;

@Path("/packages")
public class PackageResource {

	private PackageDao pdao = DaoFactory.getInstance().getPackageDao();
	@Context UriInfo uriInfo;
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response addPackage(JAXBElement<Package> packageXML) {
		
		Response response = null;
		Package pac = packageXML.getValue();
		int package_id = pac.getPackage_id();
		if (package_id != -1) {
			pdao.updatePackage(pac);
			response = Response.noContent().build(); 
		} else {
			pdao.addPackage(pac);
			response = Response.created(uriInfo.getAbsolutePath()).build();
		}
		return response;
	}
	
	@GET
	@Path("{tracking_num}")
	@Produces(MediaType.TEXT_XML)
	public Package getPackageByTracking_num(@PathParam("tracking_num") int tracking_num) {
		return pdao.getPackageByTrackingNum(tracking_num);
	}
	
	/* Only for test purpose */
	@GET
	@Produces(MediaType.TEXT_XML)
	public ArrayList<Package> getAllPackages() {
		return pdao.getAllPackages();
	}
	
	// This method has been commented because of 
	// name collision
	/*@GET
	@Path("{id}")
	@Produces(MediaType.TEXT_XML)
	public Package getPackageById(@PathParam("id") int package_id) {
		return pdao.getPackageById(package_id);
	}*/
	
}
