module Converter {

    requires javafx.controls;
    requires javafx.graphics;

    requires com.opencsv;

    requires org.apache.commons.lang3;

    requires java.logging;

    opens ecommerce.converter;
    opens ecommerce.converter.platformextractor;
}