package com.loodeer.service.model;

public class UserModel {
        private Integer id;
        private String name;
        private Integer gender;
        private Integer age;
        private String telphone;
        private Integer registerMode;
        private String thirdPartyId;
        private String encrptPassword;

        public Integer getId() {
                return id;
        }

        public void setId(Integer id) {
                this.id = id;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public Integer getGender() {
                return gender;
        }

        public void setGender(Integer gender) {
                this.gender = gender;
        }

        public Integer getAge() {
                return age;
        }

        public void setAge(Integer age) {
                this.age = age;
        }

        public String getTelphone() {
                return telphone;
        }

        public void setTelphone(String telphone) {
                this.telphone = telphone;
        }

        public Integer getRegisterMode() {
                return registerMode;
        }

        public void setRegisterMode(Integer registerMode) {
                this.registerMode = registerMode;
        }

        public String getThirdPartyId() {
                return thirdPartyId;
        }

        public void setThirdPartyId(String thirdPartyId) {
                this.thirdPartyId = thirdPartyId;
        }

        public String getEncrptPassword() {
                return encrptPassword;
        }

        public void setEncrptPassword(String encrptPassword) {
                this.encrptPassword = encrptPassword;
        }
}
