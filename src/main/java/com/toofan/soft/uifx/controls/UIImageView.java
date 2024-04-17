package com.toofan.soft.uifx.controls;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class UIImageView extends ImageView implements Initializable {
    //************************************************************************
    //            Properties Size and Radius
    //************************************************************************
    private final SimpleDoubleProperty recArcWidth = new SimpleDoubleProperty(10);
    private final SimpleDoubleProperty recArcHeight = new SimpleDoubleProperty(10);
    private   Rectangle clip = new Rectangle(this.getFitWidth(),this.getFitHeight() );
    public void onImageSizeChanged(){
        this.fitWidthProperty().addListener((observable, oldValue, newValue) -> {
            roundedImageView(newValue.doubleValue(),clip.getWidth(), recArcWidth.get(), recArcHeight.get());
        });
        this.fitHeightProperty().addListener((observable, oldValue, newValue) -> {
            roundedImageView(clip.getHeight(), newValue.doubleValue(), recArcWidth.get(), recArcHeight.get());
        });
    }
    public UIImageView() {
        this.setFitWidth(250);
        this.setFitHeight(250);
        clip.setWidth(this.getFitWidth());
        clip.setHeight(this.getFitHeight());
        roundedImageView(clip.getWidth(),clip.getHeight(), recArcWidth.get(), recArcHeight.get());
        onImageSizeChanged();
        recArcWidth.addListener((observable, oldValue, newValue) -> roundedImageView(clip.getWidth(), clip.getHeight(), newValue.doubleValue(), recArcHeight.get()));
        recArcHeight.addListener((observable, oldValue, newValue) -> roundedImageView(clip.getWidth(), clip.getHeight(), recArcWidth.get(), newValue.doubleValue()));
    }
    public double getRecArcWidth() {
        return recArcWidth.get();
    }
    public void setRecArcWidth(double recArcWidth) {
        this.recArcWidth.set(recArcWidth);
    }
    public double getRecArcHeight() {
        return recArcHeight.get();
    }
    public void setRecArcHeight(double recArcHeight) {
        this.recArcHeight.set(recArcHeight);
    }
    public void roundedImageView (double recWidth, double recHeight,double recArcWidth, double recArcHeight){
        clip = new Rectangle(this.getFitWidth(),this.getFitHeight());
        clip.setArcWidth(recArcWidth);
        clip.setArcHeight(recArcHeight);
        this.setClip(clip);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
