package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.FindResults;

//@@author tanhengyeow
/**
 * Tests that a {@code Person}'s {@code University} matches any of the keywords given.
 */
public class UniversityContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;
    private final String commandPrefix = "u/";

    public UniversityContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> FindResults.getInstance()
                        .containsWordIgnoreCase(person.getUniversity().value, keyword, commandPrefix)
                        || keywords.stream()
                        .anyMatch(fuzzyKeyword -> FindResults.getInstance().containsFuzzyMatchIgnoreCase(
                                person.getUniversity().value, fuzzyKeyword, commandPrefix,
                                FindCommand.LEVENSHTEIN_DISTANCE_THRESHOLD)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniversityContainsKeywordsPredicate // instanceof handles nulls
                && this.keywords.equals(((UniversityContainsKeywordsPredicate) other).keywords)); // state check
    }

}
