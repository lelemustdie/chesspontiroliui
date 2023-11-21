package common;

import java.util.Optional;

public class GetResult<T, R> {

    //pattern matching for getresult, devuelve el resultado del move

    private final Optional<T> optional; //valor opcional t o valor de error r, manejo de nulls

    private final R errorValue;

    public GetResult(Optional<T> optional, R errorValue){ //priv
        this.optional = optional;
        this.errorValue = errorValue; //either
    }
    //dos metodos estaticos, create error result y validResult
    public Optional<T> getOptional() {
        return optional;
    }

    public R getErrorValue() {
        return errorValue;
    }
}
