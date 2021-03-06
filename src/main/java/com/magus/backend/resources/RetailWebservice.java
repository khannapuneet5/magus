package com.magus.backend.resources;

import java.io.IOException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.magus.backend.client.RetailAPIClient;
import com.magus.backend.model.AccountBalance;
import com.magus.backend.model.AccountSummary;
import com.magus.backend.model.BranchAtmLocator;
import com.magus.backend.model.TransactionHistory;

@Path("/retail")
public class RetailWebservice extends AbstractService {

	RetailAPIClient client = new RetailAPIClient();
	
	@GET
	@Path("/accountSummary")
	@Produces(MediaType.APPLICATION_JSON)
	public AccountSummary getMessages(@QueryParam ("accountNumber") String accNo, @QueryParam("customerId") String custId) throws JsonParseException, JsonMappingException, IOException{
		return convertToJSON(client.accountSummary(accNo, custId), AccountSummary.class);
	}
	
	@GET
	@Path("/balance")
	@Produces(MediaType.APPLICATION_JSON)
	public AccountBalance getAccountBalance(@QueryParam("accountNumber") String accNo) throws JsonParseException, JsonMappingException, IOException{
		return convertToJSON(client.balanceEnquiry(accNo), AccountBalance.class);
	}
	
	@GET
	@Path("/miniStatement")
	@Produces(MediaType.APPLICATION_JSON)
	public TransactionHistory getMiniStetement(@QueryParam("accountNumber") String accNo) throws JsonParseException, JsonMappingException, IOException {
		return convertToJSON(client.miniStatement(accNo), TransactionHistory.class);
	}
	
	@GET
	@Path("/atmLocator")
	@Produces(MediaType.APPLICATION_JSON)
	public BranchAtmLocator getAtmLocator(@QueryParam("lat") String latitude, @QueryParam("long") String longitude) throws JsonParseException, JsonMappingException, IOException{
		return convertToJSON(client.branchAtmLocator(latitude, longitude), BranchAtmLocator.class);
	}
	
	
}
