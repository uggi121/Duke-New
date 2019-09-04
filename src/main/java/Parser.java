public class Parser {

    public Command parseInput(String input) throws DukeException {
        String cleanedInput = input.strip().toLowerCase();
        if (cleanedInput.startsWith("bye")) {
            return new ExitCommand();
        } else if (cleanedInput.startsWith("list")) {
            return new ListCommand();
        } else if (cleanedInput.startsWith("done")) {
            return new DoneCommand(input);
        } else if (checkValidAdd(input)) {
            return new AddCommand(input);
        } else if (cleanedInput.startsWith("delete")) {
            return new DeleteCommand(input);
        } else {
            throw new InvalidCommandDukeException("Unrecognised Command!");
        }
    }

    private boolean checkValidAdd(String input) {
        String processedString = input.strip().toLowerCase();
        return processedString.startsWith("todo ") || processedString.startsWith("deadline ")
                || processedString.startsWith("event ");
    }

}
