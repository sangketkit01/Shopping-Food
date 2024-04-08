package App;

import java.util.ArrayList;

public class Promotion {

    public ArrayList<Object[]> getAllCode(){
        ArrayList<Object[]> arr = new ArrayList<>();
        Object[] code1 = {"Code1",0.3,1000};
        Object[] code2 = {"Code2",0.5};
        Object[] code3 = {"Code3",0.2};
        arr.add(code1);
        arr.add(code2);
        arr.add(code3);

        return arr;

    }
}
