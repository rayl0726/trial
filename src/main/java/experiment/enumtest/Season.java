package experiment.enumtest;

/**
 * @author : liulei
 **/
public enum Season {
    SPRING(1), SUMMER(2), AUTUMN(3), WINER(4);

    private int code;
    private Season(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
