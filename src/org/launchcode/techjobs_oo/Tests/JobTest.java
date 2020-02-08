package org.launchcode.techjobs_oo.Tests;

import org.junit.BeforeClass;
import org.junit.Test;

import org.launchcode.techjobs_oo.CoreCompetency;
import org.launchcode.techjobs_oo.Location;
import org.launchcode.techjobs_oo.Employer;
import org.launchcode.techjobs_oo.Job;
import org.launchcode.techjobs_oo.PositionType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class JobTest {

    static Job emptyJob1;
    static Job emptyJob2;
    static Job populatedJob1;
    static Job populatedJob2;
    static Job partiallyPopulatedJob1;
    static Job partiallyPopulatedJob2;

    @BeforeClass
    public static void createTestJobObjects() {
        emptyJob1 = new Job();
        emptyJob2 = new Job();
        populatedJob1 = new Job("Product tester", new Employer("ACME"), new Location("Desert"), new PositionType("Quality control"), new CoreCompetency("Persistence"));
        populatedJob2 = new Job("Product tester", new Employer("ACME"), new Location("Desert"), new PositionType("Quality control"), new CoreCompetency("Persistence"));
        partiallyPopulatedJob1 = new Job("Product tester", new Employer("ACME"), new Location(""), new PositionType(""), new CoreCompetency(""));
        partiallyPopulatedJob2 = new Job("", new Employer(""), new Location("Desert"), new PositionType("Quality control"), new CoreCompetency("Persistence"));
    }

    @Test
    public void testSettingJobId() {
        assertEquals(emptyJob1.getId(), (emptyJob2.getId() - 1), 0);
    }

    @Test()
    public void testJobConstructorSetsAllFields() {
        assertEquals(populatedJob1.getName(), "Product tester");
        assertEquals(populatedJob1.getEmployer().toString(), "ACME");
        assertEquals(populatedJob1.getLocation().toString(), "Desert");
        assertEquals(populatedJob1.getPositionType().toString(), "Quality control");
        assertEquals(populatedJob1.getCoreCompetency().toString(), "Persistence");
        assertTrue(populatedJob1.getEmployer() instanceof Employer);
        assertTrue(populatedJob1.getLocation() instanceof Location);
        assertTrue(populatedJob1.getPositionType() instanceof PositionType);
        assertTrue(populatedJob1.getCoreCompetency() instanceof CoreCompetency);
    }

    @Test
    public void testJobsForEquality() {
        assertFalse(populatedJob1.equals(populatedJob2));
    }

    @Test
    public void test_toString_ReturnsSurroundingBlankLines() {
        assertTrue(populatedJob1.toString().startsWith("\n"));
        int index = populatedJob1.toString().length() - 1;
        assertTrue(populatedJob1.toString().startsWith("\n",index) );
    }

    @Test
    public void test_toString_FieldsAreLabeledOnSeparateLines() {
        String returnedString = populatedJob1.toString();
        String[] line = returnedString.split("\n");
        assertTrue(line[1].startsWith("ID: "));
        assertTrue(line[2].startsWith("Name: "));
        assertTrue(line[3].startsWith("Employer: "));
        assertTrue(line[4].startsWith("Location: "));
        assertTrue(line[5].startsWith("Position Type: "));
        assertTrue(line[6].startsWith("Core Competency"));
    }

    @Test
    public void test_toString_ReturnsProperlyPopulatedFields() {
        String returnedString = partiallyPopulatedJob1.toString();
        String[] line = returnedString.split("\n");
        int dataPos = line[2].indexOf(": ") + 2;
        assertEquals("Product tester", line[2].substring(dataPos));
        dataPos = line[3].indexOf(": ") + 2;
        assertEquals("ACME", line[3].substring(dataPos));
        dataPos = line[4].indexOf(": ") + 2;
        assertEquals("Data not available", line[4].substring(dataPos));
        dataPos = line[5].indexOf(": ") + 2;
        assertEquals("Data not available", line[5].substring(dataPos));
        dataPos = line[6].indexOf(": ") + 2;
        assertEquals("Data not available", line[6].substring(dataPos));

        returnedString = partiallyPopulatedJob2.toString();
        line = returnedString.split("\n");
        dataPos = line[2].indexOf(": ") + 2;
        assertEquals("Data not available", line[2].substring(dataPos));
        dataPos = line[3].indexOf(": ") + 2;
        assertEquals("Data not available", line[3].substring(dataPos));
        dataPos = line[4].indexOf(": ") + 2;
        assertEquals("Desert", line[4].substring(dataPos));
        dataPos = line[5].indexOf(": ") + 2;
        assertEquals("Quality control", line[5].substring(dataPos));
        dataPos = line[6].indexOf(": ") + 2;
        assertEquals("Persistence", line[6].substring(dataPos));
    }

    @Test    //Bonus test
    public void test_toString_ReturnsJobDoesNotExistMessage() {
        assertEquals("OOPS! This job does not seem to exist.", emptyJob1.toString());
    }
}