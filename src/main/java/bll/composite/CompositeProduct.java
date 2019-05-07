package bll.composite;

import java.util.LinkedList;

public class CompositeProduct extends MenuItem {

    private static final long serialVersionUID = 1L;
    private LinkedList<MenuItem> listOfProducts= new LinkedList<>();

    @Override
    public double computePrice() {

        double finalPrice = 0;

        for(MenuItem a : listOfProducts) {
            finalPrice += a.computePrice();
        }

        return finalPrice;
    }


    public void removeProduct(MenuItem product) {
        listOfProducts.removeIf(e-> e.equals(product));
    }

    public MenuItem addProduct(MenuItem product) {
        LinkedList<MenuItem> menuList = product.getProduct();

        listOfProducts.addAll(menuList);

        return this;
    }

    public LinkedList<MenuItem> getProduct(){
        return this.listOfProducts;
    }

    @Override
    public boolean isWellFormed() {
        if(this.listOfProducts == null)
            return false;
        if(this.listOfProducts.size() < 2)
            return false;
        for(MenuItem a : this.listOfProducts)
        {
            if(!a.isWellFormed())
                return false;
        }
        return true;
    }

    public String getName() {
        int cnt = 0;
        StringBuilder resultString = new StringBuilder();
        for(MenuItem a : listOfProducts) {
            if(cnt == 0) resultString.append(a.getName());
            else resultString.append(" + ").append(a.getName());
            cnt++;
        }

        return resultString.toString();
    }

    public Long getId()
    {
        return Long.valueOf(0);
    }


}
