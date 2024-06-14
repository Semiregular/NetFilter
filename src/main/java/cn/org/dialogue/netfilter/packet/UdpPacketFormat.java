package cn.org.dialogue.netfilter.packet;

import javafx.beans.property.SimpleStringProperty;

/**
 * @author 周正明
 * @date 2024/5/21
 */
public class UdpPacketFormat extends PacketFormat{
    private final SimpleStringProperty dstPort;
    private final SimpleStringProperty srcPort;

    public UdpPacketFormat(Integer id, String srcAddr, String dstAddr, String srcPort, String dstPort, Integer length, String time, Integer payload)
    {
        super(id, srcAddr, dstAddr, "UDP", length, time, payload);
        this.dstPort = new SimpleStringProperty(dstPort);
        this.srcPort = new SimpleStringProperty(srcPort);
    }

    public String getDstPort()
    {
        return dstPort.get();
    }

    public SimpleStringProperty dstPortProperty()
    {
        return dstPort;
    }

    public void setDstPort(String dstPort)
    {
        this.dstPort.set(dstPort);
    }

    public String getSrcPort()
    {
        return srcPort.get();
    }

    public SimpleStringProperty srcPortProperty()
    {
        return srcPort;
    }

    public void setSrcPort(String srcPort)
    {
        this.srcPort.set(srcPort);
    }
}
