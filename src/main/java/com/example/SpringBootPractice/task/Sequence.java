package com.example.SpringBootPractice.task;

import jakarta.persistence.*;

@Entity
@Table(name = "sequences")
public class Sequence {

    @Id
    @Column(length = 50)
    private String name;

    @Column(name = "current_value", nullable = false)
    private long currentValue;

    protected Sequence() {}

    public Sequence(String name, long currentValue) {
        this.name = name;
        this.currentValue = currentValue;
    }

    public String getName() {
        return name;
    }

    public long getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(long currentValue) {
        this.currentValue = currentValue;
    }
}