package gui.chef;


import gui.admin.AdminController;
import gui.waiter.WaiterController;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Observable;
import java.util.Observer;

public class ChefController implements Observer {

    private Scene sceneCustomers;

    public ChefController()
    {
        ChefView nView = new ChefView();
        setScene(new Scene(nView.interfaceUI()));

        ChefView.ordersMenuLabel.setOnMouseClicked(event -> {
            WaiterController pCtrl = new WaiterController();
            Scene newScene = pCtrl.getScene();
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(newScene);
        });
        ChefView.customersMenuLabel.setOnMouseClicked(event -> {
            AdminController pCtrl = new AdminController();
            Scene newScene = pCtrl.getScene();
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(newScene);
        });
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

    @Override
    public void update(Observable o, Object arg) {
        ChefView.table.appendText("New Order added (observer) " + arg + "\n");
        System.out.println("New Order added (observer)");
    }
}
