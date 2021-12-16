module Converter {

    requires javafx.controls;
    requires javafx.graphics;

    requires com.opencsv;

    requires org.apache.commons.lang3;
    requires  org.apache.commons.io;

    requires java.logging;
    requires java.desktop;

    opens ecommerce.converter;
    opens ecommerce.converter.platformtransformer;
    opens ecommerce.converter.generaltools;
}