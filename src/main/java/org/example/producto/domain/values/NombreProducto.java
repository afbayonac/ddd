package org.example.producto.domain.values;

import org.example.producto.generic.ValueObject;

import java.util.Objects;

public class NombreProducto implements ValueObject<NombreProducto.Props> {
    private final String name;
    private final String version;

    public NombreProducto(final String name, final String version) {
        this.name = Objects.requireNonNull(name);
        this.version = Objects.requireNonNull(version);
    }

    @Override
    public Props value() {
        return new Props() {
            @Override
            public String version() {
                return version;
            }

            @Override
            public String name() {
                return name;
            }

            @Override
            public String fullName() {
                return name + "-" + version;
            }
        };
    }

    public interface Props {
        String version();
        String name();
        String fullName();
    }
}
