package cn.org.dialogue.netfilter;

import cn.org.dialogue.netfilter.listener.AllListener;
import org.pcap4j.core.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;


/**
 * @author 周正明
 * @date 2024/5/20
 */

public class PacketCapture  extends Thread{
    public static Logger log = LoggerFactory.getLogger(PacketCapture.class);
    public static int timeout = 10;
    public static int packetCount = -1;
    public static int snapLen = 65536;
    public static InetAddress addr;
    public static PcapNetworkInterface nif;
    public static PcapHandle handle;
    public static PcapNetworkInterface.PromiscuousMode mode = PcapNetworkInterface.PromiscuousMode.PROMISCUOUS;

    public static String PRIVATE_IP;

    public static String PUBLIC_IP;

    static {
        try {
            PRIVATE_IP = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

        URL url = null;
        try {
            url = new URL("http://checkip.amazonaws.com");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream()))) {
            PUBLIC_IP = in.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
    }
    @Override
    public void run() {
        try {
            initCapture();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(){
        super.start();
    }



    public void initCapture() throws UnknownHostException, PcapNativeException, NotOpenException {
        addr = InetAddress.getByName(PRIVATE_IP);
        nif = Pcaps.getDevByAddress(addr);
        startCapture();
    }

    public void startCapture() throws PcapNativeException, NotOpenException {
        handle = nif.openLive(snapLen, mode, timeout);
        try {
            handle.loop(packetCount, new AllListener());
        } catch (InterruptedException e) {
            stopCapture();
            Thread.currentThread().interrupt();
        }
    }

    public void stopCapture() throws NotOpenException {
        handle.breakLoop();
        handle.close();
    }
}
