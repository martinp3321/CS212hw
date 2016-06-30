import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.Test;

public class TestNumberListBuilder {

	@Test
	public void testInvalidSchema() {
		String test = "testInvalidSchema";
		Path path = Paths.get("input/invalidSchema.json");
		try {
			NumberListBuilder.buildList(path);
		} catch(InvalidJsonSchemaException ise) {
			//passes test
			return;
		} catch(Exception e) {
			fail(String.format("%s: expected - InvalidJsonSchemaException actual - %s", test, e.getClass().getName()));
			return;
		}		
		fail(String.format("%s: expected - InvalidJsonSchemaException actual - no exception thrown", test));		
	}

	@Test
	public void testFileNotFound() {
		String test = "testFileNotFound";
		Path path = Paths.get("input/NOTFOUND.json");
		try {
			NumberListBuilder.buildList(path);
		} catch(NoSuchFileException e) {
			//passes test
			return;
		} catch(Exception e) {
			fail(String.format("%s: expected - NoSuchFileException actual - %s", test, e.getClass().getName()));
			return;
		}
		fail(String.format("%s: expected - NoSuchFileException actual - no exception thrown", test));
	}

	@Test
	public void testUnparseableJson() {
		String test = "testUnparseableJson";
		Path path = Paths.get("input/unparseableList.json");
		ArrayList<Integer> result = null;
		try {
			result = NumberListBuilder.buildList(path);
		} catch(Exception e) {
			fail(String.format("%s: expected - No exception actual - %s", test, e.getClass().getName()));
			return;
		}
		assertTrue((result != null && result.size() == 0));
	}

	@Test
	public void testValid() {
		String test = "testValid";
		Path path = Paths.get("input/list.json");
		ArrayList<Integer> result = null;
		try {
			result = NumberListBuilder.buildList(path);
		} catch(Exception e) {
			fail(String.format("%s: expected - No exception actual - %s", test, e.getClass().getName()));
			return;
		}

		int[] expected = {1, 3, 15, 6, 1, 9, 3};
		int[] actual = result.stream().mapToInt(i -> i).toArray();		
		assertArrayEquals(String.format("%s: mismatched result array", test), 
				expected, actual);
	}

	
}
