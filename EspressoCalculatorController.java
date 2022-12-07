import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class EspressoCalculatorController {
	
	//finals
	public static final int MIN_BREWING_TIME = 25;
    public static final int MAX_BREWING_TIME = 30;

    @FXML
    private Text flowfx;

    @FXML
    private TextField grinder_settingfx;

    @FXML
    private TextField grounded_coffeefx;

    @FXML
    private Text gtip;

    @FXML
    private Button highbutton;

    @FXML
    private TextField outputfx;

    @FXML
    private TextField pressfx;

    @FXML
    private GridPane pressure;

    @FXML
    private Text scorefx;
    

    @FXML
    private TextField timefx;

    @FXML
    private Text tip1;

    @FXML
    private Text tip2;

    @FXML
    private Text tip3;

    @FXML
    private Text tip4;

    @FXML
    private Text tip5;

    @FXML
    private VBox tipsVbox;

    @FXML
    private TextField typefx;

    @FXML
    void calculate(MouseEvent event) {
    	int type = Integer.parseInt(typefx.getText()),error=1;
        double 
        grounded_coffee = Double.parseDouble(grounded_coffeefx.getText()),
        grinder_setting = Double.parseDouble(grinder_settingfx.getText()),
        time = Double.parseDouble(timefx.getText()),
        output  = Double.parseDouble(outputfx.getText()),
        pressure= Double.parseDouble(pressfx.getText());
        double score = 100;
        int tip1=0,tip2=0,tip3=0,tip4=0;
        int recommended_amount;
        
        
        if (type==1) recommended_amount=11;
        else if (type==2) recommended_amount=18;
        else recommended_amount=24;

        if ((grounded_coffee-recommended_amount)>1)
        {
            tip1= 1; //slow flow
            score += (grounded_coffee-recommended_amount)*10;
        }
        if ((grounded_coffee-recommended_amount)<-1)
        {
            tip1=-1; //fast flow
            score += (grounded_coffee-recommended_amount)*10;
        }
        if ((time-MAX_BREWING_TIME)>0) tip2= 1; //slow flow
        if ((time-MIN_BREWING_TIME)<0) tip2=-1; //fast flow
        score += (time-((MAX_BREWING_TIME+MIN_BREWING_TIME)/2))*1;

        if (((grounded_coffee*2)-(output))>0) tip3= 1; // slow flow
        if (((grounded_coffee*2)-(output))<0) tip3=-1; // fast flow
        score += ((grounded_coffee*2)-(output))*1;

        if (pressure>9) tip4= 1; // slow flow
        if (pressure<9) tip4=-1; // fast flow
        
         
        
        
        
        //FX
        String msg= "";
        scorefx.setText(""+score);
        if  (score == 100)                gtip.setText("You'r coffee score is fantastic enjoy you'r coffee :)");
        if ((score >= 97) &&(score<=100)) gtip.setText("You'r coffee score is fantastic\nyou can put a little more coffee but we recomamnd not to change grinder settings ");
        if ((score >= 100)&&(score<=103)) gtip.setText("You'r coffee score is fantastic\nyou can put a little less coffee but we recomamnd not to change grinder settings ");

        if (score>103)//slow flow
        {
            msg+=("A score higher than 100 means there is a slow flow of the espresso");
            if (tip2==1) msg+=("\nthe brewing time was high indicating slow flow");
            if (tip3==1) msg+=("\nthe output weight was low indicating slow flow");
            if (tip4==1) msg+=("\nthe brewing pressure was high indicating slow flow");
            if (tip1==1) msg+=("\nyou used more grounded coffee than recommanded ("+ (recommended_amount) +") consider lower amount");
            else msg+=("you used a good amount of ground coffee, therfor consider coarser grinding size");
        }

        if (score<97)//fast flow
        {
            msg+=("A score lower than 100 means there is a fast flow of the espresso");
            if (tip2==-1) msg+=("\nthe brewing time was low indicating fast flow");
            if (tip3==-1) msg+=("\nthe output weight was high indicating fast flow");
            if (tip4==-1) msg+=("\nthe brewing pressure was low indicating slow flow\n");
            if (tip1==-1) msg+=("\nyou used less grounded coffee than recommanded ("+ (recommended_amount) +") consider higher amount");
            else          msg+=("you used a good amount of ground coffee, therfor consider finer grinding size");
        }
        flowfx.setText(msg);
    }

    @FXML
    void tips(MouseEvent event) {
    	tipsVbox.setVisible(!(tipsVbox.isVisible()));
    }
    
    public void initialize() {
    	
    }

}
