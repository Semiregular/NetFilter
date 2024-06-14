package cn.org.dialogue.netfilter;

import cn.org.dialogue.netfilter.listener.AllListener;
import cn.org.dialogue.netfilter.listener.TCPListener;
import cn.org.dialogue.netfilter.listener.UDPListener;
import cn.org.dialogue.netfilter.packet.PacketFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author 周正明
 */
public class MainController {

    public static Logger log = LoggerFactory.getLogger(MainApplication.class);
    public static String HOST = "127.0.0.1";
    public static String HOST_TYPE = "ANY";
    public static String PORT = "80";
    public static String PORT_TYPE = "ANY";
    public static Boolean isRunning = false;

    public static ObservableList<PacketFormat> allPacketList = FXCollections.observableArrayList();
    public static ObservableList<PacketFormat> tcpPacketList = FXCollections.observableArrayList();
    public static ObservableList<PacketFormat> udpPacketList = FXCollections.observableArrayList();

    @FXML
    private  TextField Host;

    @FXML
    private  TextField Port;

    @FXML
    private  Button Run;

    @FXML
    public Button Clear;

    @FXML
    public MenuItem SaveAs;


    @FXML
    private  ChoiceBox<String> HostType = new ChoiceBox<>();

    @FXML
    private  ChoiceBox<String> PortType = new ChoiceBox<>();

    @FXML
    public   TableView<PacketFormat> AllTableView;
    @FXML
    public  TableColumn<PacketFormat, Integer> id;

    @FXML
    public  TableColumn<PacketFormat, Integer> tcpId;

    @FXML
    public  TableColumn<PacketFormat, Integer> udpId;

    @FXML
    public  TableColumn<PacketFormat, String> tcpSrcAddr;

    @FXML
    public  TableColumn<PacketFormat, String> tcpDstAddr;

    @FXML
    public  TableColumn<PacketFormat, String> tcpSrcPort;

    @FXML
    public  TableColumn<PacketFormat, String> tcpDstPort;

    @FXML
    public  TableColumn<PacketFormat, Integer> tcpLength;

    @FXML
    public  TableColumn<PacketFormat, String> tcpTime;

    @FXML
    public  TableColumn<PacketFormat, Integer> payload;

    @FXML
    public  TableColumn<PacketFormat, Integer> udpPayload;

    @FXML
    public  TableColumn<PacketFormat, Integer> tcpPayload;


    @FXML
    public  TableView<PacketFormat> TcpTableView;

    @FXML
    public  TableColumn<PacketFormat, String> udpSrcAddr;

    @FXML
    public  TableColumn<PacketFormat, String> udpDstAddr;

    @FXML
    public  TableColumn<PacketFormat, String> udpSrcPort;

    @FXML
    public  TableColumn<PacketFormat, String> udpDstPort;

    @FXML
    public  TableColumn<PacketFormat, Integer> udpLength;

    @FXML
    public  TableColumn<PacketFormat, String> udpTime;

    @FXML
    public  TableView<PacketFormat> UdpTableView;

    @FXML
    public  TableColumn<PacketFormat, String> srcAddr;

    @FXML
    public  TableColumn<PacketFormat, String> dstAddr;

    @FXML
    public  TableColumn<PacketFormat, String> protocol;


    @FXML
    public  TableColumn<PacketFormat, Integer> length;

    @FXML
    public TableColumn<PacketFormat, String> time;

