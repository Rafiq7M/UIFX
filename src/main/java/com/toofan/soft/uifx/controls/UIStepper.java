package com.toofan.soft.uifx.controls;

import io.github.palexdev.materialfx.controls.MFXScrollPane;
import io.github.palexdev.materialfx.controls.MFXStepper;
import io.github.palexdev.materialfx.controls.MFXStepperToggle;
import io.github.palexdev.mfxresources.fonts.MFXFontIcon;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UIStepper extends Node {

    private VBox root;
    private MFXStepper stepper;
    private final Stage stage;
    private MFXStepperToggle step1StepperToggle;
    private MFXFontIcon step1Icon;
    private VBox step1ContentBox;
    private String step1Title;
    private MFXScrollPane step1ScrollPane;

    private MFXStepperToggle step2StepperToggle;
    private MFXFontIcon step2Icon;
    private VBox step2ContentBox;
    private String step2Title;
    private MFXScrollPane step2ScrollPane;

    private MFXStepperToggle step3StepperToggle;
    private MFXFontIcon step3Icon;
    private VBox step3ContentBox;
    private String step3Title;
    private MFXScrollPane step3ScrollPane;

    public UIStepper(Stage stage, String step1Title, String step2Title, String step3Title) {
        this.stepper = new MFXStepper();
        this.root = new VBox();
        this.stage = stage;
        this.step1Title = step1Title;
        this.step2Title = step2Title;
        this.step3Title = step3Title;

        step1Icon = new MFXFontIcon("fas-check", 16);
        step2Icon = new MFXFontIcon("fas-check", 16);
        step3Icon = new MFXFontIcon("fas-check", 16);
        step1ContentBox = initVBox();
        step2ContentBox = initVBox();
        step3ContentBox = initVBox();
        step1ScrollPane = new MFXScrollPane(step1ContentBox);
        step2ScrollPane = new MFXScrollPane(step2ContentBox);
        step3ScrollPane = new MFXScrollPane(step3ContentBox);

        step1StepperToggle = new MFXStepperToggle(this.step1Title, this.step1Icon);
        step2StepperToggle = new MFXStepperToggle(this.step2Title, this.step2Icon);
        step3StepperToggle = new MFXStepperToggle(this.step3Title, this.step3Icon);

        step1StepperToggle.setContent(step1ScrollPane);
        step2StepperToggle.setContent(step2ScrollPane);
        step3StepperToggle.setContent(step3ScrollPane);

        stepper.getStepperToggles().addAll(step1StepperToggle, step2StepperToggle, step3StepperToggle);

        stepper.setMaxHeight(Double.MAX_VALUE);
        stepper.setMaxWidth(Double.MAX_VALUE);

        root.getChildren().add(stepper);
    }

    public VBox get(){
        return this.root;
    }
    public VBox getStep1ContentBox() {
        return step1ContentBox;
    }

    public VBox getStep2ContentBox() {
        return step2ContentBox;
    }

    public VBox getStep3ContentBox() {
        return step3ContentBox;
    }

    private VBox initVBox() {
        VBox box = new VBox(10);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(10));
        box.setMaxWidth(Double.MAX_VALUE);
        box.setMaxHeight(Double.MAX_VALUE);
        return box;
    }
}
