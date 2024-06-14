module cn.org.dialogue.netfilter {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires slf4j.api;
    requires org.pcap4j.core;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;


    opens cn.org.dialogue.netfilter to javafx.fxml;
    exports cn.org.dialogue.netfilter;
    exports cn.org.dialogue.netfilter.listener;
    opens cn.org.dialogue.netfilter.listener to javafx.fxml;
    exports cn.org.dialogue.netfilter.packet;
    opens cn.org.dialogue.netfilter.packet to javafx.fxml;
}