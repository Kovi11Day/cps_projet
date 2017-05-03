package services;

public interface Tech {
	int damage();
	int hstun();
	int bstun();
	int sframe();
	int hframe();
	int rframe();
	RectangleHitboxService hitbox(double x, double y, int h, int w, boolean faceRight);
}
