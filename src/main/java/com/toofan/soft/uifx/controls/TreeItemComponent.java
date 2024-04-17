package com.toofan.soft.uifx.controls;

import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.mfxresources.fonts.MFXFontIcon;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class TreeItemComponent extends ScrollPane {
    private VBox parentBox;
    private HBox contentBox;
    private MFXFontIcon arrowIcon;
    private Label title0;
    private Label title1;
    private Label title2;
    private Label title3;
    private MFXCheckbox stateChBx;
    private VBox childrenBox;
    private ITreeItemState listener;
    private boolean isExpended = false;

    public VBox getParentBox() {
        return parentBox;
    }

    public HBox getContentBox() {
        return contentBox;
    }

    public MFXFontIcon getArrowIcon() {
        return arrowIcon;
    }

    public Label getTitle0() {
        return title0;
    }

    public Label getTitle1() {
        return title1;
    }

    public Label getTitle2() {
        return title2;
    }

    public Label getTitle3() {
        return title3;
    }

    public MFXCheckbox getStateChBx() {
        return stateChBx;
    }

    public VBox getChildrenBox() {
        return childrenBox;
    }

    public void setListener(ITreeItemState listener) {
        this.listener = listener;
    }

    public TreeItemComponent() {
        initialize();

        stateChBx.selectedProperty().addListener((e, o, n) -> {
            if (n){
                parentBox.getStyleClass().add("selected");
                if (listener != null) listener.onStateChange(n);

            } else {
                parentBox.getStyleClass().remove("selected");
                if (listener != null) listener.onStateChange(n);
            }
        } );

        if (!childrenBox.getChildren().isEmpty()) arrowIcon.setVisible(true);
        else arrowIcon.setVisible(false);
    }

    private void initialize() {
        parentBox = new VBox();
        parentBox.getStyleClass().add("tree-item");
        parentBox.setMaxHeight(Double.MAX_VALUE);
        parentBox.setMaxWidth(Double.MAX_VALUE);
        parentBox.setMinWidth(500.0);

        contentBox = new HBox();
        contentBox.setOnMouseClicked(event -> onTreeItemHBoxClicked(event));
        contentBox.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        contentBox.setMaxHeight(Double.MAX_VALUE);
        contentBox.setMaxWidth(Double.MAX_VALUE);
        contentBox.setSpacing(5.0);

        arrowIcon = new MFXFontIcon();
        arrowIcon.setId("arrowIcon");
        arrowIcon.setDescription("fas-angle-left");
        arrowIcon.setOnMouseClicked(event -> onArrowIconClicked());
        arrowIcon.setWrappingWidth(25.599976539611816);
        arrowIcon.getStyleClass().add("icon");

        title0 = new Label("233");
        title0.setLayoutX(40.0);
        title0.setLayoutY(15.0);
        title0.setMaxHeight(Double.MAX_VALUE);
        title0.setMaxWidth(Double.MAX_VALUE);
        title0.getStyleClass().add("lbl");
        title0.setFont(new Font("Calibri", 16.0));

        title1 = new Label("العنوان الأول");
        title1.setMaxHeight(Double.MAX_VALUE);
        title1.setMaxWidth(Double.MAX_VALUE);
        title1.getStyleClass().add("lbl");
        title1.setFont(new Font("Calibri", 16.0));

        title2 = new Label("العنوان الثاني");
        title2.setLayoutX(15.0);
        title2.setLayoutY(15.0);
        title2.setMaxHeight(Double.MAX_VALUE);
        title2.setMaxWidth(Double.MAX_VALUE);
        title2.getStyleClass().add("lbl");
        title2.setFont(new Font("Calibri", 16.0));

        title3 = new Label("العنوان الثاني");
        title3.setLayoutX(261.0);
        title3.setLayoutY(15.0);
        title3.setMaxHeight(Double.MAX_VALUE);
        title3.setMaxWidth(Double.MAX_VALUE);
        title3.getStyleClass().add("lbl");
        title3.setFont(new Font("Calibri", 16.0));

        stateChBx = new MFXCheckbox();
        stateChBx.setText(" ");

        contentBox.getChildren().addAll(arrowIcon, title0, title1, title2, title3, stateChBx);
        contentBox.setPadding(new Insets(5.0));
        contentBox.setHgrow(title0,javafx.scene.layout.Priority.SOMETIMES);
        contentBox.setHgrow(title1,javafx.scene.layout.Priority.SOMETIMES);
        contentBox.setHgrow(title2,javafx.scene.layout.Priority.SOMETIMES);
        contentBox.setHgrow(title3,javafx.scene.layout.Priority.SOMETIMES);

        childrenBox = new VBox();
        childrenBox.setMaxHeight(Double.MAX_VALUE);
        childrenBox.setMaxWidth(Double.MAX_VALUE);
        childrenBox.getStyleClass().add("tree-item");

        parentBox.getChildren().addAll(contentBox, childrenBox);

        setContent(parentBox);
        setFitToHeight(true);
        setFitToWidth(true);
        setFocusTraversable(false);
        setHbarPolicy(ScrollBarPolicy.NEVER);
        setVbarPolicy(ScrollBarPolicy.NEVER);
        setMaxHeight(Double.MAX_VALUE);
        setMaxWidth(Double.MAX_VALUE);
        setMinHeight(36.0);
        setMinWidth(500.0);
        setPrefHeight(36.0);

    }
    void onArrowIconClicked() {
        if (isExpended){
            if (!childrenBox.getChildren().isEmpty()){
                isExpended = false;
                setPrefHeight(36.0);
                arrowIcon.setDescription("fas-angle-left");
            }
        } else {
            if (!childrenBox.getChildren().isEmpty()){
                isExpended = true;
                setPrefHeight(Region.USE_COMPUTED_SIZE);
                arrowIcon.setDescription("fas-angle-down");
            }
        }
    }

    void onTreeItemHBoxClicked(MouseEvent event) {
        if(event.getClickCount() == 2){
            if (isExpended){
                if (!childrenBox.getChildren().isEmpty()){
                    isExpended = false;
                    setPrefHeight(36.0);
                    arrowIcon.setDescription("fas-angle-left");
                }
            } else {
                if (!childrenBox.getChildren().isEmpty()){
                    isExpended = true;
                    setPrefHeight(Region.USE_COMPUTED_SIZE);
                    arrowIcon.setDescription("fas-angle-down");
                }
            }
        }
    }
}
