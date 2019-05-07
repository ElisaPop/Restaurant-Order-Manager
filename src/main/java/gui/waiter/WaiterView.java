package gui.waiter;

import bll.Order;
import bll.composite.MenuItem;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import java.util.Collection;

class WaiterView {

    private static ScrollPane table = new ScrollPane();
    private static GridPane newGrid = new GridPane();
    static GridPane innerGrid = new GridPane();
    private static GridPane editGrid = new GridPane();
    private static Label orderLabel = new Label("Orders");
    private static Region gridRegionH = new Region();
    private static Region gridRegionW = new Region();
    private static Region hBoxRegion = new Region();
    private static Region wBoxRegion = new Region();

    private static Label orderNrLabel = new Label("Order:");
    static ComboBox<MenuItem> menuCombo = new ComboBox<>();
    static ComboBox<Order> orderCombo = new ComboBox<>();
    static Button printButton = new Button("Print");
    static Button newOrderButton = new Button("New Order");
    static Button addButton = new Button("Add");

    static Label customersMenuLabel = new Label("Menu");
    private static Label ordersMenuLabel = new Label("Orders");
    static Label chefMenuLabel = new Label("Chef");
    private static Menu menu1 = new Menu("", customersMenuLabel);
    private static Menu menu2 = new Menu("", ordersMenuLabel);
    private static Menu menu3 = new Menu("", chefMenuLabel);

    private static MenuBar menuBar = new MenuBar();

    BorderPane interfaceUI() {
        newGrid.getChildren().clear();
        editGrid.getChildren().clear();
        innerGrid.getChildren().clear();
        menuBar.getMenus().clear();
        newGrid.setHgap(10);
        newGrid.setVgap(10);
        newGrid.setPadding(new Insets(10, 10, 10, 10));

        orderNrLabel.setPrefWidth(70);
        menuCombo.setPrefWidth(150);
        orderCombo.setPrefWidth(150);
        printButton.setPrefWidth(70);
        addButton.setPrefWidth(70);

        menuBar.getMenus().addAll(menu1,menu2,menu3);
        table.setPrefWidth(300);
        table.setPrefHeight(400);

        gridRegionH.setPrefHeight(100);
        VBox.setVgrow(gridRegionH, Priority.ALWAYS);

        gridRegionW.setPrefWidth(40);
        VBox.setVgrow(gridRegionW, Priority.ALWAYS);

        orderLabel.setScaleX(4);
        orderLabel.setScaleY(4);
        orderLabel.setAlignment(Pos.CENTER);

        newGrid.add(gridRegionH, 0, 0);
        newGrid.add(orderLabel, 1, 1,2,1);
        newGrid.add(table, 1, 3,4,4);
        newGrid.setAlignment(Pos.CENTER);

        editGrid.add(orderNrLabel,0,0);
        editGrid.add(orderCombo,1,0);
        editGrid.add(printButton,2,0);
        editGrid.add(menuCombo,1,1);
        editGrid.add(addButton,2,1);
        editGrid.add(newOrderButton,0,1);

        table.setContent(innerGrid);

        hBoxRegion.setPrefWidth(200);
        HBox.setHgrow(hBoxRegion, Priority.ALWAYS);

        wBoxRegion.setPrefWidth(200);
        HBox.setHgrow(wBoxRegion, Priority.ALWAYS);

        editGrid.setVgap(10);
        editGrid.setPadding(new Insets(10, 10, 10, 10));
        editGrid.setAlignment(Pos.CENTER);
        editGrid.setVgap(5);

        editGrid.setBackground(new Background(new BackgroundFill(Color.rgb(35, 45, 55), CornerRadii.EMPTY, Insets.EMPTY)));

        BorderPane borderPane = new BorderPane();

        VBox mainBox = new VBox();

        mainBox.getChildren().addAll(hBoxRegion,newGrid,wBoxRegion,editGrid);
        borderPane.setCenter(mainBox);
        borderPane.setTop(menuBar);

        borderPane.setPrefSize(1200,800);

        borderPane.setStyle("-fx-base: rgb(80,91,107);" +
                "    -fx-background: rgb(44,51,61);");
        return borderPane;
    }

    static void addPane(Order obj, Collection<MenuItem> itemCollect, int row)
    {
        String newString = "Order #" + obj.getId() + "         " + obj.date.toString();
        TextArea newContent = new TextArea();
        StringBuilder textString = new StringBuilder();
        double totalPrice = 0;
        for(MenuItem a: itemCollect)
        {
            totalPrice += a.computePrice();
            textString.append(a.getName()).append("\n");
        }

        textString.append("                       Total: ").append(Double.toString(totalPrice));
        newContent.setText(textString.toString());
        newContent.setEditable(false);
        newContent.setMaxWidth(270);
        TitledPane newPane;
        newPane = new TitledPane(newString, newContent);
        newPane.setExpanded(false);

        innerGrid.add(newPane,0,row);
    }


}
