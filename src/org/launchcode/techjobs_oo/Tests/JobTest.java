package org.launchcode.techjobs_oo.Tests;

import org.launchcode.techjobs_oo.CoreCompetency;
import org.launchcode.techjobs_oo.Location;
import org.launchcode.techjobs_oo.Employer;
import org.launchcode.techjobs_oo.Job;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.launchcode.techjobs_oo.PositionType;


public class JobTest {

    @Test
    public void testSettingJobId() {
        Job job1 = new Job();
        Job job2 = new Job();
        assertEquals(job1.getId(), (job2.getId() - 1), 0);
    }

    @Test()
    public void testJobConstructorSetsAllFields() {
        Job job1 = new Job("Product tester", new Employer("ACME"), new Location("Desert"), new PositionType("Quality control"), new CoreCompetency("Persistence"));
        assertEquals(job1.getName(), "Product tester");
        assertEquals(job1.getEmployer().toString(), "ACME");
        assertEquals(job1.getLocation().toString(), "Desert");
        assertEquals(job1.getPositionType().toString(), "Quality control");
        assertEquals(job1.getCoreCompetency().toString(), "Persistence");
        assertTrue(job1.getEmployer() instanceof Employer);
        assertTrue(job1.getLocation() instanceof Location);
        assertTrue(job1.getPositionType() instanceof PositionType);
        assertTrue(job1.getCoreCompetency() instanceof CoreCompetency);

    }

    @Test
    public void testJobsForEquality() {
        Job job1 = new Job("Product tester", new Employer("ACME"), new Location("Desert"), new PositionType("Quality control"), new CoreCompetency("Persistence"));
        Job job2 = new Job("Product tester", new Employer("ACME"), new Location("Desert"), new PositionType("Quality control"), new CoreCompetency("Persistence"));
        assertFalse(job1.equals(job2));
    }

    @Test
    public void test_toString_ReturnsSurroundingBlankLines() {
        Job job1 = new Job("Product tester", new Employer("ACME"), new Location("Desert"), new PositionType("Quality control"), new CoreCompetency("Persistence"));
        assertTrue(job1.toString().startsWith("\n"));
        int index = job1.toString().length() - 1;
        assertTrue(job1.toString().startsWith("\n",index) );
    }

    @Test
    public void test_toString_FieldsAreLabeledOnSeparateLines() {
        Job job1 = new Job("Product tester", new Employer("ACME"), new Location("Desert"), new PositionType("Quality control"), new CoreCompetency("Persistence"));
        String returnedString = job1.toString();
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
        Job job1 = new Job("Product tester", new Employer("ACME"), new Location(""), new PositionType(""), new CoreCompetency(""));
        String returnedString = job1.toString();
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

        Job job2 = new Job("", new Employer(""), new Location("Desert"), new PositionType("Quality control"), new CoreCompetency("Persistence"));
        returnedString = job2.toString();
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
        Job job1 = new Job("", new Employer(""), new Location(""), new PositionType(""), new CoreCompetency(""));
        assertEquals("OOPS! This job does not seem to exist.", job1.toString());
    }
}