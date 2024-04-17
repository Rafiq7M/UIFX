package com.toofan.soft.uifx.controls;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.mfxresources.fonts.MFXFontIcon;
import javafx.beans.property.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class UITitledPane extends TitledPane {


    //************************************************************************
    //            Properties
    //************************************************************************


    private final StringProperty titledPaneId;

    private final  DoubleProperty widthTitle1Lbl;
    private final  DoubleProperty widthTitle2Lbl;
    private final  DoubleProperty widthTitle3Lbl;
    private final  DoubleProperty spacingTitles;
    private final  StringProperty title1;
    private final  StringProperty title2;
    private final  StringProperty title3;
    private final  StringProperty mainTitle;

    private final  StringProperty deleteBtnText;
    private final  StringProperty modifyBtnText;
    private final  StringProperty addBtnText;

    private final  StringProperty deleteBtnIcon;
    private final  StringProperty modifyBtnIcon;
    private final  StringProperty addBtnIcon;

    private   final ObjectProperty<HBox> headerLabelsHbox;
    private   final ObjectProperty<HBox> headerButtonsHbox;
    private   final ObjectProperty<HBox> mainLabelsHbox;
    private   final ObjectProperty<HBox> mainButtonsHbox;
    private    ObjectProperty<Node> contentNode;
//    private ObjectProperty<Node> content;





    private  HBox headerHbox;
    private  VBox mainVbox;
    private  HBox mainHeaderHbox;

    private Label titledPaneIdLbl;
    private Label titleLbl1;
    private Label titleLbl2;
    private Label titleLbl3;

    private MFXButton modifyBtn;
    private MFXButton deleteBtn;
    private Label mainTitleLbl;
    private MFXButton addBtn;

    private Runnable onModifyBtnHandler;
    private Runnable onDeleteBtnHandler;
    private Runnable onAddBtnHandler;
    public UITitledPane() {
//        super();
        this.setAnimated(false);
        this.setAlignment(Pos.TOP_LEFT);
        this.setMaxHeight(Double.MAX_VALUE);
        this.setMaxWidth(Double.MAX_VALUE);

        this.getStyleClass().add("ui-titled-pane");

        widthTitle1Lbl = new SimpleDoubleProperty(Region.USE_COMPUTED_SIZE);
        widthTitle2Lbl = new SimpleDoubleProperty(Region.USE_COMPUTED_SIZE);
        widthTitle3Lbl = new SimpleDoubleProperty(Region.USE_COMPUTED_SIZE);

        spacingTitles = new SimpleDoubleProperty(10);

        titledPaneId = new SimpleStringProperty("id");
        title1 = new SimpleStringProperty("title1");
        title2 = new SimpleStringProperty("");
        title3 = new SimpleStringProperty("");
        mainTitle = new SimpleStringProperty("main Title");


        modifyBtnText = new SimpleStringProperty("");
        deleteBtnText = new SimpleStringProperty("");
        addBtnText = new SimpleStringProperty("");

        modifyBtnIcon = new SimpleStringProperty("fas-pen");
        deleteBtnIcon = new SimpleStringProperty("fas-trash-can");
        addBtnIcon = new SimpleStringProperty("fas-plus");






        headerLabelsHbox = new SimpleObjectProperty<>(new HBox());
        headerButtonsHbox = new SimpleObjectProperty<>(new HBox());
        mainLabelsHbox = new SimpleObjectProperty<>(new HBox());
        mainButtonsHbox = new SimpleObjectProperty<>(new HBox());

        initHeaderHbox();
        initMainHeaderVbox();
        initMainVbox();


        getHeaderLabelsHbox().spacingProperty().bind(spacingTitles);
        titleLbl1.prefWidthProperty().bindBidirectional(widthTitle1Lbl);
        titleLbl1.prefWidthProperty().bindBidirectional(widthTitle1Lbl);
        titleLbl2.prefWidthProperty().bindBidirectional(widthTitle2Lbl);
        titleLbl3.prefWidthProperty().bindBidirectional(widthTitle3Lbl);


        titledPaneIdLbl.textProperty().bind(titledPaneId);
        titleLbl1.textProperty().bind(title1);
        titleLbl2.textProperty().bind(title2);
        titleLbl3.textProperty().bind(title3);
        mainTitleLbl.textProperty().bind(mainTitle);


        this.setGraphic(headerHbox);
        this.setContent(mainVbox);
//        this.getGraphic().maxWidth(Double.MAX_VALUE);
//        this.getGraphic().prefHeight(Double.MAX_VALUE);
//        HBox.setHgrow(this.getGraphic(), Priority.SOMETIMES);

    }



    private void initHeaderHbox(){
        headerHbox = new HBox();
        headerHbox.setSpacing(20);
        headerHbox.setMaxWidth(Double.MAX_VALUE);
        headerHbox.getStyleClass().add("header-titled-pane-hbox");

        getHeaderLabelsHbox().setMaxWidth(Double.MAX_VALUE);
        getHeaderLabelsHbox().getStyleClass().add("header-labels-hbox");
        HBox.setHgrow(getHeaderLabelsHbox(), Priority.SOMETIMES);


        titledPaneIdLbl = new Label("1");
        titledPaneIdLbl.getStyleClass().add("header-count-lbl");
        titledPaneIdLbl.setFont(new Font("Calibri Bold", 18));
        titledPaneIdLbl.setPrefWidth(50.0);
        titledPaneIdLbl.setAlignment(Pos.CENTER);

        titleLbl1 = new Label(getTitle1());
        titleLbl1.setFont(new Font("Calibri Bold", 20.0));
        titleLbl1.setMaxWidth(Double.MAX_VALUE);


        titleLbl2 = new Label(getTitle2());
        titleLbl2.setFont(new Font("Calibri Bold", 20.0));
        titleLbl2.setMaxWidth(Double.MAX_VALUE);


        titleLbl3 = new Label(getTitle3());
        titleLbl3.setFont(new Font("Calibri Bold", 20.0));
        titleLbl3.setMaxWidth(Double.MAX_VALUE);

        getHeaderLabelsHbox().getChildren().addAll(titledPaneIdLbl, titleLbl1, titleLbl2, titleLbl3);

        getHeaderButtonsHbox().setAlignment(Pos.TOP_CENTER);
        getHeaderButtonsHbox().getStyleClass().add("header-buttons-hbox");
        getHeaderButtonsHbox().setSpacing(10.0);

        modifyBtn = new MFXButton(getModifyBtnText());
        modifyBtn.getStyleClass().add("btn-icon");
        modifyBtn.setMinWidth(31.0);
        modifyBtn.setMaxWidth(200);
        modifyBtn.setMnemonicParsing(false);
        MFXFontIcon modifyBtnMFXFontIcon =new MFXFontIcon(getModifyBtnIcon(), 16);
        modifyBtnMFXFontIcon.descriptionProperty().bind(modifyBtnIcon);
        modifyBtn.setGraphic(modifyBtnMFXFontIcon);
        modifyBtn.setOnAction(e -> {if (onModifyBtnHandler != null) onModifyBtnHandler.run();});

        deleteBtn = new MFXButton(getDeleteBtnText());
        deleteBtn.getStyleClass().add("btn-icon");
        deleteBtn.setMinWidth(31.0);
        deleteBtn.setMaxWidth(200);
        deleteBtn.setMnemonicParsing(false);

        modifyBtn.textProperty().bind(modifyBtnText);
        deleteBtn.textProperty().bind(deleteBtnText);
        MFXFontIcon deleteBtnMFXFontIcon =new MFXFontIcon(getDeleteBtnIcon(), 16);
        deleteBtnMFXFontIcon.descriptionProperty().bind(deleteBtnIcon);
        deleteBtn.setGraphic(deleteBtnMFXFontIcon);
        deleteBtn.setOnAction(e ->  {if (onDeleteBtnHandler != null) onDeleteBtnHandler.run();});

        getHeaderButtonsHbox().getChildren().addAll(modifyBtn, deleteBtn);
        headerHbox.getChildren().addAll(getHeaderLabelsHbox(), getHeaderButtonsHbox());

    }

    private void initMainHeaderVbox() {
        mainHeaderHbox = new HBox();
        mainHeaderHbox.getStyleClass().add("main-header-hbox");
        mainHeaderHbox.setMaxWidth(Double.MAX_VALUE);

        getMainLabelsHbox().getStyleClass().add("main-header-labels-hbox");
        getMainLabelsHbox().setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(getMainLabelsHbox(), Priority.SOMETIMES);

        mainTitleLbl = new Label(getMainTitle());
        mainTitleLbl.setFont(new Font("Calibri Bold", 20.0));
        mainTitleLbl.setMaxWidth(Double.MAX_VALUE);


        getMainLabelsHbox().getChildren().add(mainTitleLbl);

        getMainButtonsHbox().setAlignment(Pos.TOP_CENTER);
        getMainButtonsHbox().getStyleClass().add("main-header-buttons-hbox");
        getMainButtonsHbox().setSpacing(10.0);
        getMainButtonsHbox().setMaxWidth(Double.MAX_VALUE);


        addBtn = new MFXButton(getAddBtnText());
        addBtn.getStyleClass().add("btn-icon");
        addBtn.setMinWidth(31.0);
        addBtn.setMaxWidth(200);

        addBtn.setMnemonicParsing(false);
        addBtn.textProperty().bind(addBtnText);
        MFXFontIcon addBtnMFXFontIcon =new MFXFontIcon(getAddBtnIcon(), 16);
        addBtnMFXFontIcon.descriptionProperty().bind(addBtnIcon);
        addBtn.setGraphic(addBtnMFXFontIcon);
        addBtn.setOnAction(e ->  {if (onAddBtnHandler != null) onAddBtnHandler.run();});

        getMainButtonsHbox().getChildren().addAll(addBtn);

        mainHeaderHbox.getChildren().addAll(getMainLabelsHbox(), getMainButtonsHbox());
        mainHeaderHbox.setMargin(getMainButtonsHbox(), new Insets(0, 0, 0, 50.0));

    }
    private void initMainVbox(){
        mainVbox = new VBox();
        mainVbox.getStyleClass().add("main-titled-pane-vbox");
        mainVbox.setSpacing(5.0);
        mainVbox.setPrefWidth(Double.MAX_VALUE);
        mainVbox.setMaxWidth(Double.MAX_VALUE);

//        getContentNode().maxWidth(Double.MAX_VALUE);
//        getContentNode().maxHeight(Double.MAX_VALUE);
        mainVbox.getChildren().addAll( mainHeaderHbox);
    }

    public String getTitledPaneId() {
        return titledPaneId.get();
    }

    public StringProperty titledPaneIdProperty() {
        return titledPaneId;
    }

    public void setTitledPaneId(String titledPaneId) {
        this.titledPaneId.set(titledPaneId);
    }

    public double getWidthTitle1Lbl() {
        return widthTitle1Lbl.get();
    }

    public DoubleProperty widthTitle1LblProperty() {
        return widthTitle1Lbl;
    }

    public void setWidthTitle1Lbl(double widthTitlesLbl) {
        this.widthTitle1Lbl.set(widthTitlesLbl);
    }

    public double getWidthTitle2Lbl() {
        return widthTitle2Lbl.get();
    }

    public DoubleProperty widthTitle2LblProperty() {
        return widthTitle2Lbl;
    }

    public void setWidthTitle2Lbl(double widthTitle2Lbl) {
        this.widthTitle2Lbl.set(widthTitle2Lbl);
    }

    public double getWidthTitle3Lbl() {
        return widthTitle3Lbl.get();
    }

    public DoubleProperty widthTitle3LblProperty() {
        return widthTitle3Lbl;
    }

    public void setWidthTitle3Lbl(double widthTitle3Lbl) {
        this.widthTitle3Lbl.set(widthTitle3Lbl);
    }

    public String getTitle1() {
        return title1.get();
    }

    public StringProperty title1Property() {
        return title1;
    }

    public void setTitle1(String title1) {
        this.title1.set(title1);
    }

    public String getTitle2() {
        return title2.get();
    }

    public StringProperty title2Property() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2.set(title2);
    }

    public String getTitle3() {
        return title3.get();
    }

    public StringProperty title3Property() {
        return title3;
    }

    public void setTitle3(String title3) {
        this.title3.set(title3);
    }

    public String getMainTitle() {
        return mainTitle.get();
    }

    public StringProperty mainTitleProperty() {
        return mainTitle;
    }

    public void setMainTitle(String mainTitle) {
        this.mainTitle.set(mainTitle);
    }

    public double getSpacingTitles() {
        return spacingTitles.get();
    }

    public DoubleProperty spacingTitlesProperty() {
        return spacingTitles;
    }

    public void setSpacingTitles(double spacingTitles) {
        this.spacingTitles.set(spacingTitles);
    }

    public HBox getHeaderLabelsHbox() {
        return headerLabelsHbox.get();
    }

    public ObjectProperty<HBox> headerLabelsHboxProperty() {
        return headerLabelsHbox;
    }

    public void setHeaderLabelsHbox(HBox headerLabelsHbox) {
        this.headerLabelsHbox.set(headerLabelsHbox);
        headerHbox.getChildren().clear();
        headerHbox.getChildren().addAll(headerLabelsHbox, getHeaderButtonsHbox());
    }

    public HBox getHeaderButtonsHbox() {
        return headerButtonsHbox.get();
    }

    public ObjectProperty<HBox> headerButtonsHboxProperty() {
        return headerButtonsHbox;
    }

    public void setHeaderButtonsHbox(HBox headerButtonsHbox) {
        this.headerButtonsHbox.set(headerButtonsHbox);
        headerHbox.getChildren().clear();
        headerHbox.getChildren().addAll(getHeaderLabelsHbox(), headerButtonsHbox);
    }

    public HBox getMainLabelsHbox() {
        return mainLabelsHbox.get();
    }

    public ObjectProperty<HBox> mainLabelsHboxProperty() {
        return mainLabelsHbox;
    }

    public void setMainLabelsHbox(HBox mainLabelsHbox) {
        this.mainLabelsHbox.set(mainLabelsHbox);
        mainHeaderHbox.getChildren().clear();
        mainHeaderHbox.getChildren().addAll(mainLabelsHbox, getMainButtonsHbox());
    }

    public HBox getMainButtonsHbox() {
        return mainButtonsHbox.get();
    }

    public ObjectProperty<HBox> mainButtonsHboxProperty() {
        return mainButtonsHbox;
    }

    public void setMainButtonsHbox(HBox mainButtonsHbox) {
        this.mainButtonsHbox.set(mainButtonsHbox);
        mainHeaderHbox.getChildren().clear();
        mainHeaderHbox.getChildren().addAll(getMainLabelsHbox(), mainButtonsHbox);
    }

    public final Node getContentNode() {
        if(contentNode ==null)
            contentNode =new SimpleObjectProperty<Node>(mainVbox, "contentNode");
        return  contentNode.get();
    }

    public void setContentNode(Node contentNode) {
        this.contentNodeProperty().set(contentNode);
        mainVbox.getChildren().clear();
        mainVbox.getChildren().addAll(mainHeaderHbox, contentNode);

    }
    public ObjectProperty<Node> contentNodeProperty() {
        if (contentNode == null) {
            contentNode = new SimpleObjectProperty<Node>(this, "contentNode");
        }
        return contentNode;
    }



    public String getDeleteBtnText() {
        return deleteBtnText.get();
    }

    public StringProperty deleteBtnTextProperty() {
        return deleteBtnText;
    }

    public void setDeleteBtnText(String deleteBtnText) {
        this.deleteBtnText.set(deleteBtnText);
    }

    public String getModifyBtnText() {
        return modifyBtnText.get();
    }

    public StringProperty modifyBtnTextProperty() {
        return modifyBtnText;
    }

    public void setModifyBtnText(String modifyBtnText) {
        this.modifyBtnText.set(modifyBtnText);
    }

    public String getAddBtnText() {
        return addBtnText.get();
    }

    public StringProperty addBtnTextProperty() {
        return addBtnText;
    }

    public void setAddBtnText(String addBtnText) {
        this.addBtnText.set(addBtnText);
    }

    public String getDeleteBtnIcon() {
        return deleteBtnIcon.get();
    }

    public StringProperty deleteBtnIconProperty() {
        return deleteBtnIcon;
    }

    public void setDeleteBtnIcon(String deleteBtnIcon) {
        this.deleteBtnIcon.set(deleteBtnIcon);
    }

    public String getModifyBtnIcon() {
        return modifyBtnIcon.get();
    }

    public StringProperty modifyBtnIconProperty() {
        return modifyBtnIcon;
    }

    public void setModifyBtnIcon(String modifyBtnIcon) {
        this.modifyBtnIcon.set(modifyBtnIcon);
    }

    public String getAddBtnIcon() {
        return addBtnIcon.get();
    }

    public StringProperty addBtnIconProperty() {
        return addBtnIcon;
    }

    public void setAddBtnIcon(String addBtnIcon) {
        this.addBtnIcon.set(addBtnIcon);
    }

    public void setOnModifyBtnHandler(Runnable onModifyBtnHandler) {
        this.onModifyBtnHandler = onModifyBtnHandler;
    }

    public void setOnDeleteBtnHandler(Runnable onDeleteBtnHandler) {
        this.onDeleteBtnHandler = onDeleteBtnHandler;
    }

    public void setOnAddBtnHandler(Runnable onAddBtnHandler) {
        this.onAddBtnHandler = onAddBtnHandler;
    }

}