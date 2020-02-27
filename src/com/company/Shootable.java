package com.company;

import java.io.IOException;

public interface Shootable {
    boolean intersects(Entity bullet);

    void createBullet(Projectile projectile);

    void shoot(String type) throws IOException;
}
