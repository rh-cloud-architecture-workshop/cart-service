package org.globex.retail.cart;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/cart")
public class CartResource {

    @Inject
    CartService cartService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{cartId}")
    public Response getCart(@PathParam("cartId") String cartId) {
        return Response.ok(cartService.getCart(cartId)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{cartId}")
    public Response addItemToCart(@PathParam("cartId") String cartId, CartItem cartItem) {
        if (cartItem == null) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.ok(cartService.addItemToCart(cartId, cartItem)).build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{cartId}")
    public Response removeItemFromCart(@PathParam("cartId") String cartId, CartItem cartItem) {
        if (cartItem == null) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.ok(cartService.removeItemFromCart(cartId, cartItem)).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/empty/{cartId}")
    public Response emptyCart(@PathParam("cartId") String cartId) {
        return Response.ok(cartService.emptyCart(cartId)).build();
    }
}
