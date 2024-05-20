package hexlet.code;

public class Validator implements ValidateSchema {
    //private ValidateSchema validateSchema;
    private Validator validator;
    private Integer minLength;
   // private boolean isRequired;

   /* public Validator() {
        validateSchema = new Validator();
    }*/

    public StringSchema string() {
        return new StringSchema();
    }

}
