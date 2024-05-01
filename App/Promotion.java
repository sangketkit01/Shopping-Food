package App;

import java.util.ArrayList;

public class Promotion {

    public ArrayList<Object[]> getAllCode(){
        final ArrayList<Object[]> promotions = new ArrayList<>();
        Object[] code1 = {"Code1",0.3,1000};
        Object[] code2 = {"Code2",0.5};
        Object[] code3 = {"Code3",0.2};
        promotions.add(code1);
        promotions.add(code2);
        promotions.add(code3);

        return promotions;

    }
}
