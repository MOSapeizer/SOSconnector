package sosconnector.Filter;

/**
 * Created by zil on 2016/4/26.
 */
public class Analyze {

    private static int mb = 1024 * 1024;
    private static Packet sensor = new Packet( "swes:InsertSensor", "swes:InsertSensorResponse" );
    private static Packet observation = new Packet( "sos:InsertObservation", "sos:InsertObservationResponse" );
    private static Packet[] packets = new Packet[] { sensor, observation };

    public static void statistic( String type, String message ){
        for (Packet packet : packets) {
            if( packet.isType( type ) )
                packet.check( message );
        }
    }

    private static String packet_result( int index, String type ){
        int total = packets[index].getTOTAL();
        int success = packets[index].getSUCCESS();
        String result = "\nTotal " + type + ": " + total + ", new: " + success + ", same: " + (total - success);
        return result;
    }

    public static String result(){
        int total = getTotal();
        int success = getSuccess();

        String result = "";
        result += packet_result(0, "Sensors");
        result += packet_result(1, "Observations");
        result += "\nTotal Packets: " + total + ", new: " + success + ", same: " + (total - success)  + "\n" + usage() ;
        clean();
        return result;
    }

    private static int getTotal() {
        int total = 0;
        for (Packet packet : packets)
            total += packet.getTOTAL();
        return total;
    }

    private static int getSuccess() {
        int success = 0;
        for (Packet packet : packets)
                success += packet.getSUCCESS();
        return success;
    }

    private static String usage(){
        Runtime instance = Runtime.getRuntime();
        String result = "Total Memory: " + instance.totalMemory() / mb + "M"
                + ", Free Memory: " + instance.freeMemory() /mb + "M"
                + ", Used Memory: " + ( instance.totalMemory() - instance.freeMemory()) / mb  + "M"
                + ", Max Memory: " + instance.maxMemory() /mb + "M\n";

        return result;
    }

    private static void clean() {
        for (Packet packet : packets)
            packet.clean();
    }
}

class Packet{
    private int TOTAL = 0;
    private int SUCCESS = 0;
    private final String type;
    private final String success;

    Packet(String type, String success){
        this.type = type;
        this.success = success;
    }

    void check(String message){
        if( message.contains( success ))
            SUCCESS++;
        TOTAL++;
    }

    Boolean isType( String message ){
        return message.contains(type);
    }

    int getTOTAL() {
        return TOTAL;
    }

    int getSUCCESS() {
        return SUCCESS;
    }

    void clean(){
        TOTAL = 0;
        SUCCESS = 0;
    }
}
