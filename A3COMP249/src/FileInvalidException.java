//----------------------------------
//Assignment 3
//Question 1 of 1 A3
//Written by Dariush Noorzada ID 40064242
//Due date March 19th
//----------------------------------

/**
 * Exception class in case file is invalid
 * @author Dariush Noorzada
 * @version 1.1
 */
public class FileInvalidException extends Exception{
    //Just a simple exception used in case the file is invalid

    /**
     * Default constructor
     */
    public FileInvalidException() {
        super("Error: Input file cannot be parsed due to missing information\n" +
                "(i.e. month={}, title={}, etc.)");
    }

    /**
     * Return the error message
     * @return Error message
     */
    public String getMessage () {
        return super.getMessage();
    }
}
