package experiment.base;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

/**
 * @author : liulei
 **/
public class Iterator {

    public static void main(String[] args) throws Exception{
//        InetAddress localHost = InetAddress.getLocalHost();
//        byte[] address = localHost.getAddress();
        Set<String> addrs = new HashSet<String>();
        Enumeration<NetworkInterface> ns = NetworkInterface.getNetworkInterfaces();
        while (ns != null && ns.hasMoreElements()) {
            NetworkInterface n = ns.nextElement();
            Enumeration<InetAddress> is = n.getInetAddresses();
            while (is.hasMoreElements()) {
                InetAddress i = is.nextElement();
                if (!i.isLoopbackAddress() && !i.isLinkLocalAddress() && !i.isMulticastAddress()) {
                    addrs.add(i.getHostAddress());
                }
            }
        }


        for ( String temp : addrs) {
            System.out.println(temp);
        }

    }

}
