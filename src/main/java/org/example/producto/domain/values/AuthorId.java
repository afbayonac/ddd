package org.example.producto.domain.values;

import org.example.producto.generic.Identity;

public class AuthorId extends Identity {
    private AuthorId(String uuid) {
        super(uuid);
    }

    public static AuthorId of(String uuid){
        return new AuthorId(uuid);
    }
}
