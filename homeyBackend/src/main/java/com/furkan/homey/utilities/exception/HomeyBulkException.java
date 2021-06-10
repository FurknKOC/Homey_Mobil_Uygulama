package com.furkan.homey.utilities.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Getter
@Setter
public class HomeyBulkException extends RuntimeException{

    private List<HomeyException> exceptions = new ArrayList<>();

    public void throwIfNecessary() {
        if (!exceptions.isEmpty()) {
            throw this;
        }
    }

    public void addException(HomeyException homeyException) {
        this.exceptions.add(homeyException);
    }


    public void addException(Supplier<HomeyException> supplierAesException) {
        this.exceptions.add(supplierAesException.get());
    }

}
