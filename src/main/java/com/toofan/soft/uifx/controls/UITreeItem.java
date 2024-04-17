package com.toofan.soft.uifx.controls;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;

public class UITreeItem extends Node {
    List<UITreeItem> children;
    private final Stage stage;
//    private FXMLComponentLoader loader;
//    private TreeItemController treeItemController;
    private TreeItemComponent treeItemComponent;

    public UITreeItem(Stage stage, int order, String title1, String title2, String title3, boolean checked) {
        this.treeItemComponent = new TreeItemComponent();
//        loader = new FXMLComponentLoader(stage,"components/UITreeItem.fxml", c -> this.treeItemController);

        this.stage = stage;

        this.setOrder(order);
        this.setTitle1(title1);
        this.setTitle2(title2);
        this.setTitle3(title3);
        this.setChecked(checked);

        this.treeItemComponent.setListener((state) -> {
            if(this.hasChildren()) this.getChildren().forEach(item -> item.setChecked(state));
        });

    }

    public Node get() {

//        return loader.getComponent();
        return treeItemComponent;
//        return treeItemComponent.getRootPane();
    }


    public boolean isChecked() {
        return this.treeItemComponent.getStateChBx().isSelected();
    }

    public void setChecked(boolean checked) {
        this.treeItemComponent.getStateChBx().setSelected(checked);
    }

    private boolean hasChildren() {
        return  !this.treeItemComponent.getChildrenBox().getChildren().isEmpty();
    }

    public int getOrder() {
        return Integer.parseInt(this.treeItemComponent.getTitle0().getText());
    }

    public void setOrder(int order) {
        this.treeItemComponent.getTitle0().setText(Integer.toString(order));
    }
    public VBox getParentBox() {
        return this.treeItemComponent.getParentBox();
    }
    public List<UITreeItem> getChildren() {
        return this.children;
    }
    public void setChildren(UITreeItem... children) {
        for (UITreeItem item: children) {
            item.getParentBox().setPadding(new Insets(0, 10.0, 0, 10.0));
            this.treeItemComponent.getChildrenBox().getChildren().add(item.get());
        }
        this.treeItemComponent.getArrowIcon().setVisible(true);
        this.children = Arrays.stream(children).toList();
    }

    public void clear() {
        this.treeItemComponent.getChildrenBox().getChildren().clear();
        this.children.clear();
        this.treeItemComponent.getArrowIcon().setVisible(false);
    }
    public String getTitle1() {
        return this.treeItemComponent.getTitle1().getText();
    }

    public void setTitle1(String title) {
        this.treeItemComponent.getTitle1().setText(title);
    }

    public String getTitle2() {
        return this.treeItemComponent.getTitle2().getText();
    }

    public void setTitle2(String title) {
        this.treeItemComponent.getTitle2().setText(title);
    }

    public String getTitle3() {
        return this.treeItemComponent.getTitle3().getText();
    }

    public void setTitle3(String title) {
        this.treeItemComponent.getTitle3().setText(title);
    }
}