    @FXML
    private void initialize() {
        tcpId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcpSrcAddr.setCellValueFactory(new PropertyValueFactory<>("srcAddr"));
        tcpDstAddr.setCellValueFactory(new PropertyValueFactory<>("dstAddr"));
        tcpSrcPort.setCellValueFactory(new PropertyValueFactory<>("srcPort"));
        tcpDstPort.setCellValueFactory(new PropertyValueFactory<>("dstPort"));
        tcpLength.setCellValueFactory(new PropertyValueFactory<>("length"));
        tcpTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        tcpPayload.setCellValueFactory(new PropertyValueFactory<>("payload"));

        udpId.setCellValueFactory(new PropertyValueFactory<>("id"));
        udpSrcAddr.setCellValueFactory(new PropertyValueFactory<>("srcAddr"));
        udpDstAddr.setCellValueFactory(new PropertyValueFactory<>("dstAddr"));
        udpSrcPort.setCellValueFactory(new PropertyValueFactory<>("srcPort"));
        udpDstPort.setCellValueFactory(new PropertyValueFactory<>("dstPort"));
        udpLength.setCellValueFactory(new PropertyValueFactory<>("length"));
        udpTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        udpPayload.setCellValueFactory(new PropertyValueFactory<>("payload"));

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        time.setCellValueFactory(new PropertyValueFactory<>("time"));
        length.setCellValueFactory(new PropertyValueFactory<>("length"));
        srcAddr.setCellValueFactory(new PropertyValueFactory<>("srcAddr"));
        dstAddr.setCellValueFactory(new PropertyValueFactory<>("dstAddr"));
        protocol.setCellValueFactory(new PropertyValueFactory<>("protocol"));
        payload.setCellValueFactory(new PropertyValueFactory<>("payload"));

        AllTableView.setItems(allPacketList);
        TcpTableView.setItems(tcpPacketList);
        UdpTableView.setItems(udpPacketList);

        HostType.getItems().addAll("源地址", "目的地址", "任意地址");
        HostType.setValue("任意地址");
        Host.setDisable(true);
        HostType.setOnAction(event -> {
            switch(HostType.getValue()){
                case "源地址":
                    HOST_TYPE = "SRC";
                    Host.setDisable(false);
                    break;
                case "目的地址":
                    HOST_TYPE = "DST";
                    Host.setDisable(false);
                    break;
                case "任意地址":
                    HOST_TYPE = "ANY";
                    Host.setDisable(true);
                    break;
            }
        });

        PortType.getItems().addAll("源端口", "目的端口", "任意端口");
        PortType.setValue("任意端口");
        Port.setDisable(true);
        PortType.setOnAction(event -> {
            switch(PortType.getValue()){
                case "源端口":
                    PORT_TYPE = "SRC";
                    Port.setDisable(false);
                    break;
                case "目的端口":
                    PORT_TYPE = "DST";
                    Port.setDisable(false);
                    break;
                case "任意端口":
                    PORT_TYPE = "ANY";
                    Port.setDisable(true);
                    break;
            }
        });

        Host.textProperty().addListener((observable, oldValue, newValue) -> {
            HOST = newValue;
        });
        Port.textProperty().addListener((observable, oldValue, newValue) -> {
            PORT = newValue;
        });

        AtomicReference<PacketCapture> packetCapture = new AtomicReference<>(new PacketCapture());
        Run.setOnAction(event -> {
            if (isRunning) {
                isRunning = false;
                Run.setText("开始监听");
                log.info("已停止监听");
                try {
                    packetCapture.get().stopCapture();
                    packetCapture.get().interrupt();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }else{
                Run.setText("停止监听");
                isRunning = true;
                log.info("已开始监听");
                try {
                    packetCapture.set(new PacketCapture());
                    packetCapture.get().start();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Clear.setOnAction(event -> {
            allPacketList.clear();
            tcpPacketList.clear();
            udpPacketList.clear();
            AllListener.clear();
            TCPListener.clear();
            UDPListener.clear();
        });

        SaveAs.setOnAction((EventHandler) event -> saveToFile());

    }

    private void saveToFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );
        File selectedFile = fileChooser.showSaveDialog(null);

        if (selectedFile != null) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(selectedFile))) {
                ObjectMapper mapper = new ObjectMapper();
                mapper.writeValue(writer, allPacketList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("User cancelled the save operation.");
        }
    }
}