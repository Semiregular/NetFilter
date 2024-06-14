package cn.org.dialogue.netfilter.listener;

import cn.org.dialogue.netfilter.MainController;
import cn.org.dialogue.netfilter.packet.TcpPacketFormat;
import org.pcap4j.core.PacketListener;
import org.pcap4j.packet.IpV4Packet;
import org.pcap4j.packet.Packet;
import org.pcap4j.packet.TcpPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author 周正明
 * @date 2024/5/20
 */
public class TCPListener implements PacketListener {
    private static Logger log = LoggerFactory.getLogger(TCPListener.class);
    private static int tcpCount = 0;

    public static void clear() {
        tcpCount = 0;
    }

    public static void convert(Packet packet,String time) {
        TcpPacket tcpPacket = packet.get(TcpPacket.class);
        IpV4Packet ipV4Packet = packet.get(IpV4Packet.class);
        if (tcpPacket != null) {
            String srcPort = tcpPacket.getHeader().getSrcPort().valueAsString();
            if(MainController.PORT_TYPE.equals("SRC") && !srcPort.equals(MainController.PORT)){
                return;
            }
            String dstPort = tcpPacket.getHeader().getDstPort().valueAsString();
            if(MainController.PORT_TYPE.equals("DST") && !dstPort.equals(MainController.PORT)){
                return;
            }
            tcpCount++;
            String srcAddr = ipV4Packet.getHeader().getSrcAddr().getHostAddress();
            String dstAddr = ipV4Packet.getHeader().getDstAddr().getHostAddress();
            Integer length = tcpPacket.length();
            Integer payload;
            if(tcpPacket.getPayload() == null){
                payload = 0;
            }else{
                payload = tcpPacket.getPayload().length();
            }
            MainController.tcpPacketList.add(new TcpPacketFormat(tcpCount,srcAddr,dstAddr,srcPort,dstPort,length,time,payload));
            log.info("TCP id:{}, srcAddr:{}, srcPort:{}, dstAddr:{}, dstPort:{}, length:{}",
                    tcpCount,srcAddr, srcPort, dstAddr, dstPort, length);
        }
    }
    public void gotPacket(Packet packet) {
        System.out.println("TCPListener gotPacket");
    }
}
