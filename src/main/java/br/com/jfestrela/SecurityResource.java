package br.com.jfestrela;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.jfestrela.service.AccountDetails;
import br.com.jfestrela.service.SecurityService;

@Path("/account")
public class SecurityResource {
	
	@Inject
	private SecurityService service;

    @GET
    @Path("/")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response hello() {
        return Response.ok(getGenericEntity(service.account())).build();
    }
    
    @GET
    @Path("/details")
    @RolesAllowed("USER") 
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response helloApresentation() {
      return Response.ok(getGenericEntity(service.accountDetails())).build();
    }
    
    @GET
    @Path("/details/full")
    @RolesAllowed("ADMIN") 
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response greeting() {
        return Response.ok(getGenericEntity(service.accountDetailsFull())).build();
    }
    
    private GenericEntity<AccountDetails> getGenericEntity(AccountDetails entity){
    	return new GenericEntity<AccountDetails>(entity, AccountDetails.class);
    }
}