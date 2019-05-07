package gui.waiter;

import bll.Order;
import bll.composite.MenuItem;
import gui.admin.AdminController;
import gui.chef.ChefController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.util.*;

import bll.Restaurant;

public class WaiterController{
    private Scene sceneCustomers;
    static Restaurant restaurant = new Restaurant();
    private ObservableList tableList;
    private HashMap<Order, LinkedList<MenuItem>> orderMap = new HashMap<>(restaurant.getOrders());
    private HashSet<MenuItem> menuList;

    public WaiterController()
    {
        WaiterView nView = new WaiterView();
        this.setScene(new Scene(nView.interfaceUI()));
        setTheConverter(WaiterView.menuCombo);
        setOrderConverter(WaiterView.orderCombo);
        ChefController aNew = new ChefController();
        menuList = restaurant.getMenu();
        restaurant.addObserver(aNew);

        WaiterView.customersMenuLabel.setOnMouseClicked(event -> {
            AdminController pCtrl = new AdminController();
            Scene newScene = pCtrl.getScene();
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(newScene);
        });
        WaiterView.chefMenuLabel.setOnMouseClicked(event -> {
            ChefController pCtrl = new ChefController();
            Scene newScene = pCtrl.getScene();
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(newScene);
        });

        WaiterView.newOrderButton.setOnMouseClicked(event -> {
            restaurant.addOrder();
            setTable();
        });
        WaiterView.addButton.setOnMouseClicked(event -> {
            Order gottenKey = WaiterView.orderCombo.getValue();
            LinkedList<MenuItem> gottenList = orderMap.get(gottenKey);
            gottenList.add(WaiterView.menuCombo.getValue());
            orderMap.put(gottenKey,gottenList);
            setTable();
        });
        WaiterView.printButton.setOnMouseClicked(event -> restaurant.printOrder(WaiterView.orderCombo.getValue()));

        menuList = restaurant.getMenu();
        List<MenuItem> list = new ArrayList<>(menuList);
        this.tableList = FXCollections.observableList(list);
        setTable();
    }

    private void setTable()
    {
        orderMap = new HashMap<>(restaurant.getOrders());
        WaiterView.innerGrid.getChildren().clear();
        for (Order key : orderMap.keySet()) {
            WaiterView.addPane(key,orderMap.get(key),key.getId());
        }

        WaiterView.menuCombo.setItems(tableList);
        List<Order> newList = new ArrayList<>(orderMap.keySet());
        ObservableList<Order> obsList = FXCollections.observableList(newList);
        WaiterView.orderCombo.setItems(obsList);
    }

    /**
     * Method used to get the scene for other packages.
     *
     * @return the scene tied to this controller.
     */
    public Scene getScene() {
        return this.sceneCustomers;
    }

    /**
     * Method used to change between scenes.
     */
    private void setScene(Scene sceneCustomers) {
        this.sceneCustomers = sceneCustomers;
    }

    private void setTheConverter(ComboBox<MenuItem> newCombobox)
    {
        newCombobox.setConverter(new StringConverter<MenuItem>() {

            @Override
            public String toString(MenuItem object) {
                return object.getName();
            }

            @Override
            public MenuItem fromString(String string) {
                for( MenuItem a : menuList)
                {
                    if (a.getName().equals(string))
                        return a;
                }
                return null;
            }
        });
    }
    private void setOrderConverter(ComboBox<Order> newCombobox)
    {
        newCombobox.setConverter(new StringConverter<Order>() {

            @Override
            public String toString(Order object) {
                return Integer.toString(object.getId());
            }

            @Override
            public Order fromString(String string) {
                for( Order a : orderMap.keySet())
                {
                    if (Integer.toString(a.getId()).equals(string))
                        return a;
                }
                return null;
            }
        });
    }
}
