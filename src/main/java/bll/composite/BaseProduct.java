package bll.composite;

import java.util.LinkedList;

public class BaseProduct extends MenuItem{

    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private double price;

    public BaseProduct(Long id, String name, double price)
    {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public BaseProduct(String name, double price)
    {
        this.name = name;
        this.price = price;
    }

    @Override
    public double computePrice() {
        return this.price;
    }

    public Long getId() {
        return id;
    }

    @Override
    public void removeProduct(MenuItem product) {

    }

    @Override
    public MenuItem addProduct(MenuItem product) {
        CompositeProduct newComp = new CompositeProduct();
        newComp.addProduct(this);
        newComp.addProduct(product);
        return newComp;
    }

    @Override
    public LinkedList<MenuItem> getProduct() {
        LinkedList<MenuItem> newLinked = new LinkedList<>();
        newLinked.add(this);

        return newLinked;
    }

    @Override
    public boolean isWellFormed() {
        return !(this.price < 0) && !this.name.isEmpty();
    }

    public double getPrice() {
        return this.price;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int hashCode() {
        int result = ((int)price ^ ((int)price >>> 32));
        result = 31 * result + name.hashCode();
        return result;
    }

}
