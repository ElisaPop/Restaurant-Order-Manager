package bll;

import bll.composite.MenuItem;
import java.util.HashSet;

public interface RestaurantProcessing {
    /**
     * @pre hSet != NULL
     * @post getSize() == getSize()@pre + 1
     * @pre !name.isEmpty()
     * @pre price >= 0
     *
     * @param id
     * @param name
     * @param price
     */
    void addMenuItem(String id, String name, String price);

    /**
     * @pre a != NULL
     * @pre !isEmpty()
     * @pre toDelete != NULL
     *
     * @post size = @pre.size() + 1;
     * @param toDelete
     */
    void deleteMenuItem(MenuItem toDelete);

    /**
     * @pre !name.isEmpty()
     * @pre price >= 0
     * @inv !isEmpty()
     * @post getSize() == getSize()@pre + 1

     *
     * @param id
     * @param name
     * @param price
     */
    void editMenuItem(String id, String name, double price);

    /**
     * @pre mSet != NULL
     * @post !isEmpty();
     */
    void addOrder();

    /**
     * @pre mSet != NULL
     * @param givenOrder
     */
    void printOrder(Order givenOrder);

    HashSet<MenuItem> getMenu();

}
