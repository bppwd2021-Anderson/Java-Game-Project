package com.company;

import java.io.IOException;

public interface Shootable {
    boolean intersects(Entity bullet);

    void shoot(String type) throws IOException;

    void createBullet(Projectile projectile);
}
