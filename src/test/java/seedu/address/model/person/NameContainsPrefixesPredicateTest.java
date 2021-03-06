package seedu.address.model.person;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import seedu.address.testutil.PersonBuilder;

//@@author tanhengyeow
public class NameContainsPrefixesPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        NameContainsPrefixesPredicate firstPredicate =
                new NameContainsPrefixesPredicate(firstPredicateKeywordList);
        NameContainsPrefixesPredicate secondPredicate =
                new NameContainsPrefixesPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameContainsPrefixesPredicate firstPredicateCopy =
                new NameContainsPrefixesPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsPrefixes_returnsTrue() {
        // One prefix
        NameContainsPrefixesPredicate predicate =
                new NameContainsPrefixesPredicate(Collections.singletonList("Ali"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Mixed-case prefix
        predicate = new NameContainsPrefixesPredicate(Arrays.asList("aLi"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainPrefixes_returnsFalse() {
        // Zero prefix
        NameContainsPrefixesPredicate predicate = new NameContainsPrefixesPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").build()));

        // Non-matching prefix
        predicate = new NameContainsPrefixesPredicate(Arrays.asList("Charl"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }
}
