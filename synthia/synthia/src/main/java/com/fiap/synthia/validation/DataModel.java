package com.fiap.synthia.validation;

public class DataModel {
    private Long id;
    private String field;

    public DataModel(Long id, String field) {
        this.id = id;
        this.field = field;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
