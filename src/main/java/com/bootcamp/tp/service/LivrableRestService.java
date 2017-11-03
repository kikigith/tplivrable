package com.bootcamp.tp.service;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.bootcamp.tp.dao.LivrableRepository;
import com.bootcamp.tp.entities.Livrable;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@Path("/livrables")
@Api(value="/livrables",description="Api gérant  les livrables ")
public class LivrableRestService {
	
	//instanciation d'un livrable repository

	LivrableRepository livrableRepository=new LivrableRepository("tpservice");

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(
			value="list",
			notes="List des livrables",
			response=Livrable.class,
			responseContainer="Livrable"
			)
	@ApiResponses({
	     @ApiResponse(code=404, message="impossible de lister les livrable")
	})
	public Response getList() throws SQLException{

		List<Livrable> livrables= livrableRepository.findAll();
		return Response.status(200).entity(livrables).build();

	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(
			value="getById",
			notes="Consultation d'un livrable",
			response=Livrable.class,
			responseContainer="Livrable"
			)
	@ApiResponses({
            @ApiResponse(code=200, message="Livrable retrouvé"),
	    @ApiResponse(code=404, message="Livrable non trouvé")
	})
	public Response getById(@PathParam("id") int id) throws SQLException {

		Livrable livrable = livrableRepository.findByPropertyUnique("idLivrable", id);

		if(livrable != null)
			return Response.status(200).entity(livrable).build();
		else
			return Response.status(404).entity(livrable).build();
	}

	@POST
	@Path("/create")
	//    @Produces(MediaType.)
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(
			value="create",
			notes="Ajouter un nouveau livrable",
			response=Livrable.class,
			responseContainer="Livrable"
			)
	@ApiResponses({
	     @ApiResponse(code=201, message="Ajout avec succès"),
	     @ApiResponse(code=404, message="impossible d'ajouter ce livrable")
	})
	public Response create(Livrable livrable) {
		String output = " Félicitations objet créé avec succès : ";
		try {
			livrableRepository.create(livrable);
			return Response.status(200).entity(output + livrable.getReference()).build(); 
		} catch (SQLException ex) {
			return Response.status(404).entity("Erreur: Objet non créé").build();

		}


	}

	@PUT
	@Path("/update")
	//    @Produces(MediaType.)
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(
			value="update",
			notes="mettre à jour un livrable",
			response=Livrable.class,
			responseContainer="Livrable"
			)
	@ApiResponses({
	     @ApiResponse(code=201, message="Livrable mis à jour avec succès"),
	     @ApiResponse(code=404, message="Aucun livrable dans la période définie")
	})
	public Response update(Livrable livrable) {
		String output = " Félicitations Mise à jour effectuée avec succès pour : ";
		try {
			livrableRepository.update(livrable);
			return Response.status(200).entity(output + livrable.getReference()).build();
		} catch (SQLException ex) {
			return Response.status(404).entity("Erreur: Objet non mis à jour").build();
		}


	}
	
	
	@DELETE
	@Path("/delete")
	//    @Produces(MediaType.)
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(
			value="suppression",
			notes="Suppression d'un livrable",
			response=Livrable.class,
			responseContainer="Livrable"
			)
	@ApiResponses({
	     @ApiResponse(code=201, message="Livrable mis à jour avec succès"),
	     @ApiResponse(code=404, message="Aucun livrable dans la période définie")
	})
	public Response delete(@QueryParam("idL") int idL) {
		String output = " Félicitations supppression effectuée avec succès pour : ";
		try {
			Livrable livrable=livrableRepository.findByPropertyUnique("id", idL);
			Response reponse=Response.status(200).entity(output + livrable.getReference()).build();
			livrableRepository.delete(livrable);
			return reponse;
		} catch (SQLException ex) {
			return Response.status(404).entity("Erreur: Objet non mis à jour").build();
		}


	}
	
	//pagination, token, filters, tri
}
