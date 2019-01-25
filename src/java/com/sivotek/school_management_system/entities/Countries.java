/*
 * SIVOTEK SCHOOL  MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LTD.
 * ALL RIGHT RESERVED 2016
 */
package com.sivotek.school_management_system.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author MY USER
 */
@Entity
@Table(catalog = "sivotek_school_management_system_v1_2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Countries.findAll", query = "SELECT c FROM Countries c")
    , @NamedQuery(name = "Countries.findById", query = "SELECT c FROM Countries c WHERE c.id = :id")
    , @NamedQuery(name = "Countries.findBySortname", query = "SELECT c FROM Countries c WHERE c.sortname = :sortname")
    , @NamedQuery(name = "Countries.findByName", query = "SELECT c FROM Countries c WHERE c.name = :name")})
public class Countries implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(nullable = false, length = 3)
    private String sortname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(nullable = false, length = 150)
    private String name;
    @OneToMany(mappedBy = "nationality")
    private Collection<Employee> employeeCollection;
    @OneToMany(mappedBy = "countryId")
    private Collection<Employee> employeeCollection1;
    @OneToMany(mappedBy = "nationalityId")
    private Collection<Student> studentCollection;
    @OneToMany(mappedBy = "addressCountryId")
    private Collection<Student> studentCollection1;
    @OneToMany(mappedBy = "countryId")
    private Collection<Guardian> guardianCollection;

    public Countries() {
    }

    public Countries(Integer id) {
        this.id = id;
    }

    public Countries(Integer id, String sortname, String name) {
        this.id = id;
        this.sortname = sortname;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSortname() {
        return sortname;
    }

    public void setSortname(String sortname) {
        this.sortname = sortname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<Employee> getEmployeeCollection() {
        return employeeCollection;
    }

    public void setEmployeeCollection(Collection<Employee> employeeCollection) {
        this.employeeCollection = employeeCollection;
    }

    @XmlTransient
    public Collection<Employee> getEmployeeCollection1() {
        return employeeCollection1;
    }

    public void setEmployeeCollection1(Collection<Employee> employeeCollection1) {
        this.employeeCollection1 = employeeCollection1;
    }

    @XmlTransient
    public Collection<Student> getStudentCollection() {
        return studentCollection;
    }

    public void setStudentCollection(Collection<Student> studentCollection) {
        this.studentCollection = studentCollection;
    }

    @XmlTransient
    public Collection<Student> getStudentCollection1() {
        return studentCollection1;
    }

    public void setStudentCollection1(Collection<Student> studentCollection1) {
        this.studentCollection1 = studentCollection1;
    }

    @XmlTransient
    public Collection<Guardian> getGuardianCollection() {
        return guardianCollection;
    }

    public void setGuardianCollection(Collection<Guardian> guardianCollection) {
        this.guardianCollection = guardianCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Countries)) {
            return false;
        }
        Countries other = (Countries) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.school_management_system.entities.Countries[ id=" + id + " ]";
    }
    
}
