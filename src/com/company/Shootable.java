package com.company;

import java.io.IOException;

public interface Shootable {
    boolean intersects(Entity bullet);

    void shoot(String type, int times) throws IOException;

    void createBullet(Projectile projectile);

    void removeBullet(Projectile projectile);
}
