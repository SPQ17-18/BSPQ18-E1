package es.deusto.bspq.cinema.testing;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import es.deusto.bspq.cinema.server.jdo.DAO.ManagerDAO;
import es.deusto.bspq.cinema.server.jdo.data.Assembler;
import es.deusto.bspq.cinema.server.jdo.data.Employee;
import es.deusto.bspq.cinema.server.jdo.data.EmployeeDTO;
import es.deusto.bspq.cinema.server.jdo.data.Member;
import es.deusto.bspq.cinema.server.jdo.data.MemberDTO;

public class ManagerDAOTest {
	
	private static ManagerDAO managerDAO;
	private static Assembler assembler;
	
	private static EmployeeDTO employeeDTO;
	private static MemberDTO memberDTO;
	
	@BeforeClass
	public static void setUpClass() {
		managerDAO = new ManagerDAO();
		assembler = new Assembler();
		employeeDTO = new EmployeeDTO("e10", "laura", "garcía", "e10pass", 20000f);
		memberDTO = new MemberDTO("test@opendeusto.es", "leire", "rementeria", "testpass", "25/05/96");
//		managerDAO.deleteAllEmployees();
//		managerDAO.deleteAllMembers();
	}
	
	@Test
	public void testEmployee() throws Exception {
		managerDAO.storeEmployee(assembler.disassembleEmployee(employeeDTO));
		Employee e = managerDAO.getEmployee("e10");
		assertEquals("laura", e.getName());
		assertEquals("garcía", e.getSurname());
		assertEquals("e10pass", e.getPassword());
		assertEquals(true, e.getSalary() == 20000f);
	}
	
	@Test
	public void testMember() throws Exception {
		managerDAO.storeMember(assembler.disassembleMember(memberDTO));
		Member e = managerDAO.getMember("test@opendeusto.es");
		assertEquals("leire", e.getName());
		assertEquals("rementeria", e.getSurname());
		assertEquals("testpass", e.getPassword());
		assertEquals("25/05/96", e.getBirthday());
	}
	
//	@AfterClass
//	public static void tearDownClass() {
//		managerDAO.deleteAllEmployees();
//		managerDAO.deleteAllMembers();
//	}

}
