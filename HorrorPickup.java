public class HorrorPickup
{
    //item types
    public static final int AMMO = 1;
    public static final int HEALTH = 2;
    public static final int RELIC = 3;
    public static final int GATE = 4;

    final double x;
    final double y;
    final int type;
    boolean taken; //required for stop rendering

    public HorrorPickup(double x, double y, int type)
    {
        this.x = x;
        this.y = y;
        this.type = type;
        this.taken = false;
    }

    public HorrorPickup(HorrorPickup other)
    {
        this(other.x, other.y, other.type);
    }
}
