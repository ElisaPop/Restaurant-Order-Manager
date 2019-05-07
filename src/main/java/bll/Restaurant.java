package bll;

import bll.composite.BaseProduct;
import bll.composite.MenuItem;
import bll.validators.DoubleValidator;
import dl.RestaurantSerializator;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Restaurant extends Observable implements RestaurantProcessing{

    private int orderCnt;
    private HashMap<Order, LinkedList<MenuItem>> orderMap;
    private HashSet<MenuItem> menuList;
    private RestaurantSerializator menuSerializator;

    public Restaurant() {
        menuSerializator = new RestaurantSerializator("Menu.txt");
        menuList = new HashSet<>();
        menuList = menuSerializator.getMenuItems();
        orderMap = new HashMap<>();
    }


    @Override
    public void addMenuItem(String id, String name, String price)
    {
        assert this.isWellFormed();
        if (name.isEmpty() || price.isEmpty() || !DoubleValidator.validate(price)) {
            utility.MessageBox.alert("ERROR", "One of the required fields is empty or incorrect", "Ok");
        } else {
            double iPrice = Double.parseDouble(price);

            if (id.isEmpty()) {
                BaseProduct newItem = new BaseProduct(name,iPrice);

                if(!menuList.isEmpty())
                    for(MenuItem a : menuList)
                    {
                        if(a.getName().equals( newItem.getName()))
                        {
                            utility.MessageBox.alert("ERROR", "Item already exists.", "Ok");
                            return;
                        }
                    }
                newItem.setId(newItem.hashCode());
                menuList.add(newItem);
                menuSerializator.addMenuItem(menuList);
                System.out.println("Admin Added #"+ newItem.getId()+" BaseItem:" + newItem.getName());

            } else editMenuItem(id, name, iPrice);
        }
    }

    @Override
    public void editMenuItem(String id, String name, double price) {
        Long iId = Long.parseLong(id);
        BaseProduct newItem = new BaseProduct(iId, name, price);
        menuList.removeIf( e -> e.getId().compareTo(iId) == 0);
        menuList.add(newItem);
        menuSerializator.addMenuItem(menuList);
        System.out.println("Admin Edited #"+ newItem.getId()+" BaseItem:" + newItem.getName());
    }

    @Override
    public void deleteMenuItem(MenuItem toDelete) {
        assert toDelete.isWellFormed();
        int size = menuList.size();
        menuList.removeIf(e -> e.getName().equals(toDelete.getName()));
        menuSerializator.addMenuItem(menuList);
        assert menuList.size() == size - 1;
    }

    @Override
    public void addOrder() {
        setChanged();
        Order newOrder = new Order(orderCnt++);
        LinkedList<MenuItem> newCol = new LinkedList<>();
        orderMap.put(newOrder,newCol);
        notifyObservers(newOrder.getId());

    }

    @Override
    public void printOrder(Order givenOrder) {

        int id = givenOrder.getId();
        LinkedList<MenuItem> itemCollect = orderMap.get(givenOrder);

        String textString = "";
        double totalPrice = 0;

        List<String> lines = new ArrayList<>();

        lines.add("Order #" + givenOrder.getId());
        lines.add("" + givenOrder.getDate());

        for(MenuItem a: itemCollect) {
            totalPrice += a.computePrice();
            lines.add(a.getName() + ": " + a.computePrice());
        }
        lines.add("Total: " + totalPrice);

        Path file = Paths.get("Order_#" + givenOrder.getId() + ".txt");

        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
            utility.MessageBox.alert("SUCCESS", "File created", "Ok");
        } catch (IOException e) {
            e.printStackTrace();
            utility.MessageBox.alert("ERROR", "File failed to be created", "Ok");
        }
    }



    private boolean isWellFormed(){
        if(this.orderMap == null || this.menuList == null || this.orderCnt < 0 || menuSerializator == null)
            return false;
        for(Order a: this.orderMap.keySet()) {
            if(!a.isWellFormed()) return false;
            for(MenuItem b: this.orderMap.get(a)){
                if(!b.isWellFormed()) return false;
            }
        }

        for(MenuItem a: this.menuList)
        {
            if(!a.isWellFormed()) return false;
        }

        return true;
    }

    @Override
    public HashSet<MenuItem> getMenu(){
        HashSet<MenuItem> newList;
        newList = menuSerializator.getMenuItems();
        return newList;
    }

    public HashMap<Order,LinkedList<MenuItem>> getOrders(){
        return this.orderMap;
    }

}
