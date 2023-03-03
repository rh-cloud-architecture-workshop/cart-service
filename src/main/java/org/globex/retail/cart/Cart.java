package org.globex.retail.cart;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Cart {

    private final String cartId;

    private final List<CartItem> items = new ArrayList<>();

    public Cart(String cartId) {
        this.cartId = cartId;
    }

    public String getCartId() {
        return cartId;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void addItem(CartItem itemToAdd) {
        Optional<CartItem> itemInCart = items.stream().filter(cartItem -> cartItem.getProductId().equals(itemToAdd.getProductId())).findFirst();
        if (itemInCart.isPresent()) {
            CartItem item = itemInCart.get();
            item.setProductName(itemToAdd.getProductName());
            item.setQuantity(item.getQuantity() + itemToAdd.getQuantity());
            item.setPrice(itemToAdd.getPrice());
        } else {
            items.add(itemToAdd);
        }
    }

    public void removeItem(CartItem itemToRemove) {
        Optional<CartItem> itemInCart = items.stream().filter(cartItem -> cartItem.getProductId().equals(itemToRemove.getProductId())).findFirst();
        if (itemInCart.isPresent()) {
            CartItem item = itemInCart.get();
            item.setQuantity(item.getQuantity() - itemToRemove.getQuantity());
            if (item.getQuantity() <= 0) {
                items.remove(item);
            }
        }
    }

}
