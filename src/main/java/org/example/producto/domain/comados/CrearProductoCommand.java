package org.example.producto.domain.comados;

import org.example.producto.generic.Command;

public class CrearProductoCommand extends Command {
    private String uuid;
    private String author;
    private String name;
    private String version;

    public CrearProductoCommand() {}

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
