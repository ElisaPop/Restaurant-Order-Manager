package gui.admin;

import bll.composite.MenuItem;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.StringConverter;

class AdminView {

    static TableView<MenuItem> table = new TableView<>();
    private static GridPane newGrid = new GridPane();
    private static Label productsLabel = new Label("Menu");
    private static Region gridRegionH = new Region();
    private static Region gridRegionW = new Region();
    private static Region hBoxRegion = new Region();
    private static Region wBoxRegion = new Region();

    private static Label customersMenuLabel = new Label("Menu");
    static Label ordersMenuLabel =new Label("Orders");
    static Label chefMenuLabel =new Label("Chef");
    private static Menu menu1 = new Menu("", customersMenuLabel);
    private static Menu menu2 = new Menu("", ordersMenuLabel);
    private static Menu menu3 = new Menu("", chefMenuLabel);
    private static MenuBar menuBar = new MenuBar();

    private static GridPane editGrid = new GridPane();

    static TableColumn<MenuItem,Long> idCol = new TableColumn<>("ID");
    static TableColumn<MenuItem,String> nameCol = new TableColumn<>("Name");
    static TableColumn<MenuItem,Double> valueCol = new TableColumn<>("Price" );

    private static Label idLabel = new Label("ID");
    private static Label nameLabel = new Label("Name");
    private static Label amountLabel = new Label("Price");


    static TextField id = new TextField();
    static TextField nameText = new TextField();
    static TextField valueText = new TextField();

    static Button addButton = new Button("Add");
    static Button editButton = new Button("Edit");
    static Button deleteButton = new Button("Delete");
    static Button clearButton = new Button("Clear");
    static ComboBox<MenuItem> idSelect = new ComboBox<>(AdminController.tableList);
    static ComboBox<MenuItem> menuCombo2 = new ComboBox<>(AdminController.tableList);
    static ComboBox<MenuItem> menuCombo1 = new ComboBox<>(AdminController.tableList);
    static Button combineButton = new Button("Combine");


    BorderPane interfaceUI() {

        table.getItems().clear();
        table.getColumns().clear();

        menuBar.getMenus().clear();
        newGrid.getChildren().clear();
        editGrid.getChildren().clear();

        newGrid.setHgap(10);
        newGrid.setVgap(10);
        newGrid.setPadding(new Insets(10, 10, 10, 10));

        menuBar.getMenus().addAll(menu1,menu2,menu3);

        idCol.setPrefWidth(100);
        nameCol.setPrefWidth(300);
        valueCol.setPrefWidth(200);

        table.getColumns().addAll(idCol, nameCol,valueCol);
        table.setPrefWidth(600);
        table.setPrefHeight(400);

        gridRegionH.setPrefHeight(100);
        VBox.setVgrow(gridRegionH, Priority.ALWAYS);

        gridRegionW.setPrefWidth(40);
        VBox.setVgrow(gridRegionW, Priority.ALWAYS);

        productsLabel.setScaleX(4);
        productsLabel.setScaleY(4);
        productsLabel.setAlignment(Pos.CENTER);

        newGrid.add(gridRegionH, 0, 0);
        newGrid.add(productsLabel, 1, 1,2,1);
        newGrid.add(table, 1, 3,4,4);
        newGrid.setAlignment(Pos.CENTER);

        hBoxRegion.setPrefWidth(200);
        HBox.setHgrow(hBoxRegion, Priority.ALWAYS);

        wBoxRegion.setPrefWidth(200);
        HBox.setHgrow(wBoxRegion, Priority.ALWAYS);

        editGrid.setVgap(10);
        editGrid.setPadding(new Insets(10, 10, 10, 10));
        editGrid.setAlignment(Pos.CENTER);

        id.setEditable(false);
        idLabel.setAlignment(Pos.CENTER);
        nameLabel.setAlignment(Pos.CENTER);
        amountLabel.setAlignment(Pos.CENTER);

        editGrid.add(idSelect,5,0,2,1);
        editGrid.add(editButton,7,0);
        editGrid.add(deleteButton,8,0);

        editGrid.add(idLabel,0,1);
        editGrid.add(nameLabel,2,1);
        editGrid.add(amountLabel,4,1);

        id.setMaxWidth(100);
        idSelect.setMaxWidth(100);
        addButton.setAlignment(Pos.CENTER);

        menuCombo2.setMaxWidth(100);
        menuCombo1.setMaxWidth(100);
        combineButton.setAlignment(Pos.CENTER);

        editGrid.add(id,0,2);
        editGrid.add(nameText,2,2);
        editGrid.add(valueText,4,2);
        editGrid.add(addButton,5,3,1,3);
        editGrid.add(clearButton,6,3,1,3);
        editGrid.add(menuCombo1,0,8);
        editGrid.add(menuCombo2,2,8);
        editGrid.add(combineButton,4,8,1,1);

        editGrid.setBackground(new Background(new BackgroundFill(Color.rgb(35, 45, 55), CornerRadii.EMPTY, Insets.EMPTY)));

        BorderPane borderPane = new BorderPane();

        VBox mainBox = new VBox();
        mainBox.getChildren().addAll(newGrid, editGrid);
        borderPane.setCenter(mainBox);
        borderPane.setTop(menuBar);

        borderPane.setPrefSize(1200,800);

        borderPane.setStyle("-fx-base: rgb(80,91,107);" +
                "    -fx-background: rgb(44,51,61);");
        return borderPane;
    }




}
