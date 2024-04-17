module com.toofan.soft.uifx {
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;


    opens com.toofan.soft.uifx to javafx.fxml;
    exports com.toofan.soft.uifx;
    exports com.toofan.soft.uifx.controls;
    opens com.toofan.soft.uifx.controls to javafx.fxml;
}