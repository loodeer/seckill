package com.loodeer.dataobject;

public class UserDO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.id
     *
     * @mbg.generated Wed Dec 12 11:11:10 CST 2018
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.name
     *
     * @mbg.generated Wed Dec 12 11:11:10 CST 2018
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.gender
     *
     * @mbg.generated Wed Dec 12 11:11:10 CST 2018
     */
    private Boolean gender;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.age
     *
     * @mbg.generated Wed Dec 12 11:11:10 CST 2018
     */
    private Integer age;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.telphone
     *
     * @mbg.generated Wed Dec 12 11:11:10 CST 2018
     */
    private String telphone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.register_mode
     *
     * @mbg.generated Wed Dec 12 11:11:10 CST 2018
     */
    private Integer registerMode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.third_party_id
     *
     * @mbg.generated Wed Dec 12 11:11:10 CST 2018
     */
    private String thirdPartyId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.id
     *
     * @return the value of user_info.id
     *
     * @mbg.generated Wed Dec 12 11:11:10 CST 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.id
     *
     * @param id the value for user_info.id
     *
     * @mbg.generated Wed Dec 12 11:11:10 CST 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.name
     *
     * @return the value of user_info.name
     *
     * @mbg.generated Wed Dec 12 11:11:10 CST 2018
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.name
     *
     * @param name the value for user_info.name
     *
     * @mbg.generated Wed Dec 12 11:11:10 CST 2018
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.gender
     *
     * @return the value of user_info.gender
     *
     * @mbg.generated Wed Dec 12 11:11:10 CST 2018
     */
    public Boolean getGender() {
        return gender;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.gender
     *
     * @param gender the value for user_info.gender
     *
     * @mbg.generated Wed Dec 12 11:11:10 CST 2018
     */
    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.age
     *
     * @return the value of user_info.age
     *
     * @mbg.generated Wed Dec 12 11:11:10 CST 2018
     */
    public Integer getAge() {
        return age;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.age
     *
     * @param age the value for user_info.age
     *
     * @mbg.generated Wed Dec 12 11:11:10 CST 2018
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.telphone
     *
     * @return the value of user_info.telphone
     *
     * @mbg.generated Wed Dec 12 11:11:10 CST 2018
     */
    public String getTelphone() {
        return telphone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.telphone
     *
     * @param telphone the value for user_info.telphone
     *
     * @mbg.generated Wed Dec 12 11:11:10 CST 2018
     */
    public void setTelphone(String telphone) {
        this.telphone = telphone == null ? null : telphone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.register_mode
     *
     * @return the value of user_info.register_mode
     *
     * @mbg.generated Wed Dec 12 11:11:10 CST 2018
     */
    public Integer getRegisterMode() {
        return registerMode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.register_mode
     *
     * @param registerMode the value for user_info.register_mode
     *
     * @mbg.generated Wed Dec 12 11:11:10 CST 2018
     */
    public void setRegisterMode(Integer registerMode) {
        this.registerMode = registerMode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.third_party_id
     *
     * @return the value of user_info.third_party_id
     *
     * @mbg.generated Wed Dec 12 11:11:10 CST 2018
     */
    public String getThirdPartyId() {
        return thirdPartyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.third_party_id
     *
     * @param thirdPartyId the value for user_info.third_party_id
     *
     * @mbg.generated Wed Dec 12 11:11:10 CST 2018
     */
    public void setThirdPartyId(String thirdPartyId) {
        this.thirdPartyId = thirdPartyId == null ? null : thirdPartyId.trim();
    }
}