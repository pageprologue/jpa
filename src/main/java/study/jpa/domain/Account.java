package study.jpa.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    private String firstName;

    private String lastName;

    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created = new Date();

    @Transient
    private String no;

    // @Embedded
    // private Address address;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "state", column = @Column(name = "office_state")),
        @AttributeOverride(name = "city", column = @Column(name = "office_city")),
        @AttributeOverride(name = "street", column = @Column(name = "office_street")),
        @AttributeOverride(name = "zipCode", column = @Column(name = "office_zipCode"))
    })
    private Address officeAddress;

    @OneToMany(mappedBy = "owner")
    private Set<Study> studies = new HashSet<>();

	public void addStudy(Study study) {
        this.getStudies().add(study);
        study.setOwner(this);
    }

    public void removeStudy(Study study) {
        this.getStudies().remove(study);
        study.setOwner(null);
	}
}