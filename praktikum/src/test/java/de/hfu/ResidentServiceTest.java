package de.hfu;
import de.hfu.residents.domain.Resident;
import de.hfu.residents.repository.ResidentRepository;
import de.hfu.residents.repository.ResidentRepositoryStub;
import de.hfu.residents.service.BaseResidentService;
import de.hfu.residents.service.ResidentServiceException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import junit.framework.TestCase;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.easymock.EasyMock.*;

public class ResidentServiceTest extends TestCase {
	@SuppressWarnings("deprecation")
	public void testResidentMock() {
		//Personen erstellen -> Person 3 mit Wildcard
		Resident person1 = new Resident("Bernd", "Schmied", "Lange Gasse 1", "Furtwangen", new Date(1991, 12, 12));
		Resident person2 = new Resident("Berta", "Schmiedt", "Lange Gasse 2", "Furtwangen", new Date(1992, 12, 12));
		Resident person3 = new Resident("Tim", "Sommer", "L*", "Furtwangen", new Date(1993, 12, 12));
		
		//Person 1+2 zu ResidentList hinzufuegen; Service Objekt erzeugen
		List<Resident> l = new ArrayList<Resident>();
		l.add(person1);
		l.add(person2);
		BaseResidentService service = new BaseResidentService();
		
		//ResidentRepo Mock erzeugen
		ResidentRepository Mock = createMock(ResidentRepository.class);
		expect(Mock.getResidents()).andReturn(l);
		replay(Mock);
		
		//Person 3 in k einfuegen + FilteredList erstellen mit Person 3
		service.setResidentRepository(Mock);
		List<Resident> k = new ArrayList<Resident>();
		k.add(person1);
		List<Resident> j = service.getFilteredResidentsList(person1);
		
		//p und k vergleichen
		assertEquals(k.size(), j.size());
		assertTrue(j.containsAll(k));
		//Person 2 in p suchen
		assertFalse(j.contains(person2));
		verify(Mock);
	}
	
	@SuppressWarnings("deprecation")
	public void testResidentsList() {
		//Personen erzeugen -> Person 3 mit Wildcard
		Resident person1 = new Resident("Bernd", "Schmied", "Lange Gasse 1", "Furtwangen", new Date(1991, 12, 12));
		Resident person2 = new Resident("Berta", "Schmiedt", "Lange Gasse 2", "Furtwangen", new Date(1992, 12, 12));
		Resident person3 = new Resident("Tim", "Sommer", "L*", "Furtwangen", new Date(1993, 12, 12));
		
		//service und stub Objekt erzeugen
		BaseResidentService service = new BaseResidentService();
		ResidentRepositoryStub stub = new ResidentRepositoryStub();
		
		//newResident mit Person 1 und Person 2 erzeugen
		stub.newResident(person1);
		stub.newResident(person2);
		service.setResidentRepository(stub);
		
		//Person 3 in l einfuegen + FilteredList erstellen mit Person 3
		List<Resident> l = new ArrayList<Resident>();
		l.add(person1);
		List<Resident> k = service.getFilteredResidentsList(person1);
		
		//l und k vergleichen
		assertEquals(l.size(), k.size());
		assertTrue(k.containsAll(l));
		assertFalse(k.contains(person2));
	}
	
	@SuppressWarnings("deprecation")
	public void testResidentUnique() {
		//Personen erstellen
		Resident person1 = new Resident("Bernd", "Schmied", "Lange Gasse 1", "Furtwangen", new Date(1991, 12, 12));
		Resident person2 = new Resident("Berta", "Schmiedt", "Lange Gasse 2", "Furtwangen", new Date(1992, 12, 12));
		Resident person3 = new Resident("Tim", "Sommer", "L*", "Furtwangen", new Date(1993, 12, 12));
		Resident person4 = new Resident("Nicht", "Vorhanden", "Niemandsstrasse 2", "Nirgendwo", new Date(3, 3, 3));
		
		//Stub + Service Objekt
		BaseResidentService service = new BaseResidentService();
		ResidentRepositoryStub stub = new ResidentRepositoryStub();
		
		//Person 1+2 in ArrayList
		stub.newResident(person1);
		stub.newResident(person2);
		
		//ResidentRepo mit stub
		service.setResidentRepository(stub);
		
		//Tests
		try {
			service.getUniqueResident(person3);
			fail("Wildkart darf nicht angenommen werden");
		} catch (ResidentServiceException e) {}
		try {
		assertTrue(person1.equals(service.getUniqueResident(person1)));
		} catch (ResidentServiceException e) {}
		try {
			service.getUniqueResident(person4);
			fail("Resident nicht vorhanden");
		} catch (ResidentServiceException e) {}
	}
}
