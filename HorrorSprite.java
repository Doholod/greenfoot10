public class HorrorSprite
{
    final double x;
    final double y;
    final Demon demon;
    final HorrorPickup pickup;
    double distanceSq; /// squared distance for sorting performance

    // wrap demon into a sprite object
    public HorrorSprite(Demon demon)
    {
        this.x = demon.x;
        this.y = demon.y;
        this.demon = demon;
        this.pickup = null;
    }

    public HorrorSprite(HorrorPickup pickup)
    {
        this.x = pickup.x;
        this.y = pickup.y;
        this.demon = null;
        this.pickup = pickup; //// item ref
    }
}
