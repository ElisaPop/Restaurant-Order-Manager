package bll.composite;

import java.io.Serializable;
import java.util.LinkedList;

public abstract class MenuItem implements Serializable {

    private static final long serialVersionUID = 1L;

    public abstract double computePrice();
    public abstract String getName();
    public abstract Long getId();

    public abstract void removeProduct(MenuItem product);
    public abstract MenuItem addProduct(MenuItem product);
    public abstract LinkedList<MenuItem> getProduct();
    public abstract boolean isWellFormed();

}
