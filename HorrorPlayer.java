public class HorrorPlayer
{
    double x;
    double y;
    double angle;
    int health;
    int nerve;
    int ammo;
    int relics;

    public HorrorPlayer(double x, double y, double angle)
    {
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.health = 100;
        this.nerve = 100;
        this.ammo = 30;
        this.relics = 0;
    }
}
