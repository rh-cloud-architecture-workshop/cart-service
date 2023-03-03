package org.globex.retail.cart;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class CartService {

    @Inject
    CartStore cartStore;

    public Cart getCart(String cartId) {
        Cart cart = cartStore.getCart(cartId);
        if (cart == null) {
            cart = new Cart(cartId);
        }
        return cart;
    }

    public Cart addItemToCart(String cartId, CartItem item) {
        Cart cart = cartStore.getCart(cartId);
        if (cart == null) {
            cart = new Cart(cartId);
        }
        cart.addItem(item);
        cartStore.storeCart(cart);
        return cart;
    }

    public Cart removeItemFromCart(String cartId, CartItem item) {
        Cart cart = cartStore.getCart(cartId);
        if (cart == null) {
            cart = new Cart(cartId);
            return cart;
        }
        cart.removeItem(item);
        cartStore.storeCart(cart);
        return cart;
    }

    public Cart emptyCart(String cartId) {
        cartStore.removeCart(cartId);
        return new Cart(cartId);
    }

}
