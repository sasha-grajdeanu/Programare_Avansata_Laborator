package Compulsory;

/**
 * @author Grajdeanu Alexandru-Cristian
 * Clasa Road descrie un drum dintre 2 localitati, ce are un anume tip, o lungime data și o limita de viteza.
 */
public class Road {

    //members
    private RoadType type;
    private Location start;
    private Location finish;
    private int length;
    private int speed;

    /**
     * Constructorul pentru clasa Road
     *
     * @param type   tipul drumului
     * @param start  capatul de inceput al drumului
     * @param finish capatul de final al drumului
     * @param length lungimea drumului
     * @param speed  viteza maxima admisa a drumului
     */
    public Road(RoadType type, Location start, Location finish, int length, int speed) {
        this.type = type;
        this.start = start;
        this.finish = finish;
        this.length = length;
        this.speed = speed;
    }

    /**
     * returneaza lungimea drumului
     *
     * @return length
     */
    public int getLength() {
        return length;
    }

    /**
     * returneaza capatul de final al drumului
     *
     * @return finish
     */
    public Location getFinish() {
        return finish;
    }

    /**
     * returneaza tipul drumului
     *
     * @return type
     */
    public RoadType getType() {
        return type;
    }

    /**
     * returneaza viteza maxima admisa a drumului
     *
     * @return speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * returneaza capatul de start al drumului
     *
     * @return start
     */
    public Location getStart() {
        return start;
    }

    /**
     * seteaza capatul de final al drumului
     *
     * @param finish
     */
    public void setFinish(Location finish) {
        this.finish = finish;
    }

    /**
     * seteaza lungimea drumului
     *
     * @param length
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * seteaza viteza maxima admisa a drumului
     *
     * @param speed
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * seteaza capatul de start al drumului
     *
     * @param start
     */
    public void setStart(Location start) {
        this.start = start;
    }

    /**
     * seteaza tipul drumului
     *
     * @param type
     */
    public void setType(RoadType type) {
        this.type = type;
    }

    /**
     * suprascrie metoda toString() pentru clasa Road
     *
     * @return Road ca string
     */
    @Override
    public String toString() {
        String describeRoad = "Road = [ type = " + this.type + "; lenght = " + this.length + "; speed = " + this.speed + "km/h; Location 1 = " + this.start + "; Location 2 = " + this.finish + " ]";
        return describeRoad;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Road)) return false;
        final Road other = (Road) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$type = this.getType();
        final Object other$type = other.getType();
        if (this$type == null ? other$type != null : !this$type.equals(other$type)) return false;
        final Object this$start = this.getStart();
        final Object other$start = other.getStart();
        if (this$start == null ? other$start != null : !this$start.equals(other$start)) return false;
        final Object this$finish = this.getFinish();
        final Object other$finish = other.getFinish();
        if (this$finish == null ? other$finish != null : !this$finish.equals(other$finish)) return false;
        if (this.getLength() != other.getLength()) return false;
        if (this.getSpeed() != other.getSpeed()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Road;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $type = this.getType();
        result = result * PRIME + ($type == null ? 43 : $type.hashCode());
        final Object $start = this.getStart();
        result = result * PRIME + ($start == null ? 43 : $start.hashCode());
        final Object $finish = this.getFinish();
        result = result * PRIME + ($finish == null ? 43 : $finish.hashCode());
        result = result * PRIME + this.getLength();
        result = result * PRIME + this.getSpeed();
        return result;
    }
}

