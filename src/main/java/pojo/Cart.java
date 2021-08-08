package pojo;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 	���ﳵ�����ﳵ���кܶ�����Ʒ
 */
public class Cart {
	
//    private Integer totalCount;
//    private BigDecimal totalPrice;

    /**
     * 	ר�Ŵ�Ź��ﳵ��
     */
    private Map<Integer,CartItem> items = new ConcurrentHashMap<Integer,CartItem>();

    /**
     * 	�����Ʒ�����ﳵ��
     */
    public void addItem(CartItem cartItem) {
        //items�в��ҵ�ǰ��ӵ���Ʒ
    	CartItem item = items.get(cartItem.getId());

        if (item == null) {
            //itemsû�е�ǰ��ӵ���Ʒ����ӵ�items
            items.put(cartItem.getId(), cartItem);
        } else {
            //items���д���Ʒ������+1
            item.setCount( item.getCount() + 1 ); 
            //items����Ʒ�ܼ��������ã�����*����
            item.setTotalPrice( item.getPrice().multiply(new BigDecimal( item.getCount() )) ); 
        }

    }

    /**
     * 	���ﳵ��ɾ��ĳ����Ʒ
     */
    public void deleteItem(Integer id) {
        items.remove(id);
    }


    /**
     * 	������ﳵ�е�������Ʒ
     */
    public void clear() {
        items.clear();
    }

    /**
     * 	���Ĺ��ﳵ��ĳ����Ʒ������
     */
    public void updateCount(Integer id,Integer count) {
    	//items�в��ҵ�ǰ�޸ĵ���Ʒ
        CartItem cartItem = items.get(id);
        if (cartItem != null) {
        	/*
        	 * 	ȷ����ǰ��Ʒ���ڹ��ﳵ�У�����������ͬʱ����Ʒ�ܼ�Ҳ���и���
        	 */
            cartItem.setCount(count);
            cartItem.setTotalPrice( cartItem.getPrice().multiply(new BigDecimal( cartItem.getCount() )) ); 
        }
    }

    /*
     * 	ͨ��items��ȡ���ﳵ����Ʒ����
     */
    public Integer getTotalCount() {
        Integer totalCount = 0;
        //items������ÿһ����Ʒ��������ӵ�totalCount
        for (Map.Entry<Integer,CartItem>entry : items.entrySet()) {
            totalCount += entry.getValue().getCount();
        }
        return totalCount;
    }

    /*
     * 	ͨ��items��ȡ���ﳵ����Ʒ�ܼ�
     */
    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        //items����,  ÿһ����Ʒ���ܼ���ӵ�totalPrice
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
