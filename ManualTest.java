import java.util.HashMap;
import java.util.Map;

public class ManualTest {
    public static void main(String[] args) {
        // Create states

        AlpacaSheepGuards alpacaSheepGuards = new AlpacaSheepGuards();
        alpacaSheepGuards.initialize();
        StringBuffer sb = new StringBuffer();
        HashMap<String, Double> predatorsInfo = alpacaSheepGuards.getFarm().getPredatorsNamesAndDangerFactors();
        alpacaSheepGuards.getFarm().addAlpacaOnce();
        
        for (Map.Entry<String, Double> entry : predatorsInfo.entrySet()) 
        {
            String predatorName = entry.getKey();
            Double dangerFactor = entry.getValue();
            sb.append(String.format("%s %.2f", predatorName, dangerFactor)+ "\n");
        
            for (int i = 0 ; i <=2;i++)
            {

                sb.append(String.format("sheep with %d alpaca: ",  i));
                sb.append(alpacaSheepGuards.getFarm().getSheeps().get(0).getDeathProbability(dangerFactor, i) + "\n");
            }
        }
        for (Map.Entry<String, Double> entry : predatorsInfo.entrySet()) 
        {
            String predatorName = entry.getKey();
            Double dangerFactor = entry.getValue();
            sb.append(String.format("%s %.2f", predatorName, dangerFactor)+ "\n");
        
            for (int i = 0 ; i <=2;i++)
            {

                sb.append(String.format("lamb with %d alpaca: ",  i));
                sb.append(alpacaSheepGuards.getFarm().getLambs().get(0).getDeathProbability(dangerFactor, i) + "\n");
            }
        }        
        
        for (Map.Entry<String, Double> entry : predatorsInfo.entrySet()) 
        {
            String predatorName = entry.getKey();
            Double dangerFactor = entry.getValue();
            sb.append(String.format("%s %.2f", predatorName, dangerFactor));
        
            for (int i = 0 ; i <=2;i++)
            {

                sb.append(String.format("alpaca with %d alpaca: ",  i));
                sb.append(alpacaSheepGuards.getFarm().getAlpacas().get(0).getDeathProbability(dangerFactor, i) + "\n");
            }
        }
        System.out.println(sb.toString());
    }
}