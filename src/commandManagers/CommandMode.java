package commandManagers;

/**
 * Перечисление {@code CommandMode} задаёт режимы работы командного исполнителя.
 */
public enum CommandMode {
    /**
     * Режим командной строки (CLI) для обычного пользователя.
     */
    CLI_UserMode,    // Interactive command-line mode
    /**
     * Режим выполнения скриптов.
     */
    NonUserMode      // Non-interactive mode (e.g., script execution)
} 