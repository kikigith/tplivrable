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
import com.bootcamp.tp.entities.Etat;
import com.bootcamp.tp.entities.Livrable;
import com.fasterxml.jackson.databind.ObjectMapper;
//import static com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat.URI;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

@Path("/")
@Api(value = "/livrables", description = "Service qui gere les livrables ")
public class LivrableRestService {

    //instanciation d'un livrable repository
    LivrableRepository livrableRepository = new LivrableRepository("tpservice");

    @GET
    @Path("/livrables/verification")
    @Produces(MediaType.TEXT_PLAIN)
    public Response verificationService(InputStream incomingData) {
        String result = "LivrableService a bien demarre ";

        // retourner une reponse HTTP 200 en cas de succes
        return Response.status(200).entity(result).build();
    }

    @GET
    @Path("/livrables")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(
        value = "list",
        notes = "List des livrables",
        response = Livrable.class,
        responseContainer = "Livrable"
    )
    @ApiResponses({
        @ApiResponse(code = 404, message = "impossible de lister les livrable")
    })
    public Response getList() throws SQLException {

        List<Livrable> livrables = livrableRepository.findAll();
        
        if (livrables != null) {
            return Response.status(200).entity(livrables).build();
        } else {
            return Response.status(404).entity("Aucun Livrable trouve ...").build();
        }
//                return Response.ok(/* JAXB object to represent response body 
//                entity */).expires(/* Expires response header 
//                value*/).header(“CustomHeaderName”, “CustomHeaderValue”).build();
        //return Response.ok(livrables).header(null, this).build();

    }
    
    @Path("/livrable/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(
        value = "getById",
        notes = "Consultation d'un livrable",
        response = Livrable.class,
        responseContainer = "Livrable"
    )
    @ApiResponses({
        @ApiResponse(code = 200, message = "Livrable retrouve"),
        @ApiResponse(code = 404, message = "Livrable non trouve")
    })
    public Response getById(@PathParam("id") int id) throws SQLException {

        Livrable livrable = livrableRepository.findByPropertyUnique("idLivrable", id);

        if (livrable != null) {
            return Response.status(200).entity(livrable).build();
        } else {
            return Response.status(404).entity("Livrable non trouve ...").build();
        }
    }

    // creation de livrable depuis un fichier Json
    @Path("/livrable/addlivrable")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createLivrable(InputStream is) {

        StringBuilder livrBuild = new StringBuilder();

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while ((line = in.readLine()) != null) {
                livrBuild.append(line);
            }
        } catch (Exception e) {
            System.out.println("Erreur du fichier : ");
        }
        System.out.println("Donnees recues : \n" + livrBuild.toString());

        // retourner une reponse HTTP 200 en cas de succes
        return Response.status(200).entity(livrBuild.toString()).build();
    }
    
    @POST
    @Path("/livrable/{id}")
    //    @Produces(MediaType.)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(
        value = "Creation",
        notes = "creation d'un livrable",
        response = Livrable.class,
        responseContainer = "Livrable"
    )
    @ApiResponses({
        @ApiResponse(code = 201, message = "Livrable cree avec succes"),
        @ApiResponse(code = 404, message = "Aucun livrable cree")
    })
    public Response create(@PathParam("id") int idL) {
        String output = " Felicitations creation effectuee avec succes pour id = ";
        try {
            Livrable livrable = livrableRepository.findByPropertyUnique("idLivrable", idL);
            Response reponse = Response.status(200).entity(output + livrable.getIdLivrable()).build();
            livrableRepository.update(livrable);
            return reponse;
        } catch (SQLException ex) {
            return Response.status(404).entity("Erreur: Objet non cree").build();
        }

    }
    
    @PUT
    @Path("/livrable/{id}")
    //    @Produces(MediaType.)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(
        value = "modification",
        notes = "mis a jour d'un livrable",
        response = Livrable.class,
        responseContainer = "Livrable"
    )
    @ApiResponses({
        @ApiResponse(code = 201, message = "Livrable mis a jour avec succes"),
        @ApiResponse(code = 404, message = "Aucune mis a jour effectue")
    })
    public Response update(@PathParam("id") int idL) {
        String output = " Felicitations Mise a jour effectuee avec succes pour id = ";
        try {
            Livrable livrable = livrableRepository.findByPropertyUnique("idLivrable", idL);
            Response reponse = Response.status(200).entity(output + livrable.getIdLivrable()).build();
            livrableRepository.update(livrable);
            return reponse;
        } catch (SQLException ex) {
            return Response.status(404).entity("Erreur: Objet non mis a jour").build();
        }

    }
    
    @DELETE
    @Path("/livrable/{id}")
    //    @Produces(MediaType.)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(
        value = "suppression",
        notes = "Suppression d'un livrable",
        response = Livrable.class,
        responseContainer = "Livrable"
    )
    @ApiResponses({
        @ApiResponse(code = 201, message = "Livrable supprime avec succes"),
        @ApiResponse(code = 404, message = "Aucun suppression effectue")
    })
    public Response delete(@PathParam("id") int idL) {
        String output = " Felicitations supppression effectuee avec succes pour id = ";
        try {
            Livrable livrable = livrableRepository.findByPropertyUnique("idLivrable", idL);
            Response reponse = Response.status(200).entity(output + livrable.getIdLivrable()).build();
            livrableRepository.delete(livrable);
            return reponse;
        } catch (SQLException ex) {
            return Response.status(404).entity("Erreur: Objet non supprime").build();
        }

    }

    //pagination, token, filters, tri

}
