package es.deusto.bspq.cinema.server.jdo.data;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import org.junit.Rule;
import org.databene.contiperf.Required;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;

@PerfTest(invocations = 5)
@Required(max = 1200, average = 250)
public class AssemblerTest {
	
	private static Assembler assembler;
	
	@Rule public ContiPerfRule rule = new ContiPerfRule();
	
	
	@BeforeClass
	public static void setUpClass() {
		assembler = new Assembler();
	}
	
	@Test
	@PerfTest(invocations = 1000, threads = 20)
    @Required(max = 120, average = 30)
	public void testDissassembleSession() throws Exception {
		SessionDTO sessionDTO = new SessionDTO("03-03-2018", "16:30", 5f, 1, 55, "Inmersion");
		Session s = assembler.disassembleSession(sessionDTO);
		assertEquals("03-03-2018", s.getDate());
		assertEquals("16:30", s.getHour());
		assertEquals(true, s.getPrice() == 5f);
	}

	@Test
	@Required(totalTime = 500)
	public void testDissassembleEmployee() throws Exception {
		EmployeeDTO employeeDTO = new EmployeeDTO("e1", "Some", "Somer", "e1", 20000f);
		Employee e = assembler.disassembleEmployee(employeeDTO);
		assertEquals("e1", e.getUsername());
		assertEquals("Some", e.getName());
		assertEquals("Somer", e.getSurname());
		assertEquals("e1", e.getPassword());
		assertEquals(true, e.getSalary() == 20000f);
	}
	
	@Test
	@Required(max = 120, average = 30)
	public void testAssembleEmployee() throws Exception {
		Employee e = new Employee("e1", "Some", "Somer", "e1", 20000f);
		EmployeeDTO employeeDTO = assembler.assembleEmployee(e);
		assertEquals("e1", employeeDTO.getUsername());
		assertEquals("Some", employeeDTO.getName());
		assertEquals("Somer", employeeDTO.getSurname());
		assertEquals("e1", employeeDTO.getPassword());
		assertEquals(true, employeeDTO.getSalary() == 20000f);
	}

}
