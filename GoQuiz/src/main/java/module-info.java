module gamification.accesa {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires org.apache.logging.log4j;
    requires java.sql;

    opens gamification.accesa to javafx.fxml;
    exports gamification.accesa;
    exports gamification.accesa.controller;
    exports gamification.accesa.controller.exception;
    exports gamification.accesa.domain;
    exports gamification.accesa.service;
    exports gamification.accesa.service.utils;
    exports gamification.accesa.utils;
    exports gamification.accesa.repository;
    exports gamification.accesa.repository.utils;
    exports gamification.accesa.repository.irepository;
    exports gamification.accesa.repository.dbrepository;
    opens gamification.accesa.controller to javafx.fxml;
}