package cn.org.dialogue.netfilter.listener;

import cn.org.dialogue.netfilter.MainController;
import cn.org.dialogue.netfilter.PacketCapture;
import cn.org.dialogue.netfilter.packet.PacketFormat;
import org.pcap4j.core.PacketListener;
import org.pcap4j.packet.IpV4Packet;
import org.pcap4j.packet.Packet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * @author 周正明
 * @date 2024/5/20
 */
public class AllListener implements PacketListener {
    public static Logger log = LoggerFactory.getLogger(AllListener.class);
    private static int allCount = 0;
    public static void clear() {
        allCount = 0;
    }
    @Override
    public void gotPacket(Packet packet) {
        IpV4Packet ipV4Packet = packet.get(IpV4Packet.class);
        if(ipV4Packet != null){
            String srcAddr = ipV4Packet.getHeader().getSrcAddr().getHostAddress();
            if(MainController.HOST_TYPE.equals("SRC") && !srcAddr.equals(MainController.HOST)){
                return;
            }
            String dstAddr = ipV4Packet.getHeader().getDstAddr().getHostAddress();
            if(MainController.HOST_TYPE.equals("DST") && !dstAddr.equals(MainController.HOST)){
                return;
            }
            String time = String.valueOf(PacketCapture.handle.getTimestamp());
            String protocol = ipV4Packet.getHeader().getProtocol().name();
            switch (protocol)
                {
                    case "TCP":
                        TCPListener.convert(packet,time);
                        break;
                    case "UDP":
                        UDPListener.convert(packet,time);
                        break;
                    default:
                       break;
                }
            allCount++;
            Integer length = ipV4Packet.length();
            Integer payload = ipV4Packet.getPayload().length();
            MainController.allPacketList.add(new PacketFormat(allCount,srcAddr,dstAddr,protocol,length,time,payload));
        }
    }
}
