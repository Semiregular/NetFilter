package cn.org.dialogue.netfilter.packet;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @author 周正明
 * @date 2024/5/20
 */

public class PacketFormat {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty srcAddr;
    private final SimpleStringProperty dstAddr;
    private final SimpleStringProperty protocol;
    private final SimpleIntegerProperty length;
    private final SimpleStringProperty time;

    private final SimpleIntegerProperty payload;

    public PacketFormat(Integer id, String srcAddr, String dstAddr, String protocol, Integer length, String time, Integer payload) {
        this.id = new SimpleIntegerProperty(id);
        this.srcAddr = new SimpleStringProperty(srcAddr);
        this.dstAddr = new SimpleStringProperty(dstAddr);
        this.protocol = new SimpleStringProperty(protocol);
        this.length = new SimpleIntegerProperty(length);
        this.time = new SimpleStringProperty(time);
        this.payload = new SimpleIntegerProperty(payload);
    }

    public Integer getPayload() {
        return payload.get();
    }

    public void setPayload(Integer payload) {
        this.payload.set(payload);
    }

    public SimpleIntegerProperty payloadProperty() {
        return payload;
    }

    public Integer getId() {
        return id.get();
    }

    public void setId(Integer id) {
        this.id.set(id);
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public String getSrcAddr() {
        return srcAddr.get();
    }

    public void setSrcAddr(String srcAddr) {
        this.srcAddr.set(srcAddr);
    }

    public SimpleStringProperty srcAddrProperty() {
        return srcAddr;
    }

    public String getDstAddr() {
        return dstAddr.get();
    }

    public void setDstAddr(String dstAddr) {
        this.dstAddr.set(dstAddr);
    }

    public SimpleStringProperty dstAddrProperty() {
        return dstAddr;
    }

    public String getProtocol() {
        return protocol.get();
    }

    public void setProtocol(String protocol) {
        this.protocol.set(protocol);
    }

    public SimpleStringProperty protocolProperty() {
        return protocol;
    }

    public Integer getLength() {
        return length.get();
    }

    public void setLength(Integer length) {
        this.length.set(length);
    }

    public SimpleIntegerProperty lengthProperty() {
        return length;
    }

    public String getTime() {
        return time.get();
    }

    public void setTime(String time) {
        this.time.set(time);
    }

    public SimpleStringProperty timeProperty() {
        return time;
    }


    @Override
    public String toString() {
        return "id=" + id +
                ", srcAddr=" + srcAddr +
                ", dstAddr=" + dstAddr +
                ", protocol=" + protocol +
                ", length=" + length +
                ", time=" + time;
    }
}
