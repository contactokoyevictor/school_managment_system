/*
 * SIVOTEK SCHOOL  MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LTD.
 * ALL RIGHT RESERVED 2016
 */
package com.sivotek.school_management_system.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MY USER
 */
@Entity
@Table(name = "school_fee_invoice_details", catalog = "sivotek_school_management_system_v1_2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SchoolFeeInvoiceDetails.findAll", query = "SELECT s FROM SchoolFeeInvoiceDetails s")
    , @NamedQuery(name = "SchoolFeeInvoiceDetails.findById", query = "SELECT s FROM SchoolFeeInvoiceDetails s WHERE s.id = :id")
    , @NamedQuery(name = "SchoolFeeInvoiceDetails.findByAmount", query = "SELECT s FROM SchoolFeeInvoiceDetails s WHERE s.amount = :amount")
    , @NamedQuery(name = "SchoolFeeInvoiceDetails.findByStartDate", query = "SELECT s FROM SchoolFeeInvoiceDetails s WHERE s.startDate = :startDate")
    , @NamedQuery(name = "SchoolFeeInvoiceDetails.findByDueDate", query = "SELECT s FROM SchoolFeeInvoiceDetails s WHERE s.dueDate = :dueDate")
    , @NamedQuery(name = "SchoolFeeInvoiceDetails.findByEndDate", query = "SELECT s FROM SchoolFeeInvoiceDetails s WHERE s.endDate = :endDate")
    , @NamedQuery(name = "SchoolFeeInvoiceDetails.findByClassId", query = "SELECT s FROM SchoolFeeInvoiceDetails s WHERE s.classId = :classId")
    , @NamedQuery(name = "SchoolFeeInvoiceDetails.findByCreatedDate", query = "SELECT s FROM SchoolFeeInvoiceDetails s WHERE s.createdDate = :createdDate")
    , @NamedQuery(name = "SchoolFeeInvoiceDetails.findByModifieddate", query = "SELECT s FROM SchoolFeeInvoiceDetails s WHERE s.modifieddate = :modifieddate")
    , @NamedQuery(name = "SchoolFeeInvoiceDetails.findBySynchStatus", query = "SELECT s FROM SchoolFeeInvoiceDetails s WHERE s.synchStatus = :synchStatus")})
public class SchoolFeeInvoiceDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Long id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(precision = 22)
    private Double amount;
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "due_date")
    @Temporal(TemporalType.DATE)
    private Date dueDate;
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "class_id", nullable = false)
    private long classId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifieddate;
    @Column(name = "synch_status")
    private Boolean synchStatus;
    @JoinColumn(name = "branch_id", referencedColumnName = "branch_id")
    @ManyToOne
    private CompanyBranch branchId;
    @JoinColumn(name = "invoice_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Invoice invoiceId;
    @JoinColumn(name = "student_id", referencedColumnName = "student_id", nullable = false)
    @ManyToOne(optional = false)
    private Student studentId;
    @JoinColumn(name = "term_id", referencedColumnName = "term_id", nullable = false)
    @ManyToOne(optional = false)
    private Term termId;
    @JoinColumn(name = "section_id", referencedColumnName = "section_id", nullable = false)
    @ManyToOne(optional = false)
    private Section sectionId;
    @JoinColumn(name = "academic_year", referencedColumnName = "year_id", nullable = false)
    @ManyToOne(optional = false)
    private AcademicYears academicYear;
    @JoinColumn(name = "payment_method", referencedColumnName = "id")
    @ManyToOne
    private PaymentMethod paymentMethod;
    @JoinColumn(name = "created_by_employee_id", referencedColumnName = "employee_id", nullable = false)
    @ManyToOne(optional = false)
    private Employee createdByEmployeeId;
    @JoinColumn(name = "last_modified_employee_id", referencedColumnName = "employee_id")
    @ManyToOne
    private Employee lastModifiedEmployeeId;

    public SchoolFeeInvoiceDetails() {
    }

    public SchoolFeeInvoiceDetails(Long id) {
        this.id = id;
    }

    public SchoolFeeInvoiceDetails(Long id, long classId, Date createdDate) {
        this.id = id;
        this.classId = classId;
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public long getClassId() {
        return classId;
    }

    public void setClassId(long classId) {
        this.classId = classId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifieddate() {
        return modifieddate;
    }

    public void setModifieddate(Date modifieddate) {
        this.modifieddate = modifieddate;
    }

    public Boolean getSynchStatus() {
        return synchStatus;
    }

    public void setSynchStatus(Boolean synchStatus) {
        this.synchStatus = synchStatus;
    }

    public CompanyBranch getBranchId() {
        return branchId;
    }

    public void setBranchId(CompanyBranch branchId) {
        this.branchId = branchId;
    }

    public Invoice getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Invoice invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Student getStudentId() {
        return studentId;
    }

    public void setStudentId(Student studentId) {
        this.studentId = studentId;
    }

    public Term getTermId() {
        return termId;
    }

    public void setTermId(Term termId) {
        this.termId = termId;
    }

    public Section getSectionId() {
        return sectionId;
    }

    public void setSectionId(Section sectionId) {
        this.sectionId = sectionId;
    }

    public AcademicYears getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(AcademicYears academicYear) {
        this.academicYear = academicYear;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Employee getCreatedByEmployeeId() {
        return createdByEmployeeId;
    }

    public void setCreatedByEmployeeId(Employee createdByEmployeeId) {
        this.createdByEmployeeId = createdByEmployeeId;
    }

    public Employee getLastModifiedEmployeeId() {
        return lastModifiedEmployeeId;
    }

    public void setLastModifiedEmployeeId(Employee lastModifiedEmployeeId) {
        this.lastModifiedEmployeeId = lastModifiedEmployeeId;
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
        if (!(object instanceof SchoolFeeInvoiceDetails)) {
            return false;
        }
        SchoolFeeInvoiceDetails other = (SchoolFeeInvoiceDetails) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.school_management_system.entities.SchoolFeeInvoiceDetails[ id=" + id + " ]";
    }
    
}
