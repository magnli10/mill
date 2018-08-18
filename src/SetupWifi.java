import java.net.Socket;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.HashMap;

public class SetupWifi {
    public static void main(String[] args) {

        try{
            sendToDeviceTcp("0", "");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private static void sendToDeviceTcp(String ssid, String pwd) {
        Socket socket = null;
        boolean z;
        try {
            socket = getWifiSocket("192.168.2.3");
            byte[] data = ("xcmd_req::cmd=wifiset,wtype=0,ssid=" + ssid + ",pass=" + pwd + ",enc=7,xopt=3,").getBytes();
            byte[] commandByte = new byte[(data.length + 1)];
            System.arraycopy(data, 0, commandByte, 0, data.length);
            commandByte[data.length - 1] = 13; // org.java_websocket.drafts.Draft_75.CR
            commandByte[data.length] = (byte) 10;
            if (socket == null) {
                System.out.println("No socket");
            }
            OutputStream os = socket.getOutputStream();
            os.write(commandByte);
            os.flush();
            BufferedReader reader = new BufferedReader(new InputStreamReader(new BufferedInputStream(socket.getInputStream())));
            String str = "";
            //Log.i("ap response", reader.readLine());
            System.out.println("ap response" + reader.readLine());
            reader.close();
            closeSocket(socket);

        } catch (Exception e) {
            //Log.i(z, e.getMessage());
            System.out.println(e.getMessage());
        } finally {
            closeSocket(socket);
        }
    }

    private static Socket getWifiSocket(String ip) {
        Exception e;
        try {
            Socket s = new Socket();
            Socket socket;
            try {
                s.setSoTimeout(5000);
                s.connect(new InetSocketAddress(ip, 25001), 5000);
                socket = s;
                return s;
            } catch (Exception e2) {
                e = e2;
                socket = s;
                e.printStackTrace();
                return null;
            }
        } catch (Exception e3) {
            e = e3;
            e.printStackTrace();
            return null;
        }
    }

    private static void closeSocket(Socket socket) {
        if (socket != null) {
            try {
                if (!socket.isClosed()) {
                    socket.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}


