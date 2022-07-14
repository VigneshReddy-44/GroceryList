import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Basket {
    private final String name;
    private final Map<StockItem, Integer> list;

    public Basket(String name) {
        this.name = name;
        this.list = new HashMap<>();
    }
    public int addToBasket(StockItem item, int quantity){
        if((item!=null) && (quantity>0)){
            int inBasket= list.getOrDefault(item,0);
            list.put(item,inBasket+quantity);
            return inBasket;
        }
        return 0;
    }
    public Map<StockItem, Integer> Items(){
        return Collections.unmodifiableMap(list);
    }
    @Override
    public String toString(){
        String s="\nShopping basket "+name+" contains "+list.size()+" items\n";
        double totalCost=0.0;
        for(Map.Entry<StockItem,Integer> item: list.entrySet()){
            s=s+item.getKey()+" . "+item.getValue()+" purchased\n";
            totalCost+=item.getKey().getPrice()* item.getValue();
        }
        return s+"Total cost "+totalCost;
    }
    public void checkOut() {
        for (Map.Entry<StockItem, Integer> map : list.entrySet()) {
            int remainingQuantity = map.getKey().quantityInStock() - map.getKey().getReserved();
            map.getKey().setQuantityStock(remainingQuantity);
        }
    }
    public void unReserve(String name,int quantity){
        StockItem stockItem=null;
        for(StockItem stockItem1: list.keySet()){
            if(stockItem1.getName().equals(name)){
                stockItem=stockItem1;break;
            }
        }
        int x=list.getOrDefault(stockItem,-1);
        if(x!=-1 && quantity<= stockItem.getReserved()){
            stockItem.setReserved(stockItem.getReserved()-quantity);
        }
    }
}
