package common;

import java.util.Optional;

public class GetResult<T, R> {

    //pattern matching for getresult, devuelve el resultado del move

    private final Optional<T> optional;

    private final R errorValue;

    public GetResult(Optional<T> optional, R errorValue){
        this.optional = optional;
        this.errorValue = errorValue;
    }

    public Optional<T> getOptional() {
        return optional;
    }

    public R getErrorValue() {
        return errorValue;
    }
}
