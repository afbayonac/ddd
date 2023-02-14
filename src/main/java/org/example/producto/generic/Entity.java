package org.example.producto.generic;

public abstract class Entity<I extends Identity>{
    private final I id;
    public Entity(I id) {
        this.id = id;
    }

    public I identity() {
        return id;
    }

}
