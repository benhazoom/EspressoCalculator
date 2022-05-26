
/**
 * This program calculaes how well you made a shot of espresso
 *
 * @author - 208648642 Binyamin Hazoom
 * @date 05.22
 */
import java.util.Scanner;
public class CoffeeCalculator
{

    public static final int MIN_BREWING_TIME = 25;
    public static final int MAX_BREWING_TIME = 30;


    public static void main(String[] args)
    {

        Scanner scan = new Scanner(System.in);
        int type,error=1;
        double grounded_coffee,grinder_setting,time,output,pressure;
        char answer;
        double score = 100;
        int tip1=0,tip2=0,tip3=0,tip4=0;
        int recommended_amount;
        System.out.println("Hello dear coffee maker this is a program designed to help you extract the most out of your espreesso shot");
        System.out.println("would you like to review coffee tips for making espresso ? (y/n) ");
        answer = scan.next().charAt(0);
        if (answer=='y')
            System.out.println("Recommended equipment: kitchen scale 1gr accuracy, timer. \nUse propper amount of coffee beans for corresponding basket (a gram less or a gram more is okay more than that is not good)\nSingle - 11g\nDouble - 18g\nTriple - 24g \nGrind your beans in the grinder and write down the grind size\nWeight the grinded beans to make sure its the same as said before and write down the weight\nThump the coffe pock, start brewing, start the timer, write down how long it took the shot (should be around 25-30 secounds)\nWrite down the pressure inwhich the machine brewed \nWrite down the finel output (should be twice as the grounded coffee weight)\nIn summery there are 6 factors to write down - basket type,coffee weight,grinding size,brew time,output,pressure\n");

        System.out.println("Enter only numbers and try to answer as many questions as you can for better calculating");

        System.out.println("please select basket type 1 for single 2 for double 3 for triple");
        type = scan.nextInt();
        while (type!=1 && type!=2 && type!=3) 
        {
            System.out.println("invalid input");
            System.out.println("please select basket type 1 for single 2 for double 3 for triple");
            type = scan.nextInt();
        }

        System.out.println("please enter the amount of coffee you brewed in grams");
        grounded_coffee = scan.nextDouble();

        System.out.println("please enter the grind size (grinder setting)");
        grinder_setting = scan.nextDouble();

        System.out.println("please enter the time took for brewing in secounds (enter 0 if not counted)");
        time = scan.nextDouble();

        System.out.println("please enter the finel output weight in grams (enter 0 if not counted)");
        output = scan.nextDouble();

        System.out.println("please enter the brewing pressure in bar (enter 0 if not counted)");
        pressure = scan.nextDouble();

        //score calculation low score for fast flow

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

        if (pressure>9) tip4= 1; // slow flors
        if (pressure<9) tip4=-1; // fast flow
        
             
        System.out.println("You'r coffee score is "+ score);
        if  (score == 100) System.out.println("You'r coffee score is fantastic enjoy you'r coffee :)");
        if ((score >= 97) &&(score<=100)) System.out.println("You'r coffee score is fantastic\nyou can put a little more coffee but we recomamnd not to change grinder settings ");
        if ((score >= 100)&&(score<=103)) System.out.println("You'r coffee score is fantastic\nyou can put a little less coffee but we recomamnd not to change grinder settings ");

                if (score>103)//slow flow
        {
            System.out.println("A score higher than 100 means there is a slow flow of the espresso");
            if (tip2==1) System.out.println("the brewing time was high indicating slow flow");
            if (tip3==1) System.out.println("the output weight was low indicating slow flow");
            if (tip4==1) System.out.println("the brewing pressure was high indicating slow flow");
            if (tip1==1) System.out.println("you used more grounded coffee than recommanded ("+ (recommended_amount) +") consider lower amount");
            else System.out.println("you used a good amount of ground coffee, therfor consider coarser grinding size");
        }

        if (score<97)//fast flow
        {
            System.out.println("A score lower than 100 means there is a fast flow of the espresso");
            if (tip2==-1) System.out.println("the brewing time was low indicating fast flow");
            if (tip3==-1) System.out.println("the output weight was high indicating fast flow");
            if (tip4==-1) System.out.println("the brewing pressure was low indicating slow flow");
            if (tip1==-1) System.out.println("you used less grounded coffee than recommanded ("+ (recommended_amount) +") consider higher amount");
            else System.out.println("you used a good amount of ground coffee, therfor consider finer grinding size");
        }
    }
}