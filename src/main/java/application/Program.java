package application;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import domain.entities.Person;

public class Program {
	public static void main(String[] args) {
		// Instancia de objetos Person para armazenar no banco de dados
		Person p1 =  new Person(null, "Carlos da Silva", "carlos@gmail.com");
		Person p2 =  new Person(null, "Marcos Pérsico", "marcos@gmail.com");
		Person p3 =  new Person(null, "Artur Carlos Pérsico", "artur@gmail.com");
		
		// Instância de EntityManagerFactory que faz uso das configurações em persistence.xml para instanciar EntityManagers
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("demo-jpa");
		EntityManager em = emf.createEntityManager();
		
		// Inicia transação e persiste os objetos criados;
//		em.getTransaction().begin();
//		em.persist(p1);
//		em.persist(p2);
//		em.persist(p3);
//		em.getTransaction().commit();
		
		// Recupera um registro de uma entidade por ID
		Person p4 = em.find(Person.class, 2);
		System.out.println(p4);
		
		// Recupera todos os registros de uma entidade
		List<Person> persons = em.createQuery("SELECT obj FROM Person AS obj", Person.class).getResultList();
		System.out.println(persons);
		
		// Remove registro de uma entidade (deve ser feito com uma entidade monitorada pela JPA)
//		em.getTransaction().begin();
//		em.remove(p4);
//		em.getTransaction().commit();
		
		// Atualiza o registro de uma entidade (same)
		em.getTransaction().begin();
		p4.setEmail("willian.marcos7@hotmail.com");
		em.getTransaction().commit();
		
		em.close();
		emf.close();
	}
}
