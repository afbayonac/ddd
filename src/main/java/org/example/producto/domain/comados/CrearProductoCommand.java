package org.example.producto.domain.comados;

import org.example.producto.generic.Command;

public class CrearProductoCommand extends Command {
    private String authorId;
    private String name;
    private String version;

    public CrearProductoCommand() {}

    public CrearProductoCommand(String author, String name, String version) {
        this.authorId = author;
        this.name = name;
        this.version = version;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
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
