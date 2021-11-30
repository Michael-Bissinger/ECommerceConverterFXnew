module Converter {

    requires javafx.controls;
    requires javafx.graphics;

    requires com.opencsv;

    requires org.apache.commons.lang3;

    requires java.logging;
    requires java.desktop;

    opens ecommerce.converter;
    opens ecommerce.converter.platformextractor;
}