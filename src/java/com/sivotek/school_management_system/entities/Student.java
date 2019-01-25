/*
 * SIVOTEK SCHOOL  MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LTD.
 * ALL RIGHT RESERVED 2016
 */
package com.sivotek.school_management_system.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    @NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s")
    , @NamedQuery(name = "Student.findByStudentId", query = "SELECT s FROM Student s WHERE s.studentId = :studentId")
    , @NamedQuery(name = "Student.findByAdmissionNo", query = "SELECT s FROM Student s WHERE s.admissionNo = :admissionNo")
    , @NamedQuery(name = "Student.findByAdmissionDate", query = "SELECT s FROM Student s WHERE s.admissionDate = :admissionDate")
    , @NamedQuery(name = "Student.findByClassRollNo", query = "SELECT s FROM Student s WHERE s.classRollNo = :classRollNo")
    , @NamedQuery(name = "Student.findByFirstName", query = "SELECT s FROM Student s WHERE s.firstName = :firstName")
    , @NamedQuery(name = "Student.findByMiddleName", query = "SELECT s FROM Student s WHERE s.middleName = :middleName")
    , @NamedQuery(name = "Student.findByLastName", query = "SELECT s FROM Student s WHERE s.lastName = :lastName")
    , @NamedQuery(name = "Student.findByStudentguardianRelationship", query = "SELECT s FROM Student s WHERE s.studentguardianRelationship = :studentguardianRelationship")
    , @NamedQuery(name = "Student.findByDateOfBirth", query = "SELECT s FROM Student s WHERE s.dateOfBirth = :dateOfBirth")
    , @NamedQuery(name = "Student.findByGender", query = "SELECT s FROM Student s WHERE s.gender = :gender")
    , @NamedQuery(name = "Student.findByBloodGroup", query = "SELECT s FROM Student s WHERE s.bloodGroup = :bloodGroup")
    , @NamedQuery(name = "Student.findByBirthPlace", query = "SELECT s FROM Student s WHERE s.birthPlace = :birthPlace")
    , @NamedQuery(name = "Student.findByLanguage", query = "SELECT s FROM Student s WHERE s.language = :language")
    , @NamedQuery(name = "Student.findByReligion", query = "SELECT s FROM Student s WHERE s.religion = :religion")
    , @NamedQuery(name = "Student.findByEmail", query = "SELECT s FROM Student s WHERE s.email = :email")
    , @NamedQuery(name = "Student.findByPhone", query = "SELECT s FROM Student s WHERE s.phone = :phone")
    , @NamedQuery(name = "Student.findByStatusDescription", query = "SELECT s FROM Student s WHERE s.statusDescription = :statusDescription")
    , @NamedQuery(name = "Student.findByStatus", query = "SELECT s FROM Student s WHERE s.status = :status")
    , @NamedQuery(name = "Student.findByCreatedAt", query = "SELECT s FROM Student s WHERE s.createdAt = :createdAt")
    , @NamedQuery(name = "Student.findByUpdatedAt", query = "SELECT s FROM Student s WHERE s.updatedAt = :updatedAt")
    , @NamedQuery(name = "Student.findByHasPaidFees", query = "SELECT s FROM Student s WHERE s.hasPaidFees = :hasPaidFees")
    , @NamedQuery(name = "Student.findByAuthenticationKey", query = "SELECT s FROM Student s WHERE s.authenticationKey = :authenticationKey")
    , @NamedQuery(name = "Student.findByPreviousInstitution", query = "SELECT s FROM Student s WHERE s.previousInstitution = :previousInstitution")
    , @NamedQuery(name = "Student.findByPreviousSchoolYear", query = "SELECT s FROM Student s WHERE s.previousSchoolYear = :previousSchoolYear")
    , @NamedQuery(name = "Student.findBySynchStatus", query = "SELECT s FROM Student s WHERE s.synchStatus = :synchStatus")})
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "student_id", nullable = false)
    private Long studentId;
    @Size(max = 255)
    @Column(name = "admission_no", length = 255)
    private String admissionNo;
    @Column(name = "admission_date")
    @Temporal(TemporalType.DATE)
    private Date admissionDate;
    @Size(max = 255)
    @Column(name = "class_roll_no", length = 255)
    private String classRollNo;
    @Size(max = 255)
    @Column(name = "first_name", length = 255)
    private String firstName;
    @Size(max = 255)
    @Column(name = "middle_name", length = 255)
    private String middleName;
    @Size(max = 255)
    @Column(name = "last_name", length = 255)
    private String lastName;
    @Size(max = 255)
    @Column(name = "studentguardian_relationship", length = 255)
    private String studentguardianRelationship;
    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @Size(max = 255)
    @Column(length = 255)
    private String gender;
    @Size(max = 255)
    @Column(name = "blood_group", length = 255)
    private String bloodGroup;
    @Size(max = 255)
    @Column(name = "birth_place", length = 255)
    private String birthPlace;
    @Size(max = 255)
    @Column(length = 255)
    private String language;
    @Size(max = 255)
    @Column(length = 255)
    private String religion;
    @Lob
    @Size(max = 65535)
    @Column(name = "current_address", length = 65535)
    private String currentAddress;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(length = 255)
    private String email;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(length = 45)
    private String phone;
    @Size(max = 255)
    @Column(name = "status_description", length = 255)
    private String statusDescription;
    private Boolean status;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Column(name = "has_paid_fees")
    private Boolean hasPaidFees;
    @Size(max = 45)
    @Column(name = "authentication_key", length = 45)
    private String authenticationKey;
    @Size(max = 255)
    @Column(name = "previous_institution", length = 255)
    private String previousInstitution;
    @Size(max = 255)
    @Column(name = "previous_school_year", length = 255)
    private String previousSchoolYear;
    @Column(name = "synch_status")
    private Boolean synchStatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentId")
    private Collection<PrincipalTerminalExamComment> principalTerminalExamCommentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentId")
    private Collection<SubjectAttendance> subjectAttendanceCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentId")
    private Collection<FormMasterTerminalExamComment> formMasterTerminalExamCommentCollection;
    @OneToMany(mappedBy = "studentId")
    private Collection<Users> usersCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentId")
    private Collection<StudentBehavouralTrait> studentBehavouralTraitCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentId")
    private Collection<ExamClassPosition> examClassPositionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentId")
    private Collection<SchoolFeeInvoiceDetails> schoolFeeInvoiceDetailsCollection;
    @JoinColumn(name = "nationality_id", referencedColumnName = "id")
    @ManyToOne
    private Countries nationalityId;
    @JoinColumn(name = "branch_id", referencedColumnName = "branch_id")
    @ManyToOne
    private CompanyBranch branchId;
    @JoinColumn(name = "academic_year", referencedColumnName = "year_id")
    @ManyToOne
    private AcademicYears academicYear;
    @JoinColumn(name = "guardian_id", referencedColumnName = "guardian_id")
    @ManyToOne
    private Guardian guardianId;
    @JoinColumn(name = "class_id", referencedColumnName = "class_id", nullable = false)
    @ManyToOne(optional = false)
    private Class classId;
    @JoinColumn(name = "term_id", referencedColumnName = "term_id", nullable = false)
    @ManyToOne(optional = false)
    private Term termId;
    @JoinColumn(name = "section_id", referencedColumnName = "section_id")
    @ManyToOne
    private Section sectionId;
    @JoinColumn(name = "state_of_origin", referencedColumnName = "id")
    @ManyToOne
    private States stateOfOrigin;
    @JoinColumn(name = "address_city_id", referencedColumnName = "id")
    @ManyToOne
    private Cities addressCityId;
    @JoinColumn(name = "address_state_id", referencedColumnName = "id")
    @ManyToOne
    private States addressStateId;
    @JoinColumn(name = "address_country_id", referencedColumnName = "id")
    @ManyToOne
    private Countries addressCountryId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentId")
    private Collection<StudentDailyAttendance> studentDailyAttendanceCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentId")
    private Collection<ExamMark> examMarkCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentId")
    private Collection<CaMark> caMarkCollection;
    @OneToMany(mappedBy = "studentId")
    private Collection<Invoice> invoiceCollection;

    public Student() {
    }

    public Student(Long studentId) {
        this.studentId = studentId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getAdmissionNo() {
        return admissionNo;
    }

    public void setAdmissionNo(String admissionNo) {
        this.admissionNo = admissionNo;
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }

    public String getClassRollNo() {
        return classRollNo;
    }

    public void setClassRollNo(String classRollNo) {
        this.classRollNo = classRollNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStudentguardianRelationship() {
        return studentguardianRelationship;
    }

    public void setStudentguardianRelationship(String studentguardianRelationship) {
        this.studentguardianRelationship = studentguardianRelationship;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Boolean getHasPaidFees() {
        return hasPaidFees;
    }

    public void setHasPaidFees(Boolean hasPaidFees) {
        this.hasPaidFees = hasPaidFees;
    }

    public String getAuthenticationKey() {
        return authenticationKey;
    }

    public void setAuthenticationKey(String authenticationKey) {
        this.authenticationKey = authenticationKey;
    }

    public String getPreviousInstitution() {
        return previousInstitution;
    }

    public void setPreviousInstitution(String previousInstitution) {
        this.previousInstitution = previousInstitution;
    }

    public String getPreviousSchoolYear() {
        return previousSchoolYear;
    }

    public void setPreviousSchoolYear(String previousSchoolYear) {
        this.previousSchoolYear = previousSchoolYear;
    }

    public Boolean getSynchStatus() {
        return synchStatus;
    }

    public void setSynchStatus(Boolean synchStatus) {
        this.synchStatus = synchStatus;
    }

    @XmlTransient
    public Collection<PrincipalTerminalExamComment> getPrincipalTerminalExamCommentCollection() {
        return principalTerminalExamCommentCollection;
    }

    public void setPrincipalTerminalExamCommentCollection(Collection<PrincipalTerminalExamComment> principalTerminalExamCommentCollection) {
        this.principalTerminalExamCommentCollection = principalTerminalExamCommentCollection;
    }

    @XmlTransient
    public Collection<SubjectAttendance> getSubjectAttendanceCollection() {
        return subjectAttendanceCollection;
    }

    public void setSubjectAttendanceCollection(Collection<SubjectAttendance> subjectAttendanceCollection) {
        this.subjectAttendanceCollection = subjectAttendanceCollection;
    }

    @XmlTransient
    public Collection<FormMasterTerminalExamComment> getFormMasterTerminalExamCommentCollection() {
        return formMasterTerminalExamCommentCollection;
    }

    public void setFormMasterTerminalExamCommentCollection(Collection<FormMasterTerminalExamComment> formMasterTerminalExamCommentCollection) {
        this.formMasterTerminalExamCommentCollection = formMasterTerminalExamCommentCollection;
    }

    @XmlTransient
    public Collection<Users> getUsersCollection() {
        return usersCollection;
    }

    public void setUsersCollection(Collection<Users> usersCollection) {
        this.usersCollection = usersCollection;
    }

    @XmlTransient
    public Collection<StudentBehavouralTrait> getStudentBehavouralTraitCollection() {
        return studentBehavouralTraitCollection;
    }

    public void setStudentBehavouralTraitCollection(Collection<StudentBehavouralTrait> studentBehavouralTraitCollection) {
        this.studentBehavouralTraitCollection = studentBehavouralTraitCollection;
    }

    @XmlTransient
    public Collection<ExamClassPosition> getExamClassPositionCollection() {
        return examClassPositionCollection;
    }

    public void setExamClassPositionCollection(Collection<ExamClassPosition> examClassPositionCollection) {
        this.examClassPositionCollection = examClassPositionCollection;
    }

    @XmlTransient
    public Collection<SchoolFeeInvoiceDetails> getSchoolFeeInvoiceDetailsCollection() {
        return schoolFeeInvoiceDetailsCollection;
    }

    public void setSchoolFeeInvoiceDetailsCollection(Collection<SchoolFeeInvoiceDetails> schoolFeeInvoiceDetailsCollection) {
        this.schoolFeeInvoiceDetailsCollection = schoolFeeInvoiceDetailsCollection;
    }

    public Countries getNationalityId() {
        return nationalityId;
    }

    public void setNationalityId(Countries nationalityId) {
        this.nationalityId = nationalityId;
    }

    public CompanyBranch getBranchId() {
        return branchId;
    }

    public void setBranchId(CompanyBranch branchId) {
        this.branchId = branchId;
    }

    public AcademicYears getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(AcademicYears academicYear) {
        this.academicYear = academicYear;
    }

    public Guardian getGuardianId() {
        return guardianId;
    }

    public void setGuardianId(Guardian guardianId) {
        this.guardianId = guardianId;
    }

    public Class getClassId() {
        return classId;
    }

    public void setClassId(Class classId) {
        this.classId = classId;
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

    public States getStateOfOrigin() {
        return stateOfOrigin;
    }

    public void setStateOfOrigin(States stateOfOrigin) {
        this.stateOfOrigin = stateOfOrigin;
    }

    public Cities getAddressCityId() {
        return addressCityId;
    }

    public void setAddressCityId(Cities addressCityId) {
        this.addressCityId = addressCityId;
    }

    public States getAddressStateId() {
        return addressStateId;
    }

    public void setAddressStateId(States addressStateId) {
        this.addressStateId = addressStateId;
    }

    public Countries getAddressCountryId() {
        return addressCountryId;
    }

    public void setAddressCountryId(Countries addressCountryId) {
        this.addressCountryId = addressCountryId;
    }

    @XmlTransient
    public Collection<StudentDailyAttendance> getStudentDailyAttendanceCollection() {
        return studentDailyAttendanceCollection;
    }

    public void setStudentDailyAttendanceCollection(Collection<StudentDailyAttendance> studentDailyAttendanceCollection) {
        this.studentDailyAttendanceCollection = studentDailyAttendanceCollection;
    }

    @XmlTransient
    public Collection<ExamMark> getExamMarkCollection() {
        return examMarkCollection;
    }

    public void setExamMarkCollection(Collection<ExamMark> examMarkCollection) {
        this.examMarkCollection = examMarkCollection;
    }

    @XmlTransient
    public Collection<CaMark> getCaMarkCollection() {
        return caMarkCollection;
    }

    public void setCaMarkCollection(Collection<CaMark> caMarkCollection) {
        this.caMarkCollection = caMarkCollection;
    }

    @XmlTransient
    public Collection<Invoice> getInvoiceCollection() {
        return invoiceCollection;
    }

    public void setInvoiceCollection(Collection<Invoice> invoiceCollection) {
        this.invoiceCollection = invoiceCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (studentId != null ? studentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Student)) {
            return false;
        }
        Student other = (Student) object;
        if ((this.studentId == null && other.studentId != null) || (this.studentId != null && !this.studentId.equals(other.studentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.school_management_system.entities.Student[ studentId=" + studentId + " ]";
    }
    
}
