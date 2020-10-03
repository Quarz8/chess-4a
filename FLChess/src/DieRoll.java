import java.util.Random;

public class DieRoll {


        public static int roll()
        {

            Random rnd = new Random();
            int dieNum = -1;

            dieNum = rnd.nextInt(6) + 1;
            return dieNum;
        }
}