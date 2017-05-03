package services;

public interface Tech {
	int damage();
	int hstun();
	int bstun();
	int sframe();
	int hframe();
	int rframe();
	HitboxService hitbox(int x, int y, int h, int w, boolean faceRight);
}
