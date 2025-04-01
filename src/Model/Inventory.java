package Model;

public class Inventory extends Manga {

    public enum Condition{New,Used,Undefined};

    private Manga iManga;
    private int iValue;
    private Condition iCondition;

    public Inventory(Manga manga, int value, Condition condition){
        super(manga.getName(), manga.getNumber(), manga.getPreis(), manga.getStatus() );
        iManga = manga;
        iValue = value;
        iCondition = condition;
    }

    // Setter
    public void setiValue(int iValue) {
        this.iValue = iValue;
    }

    // Getter
    public Manga getiManga() {
        return iManga;
    }
    public int getiValue() {
        return iValue;
    }
    public Condition getiCondition() {
        return iCondition;
    }

    public static Condition getConditionByString(String status){

        if (status.equals("New")){
            return Condition.New;
        } else if (status.equals("Used")) {
            return Condition.Used;
        }else{
            return Condition.Undefined;
        }
    }
}
