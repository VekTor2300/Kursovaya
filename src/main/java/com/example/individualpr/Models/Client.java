package com.example.individualpr.Models;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 1, max = 250, message = "От 1 до 250 символов")
    @Column(unique = true)
    private String email;
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 1, max = 250, message = "От 1 до 250 символов")
    private String address;

    @Column(name = "serial_pass", unique = true, nullable = false)
    private Integer serialPass;

    @Column(name = "num_pass", unique = true, nullable = false)
    private Integer numPass;

    @NotNull
    public String  birthdate = "19/01/2003";

    @NotNull(message = "Поле не может быть пустым")
    @Column(name = "number_phone", unique = true)
    @Digits(message="Number should contain 10 digits.", fraction = 0, integer = 11)
    private Long numberPhone;

    private boolean vailid;

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    @OneToOne(optional = true)
    @JoinColumn(name = "user_id")
    private User users;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private Set<Cheque> cheques;

    @OneToOne(mappedBy = "clients")
    private PersonalAccount personalAccount;

    @OneToOne(mappedBy = "clients")
    private EthernetContract ethernetContract;

    @OneToOne(mappedBy = "clients")
    private SubscriberMemo subscriberMemo;


    public Client(Long id, String email, String address, Set<Cheque> cheques, boolean vailid) {
        this.id = id;
        this.email = email;
        this.address = address;
        this.cheques = cheques;
        this.vailid = vailid;
    }

    public Client() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Cheque> getCheques() {
        return cheques;
    }

    public void setCheques(Set<Cheque> cheques) {
        this.cheques = cheques;
    }

    public boolean isVailid() {
        return vailid;
    }

    public void setVailid(boolean vailid) {
        this.vailid = vailid;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public PersonalAccount getPersonalAccount() {
        return personalAccount;
    }

    public void setPersonalAccount(PersonalAccount personalAccount) {
        this.personalAccount = personalAccount;
    }

    public EthernetContract getEthernetContract() {
        return ethernetContract;
    }

    public void setEthernetContract(EthernetContract ethernetContract) {
        this.ethernetContract = ethernetContract;
    }


    public SubscriberMemo getSubscriberMemo() {
        return subscriberMemo;
    }

    public void setSubscriberMemo(SubscriberMemo subscriberMemo) {
        this.subscriberMemo = subscriberMemo;
    }

    public Integer getSerialPass() {
        return serialPass;
    }

    public void setSerialPass(Integer serialPass) {
        this.serialPass = serialPass;
    }

    public Integer getNumPass() {
        return numPass;
    }

    public void setNumPass(Integer numPass) {
        this.numPass = numPass;
    }

    public Long getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(Long numberPhone) {
        this.numberPhone = numberPhone;
    }
}
