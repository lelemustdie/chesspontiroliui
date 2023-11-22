package common;

import java.util.Optional;

public class GetResult<T, R> {

    //pattern matching for getresult, devuelve el resultado del move

    private final Optional<T> optional; //valor opcional t o valor de error r, manejo de nulls

    private final R errorValue;

    private final String message;

    public GetResult(Optional<T> optional, R errorValue){ //priv
        this.optional = optional;
        this.errorValue = errorValue;
        this.message="";  //either
    }

    public GetResult(Optional<T> optional, R errorValue, String message) {
        this.optional = optional;
        this.errorValue = errorValue;
        this.message = message;

    }
    //dos metodos estaticos, create error result y validResult
    public Optional<T> getOptional() {
        return optional;
    }

    public R getErrorValue() {
        return errorValue;
    }

    public String getMessage() {
        return message;
    }

}
