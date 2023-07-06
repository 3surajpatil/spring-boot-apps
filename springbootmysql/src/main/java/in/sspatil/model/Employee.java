package in.sspatil.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {

	@Id
	@GeneratedValue
	int id;
	String ename;
	float esal;
	float bonous;
	
	public Employee() {
		
	}
	
	public Employee(int id, String ename, float esal, float bonous) {
		super();
		this.id = id;
		this.ename = ename;
		this.esal = esal;		
		this.bonous = bonous;
	}
	
	@Column(name = "id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name = "ename")
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	@Column(name = "esal")
	public float getEsal() {
		return esal;
	}
	public void setEsal(float esal) {
		this.esal = esal;
	}
	
	@Column(name = "bonous")
	public float getBonous() {
		return bonous;
	}
	public void setBonous(float bonous) {
		this.bonous = bonous;
	}
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", ename=" + ename + ", esal=" + esal +", bonous" + bonous +"]";
	}
}
