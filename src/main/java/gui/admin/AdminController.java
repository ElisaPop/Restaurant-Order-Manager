package gui.admin;

import bll.Restaurant;
import bll.composite.MenuItem;
import gui.chef.ChefController;
import gui.waiter.WaiterController;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class AdminController {

    private Scene sceneCustomers;
    static ObservableList tableList;
    private HashSet<MenuItem> menuList;

    public AdminController()
    {
        AdminView nView = new AdminView();
        this.setScene(new Scene(nView.interfaceUI()));
        Restaurant restaurant = new Restaurant();
        setTheConverter(AdminView.idSelect);
        setTheConverter(AdminView.menuCombo1);
        setTheConverter(AdminView.menuCombo2);

        AdminView.ordersMenuLabel.setOnMouseClicked(event -> {
            WaiterController pCtrl = new WaiterController();
            Scene newScene = pCtrl.getScene();
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(newScene);
        });
        AdminView.chefMenuLabel.setOnMouseClicked(event -> {
            ChefController pCtrl = new ChefController();
            Scene newScene = pCtrl.getScene();
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(newScene);
        });

        AdminView.addButton.setOnAction(e ->{
            restaurant.addMenuItem(AdminView.id.getText(), AdminView.nameText.getText(), AdminView.valueText.getText());
            tableList.removeAll();
            menuList = restaurant.getMenu();
            tableList.setAll(menuList);
            refreshTable();
        });

        AdminView.combineButton.setOnAction(e ->{
            if(AdminView.menuCombo1.getSelectionModel().isEmpty() || AdminView.menuCombo2.getSelectionModel().isEmpty()) {
                utility.MessageBox.alert("ERROR", "One of the required fields is empty.", "Ok");
            }
            else {
            MenuItem mc2 = AdminView.menuCombo2.getValue();
            MenuItem mc1 = AdminView.menuCombo1.getValue();
            MenuItem mc3 = mc1.addProduct(mc2);

            restaurant.addMenuItem("", mc3.getName() , Double.toString(mc3.computePrice()));
            tableList.removeAll();
            menuList = restaurant.getMenu();
            tableList.setAll(menuList);
            refreshTable();
            }

        });

        AdminView.deleteButton.setOnAction(e ->{
            if(AdminView.idSelect.getSelectionModel().isEmpty()) {
                utility.MessageBox.alert("ERROR", "One of the required fields is empty.", "Ok");
            }
            else {
                MenuItem newItem = AdminView.idSelect.getValue();
                restaurant.deleteMenuItem(newItem);
                tableList.removeAll();
                menuList = restaurant.getMenu();
                tableList.setAll(menuList);
                refreshTable();
            }
        });

        AdminView.editButton.setOnAction(e ->{
            if(AdminView.idSelect.getSelectionModel().isEmpty()) {
                utility.MessageBox.alert("ERROR", "One of the required fields is empty.", "Ok");
            }
            else {
                MenuItem newItem = AdminView.idSelect.getValue();
                AdminView.id.setText(Long.toString(newItem.getId()));
                AdminView.nameText.setText(newItem.getName());
                AdminView.valueText.setText(Double.toString(newItem.computePrice()));
            }
        });

        AdminView.clearButton.setOnAction(e -> {
            AdminView.id.clear();
            AdminView.nameText.clear();
            AdminView.valueText.clear();
        });
        menuList = restaurant.getMenu();
        List<MenuItem> list = new ArrayList<>(menuList);
        tableList = FXCollections.observableList(list);
        setTable(tableList);
    }

    /**
     * Method used to refresh the data from the table.
     */
    private void refreshTable()
    {
        AdminView.table.refresh();
    }

    /**
     * Method used to get the scene for other packages.
     *
     * @return the scene tied to this controller.
     */
    public Scene getScene() {
        return this.sceneCustomers;
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

    /**
     * Method used to change between scenes.
     */
    private void setScene(Scene sceneCustomers) {
        this.sceneCustomers = sceneCustomers;
    }


    private void setTable(ObservableList a)
    {
        AdminView.idCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getId()));
        AdminView.nameCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getName()));
        AdminView.valueCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().computePrice()));
        AdminView.table.setItems(a);
        AdminView.idSelect.setItems(tableList);
        AdminView.menuCombo1.setItems(tableList);
        AdminView.menuCombo2.setItems(tableList);
    }
}
