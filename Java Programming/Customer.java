import java.io.Serializable;

public class Customer implements Serializable {
    int id;
    String name;
    String phon_num;
    String address;
    String branch;
    int balance;
    String lastpayment;

    @Override
    public String toString()
    {
        return name+" "+phon_num+" "+address+" "+branch+" "+balance+" "+lastpayment;
    }

}