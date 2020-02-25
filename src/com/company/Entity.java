package com.company;

public interface Entity {

    int get_y();

    int get_x();

    boolean intersects(Entity bullet);

    int get_height();

    int get_width();
}
