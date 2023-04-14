package gamification.accesa.controller.validator;

import gamification.accesa.controller.exception.InputException;

public class NumberValidator{
    public static void validate(String number) throws InputException{
        if(number == null || number.isEmpty()){
            throw new InputException("Tokens field is empty!");
        }
        if(!number.matches("[0-9]+")){
            throw new InputException("Tokens field is not a number!");
        }
    }
}
