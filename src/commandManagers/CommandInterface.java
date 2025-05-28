package commandManagers;

import exceptions.BuildObjectException;

/**
 * Интерфейс для реализации команд.
 */
public interface CommandInterface {

    /**
     * Returns the name of the command.
     * @return the command name
     */
    String getName();

    /**
     * Returns the description of the command.
     * @return the command description
     */
    String getDescr();

    /**
     * Executes the command.
     * @throws BuildObjectException if there is an error building an object required by the command
     */
    void execute() throws BuildObjectException;

    /**
     * Checks if the given argument is valid for this command.
     * @param inputArgument the argument to check
     * @return true if the argument is valid, false otherwise
     */
    boolean checkArgument(Object inputArgument);
}
