package pojo;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 	购物车：购物车中有很多种商品
 */
public class Cart {
	
//    private Integer totalCount;
//    private BigDecimal totalPrice;

    /**
     * 	专门存放购物车项
     */
    private Map<Integer,CartItem> items = new ConcurrentHashMap<Integer,CartItem>();

    /**
     * 	添加商品到购物车中
     */
    public void addItem(CartItem cartItem) {
        //items中查找当前添加的商品
    	CartItem item = items.get(cartItem.getId());

        if (item == null) {
            //items没有当前添加的商品，添加到items
            items.put(cartItem.getId(), cartItem);
        } else {
            //items中有此商品，数量+1
            item.setCount( item.getCount() + 1 ); 
            //items的商品总价重新设置：单价*数量
            item.setTotalPrice( item.getPrice().multiply(new BigDecimal( item.getCount() )) ); 
        }

    }

    /**
     * 	购物车中删除某个商品
     */
    public void deleteItem(Integer id) {
        items.remove(id);
    }


    /**
     * 	清除购物车中的所有商品
     */
    public void clear() {
        items.clear();
    }

    /**
     * 	更改购物车中某个商品的数量
     */
    public void updateCount(Integer id,Integer count) {
    	//items中查找当前修改的商品
        CartItem cartItem = items.get(id);
        if (cartItem != null) {
        	/*
        	 * 	确定当前商品存在购物车中，更改数量的同时，商品总价也进行更改
        	 */
            cartItem.setCount(count);
            cartItem.setTotalPrice( cartItem.getPrice().multiply(new BigDecimal( cartItem.getCount() )) ); 
        }
    }

    /*
     * 	通过items获取购物车的商品数量
     */
    public Integer getTotalCount() {
        Integer totalCount = 0;
        //items遍历，每一种商品的数量添加到totalCount
        for (Map.Entry<Integer,CartItem>entry : items.entrySet()) {
            totalCount += entry.getValue().getCount();
        }
        return totalCount;
    }

    /*
     * 	通过items获取购物车的商品总价
     */
    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        //items遍历,  每一种商品的总价添加到totalPrice
        for (Map.Entry<Integer,CartItem>entry : items.entrySet()) {
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }


    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

	@Override
	public String toString() {
		return "Cart [totalCount=" + getTotalCount() + ", totalPrice=" + getTotalPrice()
		+ ", items=" + items + "]";
	}

}
