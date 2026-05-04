//DTO For results of single raycast

public class RayHit
{
    final double distance; // dist to the wall hit
    final int side; // NS or EW hit
    final int cell; // wall type id
    final int mapX;
    final int mapY;
    final double texture; // x-coord on the texture

    public RayHit(double distance, int side, int cell, int mapX, int mapY, double texture)
    {
        this.distance = distance;
        this.side = side;
        this.cell = cell;
        this.mapX = mapX;
        this.mapY = mapY;
        this.texture = texture;
    }
}
