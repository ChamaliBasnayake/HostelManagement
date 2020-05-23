/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

/**
 *
 * @author Chandika
 */
import java.util.Scanner;
import org.smslib.AGateway;
import org.smslib.IOutboundMessageNotification;
import org.smslib.Library;
import org.smslib.OutboundMessage;
import org.smslib.Service;
import org.smslib.modem.SerialModemGateway;



public class SendMessage {
     public void doIt(String recip,String sms) throws Exception
        {
                OutboundNotification outboundNotification = new OutboundNotification();
                System.out.println("Example: Send message from a serial gsm modem.");
                System.out.println(Library.getLibraryDescription());
                System.out.println("Version: " + Library.getLibraryVersion());
                SerialModemGateway gateway = new SerialModemGateway("modem.com4", "COM24", 9600, "", "");
                gateway.setSmscNumber("+947100003"); 
                gateway.setInbound(true);
                gateway.setOutbound(true);
                Service.getInstance().setOutboundMessageNotification(outboundNotification);
                Service.getInstance().addGateway(gateway);
                Service.getInstance().startService();
                System.out.println();
                System.out.println("Modem Information:");
                System.out.println("  Manufacturer: " + gateway.getManufacturer());
                System.out.println("  Model: " + gateway.getModel());
                System.out.println("  Serial No: " + gateway.getSerialNo());
                System.out.println("  SIM IMSI: " + gateway.getImsi());
                System.out.println("  Signal Level: " + gateway.getSignalLevel() + " dBm");
                System.out.println("  Battery Level: " + gateway.getBatteryLevel() + "%");
                System.out.println();
               
                OutboundMessage msg = new OutboundMessage("+94"+recip, sms);
                Service.getInstance().sendMessage(msg);
                System.out.println(msg);
                //System.out.println("Now Sleeping - Hit <enter> to terminate.");
                //System.in.read();
                Service.getInstance().stopService();
                Service.getInstance().removeGateway(gateway);
               
        }

        public class OutboundNotification implements IOutboundMessageNotification
        {
                public void process(AGateway gateway, OutboundMessage msg)
                {
                        System.out.println("Outbound handler called from Gateway: " + gateway.getGatewayId());
                        System.out.println(msg);
                }
        }

       public static void main(String args[])
        {
                SendMessage app = new SendMessage();
                try
                {
                 /*String[] arr = { "712282223", "713857140","775816190","716532033" };
                    System.out.println("Enter your message");
                 Scanner sc=new Scanner(System.in);
                 String msg=sc.nextLine();
                    for(int i=0;i<arr.length;i++){
                    
                        app.doIt(arr[i],msg);
                    }*/
                   /* String number="712282223";
                    String message="hello";
                    app.doIt(number,message);*/
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }
        }
}
