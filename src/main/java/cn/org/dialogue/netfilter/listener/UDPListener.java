package cn.org.dialogue.netfilter.listener;

import cn.org.dialogue.netfilter.MainController;
import cn.org.dialogue.netfilter.packet.UdpPacketFormat;
import org.pcap4j.core.PacketListener;
import org.pcap4j.packet.IpV4Packet;
import org.pcap4j.packet.Packet;
import org.pcap4j.packet.UdpPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author 周正明
 * @date 2024/5/20
 */
public class UDPListener implements PacketListener {
    private static Logger log = LoggerFactory.getLogger(UDPListener.class);

    private static int udpCount = 0;

    public static void clear() {
        udpCount = 0;
    }
    public static void convert(Packet packet,String time) {
        UdpPacket udpPacket = packet.get(UdpPacket.class);
        IpV4Packet ipV4Packet = packet.get(IpV4Packet.class);
        if (udpPacket != null) {
            String srcPort = udpPacket.getHeader().getSrcPort().valueAsString();
            if(MainController.PORT_TYPE.equals("SRC") && !srcPort.equals(MainController.PORT)){
                return;
            }
            String dstPort = udpPacket.getHeader().getDstPort().valueAsString();
            if(MainController.PORT_TYPE.equals("DST") && !dstPort.equals(MainController.PORT)){
                return;
            }
            udpCount++;
            String srcAddr = ipV4Packet.getHeader().getSrcAddr().getHostAddress();
            String dstAddr = ipV4Packet.getHeader().getDstAddr().getHostAddress();
            Integer length = udpPacket.length();
            Integer payload = udpPacket.getPayload().length();
            MainController.udpPacketList.add(new UdpPacketFormat(udpCount,srcAddr,dstAddr,srcPort,dstPort,length,time,payload));
            log.info("UDP id:{}, srcAddr:{}, srcPort:{}, dstAddr:{}, dstPort:{}, length:{}",
                    udpCount,srcAddr, srcPort, dstAddr, dstPort, length);
        }
    }
    public void gotPacket(Packet packet) {
        System.out.println("UDPListener gotPacket");
    }
}
