import java.util.Random;

class Die {

    private int numSides;
    private boolean is_kept;
    private int sideUp;

    Die(int sides){
        numSides = sides;
    }

    void roll() {
        if (!is_kept) {
            Random r = new Random();
            sideUp = r.nextInt((6 - 1) + 1) + 1;
        }
    }

    int getSideUp(){
        return sideUp;
    }

    //could intake Y/N
    void setIs_kept(boolean keep){
        is_kept = keep;
    }

    boolean getIs_kept(){
        return is_kept;
    }

    int getNumSides(){
        return numSides;
    }

    void setSideUp(int side){
        sideUp = side;
    }
}

