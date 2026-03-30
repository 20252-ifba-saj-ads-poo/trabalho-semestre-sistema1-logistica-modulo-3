package br.edu.ifba.saj.fwads.model;

import java.util.UUID;

public abstract class AbstractModel {
    
    private UUID id;

    public AbstractModel() {
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ID: " + id;
    }
}
