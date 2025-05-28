package commandManagers;

/**
 * Abstract class representing a command that can be executed in the program.
 * @param <T> The type of collection manager this command works with
 */
public abstract class Command<T> {
    /**
     * The collection manager for this command.
     */
    protected T collectionManager;

    /**
     * The argument for this command.
     */
    protected String argument;

    /**
     * Constructs a new command with the specified collection manager.
     * @param collectionManager the collection manager for this command
     */
    public Command(T collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Gets the name of this command.
     * @return the command name
     */
    public abstract String getCommandName();

    /**
     * Gets the description of this command.
     * @return the command description
     */
    public abstract String getDescription();

    /**
     * Checks if the given arguments are valid for this command.
     * @param args the arguments to check
     * @return true if the arguments are valid, false otherwise
     */
    public abstract boolean checkArgument(String[] args);

    /**
     * Executes this command with the given arguments.
     * @param args the arguments for this command
     */
    public abstract void execute(String[] args);

    /**
     * Sets the argument for this command.
     * @param argument the argument to set
     */
    public void setArgument(String argument) {
        this.argument = argument;
    }

    /**
     * Gets the argument for this command.
     * @return the argument
     */
    public String getArgument() {
        return argument;
    }
}
