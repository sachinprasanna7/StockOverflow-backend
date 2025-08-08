package com.practice.StockOverflowBackend.entities;

import java.math.BigDecimal;
import java.sql.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "user_account")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "phone_number", nullable = false, length = 15)
    private String phoneNumber;

    @Column(name = "date_of_birth", nullable = false)
    private Date dateOfBirth;

    @Column(name = "address", nullable = false, columnDefinition = "TEXT")
    private String address;

    @Column(name = "zip_code", nullable = false, length = 10)
    private String zipCode;

    @Column(name = "state", nullable = false, length = 50)
    private String state;

    @Column(name = "demat_number", nullable = false, unique = true, length = 50)
    private String dematNumber;

    @Column(name = "client_number", nullable = false, unique = true, length = 50)
    private String clientNumber;

    @Column(name = "bank_account_number", nullable = false, length = 30)
    private String bankAccountNumber;

    @Column(name = "ifsc_code", nullable = false, length = 15)
    private String ifscCode;

    @Column(name = "trading_account_money", precision = 15, scale = 2)
    private BigDecimal tradingAccountMoney;

    @Column(name = "stock_investments_money", precision = 15, scale = 2)
    private BigDecimal stockInvestmentsMoney;

    // Default constructor for JPA
    public UserAccount() {
    }

    public UserAccount(String fullName, String email, String phoneNumber, Date dateOfBirth, String address, String zipCode, String state, String dematNumber, String clientNumber, String bankAccountNumber, String ifscCode, BigDecimal tradingAccountMoney, BigDecimal stockInvestmentsMoney) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.zipCode = zipCode;
        this.state = state;
        this.dematNumber = dematNumber;
        this.clientNumber = clientNumber;
        this.bankAccountNumber = bankAccountNumber;
        this.ifscCode = ifscCode;
        this.tradingAccountMoney = tradingAccountMoney;
        this.stockInvestmentsMoney = stockInvestmentsMoney;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getState() {
        return state;
    }

    public String getDematNumber() {
        return dematNumber;
    }

    public String getClientNumber() {
        return clientNumber;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public BigDecimal getTradingAccountMoney() {
        return tradingAccountMoney;
    }

    public BigDecimal getStockInvestmentsMoney() {
        return stockInvestmentsMoney;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setDematNumber(String dematNumber) {
        this.dematNumber = dematNumber;
    }

    public void setClientNumber(String clientNumber) {
        this.clientNumber = clientNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public void setTradingAccountMoney(BigDecimal tradingAccountMoney) {
        this.tradingAccountMoney = tradingAccountMoney;
    }

    public void setStockInvestmentsMoney(BigDecimal stockInvestmentsMoney) {
        this.stockInvestmentsMoney = stockInvestmentsMoney;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", address='" + address + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", state='" + state + '\'' +
                ", dematNumber='" + dematNumber + '\'' +
                ", clientNumber='" + clientNumber + '\'' +
                ", bankAccountNumber='" + bankAccountNumber + '\'' +
                ", ifscCode='" + ifscCode + '\'' +
                ", tradingAccountMoney=" + tradingAccountMoney +
                ", stockInvestmentsMoney=" + stockInvestmentsMoney +
                '}';
    }
}
