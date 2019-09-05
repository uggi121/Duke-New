public class FindCommand extends Command {

    private String cleanedInput;

    public FindCommand(String cleanedInput) {
        this.cleanedInput = cleanedInput;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidFindDukeException {
        if (cleanedInput.equalsIgnoreCase("find")
                || cleanedInput.split("\\s+").length == 1) {
            throw new InvalidFindDukeException("Invalid find command! Please enter a description after \"find\"");
        } else {
            String descriptionToMatch = cleanedInput.substring(cleanedInput.indexOf("find") + 4)
                    .toLowerCase().strip();
            String matchedDescriptions = tasks.findTasks(descriptionToMatch);
            if (matchedDescriptions.isBlank()) {
                ui.displayOutput("No matching tasks found!");
            } else {
                ui.displayOutput(matchedDescriptions);
            }
        }
    }

    public boolean checkExit() {
        return false;
    }

}
