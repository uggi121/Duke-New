public class Parser {

    public Command parseInput(String input) throws DukeException {
        String cleanedInput = input.strip().toLowerCase();
        if (isNullaryCommand(cleanedInput)) {
            return makeNullaryCommand(cleanedInput);
        } else if (isUnaryCommand(cleanedInput)) {
            return makeUnaryCommand(cleanedInput);
        } else if (cleanedInput.startsWith("done")) {
            return new DoneCommand(cleanedInput);
        } else {
            throw new InvalidCommandDukeException("Unrecognized Command!");
        }
    }

    private boolean isNullaryCommand(String cleanedInput) {
        return cleanedInput.startsWith("list") || cleanedInput.startsWith("bye");
    }

    private Command makeNullaryCommand(String cleanedInput) {
        if (cleanedInput.startsWith("list")) {
            return new ListCommand();
        } else {
            return new ExitCommand();
        }
    }

    private boolean isUnaryCommand(String cleanedInput) {
        return cleanedInput.startsWith("delete") || checkValidAdd(cleanedInput) || cleanedInput.startsWith("find");
    }

    private Command makeUnaryCommand(String cleanedInput) {
        if (cleanedInput.startsWith("delete")) {
            return new DeleteCommand(cleanedInput);
        } else if (cleanedInput.startsWith("find")) {
            return new FindCommand(cleanedInput);
        } else {
            return new AddCommand(cleanedInput);
        }
    }

    private boolean checkValidAdd(String cleanedInput) {
        return cleanedInput.startsWith("todo ") || cleanedInput.startsWith("deadline ")
                || cleanedInput.startsWith("event ");
    }

}
